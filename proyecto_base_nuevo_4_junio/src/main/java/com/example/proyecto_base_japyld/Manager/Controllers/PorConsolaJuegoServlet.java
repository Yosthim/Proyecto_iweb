package com.example.proyecto_base_japyld.Manager.Controllers;

import com.example.proyecto_base_japyld.Manager.Models.Daos.PorConsolaJuegoDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "PorConsolaJuegoServlet", value = "/PorConsolaJuegoServlet")
public class PorConsolaJuegoServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        response.setContentType("text/html");
        PorConsolaJuegoDao porConsolaJuegoDao = new PorConsolaJuegoDao();
        request.setAttribute("listaPorConsolaJuego", porConsolaJuegoDao.listarJuegosPorConsola());
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("Manager/PorConsolaJuego.jsp");
        requestDispatcher.forward(request,response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
    }
}
