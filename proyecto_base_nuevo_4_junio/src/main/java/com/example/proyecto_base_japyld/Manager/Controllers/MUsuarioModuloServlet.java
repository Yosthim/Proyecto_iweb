package com.example.proyecto_base_japyld.Manager.Controllers;

import com.example.proyecto_base_japyld.Manager.Models.Daos.MUsuarioModuloDao;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "MUsuarioModuloServlet", value = "/MUsuarioModuloServlet")
public class MUsuarioModuloServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {

        response.setContentType("text/html");

        MUsuarioModuloDao usuariosModuloDao = new MUsuarioModuloDao();

        request.setAttribute("lista", usuariosModuloDao.listarUsuarios());

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("Manager/MUsuarioModulo.jsp");
        requestDispatcher.forward(request,response);

    }


    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {

    }
}
