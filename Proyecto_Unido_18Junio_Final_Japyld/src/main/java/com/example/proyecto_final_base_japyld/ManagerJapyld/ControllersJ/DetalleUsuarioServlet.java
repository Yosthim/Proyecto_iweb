package com.example.proyecto_final_base_japyld.ManagerJapyld.ControllersJ;

import com.example.proyecto_final_base_japyld.ManagerJapyld.ModelsJ.DaosJ.DetalleUsuarioDao;
import com.example.proyecto_final_base_japyld.ManagerJapyld.ModelsJ.DaosJ.ModuloAdminDao;
import com.example.proyecto_final_base_japyld.ManagerJapyld.ModelsJ.DtoJ.DetalleUsuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name="DetalleUsuarioServlet", value = "/DetalleUsuarioServlet")

public class DetalleUsuarioServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");
        DetalleUsuarioDao perfilUsuarioDao = new DetalleUsuarioDao();
        ModuloAdminDao adminModuloDao = new ModuloAdminDao();

        String id = request.getParameter("nm");

        switch (action) {
            case "lista":

                request.setAttribute("e", perfilUsuarioDao.detallesUsuario(Integer.parseInt(id)));
                request.setAttribute("listaJuegosVendidos", perfilUsuarioDao.listarJuegosVendidos(Integer.parseInt(id)));
                request.setAttribute("listaJuegosComprados", perfilUsuarioDao.listarJuegosComprados( Integer.parseInt(id)));

                request.getRequestDispatcher("ManagerJapyld/DetalleUsuario.jsp").forward(request, response);
                break;
            case "editar":

                perfilUsuarioDao.editarUser(Integer.parseInt(id));
                response.sendRedirect(request.getContextPath() + "/ModuloUsuarioServlet");
                break;

            case "noban":
                String idStr = request.getParameter("idUser");
                int id2 = Integer.parseInt(idStr);
                perfilUsuarioDao.desbanearUser(id2);
                response.sendRedirect(request.getContextPath() + "/ModuloUsuarioServlet");
                break;

            case "exportarCSV":

                ArrayList<DetalleUsuario> listaJuegosComprados = perfilUsuarioDao.listarJuegosComprados(Integer.parseInt(id));
                ArrayList<DetalleUsuario> listaJuegosVendidos = perfilUsuarioDao.listarJuegosVendidos(Integer.parseInt(id));
                DetalleUsuario usuario = perfilUsuarioDao.detallesUsuario(Integer.parseInt(id));

                response.setContentType("text/csv");
                response.setHeader("Content-Disposition", "attachment; filename=\"informacion_usuario.csv\"");

                PrintWriter writer = response.getWriter();


                writer.println("Información del Usuario");
                writer.println("Id:," + usuario.getId());
                writer.println("Nombre:," + usuario.getNombre());
                writer.println("Apellido:," + usuario.getApellido());
                writer.println("Correo:," + usuario.getCorreo());
                writer.println("Nacimiento:," + usuario.getFechaDeNacimiento());
                writer.println("Dni:," + usuario.getDni());
                writer.println("Genero:," + usuario.getGenero());
                writer.println("Categoria Preferida:," + usuario.getCategoriaJuegoPreferida());
                writer.println("Registro:," + usuario.getFechaRegistro());
                writer.println("Estado:," + usuario.getEstado());
                writer.println();


                writer.println("Juegos Comprados");
                writer.println("NombreJuego,PrecioCompra");
                for (DetalleUsuario juego1 : listaJuegosComprados) {
                    writer.println(juego1.getNombreJuegos() + "," + juego1.getPrecio());
                }
                writer.println();

                writer.println("Juegos Vendidos");
                writer.println("NombreJuego,PrecioVenta");
                for (DetalleUsuario juego2 : listaJuegosVendidos) {
                    writer.println(juego2.getNombreJuegos() + "," + juego2.getPrecio_admin());
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
