package com.example.proyecto_base_japyld.controllers;

import java.io.*;

import com.example.proyecto_base_japyld.models.daos.VideoJuegosAdminDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "VideojuegosAdminServlet", value = "/VideojuegosAdminServlet")

public class VideojuegosAdminServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");

        VideoJuegosAdminDao JuegosDao = new VideoJuegosAdminDao();
        request.setAttribute("lista", JuegosDao.listarJuegos());
        request.setAttribute("lista2",JuegosDao.listarJuegosPopularesMes());
        request.setAttribute("lista3",JuegosDao.listarCategoriasPopularesMes());

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("administrador/videojuegos/videojuegos.jsp");
        requestDispatcher.forward(request,response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
