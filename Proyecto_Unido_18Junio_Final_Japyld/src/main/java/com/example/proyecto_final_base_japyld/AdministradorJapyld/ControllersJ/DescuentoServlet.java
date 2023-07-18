package com.example.proyecto_final_base_japyld.AdministradorJapyld.ControllersJ;

import com.example.proyecto_final_base_japyld.AdministradorJapyld.ModelsJ.DaosJ.DescuentoDao;
import com.example.proyecto_final_base_japyld.BeansGenerales.*;
import com.example.proyecto_final_base_japyld.UsuarioJapyld.ModelsJ.DaosJ.PerfilDao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "DescuentoServlet", value = "/DescuentoServlet")
public class DescuentoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action") == null ? "lista" :request.getParameter("action");
        DescuentoDao descuentoDao = new DescuentoDao();
        PerfilDao perfilDao1 = new PerfilDao();
        RequestDispatcher view;

        switch (action){

            case "descuento":

                if (request.getParameter("id") != null) {
                    String id_juego = request.getParameter("id");
                    int id_juego_int = 0;
                    try{
                        id_juego_int=Integer.parseInt(id_juego);
                    }catch (NumberFormatException e){
                        response.sendRedirect("OfertasServlet");
                    }

                    Juegos juego = descuentoDao.obtenerJuego(id_juego_int);

                    if(juego != null){
                        request.setAttribute("listaFotoPerfil",perfilDao1.listarFotoPerfil());
                        request.setAttribute("juego",juego);
                        view = request.getRequestDispatcher("AdministradorJapyld/descuentoAdmin.jsp");
                        view.forward(request,response);
                    }else{
                        response.sendRedirect("OfertasServlet");
                    }

                }else{
                    request.setAttribute("listaFotoPerfil",perfilDao1.listarFotoPerfil());
                    response.sendRedirect("OfertasServlet");
                }

                break;

            default:
                response.sendRedirect("AdminServlet");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action") == null ? "lista" :request.getParameter("action");
        DescuentoDao descuentoDao = new DescuentoDao();
        HttpSession session = request.getSession();
        Personas administrador= (Personas)session.getAttribute("personaSession");

        switch (action){
            case "NuevoPrecio":


               if(valida_precio(request.getParameter("precioDescuento").trim())==true){

                   if(valida_precio(request.getParameter("dias").trim())==true){
                       Descuentos descuentos = setDescuento(request);
                       Juegos juegos =new Juegos();
                       juegos.setIdJuegos(Integer.parseInt(request.getParameter("id_juego")));
                       descuentoDao.publicarDescuento(descuentos,administrador, juegos);
                       descuentoDao.actualizarEstado(juegos);
                       request.getSession().setAttribute("info","Descuento publicado exitosamente");
                       response.sendRedirect("AdminTodosJuegos");
                   }else{
                       request.getSession().setAttribute("info","Descuento no publicado, verifique que se ingrese un valor de dias correcto");
                       response.sendRedirect("AdminTodosJuegos");
                   }
               }else{
                   request.getSession().setAttribute("info","Descuento no publicado, verifique que se ingrese un valor de precio valido");
                   response.sendRedirect("AdminTodosJuegos");
               }


                break;

            default:
                response.sendRedirect("AdminServlet");
        }

    }

    private Descuentos setDescuento(HttpServletRequest request){

        Descuentos descuentos = new Descuentos();
        descuentos.setDuracionDescuento(Integer.parseInt(request.getParameter("dias")));
        descuentos.setPrecioDescuentoNuevo(Integer.parseInt(request.getParameter("precioDescuento")));
        return  descuentos;
    }

    public boolean valida_precio(String input){
        String regex = "\\d+";
        if (!input.matches(regex)) {
            return false;
        }

        int numero = Integer.parseInt(input);
        if (numero <= 0) {
            return false;
        }

        return true;
    }

}
