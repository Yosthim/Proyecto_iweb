package com.example.proyecto_base_japyld.Manager.Controllers;

import com.example.proyecto_base_japyld.Manager.Models.Daos.JuegosDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "MasDetallesJuegosServlet", value = "/MasDetallesJuegosServlet")
public class MasDetallesJuegosServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JuegosDao detallesJuegos = new JuegosDao();

        String idjuego = request.getParameter("idjuego");

        request.setAttribute("MasDetallesJuego", detallesJuegos.listarMasDetallesJuego(idjuego));
        RequestDispatcher view = request.getRequestDispatcher("Manager/MasDetallesJuego.jsp");
        view.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}