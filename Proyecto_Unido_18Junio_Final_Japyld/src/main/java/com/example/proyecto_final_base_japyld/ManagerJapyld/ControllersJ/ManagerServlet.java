package com.example.proyecto_final_base_japyld.ManagerJapyld.ControllersJ;

import com.example.proyecto_final_base_japyld.BeansGenerales.Objetivos;
import com.example.proyecto_final_base_japyld.ManagerJapyld.ModelsJ.DaosJ.EstadisticasDao;
import com.example.proyecto_final_base_japyld.ManagerJapyld.ModelsJ.DaosJ.JuegosManagerDao;
import com.example.proyecto_final_base_japyld.ManagerJapyld.ModelsJ.DaosJ.ManagerDao;
import com.example.proyecto_final_base_japyld.ManagerJapyld.ModelsJ.DaosJ.ProductosVendidosDao;
import com.example.proyecto_final_base_japyld.UsuarioJapyld.ModelsJ.DaosJ.PerfilDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "ManagerServlet", value = "/ManagerServlet")
public class ManagerServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ManagerDao managerDao = new ManagerDao();
        JuegosManagerDao juegosManagerDao = new JuegosManagerDao();
        ProductosVendidosDao productosVendidosDao = new ProductosVendidosDao();
        EstadisticasDao estadisticasDao = new EstadisticasDao();

        String action = request.getParameter("a") == null ? "resumen" :request.getParameter("a");

        switch (action){
            case "resumen":
                request.setAttribute("juegosRecienComentados", managerDao.resumenComentarios());
                request.setAttribute("catalogoReciente", managerDao.catologoReciente());
                request.getRequestDispatcher("ManagerJapyld/PaginaPrincipalManager.jsp").forward(request,response);
                break;

            case "productos":
                request.getRequestDispatcher("ManagerJapyld/PagPrin_Productos.jsp").forward(request, response);
                break;

            case "metas":
                request.setAttribute("objetivos", managerDao.Objetivos());
                request.setAttribute("objetivosPasados", managerDao.listarObjetivosPasados());
                request.getRequestDispatcher("ManagerJapyld/PagPrin_Metas.jsp").forward(request, response);
                break;

            case "estadisticasVentas":

                request.getRequestDispatcher("ManagerJapyld/PagPrin_EstadisticasVentas.jsp").forward(request, response);
                break;

            case "estadisticasCompras":
                request.getRequestDispatcher("ManagerJapyld/PagPrin_EstadisticasCompras.jsp").forward(request, response);
                break;
            case "estadisticasIngresos":
                request.getRequestDispatcher("ManagerJapyld/PagPrin_EstadisticasIngresos.jsp").forward(request, response);
                break;
            case "estadisticasUsuarios":
                request.getRequestDispatcher("ManagerJapyld/PagPrin_EstadisticasUsuarios.jsp").forward(request, response);
                break;

            case "actualizarObjetivos":

                request.getRequestDispatcher("ManagerJapyld/PagPrin_ActualizarMetas.jsp").forward(request, response);

                break;

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
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                Timestamp objFecha = Timestamp.valueOf(objFechaStr);

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

                objetivos.setVentasPorMesJuego(objVentas);
                objetivos.setGastosPorMesJuego(objCompras);
                objetivos.setUsuarioPorMes(objUsuarios);
                objetivos.setFecha(objFecha);
                managerDao.actualizarObjetivos(objetivos);

                response.sendRedirect(request.getContextPath() + "/ManagerServlet?a=metas");

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
