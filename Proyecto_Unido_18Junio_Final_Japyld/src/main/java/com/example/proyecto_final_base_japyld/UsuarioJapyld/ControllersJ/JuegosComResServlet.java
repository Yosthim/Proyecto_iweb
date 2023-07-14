package com.example.proyecto_final_base_japyld.UsuarioJapyld.ControllersJ;

import com.example.proyecto_final_base_japyld.BeansGenerales.Personas;
import com.example.proyecto_final_base_japyld.UsuarioJapyld.ModelsJ.DaosJ.TusJuegosDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "JuegosComResServlet", value = "/TusJuegos")
public class JuegosComResServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        RequestDispatcher view;

        TusJuegosDao tusJuegosDao = new TusJuegosDao();
        Personas usuario = (Personas) request.getSession().getAttribute("personaSession");

        String categoria = tusJuegosDao.categoriaMasComprada(usuario.getIdPersona());
        //En caso el usuario no haya comprado retornará nulo, entonces obtenemos su categoria preferida (pedida en el registro)
        if (categoria == null) {
            categoria = tusJuegosDao.categoriaPreferida(usuario.getIdPersona());
        }

        //Asignamos los valores correspondientes como atributos de la página
        request.setAttribute("categoriaPreferida", categoria);
        request.setAttribute("listaJuegos", tusJuegosDao.listarJuegos(usuario.getIdPersona()));
        request.setAttribute("juegosRecomendados", tusJuegosDao.listarJuegosRecomendados(categoria));
        view = request.getRequestDispatcher("UsuarioJapyld/TusJuegos.jsp");
        view.forward(request, response);
    }
}
