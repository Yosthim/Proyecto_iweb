package com.example.proyecto_final_base_japyld.UsuarioJapyld.ControllersJ;

import com.example.proyecto_final_base_japyld.BeansGenerales.*;
import com.example.proyecto_final_base_japyld.UsuarioJapyld.ModelsJ.DaosJ.ComentariosDao;
import com.example.proyecto_final_base_japyld.UsuarioJapyld.ModelsJ.DaosJ.MasDetallesDao;

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
import java.util.Date;
import java.util.Random;

@WebServlet(name = "MasDetallesServlet", value = {"/MasDetallesJuego"})
public class MasDetallesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ComentariosDao comentariosDao = new ComentariosDao();
        MasDetallesDao masdetallesdao = new MasDetallesDao();

        int idjuego = Integer.parseInt(request.getParameter("idjuego"));

        request.setAttribute("listaMasDetallesJuego", masdetallesdao.listarMasDetallesJuego(idjuego));
        request.setAttribute("listaComentarios",comentariosDao.listarComentarios(idjuego));
        request.setAttribute("listaConsolaPorJuego",masdetallesdao.listarConsolasPorJuego(idjuego));

        RequestDispatcher view = request.getRequestDispatcher("UsuarioJapyld/MasDetallesJuego.jsp");
        view.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ComentariosDao comentarioDao = new ComentariosDao();
        Comentarios comentario = parseComentario(request);

        comentarioDao.guardarComentario(comentario);

        int idjuego = Integer.parseInt(request.getParameter("idjuego"));
        response.sendRedirect(request.getContextPath()+ "/MasDetallesJuego?idjuego=" + idjuego);



    }

    public Comentarios parseComentario(HttpServletRequest request){

        Comentarios comentarioCreado = new Comentarios();

        String comentario = request.getParameter("Comentario");
        String juegoId = request.getParameter("idjuego");
        String idpersona = request.getParameter("idpersona");

        Date fechaComentario = new Date();
        DateFormat fechaFormato = new SimpleDateFormat("yyyy-MM-dd");
        String fechaFormateada = fechaFormato.format(fechaComentario);

        try {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            try{
                int juegoId1 = Integer.parseInt(juegoId);
                int idpersona1 = Integer.parseInt(idpersona);

                Date fecha_comentario1 = formato.parse(fechaFormateada);

                comentarioCreado.setComentario(comentario);

                comentarioCreado.setFecha_comentario1(fecha_comentario1);

                Juegos juego = new Juegos();
                juego.setIdJuegos(juegoId1);
                comentarioCreado.setJuegoComentario(juego);

                Personas persona = new Personas();
                persona.setIdPersona(idpersona1);
                comentarioCreado.setPersonaComentario(persona);



                return comentarioCreado;
            }catch (ParseException p){

            }
            return comentarioCreado;
        } catch (NumberFormatException e) {

        }
        return comentarioCreado;
    }
}
