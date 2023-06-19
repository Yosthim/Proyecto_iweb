package com.example.proyecto_final_base_japyld.ManagerJapyld.ControllersJ;

import com.example.proyecto_final_base_japyld.ManagerJapyld.ModelsJ.DaosJ.JuegosManagerDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "EditarJuegosServlet", value = "/EditarJuegosServlet")
public class EditarJuegosServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {

         JuegosManagerDao juegosManagerDao= new JuegosManagerDao();

        String action = request.getParameter("a") == null ? "listar" : request.getParameter("a");

        switch (action) {
            case "l":
                request.setAttribute("listarTodosJuegos", juegosManagerDao.listarTodosJuegos());
                request.getRequestDispatcher("ManagerJapyld/EditarJuegos.jsp").forward(request, response);
                break;
            case "b":
                String id_juegoStr = request.getParameter("idjuego");
                int id_juego = Integer.parseInt(id_juegoStr);
                juegosManagerDao.borrarJuego(id_juego);
                response.sendRedirect(request.getContextPath() + "/EditarJuegosServlet?a=l");
                break;
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
    }
}
