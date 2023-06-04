package com.example.proyecto_base_japyld.controllers;

import java.io.*;

import com.example.proyecto_base_japyld.models.daos.VideojuegosReservaAdminDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
@WebServlet(name = "VideojuegosReservaAdminServlet", value = "/VideojuegosReservaAdminServlet")

public class VideojuegosReservaAdminServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");

        VideojuegosReservaAdminDao juegosReservasDao = new VideojuegosReservaAdminDao();
        request.setAttribute("lista",juegosReservasDao.listarJuegosReservadosMas10());
        request.setAttribute("lista2",juegosReservasDao.listarJuegosReservadosMas20());
        request.setAttribute("lista3",juegosReservasDao.listarJuegosReservadosUltimamente());

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("administrador/videojuegos/videojuegosreservas.jsp");
        requestDispatcher.forward(request,response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
