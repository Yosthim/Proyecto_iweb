package com.example.proyecto_final_base_japyld.AdministradorJapyld.ControllersJ;

import com.example.proyecto_final_base_japyld.AdministradorJapyld.ModelsJ.DaosJ.JuegosReservadosDaos;
import com.example.proyecto_final_base_japyld.AdministradorJapyld.ModelsJ.DaosJ.OfertasDao;
import com.example.proyecto_final_base_japyld.BeansGenerales.Juegos;
import com.example.proyecto_final_base_japyld.BeansGenerales.JuegosCompradosReservados;
import com.example.proyecto_final_base_japyld.BeansGenerales.VentaJuegosGeneral;
import com.example.proyecto_final_base_japyld.SistemaJapyld.ModelsJ.DaosJ.CorreoDao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "DetalleReservaServlet", value = "/DetalleReservaServlet")
public class DetalleReservaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        JuegosReservadosDaos juegosReservadosDaos  = new JuegosReservadosDaos();
        CorreoDao correoDao = new CorreoDao();
        RequestDispatcher view;
        String action = request.getParameter("action") == null ? "lista" :request.getParameter("action");

        switch (action){

            case "detalle":

                if (request.getParameter("id") != null) {

                    String id_venta1 = request.getParameter("id");
                    int id_veta_int1 = 0;
                    try{
                        id_veta_int1=Integer.parseInt(id_venta1);
                    }catch (NumberFormatException e){
                        response.sendRedirect("DetalleReservaServlet");
                    }

                    JuegosCompradosReservados juegosCompradosReservados = juegosReservadosDaos.obtenerReserva(id_veta_int1);

                    if(juegosCompradosReservados != null){

                        request.setAttribute("juegosCompradosReservados",juegosCompradosReservados);
                        view = request.getRequestDispatcher("AdministradorJapyld/DetalleReserva.jsp");
                        view.forward(request,response);
                    }else{
                        response.sendRedirect("OfertasServlet");
                    }

                }else{
                    response.sendRedirect("OfertasServlet");
                }

                break;

            case "recibido":

                if (request.getParameter("id") != null) {
                    String id_venta1 = request.getParameter("id");
                    int id_veta_int1 = 0;
                    try{
                        id_veta_int1=Integer.parseInt(id_venta1);
                    }catch (NumberFormatException e){
                        response.sendRedirect("OfertasServlet");
                    }

                    JuegosCompradosReservados juegosCompradosReservados = juegosReservadosDaos.obtenerReserva(id_veta_int1);

                    if(juegosCompradosReservados != null){
                        juegosReservadosDaos.cambiarEstado(juegosCompradosReservados);
                        correoDao.correo(juegosCompradosReservados.getUsuario().getCorreo(),"Juego Recibido","Su compra del juego " + juegosCompradosReservados.getJuego().getNombreJuegos() + " ha culminado exitosamente, esperamos que esta experiencia halla sido grata para usted. Disfrute de su compra.");
                        request.getSession().setAttribute("info","Actualizacion de informacion realizada correctamente");
                        response.sendRedirect(request.getContextPath() + "/JuegosReservadosServlet");

                    }else{
                        response.sendRedirect("OfertasServlet");
                    }

                }else{
                    response.sendRedirect("OfertasServlet");
                }
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
