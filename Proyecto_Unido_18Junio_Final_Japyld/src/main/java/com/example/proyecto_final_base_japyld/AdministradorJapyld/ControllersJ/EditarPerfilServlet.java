package com.example.proyecto_final_base_japyld.AdministradorJapyld.ControllersJ;

import com.example.proyecto_final_base_japyld.UsuarioJapyld.ModelsJ.DaosJ.PerfilDao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "EditarPerfilServlet", value = "/EditarPerfilServlet")
public class EditarPerfilServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PerfilDao perfildao = new PerfilDao();
        request.setAttribute("listaImagenPerfil",perfildao.listarImagenesPerfil());
        request.setAttribute("listaFotoPerfil",perfildao.listarFotoPerfil());
        request.getRequestDispatcher("AdministradorJapyld/EditarFotoPerfil.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PerfilDao actualizarFotoDao = new PerfilDao();

        String idImagen = request.getParameter("IdImagen");
        int idFotoPerfil = Integer.parseInt(idImagen);

        String idUsuario = request.getParameter("idUsuario");
        int idUsuarioPerfil = Integer.parseInt(idUsuario);

        actualizarFotoDao.actualizarFotoPerfil(idFotoPerfil,idUsuarioPerfil);

        response.sendRedirect(request.getContextPath() + "/PerfilAdmiServlet");

    }
}
