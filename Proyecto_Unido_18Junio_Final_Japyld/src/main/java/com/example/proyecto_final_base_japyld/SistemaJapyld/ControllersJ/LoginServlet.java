package com.example.proyecto_final_base_japyld.SistemaJapyld.ControllersJ;


import com.example.proyecto_final_base_japyld.AdministradorJapyld.ModelsJ.DaosJ.DescuentoDao;
import com.example.proyecto_final_base_japyld.BeansGenerales.Personas;
import com.example.proyecto_final_base_japyld.SistemaJapyld.ModelsJ.DaosJ.CorreoDao;
import com.example.proyecto_final_base_japyld.SistemaJapyld.ModelsJ.DaosJ.PersonaDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Objects;

@WebServlet(name = "LoginServlet", value = {"","/LoginServlet"})
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action") == null ?
                "loginform" : request.getParameter("action");


        RequestDispatcher view;
        switch(action){
            case "loginform":

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
                    view = request.getRequestDispatcher("SistemaJapyld/Login.jsp");
                    view.forward(request,response);
                }
                break;

            case "logout":
                HttpSession session = request.getSession();
                session.invalidate();
                response.sendRedirect(request.getContextPath()+"/LoginServlet");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PersonaDao personadao = new PersonaDao();

        DescuentoDao descuentoDao = new DescuentoDao();

        String username = request.getParameter("inputEmail");
        String password = request.getParameter("inputPassword");
        Personas persona= personadao.validarUsuarioPassword(username,password);

        if(persona!=null) {
            String estado = persona.getEstado();
            String rol = persona.getRol().getIdRoles();
            if(!estado.equals("Baneado") && !estado.equals("Despedido")){

                HttpSession session = request.getSession();
                session.setAttribute("personaSession",persona);
                session.setMaxInactiveInterval(30*60);
                if (rol.equals("USR")){
                    response.sendRedirect(request.getContextPath()+"/PaginaPrincipal");
                    descuentoDao.actualizarEstadoJuego();
                }
                if (rol.equals("ADM")){
                    response.sendRedirect(request.getContextPath()+"/AdminServlet");
                    descuentoDao.actualizarEstadoJuego();
                }
                if (rol.equals("MNG")){
                    response.sendRedirect(request.getContextPath()+"/ManagerServlet");
                    descuentoDao.actualizarEstadoJuego();
                }
            }else{
                if(rol.equals("USR")){
                    response.sendRedirect(request.getContextPath() + "/LoginServlet?errorUSR");
                }
                if(rol.equals("ADM")){
                    response.sendRedirect(request.getContextPath() + "/LoginServlet?errorADM");
                }

            }
        }else{
            response.sendRedirect(request.getContextPath() + "/LoginServlet?error");
        }
    }
}




