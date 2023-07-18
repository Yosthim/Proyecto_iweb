package com.example.proyecto_final_base_japyld.AdministradorJapyld.ControllersJ;


import com.example.proyecto_final_base_japyld.AdministradorJapyld.ModelsJ.DaosJ.AdminDao;
import com.example.proyecto_final_base_japyld.AdministradorJapyld.ModelsJ.DaosJ.CategoriaDao;
import com.example.proyecto_final_base_japyld.UsuarioJapyld.ModelsJ.DaosJ.PerfilDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "AdminTodosJuegosServlet", value = {"/AdminTodosJuegos"})
public class AdminTodosJuegosServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        AdminDao tjuegosDao = new AdminDao();
        CategoriaDao categoriadao = new CategoriaDao();
        PerfilDao perfilDao1 = new PerfilDao();

        request.setAttribute("listaFotoPerfil",perfilDao1.listarFotoPerfil());
        request.setAttribute("tjuegos",tjuegosDao.todosJuegos());
        request.setAttribute("categorias",categoriadao.listaCategoria());

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("AdministradorJapyld/AdminTodosJuegos.jsp");
        requestDispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        AdminDao buscarjuego = new AdminDao();
        PerfilDao perfilDao1 = new PerfilDao();

        String textoBuscar = request.getParameter("textoBuscar");
        if (textoBuscar == null) {
            response.sendRedirect("EmployeeServlet");
        } else {
            request.setAttribute("textoBusqueda", textoBuscar);
            request.setAttribute("tjuegos", buscarjuego.buscarJuegoPorNombre2(textoBuscar));
            request.setAttribute("listaFotoPerfil",perfilDao1.listarFotoPerfil());
            RequestDispatcher view = request.getRequestDispatcher("AdministradorJapyld/AdminTodosJuegos.jsp");
            view.forward(request, response);
        }

    }
}
