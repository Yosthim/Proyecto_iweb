package com.example.proyecto_final_base_japyld.ManagerJapyld.ControllersJ;

import com.example.proyecto_final_base_japyld.ManagerJapyld.ModelsJ.DaosJ.JuegosManagerDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "Top5MenosVendidosServlet", value = "/Top5MenosVendidosServlet")
public class Top5MenosVendidosServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        response.setContentType("text/html");
        JuegosManagerDao top5MenosVendidosDao = new JuegosManagerDao();
        request.setAttribute("listaTop5MenosVendidos", top5MenosVendidosDao.listarJuegosMenosVendidos());
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("ManagerJapyld/Top5MenosVendidos.jsp");
        requestDispatcher.forward(request,response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
    }
}
