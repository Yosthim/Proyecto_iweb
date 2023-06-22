package com.example.proyecto_final_base_japyld.SistemaJapyld.ControllersJ;


import com.example.proyecto_final_base_japyld.BeansGenerales.Personas;
import com.example.proyecto_final_base_japyld.SistemaJapyld.ModelsJ.DaosJ.PersonaDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "LoginServlet", value = {"","/LoginServlet"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action") == null ?
                "loginform" : request.getParameter("action");


        RequestDispatcher view;
        switch(action){
            case "loginform":
                view = request.getRequestDispatcher("SistemaJapyld/Login.jsp");
                view.forward(request,response);
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

        String username = request.getParameter("inputEmail");
        String password = request.getParameter("inputPassword");

        Personas persona= personadao.validarUsuarioPassword(username,password);

        if(persona != null){
            HttpSession session = request.getSession();
            session.setAttribute("personaSession",persona);
            session.setMaxInactiveInterval(5*60);
            String rol = persona.getRol().getIdRoles();

            if (rol.equals("USR")){
                response.sendRedirect(request.getContextPath()+"/PaginaPrincipal");
            }
            if (rol.equals("ADM")){
                response.sendRedirect(request.getContextPath()+"/AdminServlet");
            }
            if (rol.equals("MNG")){
                response.sendRedirect(request.getContextPath()+"/ManagerServlet");
            }
        }else{
            response.sendRedirect(request.getContextPath() + "/LoginServlet?error");
        }

    }
}

