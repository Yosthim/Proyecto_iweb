package com.example.proyecto_base_japyld.Manager.Controllers;

import com.example.proyecto_base_japyld.Manager.Models.Daos.PorCategoriaJuegoDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "PorCategoriaJuegoServlet", value = "/PorCategoriaJuegoServlet")
public class PorCategoriaJuegoServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        response.setContentType("text/html");
        PorCategoriaJuegoDao porCategoriaJuegoDao = new PorCategoriaJuegoDao();
        request.setAttribute("listaPorCategoriaJuego", porCategoriaJuegoDao.listarJuegosPorCategoria());
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("Manager/PorCategoriaJuego.jsp");
        requestDispatcher.forward(request,response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
    }
}
