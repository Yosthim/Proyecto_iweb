package com.example.proyecto_base_japyld.Manager.Controllers;

import com.example.proyecto_base_japyld.Manager.Models.Daos.LoMasVendidoDao;
import com.example.proyecto_base_japyld.Manager.Models.Daos.LoMenosVendidoDao;
import com.example.proyecto_base_japyld.Manager.Models.Daos.ManagerDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "ManServlet", value = "/ManServlet")
public class ManServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ManagerDao managerDao = new ManagerDao();
        request.setAttribute("objetivos",managerDao.Objetivos());
        LoMasVendidoDao loMasVendidoDao = new LoMasVendidoDao();
        request.setAttribute("listaMasVendidos",loMasVendidoDao.listarJuegosMasVendidos());
        LoMenosVendidoDao loMenosVendidoDao = new LoMenosVendidoDao();
        request.setAttribute("listaMenosVendidos",loMenosVendidoDao.listarJuegosMenosVendidos());
        request.getRequestDispatcher("Manager/paginaprincipalmanager.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
