package com.example.proyecto_base_japyld.Usuario.Controllers;

import com.example.proyecto_base_japyld.Usuario.Models.Daos.PaginaprincipalJuegosDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "PaginaprincipalJuegosServlet", value = {"/Pagina-principal"})
public class PaginaprincipalJuegosServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        PaginaprincipalJuegosDao juegosDao = new PaginaprincipalJuegosDao();
        request.setAttribute("listaJuegos",juegosDao.listarJuegos());

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("Usuario/Paginaprincipal.jsp");
        requestDispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}