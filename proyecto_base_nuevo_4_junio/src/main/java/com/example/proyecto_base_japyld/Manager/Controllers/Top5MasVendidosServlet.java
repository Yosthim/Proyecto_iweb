package com.example.proyecto_base_japyld.Manager.Controllers;

import com.example.proyecto_base_japyld.Manager.Models.Daos.JuegosDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "Top5MasVendidosServlet", value = "/Top5MasVendidosServlet")
public class Top5MasVendidosServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        response.setContentType("text/html");
        JuegosDao top5MasVendidosDao = new JuegosDao();
        request.setAttribute("listaTop5MasVendidos", top5MasVendidosDao.listarJuegosMasVendidos());
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("Manager/Top5MasVendidos.jsp");
        requestDispatcher.forward(request,response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
    }
}
