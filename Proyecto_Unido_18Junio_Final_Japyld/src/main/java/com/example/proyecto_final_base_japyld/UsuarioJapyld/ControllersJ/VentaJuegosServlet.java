package com.example.proyecto_final_base_japyld.UsuarioJapyld.ControllersJ;

import com.example.proyecto_final_base_japyld.BeansGenerales.Consola;
import com.example.proyecto_final_base_japyld.BeansGenerales.Personas;
import com.example.proyecto_final_base_japyld.BeansGenerales.VentaJuegosGeneral;
import com.example.proyecto_final_base_japyld.UsuarioJapyld.ModelsJ.DaosJ.VentaJuegosDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.math.BigDecimal;

@WebServlet(name = "VentaJuegosServlet", value = "/TusVentas")
public class VentaJuegosServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        RequestDispatcher view;

        String action = request.getParameter("act") == null ? "listar" : request.getParameter("act");
        VentaJuegosDao ventaJuegosDao = new VentaJuegosDao();

        switch (action) {
            case "listar":
                view = request.getRequestDispatcher("UsuarioJapyld/TusVentas.jsp");
                view.forward(request, response);
                break;
            case "exist":
                request.setAttribute("categorias", ventaJuegosDao.listarCategorias());
                request.setAttribute("listaJuegos", ventaJuegosDao.listarNombreJuegos());
                request.setAttribute("consolas", ventaJuegosDao.listarConsolas());
                view = request.getRequestDispatcher("UsuarioJapyld/JuegoExistente.jsp");
                view.forward(request, response);
                break;
            case "new":
                request.setAttribute("categorias", ventaJuegosDao.listarCategorias());
                request.setAttribute("consolas", ventaJuegosDao.listarConsolas());
                view = request.getRequestDispatcher("UsuarioJapyld/JuegoNuevo.jsp");
                view.forward(request, response);
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("act");

        VentaJuegosGeneral ofertaJuego = setOferta(request);

    }

    public VentaJuegosGeneral setOferta(HttpServletRequest request) {
        VentaJuegosGeneral ofertaJuego = new VentaJuegosGeneral();
        Personas admin = new Personas();
        admin.setIdPersona(1);
        ofertaJuego.setAdministrador(admin);

        ofertaJuego.setNombreNuevo(request.getParameter("nombreJuego").trim());
        ofertaJuego.setDescripcionNueva(request.getParameter("descripcion").trim());

        Consola consola = new Consola();
        consola.setIdConsola(request.getParameter("idConsola"));
        ofertaJuego.setConsola(consola);

        ofertaJuego.setPrecioUsuario(new BigDecimal(request.getParameter("precio")));
        ofertaJuego.setCantidad(Integer.parseInt(request.getParameter("stock")));

        return ofertaJuego;
    }
}
