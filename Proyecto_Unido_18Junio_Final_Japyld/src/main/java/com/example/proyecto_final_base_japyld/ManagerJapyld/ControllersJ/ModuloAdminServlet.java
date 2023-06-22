package com.example.proyecto_final_base_japyld.ManagerJapyld.ControllersJ;
import com.example.proyecto_final_base_japyld.ManagerJapyld.ModelsJ.DaosJ.DetalleAdminDao;
import com.example.proyecto_final_base_japyld.ManagerJapyld.ModelsJ.DtoJ.ModuloAdmin;
import com.example.proyecto_final_base_japyld.ManagerJapyld.ModelsJ.DtoJ.DetalleAdmin;
import com.example.proyecto_final_base_japyld.ManagerJapyld.ModelsJ.DaosJ.ModuloAdminDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

@WebServlet(name = "ModuloAdminServlet", value = "/ModuloAdminServlet")
public class ModuloAdminServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {

        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");
        RequestDispatcher requestDispatcher;
        ModuloAdminDao adminModuloDao = new ModuloAdminDao();


        switch (action) {
            case "lista":
                request.setAttribute("listarAdminActivos", adminModuloDao.listarAdmin());
                request.setAttribute("listarAdminInactivo", adminModuloDao.listarAdminInactivos());

                requestDispatcher = request.getRequestDispatcher("ManagerJapyld/ModuloAdmin.jsp");
                requestDispatcher.forward(request,response);
                break;
            case "crear":
                requestDispatcher = request.getRequestDispatcher("ManagerJapyld/crearAdmin.jsp");
                requestDispatcher.forward(request, response);
                break;
        }

    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {

        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");
        RequestDispatcher requestDispatcher;
        ModuloAdmin admin = new ModuloAdmin();
        ModuloAdminDao adminModuloDao = new ModuloAdminDao();

        switch (action) {
            case "guardar":

                admin.setNombre(request.getParameter("nombre"));
                admin.setApellido(request.getParameter("apellido"));
                admin.setCorreo(request.getParameter("correo"));
                admin.setContrasenia(request.getParameter("contrasenia"));

                admin.setEstado("Activo");
                admin.setFechaDeNacimiento(Date.valueOf("2002-05-15"));
                admin.setGenero("Por Definir");
                admin.setDni(11111111);
                admin.setId_rol("ADM");
                admin.setImagen("Por Definir");
                admin.setTipo("Perfil");
                admin.setCategoriaJuegoPreferida("Por Definir");
                admin.setFechaRegistro(new Date(System.currentTimeMillis()));
                int contador=adminModuloDao.contarImagenes();
                admin.setIdImagen(contador);
                int contador2=adminModuloDao.contarPersonas();
                admin.setId(contador2);

                int centinela=0;
                ArrayList<ModuloAdmin> comparar= adminModuloDao.listarAdmin();
                for (ModuloAdmin admi1: comparar){
                    if (admi1.getCorreo().equals(request.getParameter("correo"))){
                        centinela=1;
                    }
                }
                if (centinela==1){
                    response.sendRedirect(request.getContextPath()+"/ModuloAdminServlet?action=crear");
                }else {
                    adminModuloDao.crearAdmin(admin);
                    response.sendRedirect(request.getContextPath()+"/ModuloAdminServlet");
                }
                break;

        }



    }
}
