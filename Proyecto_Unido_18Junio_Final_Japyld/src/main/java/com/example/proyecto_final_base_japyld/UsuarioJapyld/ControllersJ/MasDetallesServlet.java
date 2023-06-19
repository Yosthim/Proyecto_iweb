package com.example.proyecto_final_base_japyld.UsuarioJapyld.ControllersJ;

import com.example.proyecto_final_base_japyld.UsuarioJapyld.ModelsJ.DaosJ.MasDetallesDao;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "MasDetallesServlet", value = {"/MasDetallesJuego"})
public class MasDetallesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MasDetallesDao masdetallesdao = new MasDetallesDao();

        String idjuego = request.getParameter("idjuego");

        request.setAttribute("listaMasDetallesJuego", masdetallesdao.listarMasDetallesJuego(idjuego));
        RequestDispatcher view = request.getRequestDispatcher("UsuarioJapyld/MasDetallesJuego.jsp");
        view.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
