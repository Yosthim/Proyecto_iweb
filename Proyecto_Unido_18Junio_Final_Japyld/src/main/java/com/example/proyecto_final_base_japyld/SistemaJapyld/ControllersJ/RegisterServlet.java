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
import java.time.ZoneId;
import java.time.format.ResolverStyle;
import java.util.Date;
import java.util.Random;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@WebServlet(name = "RegisterServlet", value = "/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Personas p = (Personas) request.getSession().getAttribute("personaSession");

        if(p != null && p.getIdPersona() != 0){
            if(p.getRol().getIdRoles().equals("USR")){
                response.sendRedirect(request.getContextPath()+"/PaginaPrincipal");
            }else{
                if(p.getRol().getIdRoles().equals("ADM")){
                    response.sendRedirect(request.getContextPath()+"/AdminServlet");
                }else{
                    if(p.getRol().getIdRoles().equals("MNG")){
                        response.sendRedirect(request.getContextPath()+"/ManagerServlet");
                    }
                }
            }
        }else{
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("SistemaJapyld/Register.jsp");
            requestDispatcher.forward(request,response);
        }


    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PersonaDao crearUsuarioDao = new PersonaDao();
        //Inicio de las Validaciones

        //Validacion de Nombre
        String nombre = request.getParameter("Nombre");
        int validacion1 = 0;
        if (nombre.matches("[a-zA-Z].*")) {
            validacion1 = 1;
        }


        //Validacion de Apellido
        String apellido = request.getParameter("Apellido");
        int validacion2 = 0;
        if (apellido.matches("[a-zA-Z].*")){
            validacion2 = 1;
        }

        //Validacion de Genero
        String genero = request.getParameter("Genero");
        int validacion3 = 0;
        if (!genero.equals("")){
            validacion3 = 1;
        }



        //Validacion de Categoria
        String categoria = request.getParameter("Categoria");
        int validacion4 = 0;
        if (!categoria.equals("")){
            validacion4 = 1;
        }


        //Validacion de Correo
        String email = request.getParameter("Email");
        int validacion5 = 0;
        if (email.endsWith("@pucp.edu.pe")) {
            validacion5 = 1;
        }

        //Validacion de Fecha de Nacimiento
        String fechaNacimiento = request.getParameter("Fecha_nacimiento");
        int validacion6 = 0;
        if (fechaNacimiento.matches("^(19[0-9]{2}|20[0-9]{2})-(0[1-9]|1[0-2])-([0-2][1-9]|3[0-1])$")) {
            validacion6 = 1;
        }


        //Validacion de Dni
        String Dni = request.getParameter("Dni");
        int validacion7 = 0;
        if (Dni.matches("\\d{8}")) {
          validacion7 = 1;
        }


        //Validacion de la Contraseña
        String Contrasenia = request.getParameter("Contraseña");
        int validacion8 = 0;

        if (Contrasenia.matches("^(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()])[A-Za-z\\d!@#$%^&*()]{8,}$")) {
            validacion8 = 1;
        }


        //Ahora si comprobamos las validaciones
        if((validacion1==1) && (validacion2==1) && (validacion3==1) && (validacion4==1) && (validacion5==1) && (validacion6==1) && (validacion7 ==1) && (validacion8==1)) {
            Personas persona = parsePersona(request);
            crearUsuarioDao.guardarUsuario(persona);
            response.sendRedirect(request.getContextPath()+ "/LoginServlet");
        }else{
            if (validacion1 != 1){
                response.sendRedirect(request.getContextPath()+"/RegisterServlet?error1");
            }else{
                if (validacion2 != 1){
                    response.sendRedirect(request.getContextPath()+"/RegisterServlet?error2");
                }else{
                    if(validacion3 != 1){
                        response.sendRedirect(request.getContextPath()+"/RegisterServlet?error3");
                    }else{
                        if (validacion4 != 1){
                            response.sendRedirect(request.getContextPath()+"/RegisterServlet?error4");
                        }else{
                            if(validacion5 != 1){
                                response.sendRedirect(request.getContextPath()+"/RegisterServlet?error5");
                            }else{
                                if(validacion6 != 1){
                                    response.sendRedirect(request.getContextPath()+"/RegisterServlet?error6");
                                }
                                else{
                                    if(validacion7 != 1){
                                        response.sendRedirect(request.getContextPath()+"/RegisterServlet?error7");
                                    }
                                    else{
                                        if(validacion8 != 1){
                                            response.sendRedirect(request.getContextPath()+"/RegisterServlet?error8");
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

        }

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

