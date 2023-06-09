package com.example.proyecto_base_japyld.Admin.controllers;
import com.example.proyecto_base_japyld.Admin.Models.Daos.JuegosEditarDao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "JuegosEditarServlet", value = {"/EditarJuegos"})
public class JuegosEditarServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        JuegosEditarDao juegosEditarDao = new JuegosEditarDao();

        int idjuego = Integer.parseInt(request.getParameter("idjuego"));

        request.setAttribute("listaJuegoEditar", juegosEditarDao.listarJuegoEditar(idjuego));
        RequestDispatcher view = request.getRequestDispatcher("admi/adminEditVideojuego.jsp");
        view.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}