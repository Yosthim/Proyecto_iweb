package com.example.proyecto_base_japyld.controllers;

import java.io.*;
import com.example.proyecto_base_japyld.models.daos.VideojuegosOfertaAdminDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
@WebServlet(name = "VideojuegosOfertaAdminServlet", value = "/VideojuegosOfertaAdminServlet")

public class VideojuegosOfertaAdminServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");

        VideojuegosOfertaAdminDao juegosOfertasDao = new VideojuegosOfertaAdminDao();
        request.setAttribute("lista",juegosOfertasDao.listarJuegosAceptados());
        request.setAttribute("lista2",juegosOfertasDao.listarJuegosNuevos());
        request.setAttribute("lista3",juegosOfertasDao.listarNuevasOfertas());

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("administrador/videojuegos/videojuegosofertas.jsp");
        requestDispatcher.forward(request,response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

