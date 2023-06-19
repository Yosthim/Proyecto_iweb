package com.example.proyecto_final_base_japyld.ManagerJapyld.ControllersJ;

import com.example.proyecto_final_base_japyld.ManagerJapyld.ModelsJ.DaosJ.ModuloAdminDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "ModuloAdminServlet", value = "/ModuloAdminServlet")
public class ModuloAdminServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {

        response.setContentType("text/html");

        ModuloAdminDao adminModuloDao = new ModuloAdminDao();

        request.setAttribute("lista", adminModuloDao.listarAdmin());

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("ManagerJapyld/ModuloAdmin.jsp");
        requestDispatcher.forward(request,response);

    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {

    }
}
