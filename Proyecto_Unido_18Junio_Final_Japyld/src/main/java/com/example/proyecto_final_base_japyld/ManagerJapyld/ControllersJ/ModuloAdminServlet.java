package com.example.proyecto_final_base_japyld.ManagerJapyld.ControllersJ;

import com.example.proyecto_final_base_japyld.BeansGenerales.Categoria;
import com.example.proyecto_final_base_japyld.ManagerJapyld.ModelsJ.DaosJ.ModuloAdminDao;
import com.example.proyecto_final_base_japyld.ManagerJapyld.ModelsJ.DtoJ.CategoriasAdmin;
import com.example.proyecto_final_base_japyld.ManagerJapyld.ModelsJ.DtoJ.ImagenesAdmin;
import com.example.proyecto_final_base_japyld.ManagerJapyld.ModelsJ.DtoJ.ModuloAdmin;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Random;

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

                ArrayList<ModuloAdmin> listarAdminActivos = adminModuloDao.listarAdmin();
                ArrayList<Integer> idsConResultadoMenorCero = new ArrayList<>();

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

                    if (resultado > maxResultado) {
                        maxResultado = resultado;
                        idMaxResultado = a.getId();
                    }

                    if (resultado < 0) {
                        idsConResultadoMenorCero.add(a.getId());
                    }
                }

                request.setAttribute("idMax", idMaxResultado);
                request.setAttribute("listarAdminActivos", adminModuloDao.listarAdmin());
                request.setAttribute("listarAdminInactivo", adminModuloDao.listarAdminInactivos());
                request.setAttribute("perdidas", idsConResultadoMenorCero);

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

                ArrayList<ImagenesAdmin> perfiles= adminModuloDao.listaImagenes();
                ArrayList<CategoriasAdmin> categoriaPreferidas = adminModuloDao.listaCategorias();

                Random random = new Random();
                int indiceCategoria = random.nextInt(categoriaPreferidas.size());
                int indiceAleatorio = random.nextInt(perfiles.size());

                ImagenesAdmin perfilAleatorio = perfiles.get(indiceAleatorio);
                CategoriasAdmin categoriaAleatorio = categoriaPreferidas.get(indiceCategoria);

                admin.setNombre(request.getParameter("nombre"));
                admin.setApellido(request.getParameter("apellido"));
                admin.setCorreo(request.getParameter("correo"));
                admin.setContrasenia(request.getParameter("contrasenia"));

                int dniAleatorio;
                boolean dniUnico = false;
                while (!dniUnico) {
                    dniAleatorio = generarNumeroAleatorio8Digitos();
                    dniUnico = esDniUnico(dniAleatorio, adminModuloDao.listarAdmin());
                    if (dniUnico) {
                        admin.setDni(dniAleatorio);
                    }
                }

                admin.setEstado("Activo");
                admin.setFechaDeNacimiento(Date.valueOf("2002-05-15"));
                String genero = determinarGeneroPorNombre(request.getParameter("nombre"));
                admin.setGenero(genero);
                admin.setId_rol("ADM");
                admin.setImagen(perfilAleatorio.getDireccionArchivo());
                admin.setTipo("Predeterminado");
                admin.setCategoriaJuegoPreferida(categoriaAleatorio.getNombreCategoria());
                admin.setFechaRegistro(new Date(System.currentTimeMillis()));
                int contador=adminModuloDao.contarImagenes();
                admin.setIdImagen(contador);
                int contador2=adminModuloDao.contarPersonas();
                admin.setId(contador2);

                int centinela2=0;
                int centinela=0;

                ArrayList<ModuloAdmin> comparar= adminModuloDao.listarAdmin();
                for (ModuloAdmin admi1: comparar){
                    if (admi1.getCorreo().equals(request.getParameter("correo"))){
                        centinela=1;
                        break;
                    }
                    String valor = admi1.getNombre()+admi1.getApellido();
                    String valor2 = request.getParameter("nombre")+request.getParameter("apellido");
                    if (valor.equals(valor2)){
                        centinela2=1;
                        break;
                    }
                }

                if (centinela==1 || centinela2 ==1){
                    response.sendRedirect(request.getContextPath()+"/ModuloAdminServlet?action=crear");
                }else {
                    adminModuloDao.crearAdmin(admin);
                    response.sendRedirect(request.getContextPath()+"/ModuloAdminServlet");
                }
                break;
        }
    }

    public boolean esDniUnico(int dniGenerado, ArrayList<ModuloAdmin> administradores) {
        for (ModuloAdmin admin : administradores) {
            if (admin.getDni() == dniGenerado) {
                return false; // El DNI ya existe, no es único
            }
        }
        return true; // El DNI es único
    }

    public int generarNumeroAleatorio8Digitos() {
        return new Random().nextInt(90000000) + 10000000;
    }

    public static String determinarGeneroPorNombre(String nombre) {
        String[] sufijosFemeninos = {"a", "ia", "ana", "ina", "ela", "ella", "ara", "ita", "ena", "la", "ra", "ma", "na", "ta"};
        String sufijoNombre = nombre.toLowerCase().substring(nombre.length() - 1);

        for (String sufijo : sufijosFemeninos) {
            if (sufijoNombre.equals(sufijo)) {
                return "Femenino";
            }
        }

        return "Masculino";
    }
}
