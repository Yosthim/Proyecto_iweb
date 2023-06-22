package com.example.proyecto_final_base_japyld.SistemaJapyld.ControllersJ;


import java.lang.*;

import com.example.proyecto_final_base_japyld.BeansGenerales.Imagen;
import com.example.proyecto_final_base_japyld.BeansGenerales.Personas;
import com.example.proyecto_final_base_japyld.BeansGenerales.Roles;
import com.example.proyecto_final_base_japyld.SistemaJapyld.ModelsJ.DaosJ.PersonaDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Random;

@WebServlet(name = "RegisterServlet", value = "/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("SistemaJapyld/Register.jsp");
        requestDispatcher.forward(request,response);

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PersonaDao crearUsuarioDao = new PersonaDao();

        Personas persona = parsePersona(request);
        crearUsuarioDao.guardarUsuario(persona);
        response.sendRedirect(request.getContextPath()+ "/LoginServlet");


    }

    public Personas parsePersona(HttpServletRequest request) {

        Personas persona = new Personas();


        String nombre = request.getParameter("Nombre");
        String apellido = request.getParameter("Apellido");
        String genero = request.getParameter("Genero");
        String categoria = request.getParameter("Categoria");
        String email = request.getParameter("Email");
        String fecha_nacimiento1 = request.getParameter("Fecha_nacimiento");
        String contrasena = request.getParameter("Contraseña");
        String Dni1 = request.getParameter("Dni");
        String estado = "Activo";
        String id_roles = "USR";
        Random random = new Random();
        int perfil_img = random.nextInt(8) + 1;

        Date fechaRegistro = new Date();

        DateFormat fechaFormato = new SimpleDateFormat("yyyy-MM-dd");

        String fechaFormateada = fechaFormato.format(fechaRegistro);

        try {
            int Dni = Integer.parseInt(Dni1);
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            try{
                Date fecha_nacimientoDate = formato.parse(fecha_nacimiento1);
                Date fechaRegistroDate = formato.parse(fechaFormateada);
                persona.setNombre(nombre);
                persona.setApellido(apellido);
                persona.setGenero(genero);
                persona.setCategoriaJuegoPreferida(categoria);
                persona.setCorreo(email);
                persona.setFechaDeNacimiento(fecha_nacimientoDate);
                persona.setContrasenia(contrasena);
                persona.setDni(Dni);
                persona.setEstado(estado);

                Roles rol = new Roles();
                rol.setIdRoles(id_roles);
                persona.setRol(rol);

                Imagen imagenes = new Imagen();
                imagenes.setIdImagenes(perfil_img);
                persona.setImagen(imagenes);

                persona.setFechaRegistro(fechaRegistroDate);
                return persona;
            }catch (ParseException p){

            }
        return persona;
        } catch (NumberFormatException e) {

        }
        return persona;
    }
}

