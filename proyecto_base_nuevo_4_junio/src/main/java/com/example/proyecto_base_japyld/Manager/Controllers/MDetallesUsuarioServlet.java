package com.example.proyecto_base_japyld.Manager.Controllers;
import com.example.proyecto_base_japyld.Manager.Models.Daos.MDetallesUsuarioDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name="MDetallesUsuarioServlet", value = "/MDetallesUsuarioServlet")
public class MDetallesUsuarioServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MDetallesUsuarioDao perfilUsuarioDao = new MDetallesUsuarioDao();

        String id = request.getParameter("nm");

        request.setAttribute("e", perfilUsuarioDao.detallesUsuario(Integer.parseInt(id)));
        request.setAttribute("listaJuegosVendidos", perfilUsuarioDao.listarJuegosVendidos(Integer.parseInt(id)));
        request.setAttribute("listaJuegosComprados", perfilUsuarioDao.listarJuegosComprados( Integer.parseInt(id)));

        request.getRequestDispatcher("Manager/MDetallesUsuario.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {

    }

}
