package com.example.proyecto_final_base_japyld.ManagerJapyld.ControllersJ;

import com.example.proyecto_final_base_japyld.ManagerJapyld.ModelsJ.DaosJ.DetalleUsuarioDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name="DetalleUsuarioServlet", value = "/DetalleUsuarioServlet")

public class DetalleUsuarioServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DetalleUsuarioDao perfilUsuarioDao = new DetalleUsuarioDao();

        String id = request.getParameter("nm");

        request.setAttribute("e", perfilUsuarioDao.detallesUsuario(Integer.parseInt(id)));
        request.setAttribute("listaJuegosVendidos", perfilUsuarioDao.listarJuegosVendidos(Integer.parseInt(id)));
        request.setAttribute("listaJuegosComprados", perfilUsuarioDao.listarJuegosComprados( Integer.parseInt(id)));

        request.getRequestDispatcher("ManagerJapyld/DetalleUsuario.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {

    }
}
