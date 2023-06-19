package com.example.proyecto_final_base_japyld.UsuarioJapyld.ControllersJ;

import com.example.proyecto_final_base_japyld.UsuarioJapyld.ModelsJ.DaosJ.JuegosXCategoriaDao;
import com.example.proyecto_final_base_japyld.UsuarioJapyld.ModelsJ.DaosJ.PaginaPrincipalDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "JuegosXCategoriaServlet", value = {"/Pagina-principal-categoria"})
public class JuegosXCategoriaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        JuegosXCategoriaDao categoriadao = new JuegosXCategoriaDao();

        String idcategoria = request.getParameter("idcategoria");
        request.setAttribute("listaJuegosXcategoria", categoriadao.listarJuegosXcategoria(idcategoria));
        RequestDispatcher view = request.getRequestDispatcher("UsuarioJapyld/JuegosXCategoria.jsp");
        view.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
