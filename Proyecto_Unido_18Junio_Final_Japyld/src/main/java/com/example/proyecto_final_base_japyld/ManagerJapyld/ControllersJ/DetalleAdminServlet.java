package com.example.proyecto_final_base_japyld.ManagerJapyld.ControllersJ;

import com.example.proyecto_final_base_japyld.ManagerJapyld.ModelsJ.DaosJ.DetalleAdminDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name="DetalleAdminServlet", value = "/DetalleAdminServlet")
public class DetalleAdminServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DetalleAdminDao perfilAdminDao = new DetalleAdminDao();

        String id = request.getParameter("n");

        request.setAttribute("a", perfilAdminDao.detallesAdmin(Integer.parseInt(id)));
        request.setAttribute("listaJuegosPropuestos", perfilAdminDao.listarJuegosPropuestos(Integer.parseInt(id)));

        request.getRequestDispatcher("ManagerJapyld/DetalleAdmin.jsp").forward(request, response);
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
    }
}
