package com.example.proyecto_final_base_japyld.UsuarioJapyld.ControllersJ;


import com.example.proyecto_final_base_japyld.AdministradorJapyld.ModelsJ.DaosJ.CategoriaDao;
import com.example.proyecto_final_base_japyld.UsuarioJapyld.ModelsJ.DaosJ.PaginaPrincipalDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "PaginaPrincipalJuegosServlet", value = {"/PaginaPrincipal"})
public class PaginaPrincipalJuegosServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        PaginaPrincipalDao juegosDao = new PaginaPrincipalDao();
        CategoriaDao categoriadao = new CategoriaDao();
        request.setAttribute("listaJuegos",juegosDao.listarJuegos());
        request.setAttribute("categorias",categoriadao.listaCategoria());

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("UsuarioJapyld/PaginaPrincipal.jsp");
        requestDispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CategoriaDao categoriadao = new CategoriaDao();
        PaginaPrincipalDao buscarjuegosdao = new PaginaPrincipalDao();

        String textoBuscar = request.getParameter("textoBuscar");
        if (textoBuscar == null) {
            response.sendRedirect("EmployeeServlet");
        } else {
            request.setAttribute("textoBusqueda", textoBuscar);
            request.setAttribute("listaJuegos", buscarjuegosdao.buscarJuegoPorNombre(textoBuscar));
            request.setAttribute("categorias",categoriadao.listaCategoria());
            RequestDispatcher view = request.getRequestDispatcher("UsuarioJapyld/PaginaPrincipal.jsp");
            view.forward(request, response);
        }

    }
}