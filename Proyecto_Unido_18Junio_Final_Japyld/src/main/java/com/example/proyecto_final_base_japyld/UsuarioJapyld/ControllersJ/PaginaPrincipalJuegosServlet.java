package com.example.proyecto_final_base_japyld.UsuarioJapyld.ControllersJ;


import com.example.proyecto_final_base_japyld.AdministradorJapyld.ModelsJ.DaosJ.CategoriaDao;
import com.example.proyecto_final_base_japyld.UsuarioJapyld.ModelsJ.DaosJ.PaginaPrincipalDao;
import com.example.proyecto_final_base_japyld.UsuarioJapyld.ModelsJ.DaosJ.PerfilDao;
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
        PerfilDao perfilDao1 = new PerfilDao();

        request.setAttribute("listaJuegos",juegosDao.listarJuegos());
        request.setAttribute("categorias",categoriadao.listaCategoria());
        request.setAttribute("listaFotoPerfil",perfilDao1.listarFotoPerfil());

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("UsuarioJapyld/PaginaPrincipal.jsp");
        requestDispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CategoriaDao categoriadao = new CategoriaDao();
        PaginaPrincipalDao buscarjuegosdao = new PaginaPrincipalDao();
        PerfilDao perfilDao1 = new PerfilDao();

        String textoBuscar = request.getParameter("textoBuscar");
        if (textoBuscar == null) {
            response.sendRedirect("EmployeeServlet");
        } else {
            request.setAttribute("textoBusqueda", textoBuscar);
            request.setAttribute("listaJuegos", buscarjuegosdao.buscarJuegoPorNombre(textoBuscar));

            request.setAttribute("categorias",categoriadao.listaCategoria());
            request.setAttribute("listaFotoPerfil",perfilDao1.listarFotoPerfil());
            RequestDispatcher view = request.getRequestDispatcher("UsuarioJapyld/PaginaPrincipal.jsp");
            view.forward(request, response);
        }

    }
}