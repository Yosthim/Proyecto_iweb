package com.example.proyecto_final_base_japyld.AdministradorJapyld.ControllersJ;

import com.example.proyecto_final_base_japyld.AdministradorJapyld.ModelsJ.DaosJ.AdminDao;
import com.example.proyecto_final_base_japyld.AdministradorJapyld.ModelsJ.DaosJ.AgregarDao;
import com.example.proyecto_final_base_japyld.AdministradorJapyld.ModelsJ.DaosJ.CrudDao;
import com.example.proyecto_final_base_japyld.BeansGenerales.*;
import com.example.proyecto_final_base_japyld.UsuarioJapyld.ModelsJ.DaosJ.PerfilDao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

@WebServlet(name = "editarImagenServlet", value = "/editarImagenServlet")
@MultipartConfig
public class editarImagenServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        RequestDispatcher view;
        PerfilDao perfilDao1 = new PerfilDao();
        CrudDao crudDao =new CrudDao();

        String action = request.getParameter("act") == null ? "listar" : request.getParameter("act");

        switch (action){
            case "vista":

                if (request.getParameter("id") != null) {
                    String id_juego = request.getParameter("id");
                    int id_juego_int = 0;
                    try{
                        id_juego_int=Integer.parseInt(id_juego);
                    }catch (NumberFormatException e){
                        response.sendRedirect("OfertasServlet");
                    }

                    Juegos juego = crudDao.ObtenerJuego(id_juego_int);

                    if(juego != null){
                        request.setAttribute("listaFotoPerfil",perfilDao1.listarFotoPerfil());
                        request.setAttribute("juego",juego);
                        view = request.getRequestDispatcher("AdministradorJapyld/NuevaImagen.jsp");
                        view.forward(request,response);
                    }else{
                        response.sendRedirect("OfertasServlet");
                    }

                }else{
                    request.setAttribute("listaFotoPerfil",perfilDao1.listarFotoPerfil());
                    response.sendRedirect("OfertasServlet");
                }

                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        AgregarDao agregarDao = new AgregarDao();
        String action = request.getParameter("act");

        Juegos juegos = setJuego(request);

        switch (action){
            case "new":
                Part imageGamePart = request.getPart("imagenJuego");
                InputStream imageGameContent = imageGamePart.getInputStream();

                juegos.getImagen().setImagem(imageGameContent);
                agregarDao.actaualizarImagen(juegos);
                request.getSession().setAttribute("info","Imagen actualizada correctamente");
                response.sendRedirect("AdminTodosJuegos");

                break;
        }


    }

    public Juegos setJuego(HttpServletRequest request) {

        Juegos juegos = new Juegos();

        juegos.setIdJuegos(Integer.parseInt(request.getParameter("id_juego")));

        Imagen imagen = new Imagen();
        juegos.setImagen(imagen);

        return juegos;
    }

}
