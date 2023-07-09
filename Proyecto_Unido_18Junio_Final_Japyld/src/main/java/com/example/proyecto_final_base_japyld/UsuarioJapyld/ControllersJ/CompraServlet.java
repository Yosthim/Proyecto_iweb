package com.example.proyecto_final_base_japyld.UsuarioJapyld.ControllersJ;

import com.example.proyecto_final_base_japyld.UsuarioJapyld.ModelsJ.DaosJ.CompraDao;

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
        String consola = request.getParameter("consola");

        request.setAttribute("listaDetallesCompra", comprajuego.listarDetallesCompra(idjuego,consola));
        RequestDispatcher view = request.getRequestDispatcher("UsuarioJapyld/PaginaCompra.jsp");
        view.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}