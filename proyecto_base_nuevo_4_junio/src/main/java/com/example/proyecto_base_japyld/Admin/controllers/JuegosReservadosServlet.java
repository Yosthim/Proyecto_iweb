package com.example.proyecto_base_japyld.Admin.controllers;

import com.example.proyecto_base_japyld.Admin.Models.Daos.JuegosReservadosDaos;
import com.example.proyecto_base_japyld.Admin.Models.beans.JuegosReservadosDias;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "JuegosReservadosServlet", value = "/JuegosReservadosServlet")
public class JuegosReservadosServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JuegosReservadosDaos juegosReservadosDaos = new JuegosReservadosDaos();
        request.setAttribute("lista30dias", juegosReservadosDaos.tabla30Dias());
        request.setAttribute("lista20dias", juegosReservadosDaos.tabla20Dias());
        request.setAttribute("lista10dias", juegosReservadosDaos.tabla10Dias());

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("admi/ReservasAdmi.jsp");
        requestDispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
