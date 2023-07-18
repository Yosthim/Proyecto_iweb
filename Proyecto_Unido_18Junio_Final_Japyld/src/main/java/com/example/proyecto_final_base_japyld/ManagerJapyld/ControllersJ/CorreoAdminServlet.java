package com.example.proyecto_final_base_japyld.ManagerJapyld.ControllersJ;

import com.example.proyecto_final_base_japyld.ManagerJapyld.ModelsJ.DaosJ.ModuloUsuarioDao;
import com.example.proyecto_final_base_japyld.ManagerJapyld.ModelsJ.DaosJ.CorreoAdminDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "CorreoAdminServlet", value = "/CorreoAdminServlet")
public class CorreoAdminServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {

        response.setContentType("text/html");

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("ManagerJapyld/EnvioAdminCorreo.jsp");
        requestDispatcher.forward(request,response);

    }


    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {

        String correoDestino = request.getParameter("destino");
        String asunto = request.getParameter("asunto");
        String contenido = request.getParameter("mensaje");

        CorreoAdminDao correoAdminDao = new CorreoAdminDao();
        correoAdminDao.correo(correoDestino, asunto, contenido);
        response.sendRedirect(request.getContextPath()+"/ModuloAdminServlet");
    }

}


