package com.example.proyecto_final_base_japyld.AdministradorJapyld.ControllersJ;

import com.example.proyecto_final_base_japyld.AdministradorJapyld.ModelsJ.DaosJ.AgregarDao;
import com.example.proyecto_final_base_japyld.BeansGenerales.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;

@WebServlet(name = "AgregarJuegoServlet", value = "/AgregarJuegoServlet")
@MultipartConfig
public class AgregarJuegoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        RequestDispatcher view;

        String action = request.getParameter("act") == null ? "listar" : request.getParameter("act");
        AgregarDao agregarDao = new AgregarDao();

        switch (action){
            case "agregar":

                request.setAttribute("categorias",agregarDao.listarCategorias());
                request.setAttribute("consolas",agregarDao.listarConsolas());
                view = request.getRequestDispatcher("AdministradorJapyld/NuevoJuego.jsp");
                view.forward(request, response);
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        AgregarDao agregarDao = new AgregarDao();
        String action = request.getParameter("act");

        System.out.println("antes de llemar el seteo del juego ");
        System.out.println(request.getParameter("nombreJuego"));
        Juegos juegos = setJuego(request);

        System.out.println("antes de llemar el seteo de consola ");
        System.out.println(request.getParameter("idConsola"));
        JuegosXConsola juegosXConsola = setConsola(request);

        switch (action){
            case "new":
                Part imageGamePart = request.getPart("imagenJuego");
                InputStream imageGameContent = imageGamePart.getInputStream();
                juegos.getImagen().setImagem(imageGameContent);
                agregarDao.registrarJuego(juegos);
                agregarDao.registrarJuegoXCategoria(juegosXConsola);
                break;
        }
        response.sendRedirect(request.getContextPath() + "/AdminServlet?action=listasPaginaVideojuegos");
    }



    public Juegos setJuego(HttpServletRequest request) {

        Juegos juegos = new Juegos();

        System.out.println("empieza a cetear ");
        System.out.println(request.getParameter("nombreJuego"));

        juegos.setNombreJuegos(request.getParameter("nombreJuego"));
        juegos.setPrecio(new BigDecimal(request.getParameter("precio")));
        juegos.setDescripcion(request.getParameter("descripcion").trim());

        Categoria categoria = new Categoria();
        categoria.setIdCategorias(request.getParameter("idCategoria"));
        juegos.setCategoria(categoria);

        Imagen imagen = new Imagen();
        juegos.setImagen(imagen);

        return juegos;
    }
    public JuegosXConsola setConsola(HttpServletRequest request){

        JuegosXConsola juegosXConsola = new JuegosXConsola();

        System.out.println("empieza a cetear consola ");
        System.out.println(request.getParameter("idConsola"));

        Consola consola = new Consola();
        consola.setIdConsola(request.getParameter("idConsola"));

        juegosXConsola.setConsolaDeJuego(consola);
        return juegosXConsola;
    }

}
