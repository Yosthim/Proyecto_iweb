package com.example.proyecto_final_base_japyld.ManagerJapyld.ControllersJ;

import com.example.proyecto_final_base_japyld.ManagerJapyld.ModelsJ.DaosJ.JuegosManagerDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "MasDetallesJuegosServlet", value = "/MasDetallesJuegosServlet")
public class MasDetallesJuegosServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JuegosManagerDao detallesJuegos = new JuegosManagerDao();

        int idjuego = Integer.parseInt(request.getParameter("idjuego"));

        request.setAttribute("listaMasDetallesJuego", detallesJuegos.listarMasDetallesJuego(idjuego));
        request.setAttribute("listaConsolaPorJuego",detallesJuegos.listarConsolasPorJuego(idjuego));
        request.setAttribute("listaRating",detallesJuegos.listarRating(idjuego));

        request.setAttribute("MasDetallesJuego", detallesJuegos.listarMasDetallesJuego(idjuego));
        RequestDispatcher view = request.getRequestDispatcher("ManagerJapyld/MasDetallesJuego.jsp");
        view.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
