package com.example.proyecto_base_japyld.Usuario.Controllers;


import com.example.proyecto_base_japyld.Usuario.Models.Daos.CompraDao;
import com.example.proyecto_base_japyld.Usuario.Models.Daos.MasDetallesDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CompraServlet", value = {"/PaginaCompra"})
public class CompraServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CompraDao comprajuego = new CompraDao();

        int idjuego = Integer.parseInt(request.getParameter("idjuego"));

        request.setAttribute("listaDetallesCompra", comprajuego.listarDetallesCompra(idjuego));
        RequestDispatcher view = request.getRequestDispatcher("Usuario/PaginaCompra.jsp");
        view.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}