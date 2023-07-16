package com.example.proyecto_final_base_japyld.ManagerJapyld.ControllersJ;

import com.example.proyecto_final_base_japyld.ManagerJapyld.ModelsJ.DaosJ.DetalleUsuarioDao;
import com.example.proyecto_final_base_japyld.ManagerJapyld.ModelsJ.DaosJ.ModuloAdminDao;
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
        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");
        DetalleUsuarioDao perfilUsuarioDao = new DetalleUsuarioDao();
        ModuloAdminDao adminModuloDao = new ModuloAdminDao();

        String id = request.getParameter("nm");

        switch (action) {
            case "lista":

                request.setAttribute("e", perfilUsuarioDao.detallesUsuario(Integer.parseInt(id)));
                request.setAttribute("listaJuegosVendidos", perfilUsuarioDao.listarJuegosVendidos(Integer.parseInt(id)));
                request.setAttribute("listaJuegosComprados", perfilUsuarioDao.listarJuegosComprados( Integer.parseInt(id)));

                request.getRequestDispatcher("ManagerJapyld/DetalleUsuario.jsp").forward(request, response);
                break;
            case "editar":

                perfilUsuarioDao.editarUser(Integer.parseInt(id));
                response.sendRedirect(request.getContextPath() + "/ModuloUsuarioServlet");
                break;

            case "noban":
                String idStr = request.getParameter("idUser");
                int id2 = Integer.parseInt(idStr);
                perfilUsuarioDao.desbanearUser(id2);
                response.sendRedirect(request.getContextPath() + "/ModuloUsuarioServlet");
                break;
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {

    }
}
