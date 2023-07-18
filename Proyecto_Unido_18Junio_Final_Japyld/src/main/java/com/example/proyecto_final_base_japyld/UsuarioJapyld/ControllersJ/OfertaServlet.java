package com.example.proyecto_final_base_japyld.UsuarioJapyld.ControllersJ;


import com.example.proyecto_final_base_japyld.AdministradorJapyld.ModelsJ.DaosJ.CategoriaDao;
import com.example.proyecto_final_base_japyld.UsuarioJapyld.ModelsJ.DaosJ.JuegosOfertaDao;
import com.example.proyecto_final_base_japyld.UsuarioJapyld.ModelsJ.DaosJ.PerfilDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "OfertaServlet", value = {"/Pagina-principal-oferta"})
public class OfertaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        PerfilDao perfilDao1 = new PerfilDao();
        JuegosOfertaDao juegosDao = new JuegosOfertaDao();
        CategoriaDao categoriadao = new CategoriaDao();
        request.setAttribute("listaJuegosOferta",juegosDao.listarJuegosOferta());
        request.setAttribute("listaFotoPerfil",perfilDao1.listarFotoPerfil());
        request.setAttribute("categorias",categoriadao.listaCategoria());
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("UsuarioJapyld/JuegosOferta.jsp");

        requestDispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
