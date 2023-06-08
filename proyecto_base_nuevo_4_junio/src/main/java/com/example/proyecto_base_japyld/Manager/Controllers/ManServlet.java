package com.example.proyecto_base_japyld.Manager.Controllers;

import com.example.proyecto_base_japyld.Manager.Models.Daos.ManagerDao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "ManServlet", value = "/ManServlet")
public class ManServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ManagerDao managerDao = new ManagerDao();
        request.setAttribute("objetivos",managerDao.Objetivos());
        request.getRequestDispatcher("manager/paginaprincipalmanager.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
