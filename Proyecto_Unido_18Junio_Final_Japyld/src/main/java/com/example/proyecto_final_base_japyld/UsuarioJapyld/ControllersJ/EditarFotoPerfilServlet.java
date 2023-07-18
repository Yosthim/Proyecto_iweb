package com.example.proyecto_final_base_japyld.UsuarioJapyld.ControllersJ;

import com.example.proyecto_final_base_japyld.AdministradorJapyld.ModelsJ.DaosJ.AdminDao;
import com.example.proyecto_final_base_japyld.UsuarioJapyld.ModelsJ.DaosJ.PerfilDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "EditarFotoPerfilServlet", value = "/EditarPerfil")
public class EditarFotoPerfilServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PerfilDao perfildao = new PerfilDao();
        request.setAttribute("listaImagenPerfil",perfildao.listarImagenesPerfil());
        request.setAttribute("listaFotoPerfil",perfildao.listarFotoPerfil());
        request.getRequestDispatcher("UsuarioJapyld/EditarFotoPerfil.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        PerfilDao actualizarFotoDao = new PerfilDao();

        String idImagen = request.getParameter("IdImagen");
        int idFotoPerfil = Integer.parseInt(idImagen);

        String idUsuario = request.getParameter("idUsuario");
        int idUsuarioPerfil = Integer.parseInt(idUsuario);

        actualizarFotoDao.actualizarFotoPerfil(idFotoPerfil,idUsuarioPerfil);

        response.sendRedirect(request.getContextPath() + "/PerfilUsuarioServlet");

    }
}
