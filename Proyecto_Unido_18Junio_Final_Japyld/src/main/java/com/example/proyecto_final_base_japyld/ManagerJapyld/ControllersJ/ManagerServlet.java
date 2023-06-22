package com.example.proyecto_final_base_japyld.ManagerJapyld.ControllersJ;

import com.example.proyecto_final_base_japyld.BeansGenerales.Objetivos;
import com.example.proyecto_final_base_japyld.ManagerJapyld.ModelsJ.DaosJ.JuegosManagerDao;
import com.example.proyecto_final_base_japyld.ManagerJapyld.ModelsJ.DaosJ.ManagerDao;
import com.example.proyecto_final_base_japyld.ManagerJapyld.ModelsJ.DaosJ.ProductosVendidosDao;
import com.example.proyecto_final_base_japyld.ManagerJapyld.ModelsJ.DtoJ.JuegosManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

@WebServlet(name = "ManServlet", value = "/ManagerServlet")
public class ManagerServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ManagerDao managerDao = new ManagerDao();
        JuegosManagerDao juegosManagerDao = new JuegosManagerDao();
        ProductosVendidosDao productosVendidosDao = new ProductosVendidosDao();

        String action = request.getParameter("a") == null ? "resumen" :request.getParameter("a");

        switch (action){
            case "resumen":
                request.setAttribute("juegosRecienComentados", managerDao.resumenComentarios());
                request.setAttribute("catalogoReciente", managerDao.catologoReciente());
                request.getRequestDispatcher("ManagerJapyld/PaginaPrincipalManager.jsp").forward(request,response);
                break;

            case "productos":
                request.setAttribute("listaMasVendidos", productosVendidosDao.listarJuegosMasVendidos());
                request.setAttribute("listaMenosVendidos", productosVendidosDao.listarJuegosMenosVendidos());
                request.getRequestDispatcher("ManagerJapyld/PagPrin_Productos.jsp").forward(request, response);
                break;

            case "metas":
                request.setAttribute("objetivos", managerDao.Objetivos());
                request.setAttribute("objetivosPasados", managerDao.listarObjetivosPasados());
                request.getRequestDispatcher("ManagerJapyld/PagPrin_Metas.jsp").forward(request, response);
                break;

            case "estadisticas":
                request.getRequestDispatcher("ManagerJapyld/PagPrin_Estadisticas.jsp").forward(request, response);
                break;

            case "actualizarObjetivos":

                request.getRequestDispatcher("ManagerJapyld/PagPrin_ActualizarMetas.jsp").forward(request, response);

                break;

            case "perfilManager":
                request.getRequestDispatcher("ManagerJapyld/perfilManager.jsp").forward(request,response);

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("p") == null ? "crear" : request.getParameter("p");

        ManagerDao managerDao = new ManagerDao();
        switch (action) {
            case "aMetas": //actualizar
                Objetivos objetivos = new Objetivos();

                String objVentasStr = request.getParameter("ventas");
                String objComprasStr = request.getParameter("compras");
                String objUsuariosStr = request.getParameter("usuarios");
                String objFechaStr = request.getParameter("fecha");
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");


                if(objVentasStr.isEmpty() || objComprasStr.isEmpty() || objUsuariosStr.isEmpty() || !esNumero(objVentasStr) || !esNumero(objComprasStr) || !esNumero(objUsuariosStr)) {
                    response.sendRedirect(request.getContextPath() + "/ManagerServlet?a=actualizarObjetivos");
                    return;
                }
                int objVentas;
                int objCompras;
                int objUsuarios;

                try {
                    objVentas = Integer.parseInt(objVentasStr);
                    objCompras = Integer.parseInt(objComprasStr);
                    objUsuarios = Integer.parseInt(objUsuariosStr);
                } catch (NumberFormatException e) {
                    response.sendRedirect(request.getContextPath() + "/ManagerServlet?a=actualizarObjetivos");
                    return;
                }

                try {
                    Date objFecha = dateFormat.parse(objFechaStr);
                    objetivos.setVentasPorMesJuego(objVentas);
                    objetivos.setGastosPorMesJuego(objCompras);
                    objetivos.setUsuarioPorMes(objUsuarios);
                    objetivos.setFecha(objFecha);
                    managerDao.actualizarObjetivos(objetivos);

                    response.sendRedirect(request.getContextPath() + "/ManagerServlet?a=metas");
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }

                break;
        }


    }

    private boolean esNumero(String str){
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }
}
