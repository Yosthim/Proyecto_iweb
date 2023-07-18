package com.example.proyecto_final_base_japyld.ManagerJapyld.ControllersJ;

import com.example.proyecto_final_base_japyld.ManagerJapyld.ModelsJ.DaosJ.DetalleAdminDao;
import com.example.proyecto_final_base_japyld.ManagerJapyld.ModelsJ.DaosJ.ModuloAdminDao;
import com.example.proyecto_final_base_japyld.ManagerJapyld.ModelsJ.DtoJ.DetalleAdmin;
import com.example.proyecto_final_base_japyld.ManagerJapyld.ModelsJ.DtoJ.ModuloAdmin;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;

@WebServlet(name="DetalleAdminServlet", value = "/DetalleAdminServlet")
public class DetalleAdminServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");
        String id = request.getParameter("n");

        DetalleAdminDao perfilAdminDao = new DetalleAdminDao();
        ModuloAdminDao adminModuloDao = new ModuloAdminDao();

        switch (action) {
            case "lista":

                ArrayList<ModuloAdmin> listarAdminActivos = adminModuloDao.listarAdmin();

                double maxResultado = Double.NEGATIVE_INFINITY;


                int idMaxResultado = 0;


                for (ModuloAdmin a : listarAdminActivos) {

                    BigDecimal dineroCompraTotal = a.getDineroCompraTotal();
                    BigDecimal dineroGastoTotal = a.getDineroGastoTotal();

                    if(dineroCompraTotal==null){
                        a.setDineroCompraTotal(BigDecimal.ZERO);
                    }
                    if(dineroGastoTotal==null){
                        a.setDineroGastoTotal(BigDecimal.ZERO);
                    }

                    BigDecimal resta = a.getDineroCompraTotal().subtract(a.getDineroGastoTotal());
                    double resultado = resta.doubleValue();

                    a.setDineroTotal(resta);

                    if (resultado > maxResultado) {
                        maxResultado = resultado;
                        idMaxResultado = a.getId();
                    }
                }
                request.setAttribute("adminActivos",listarAdminActivos);

                request.setAttribute("idTrabajador", idMaxResultado);
                request.setAttribute("a", perfilAdminDao.detallesAdmin(Integer.parseInt(id)));
                request.setAttribute("listaJuegosPropuestosComprados", perfilAdminDao.listarJuegosPropuestosComprados(Integer.parseInt(id)));
                request.setAttribute("listaJuegosPropuestosVendidos", perfilAdminDao.listarJuegosPropuestosVendidos(Integer.parseInt(id)));

                request.getRequestDispatcher("ManagerJapyld/DetalleAdmin.jsp").forward(request, response);
                break;
            case "editar":

                perfilAdminDao.editarAdmin(Integer.parseInt(id));

                int idEntregaMenos =perfilAdminDao.obtenerAdminMenosEntregas();

                perfilAdminDao.cambiarEntregas(Integer.parseInt(id),idEntregaMenos);

                response.sendRedirect(request.getContextPath() + "/ModuloAdminServlet");
                break;

            case "exportarCSV":

                ArrayList<DetalleAdmin> listaComprados = perfilAdminDao.listarJuegosPropuestosComprados(Integer.parseInt(id));
                ArrayList<DetalleAdmin> listaVendidos = perfilAdminDao.listarJuegosPropuestosVendidos(Integer.parseInt(id));
                DetalleAdmin admin = perfilAdminDao.detallesAdmin(Integer.parseInt(id));

                response.setContentType("text/csv");
                response.setHeader("Content-Disposition", "attachment; filename=\"Administrador.csv\"");

                PrintWriter writer = response.getWriter();


                writer.println("Información del Administrador");
                writer.println("Id:;" + admin.getId());
                writer.println("Nombre:;" + admin.getNombre());
                writer.println("Apellido:;" + admin.getApellido());
                writer.println("Correo:;" + admin.getCorreo());
                writer.println("Nacimiento:;" + admin.getFechaDeNacimiento());
                writer.println("Dni:;" + admin.getDni());
                writer.println("Genero:;" + admin.getGenero());
                writer.println("Categoria Preferida:;" + admin.getCategoriaJuegoPreferida());
                writer.println("Registro:;" + admin.getFechaRegistro());
                writer.println("Estado:;" + admin.getEstado());
                writer.println();


                writer.println("Juegos Propuestos Comprados");
                writer.println("NombreJuego;PrecioCompra");
                for (DetalleAdmin juego1 : listaComprados) {
                    writer.println(juego1.getNombreJuegos() + ";" + juego1.getPrecioCompra());
                }
                writer.println();

                writer.println("Juegos Propuestos Vendidos");
                writer.println("NombreJuego;PrecioVenta");
                for (DetalleAdmin juego2 : listaVendidos) {
                    writer.println(juego2.getNombreJuegos() + ";" + juego2.getPrecioVenta());
                }
                writer.println();

                writer.println("Ganancia Total");
                writer.println("Ganancia");

                ArrayList<ModuloAdmin> Administradores = adminModuloDao.listarAdmin();

                for (ModuloAdmin u : Administradores) {

                    BigDecimal dineroCompraTotal = u.getDineroCompraTotal();
                    BigDecimal dineroGastoTotal = u.getDineroGastoTotal();

                    if(dineroCompraTotal==null){
                        u.setDineroCompraTotal(BigDecimal.ZERO);
                    }
                    if(dineroGastoTotal==null){
                        u.setDineroGastoTotal(BigDecimal.ZERO);
                    }

                    BigDecimal resta = u.getDineroCompraTotal().subtract(u.getDineroGastoTotal());

                    if(Integer.parseInt(id)==u.getId()){
                        u.setDineroTotal(resta);
                        writer.println(u.getDineroTotal());
                    }
                }

                writer.close();
                break;
        }
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
    }
}
