package com.example.proyecto_final_base_japyld.AdministradorJapyld.ControllersJ;


import com.example.proyecto_final_base_japyld.AdministradorJapyld.ModelsJ.DaosJ.AdminDao;
import com.example.proyecto_final_base_japyld.AdministradorJapyld.ModelsJ.DaosJ.CategoriaDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "AdminTodosJuegos", value = {"/AdminTodosJuegos"})
public class AdminTodosJuegos extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        AdminDao tjuegosDao = new AdminDao();
        CategoriaDao categoriadao = new CategoriaDao();
        request.setAttribute("tjuegos",tjuegosDao.todosJuegos());
        request.setAttribute("categorias",categoriadao.listaCategoria());

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("AdministradorJapyld/AdminTodosJuegos.jsp");
        requestDispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CategoriaDao categoriadao = new CategoriaDao();
        AdminDao buscarjuego = new AdminDao();

        String textoBuscar = request.getParameter("textoBuscar");
        if (textoBuscar == null) {
            response.sendRedirect("EmployeeServlet");
        } else {
            request.setAttribute("textoBusqueda", textoBuscar);
            request.setAttribute("tjuegos", buscarjuego.buscarJuegoPorNombre(textoBuscar));
            request.setAttribute("categorias",categoriadao.listaCategoria());
            RequestDispatcher view = request.getRequestDispatcher("AdministradorJapyld/AdminTodosJuegos.jsp");
            view.forward(request, response);
        }

    }
}
