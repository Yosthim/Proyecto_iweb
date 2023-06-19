package com.example.proyecto_base_japyld.Usuario.Controllers;

import com.example.proyecto_base_japyld.Usuario.Models.Beans.JuegoComRes;
import com.example.proyecto_base_japyld.Usuario.Models.Daos.TusJuegosDao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "TusJuegosServlet", value = "/TusJuegos")
public class TusJuegosServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        TusJuegosDao tusJuegosDao = new TusJuegosDao();

        request.setAttribute("listaJuegos", tusJuegosDao.listarTusJuegos(2));

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("Usuario/TusJuegos.jsp");
        requestDispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
