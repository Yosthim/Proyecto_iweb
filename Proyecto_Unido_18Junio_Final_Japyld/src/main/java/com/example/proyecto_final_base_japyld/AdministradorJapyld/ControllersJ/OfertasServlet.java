package com.example.proyecto_final_base_japyld.AdministradorJapyld.ControllersJ;

import com.example.proyecto_final_base_japyld.AdministradorJapyld.ModelsJ.DaosJ.CrudDao;
import com.example.proyecto_final_base_japyld.AdministradorJapyld.ModelsJ.DaosJ.OfertasDao;
import com.example.proyecto_final_base_japyld.BeansGenerales.Imagen;
import com.example.proyecto_final_base_japyld.BeansGenerales.Juegos;
import com.example.proyecto_final_base_japyld.BeansGenerales.Personas;
import com.example.proyecto_final_base_japyld.BeansGenerales.VentaJuegosGeneral;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.math.BigDecimal;

@WebServlet(name = "OfertasServlet", value = "/OfertasServlet")
public class OfertasServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        Personas administrador= (Personas)session.getAttribute("personaSession");
        OfertasDao ofertasDao = new OfertasDao();
        RequestDispatcher view;
        String action = request.getParameter("action") == null ? "lista" :request.getParameter("action");
        switch (action){

            case "ofertas":
                String id_venta = request.getParameter("id");
                int id_veta_int = Integer.parseInt(id_venta);
                request.setAttribute("ventaJuegosGeneral", ofertasDao.obtenerJuego(id_veta_int));
                request.getRequestDispatcher("AdministradorJapyld/NuevaOfertaAdmi.jsp").forward(request,response);
                break;

            case "aceptar":

                if (request.getParameter("id") != null) {
                    String id_venta1 = request.getParameter("id");
                    int id_veta_int1 = 0;
                    try{
                        id_veta_int1=Integer.parseInt(id_venta1);
                    }catch (NumberFormatException e){
                        response.sendRedirect("OfertasServlet");
                    }

                    VentaJuegosGeneral ventaJuegosGeneral = ofertasDao.obtenerJuego(id_veta_int1);

                    if(ventaJuegosGeneral != null){

                        request.setAttribute("ventaJuegosGeneral",ventaJuegosGeneral);
                        view = request.getRequestDispatcher("AdministradorJapyld/AceptarOfertaAdmi.jsp");
                        view.forward(request,response);
                    }else{
                        response.sendRedirect("OfertasServlet");
                    }

                }else{
                    response.sendRedirect("OfertasServlet");
                }

                break;

            case "guardar":

                if (request.getParameter("id") != null) {
                    String id_venta1 = request.getParameter("id");
                    int id_veta_int1 = 0;
                    try{
                        id_veta_int1=Integer.parseInt(id_venta1);
                    }catch (NumberFormatException e){
                        response.sendRedirect("OfertasServlet");
                    }

                    VentaJuegosGeneral ventaJuegosGeneral = ofertasDao.obtenerJuego(id_veta_int1);

                    if(ventaJuegosGeneral != null){
                        ofertasDao.borrar(ventaJuegosGeneral);
                        response.sendRedirect(request.getContextPath() + "/AdminServlet?action=listaPaginaOfertas");

                    }else{
                        response.sendRedirect("OfertasServlet");
                    }

                }else{
                    response.sendRedirect("OfertasServlet");
                }

                break;

            case "rechazar":

                if (request.getParameter("id") != null) {
                    String id_venta2 = request.getParameter("id");
                    int id_veta_int2 = 0;
                    try{
                        id_veta_int2=Integer.parseInt(id_venta2);
                    }catch (NumberFormatException e){
                        response.sendRedirect("OfertasServlet");
                    }

                    VentaJuegosGeneral ventaJuegosGeneral = ofertasDao.obtenerJuego(id_veta_int2);

                    if(ventaJuegosGeneral != null){

                        request.setAttribute("ventaJuegosGeneral",ventaJuegosGeneral);
                        view = request.getRequestDispatcher("AdministradorJapyld/RechazarOferta.jsp");
                        view.forward(request,response);
                    }else{
                        response.sendRedirect("OfertasServlet");
                    }

                }else{
                    response.sendRedirect("OfertasServlet");
                }

                break;

            case "contraoferta":

                if (request.getParameter("id") != null) {
                    String id_venta3 = request.getParameter("id");
                    int id_veta_int3 = 0;
                    try{
                        id_veta_int3=Integer.parseInt(id_venta3);
                    }catch (NumberFormatException e){
                        response.sendRedirect("OfertasServlet");
                    }

                    VentaJuegosGeneral ventaJuegosGeneral = ofertasDao.obtenerJuego(id_veta_int3);

                    if(ventaJuegosGeneral != null){

                        request.setAttribute("ventaJuegosGeneral",ventaJuegosGeneral);
                        view = request.getRequestDispatcher("AdministradorJapyld/ContraofertaAdmi.jsp");
                        view.forward(request,response);
                    }else{
                        response.sendRedirect("OfertasServlet");
                    }

                }else{
                    response.sendRedirect("OfertasServlet");
                }

                break;

            default:
                response.sendRedirect("OfertasServlet");
        }

    }






    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "lista" :request.getParameter("action");
        OfertasDao ofertasDao = new OfertasDao();
        VentaJuegosGeneral ventaJuegosGeneral = setVenta(request);

        VentaJuegosGeneral ventaJuegosGeneralC = setVentaC(request);

        switch (action){

            case"actualizar":

                ventaJuegosGeneral.setIdVenta(Integer.parseInt(request.getParameter("id_venta")));
                ofertasDao.editarVenta(ventaJuegosGeneral);
                response.sendRedirect("AdminServlet?action=listaPaginaOfertas");

                break;
            case "actualizarC":
                ventaJuegosGeneralC.setIdVenta(Integer.parseInt(request.getParameter("id_venta")));
                ofertasDao.editarVentaC(ventaJuegosGeneralC);
                response.sendRedirect("AdminServlet?action=listaPaginaOfertas");
                break;


        }


    }

    private VentaJuegosGeneral setVenta(HttpServletRequest request){

        VentaJuegosGeneral ventaJuegosGeneral = new VentaJuegosGeneral();
        ventaJuegosGeneral.setPrecioUsuario(new BigDecimal(request.getParameter("precio")));

        ventaJuegosGeneral.setRazonRechazo(request.getParameter("rechazo"));

        Juegos juegos = new Juegos();
        juegos.setIdJuegos(Integer.parseInt(request.getParameter("juego")));

        Imagen imagen = new Imagen();
        imagen.setIdImagenes(Integer.parseInt(request.getParameter("imagen")));
        juegos.setImagen(imagen);

        ventaJuegosGeneral.setJuego(juegos);



        return  ventaJuegosGeneral;
    }

    private VentaJuegosGeneral setVentaC(HttpServletRequest request){

        VentaJuegosGeneral ventaJuegosGeneral = new VentaJuegosGeneral();
        ventaJuegosGeneral.setPrecioUsuario(new BigDecimal(request.getParameter("precio")));
        ventaJuegosGeneral.setPrecioAdmi(new BigDecimal(request.getParameter("precioAdmi")));
        ventaJuegosGeneral.setRazonRechazo(request.getParameter("rechazo"));

        Juegos juegos = new Juegos();
        juegos.setIdJuegos(Integer.parseInt(request.getParameter("juego")));

        Imagen imagen = new Imagen();
        imagen.setIdImagenes(Integer.parseInt(request.getParameter("imagen")));
        juegos.setImagen(imagen);

        ventaJuegosGeneral.setJuego(juegos);



        return  ventaJuegosGeneral;
    }


}
