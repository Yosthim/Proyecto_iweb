package com.example.proyecto_final_base_japyld.UsuarioJapyld.ControllersJ;

import com.example.proyecto_final_base_japyld.BeansGenerales.*;
import com.example.proyecto_final_base_japyld.UsuarioJapyld.ModelsJ.DaosJ.VentaJuegosDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;

@WebServlet(name = "VentaJuegosServlet", value = "/TusVentas")
@MultipartConfig
public class VentaJuegosServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        RequestDispatcher view;

        String action = request.getParameter("act") == null ? "listar" : request.getParameter("act");
        VentaJuegosDao ventaJuegosDao = new VentaJuegosDao();

        switch (action) {
            case "listar":
                Personas usuario = (Personas) request.getSession().getAttribute("personaSession");
                request.setAttribute("listaOfertas", ventaJuegosDao.listarOfertas(usuario.getIdPersona()));
                view = request.getRequestDispatcher("UsuarioJapyld/TusVentas.jsp");
                view.forward(request, response);
                break;
            case "exist":
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
            case "change":
                try {
                    Integer idOferta = Integer.parseInt(request.getParameter("id"));
                    BigDecimal precioAdmin = new BigDecimal(request.getParameter("p"));
                    request.setAttribute("idOferta", idOferta);
                    request.setAttribute("precioAdmin", precioAdmin);
                    view = request.getRequestDispatcher("UsuarioJapyld/CambiarPrecio.jsp");
                    view.forward(request, response);
                }catch (Exception e) {
                    request.getSession().setAttribute("error", "Ocurrió un error. Intente otra vez");
                    response.sendRedirect(request.getContextPath() + "/TusVentas");
                }
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        VentaJuegosDao ventaJuegosDao = new VentaJuegosDao();
        String action = request.getParameter("act");

        VentaJuegosGeneral ofertaJuego;
        int idVenta;

        try {
            switch (action) {
                case "new":
                    ofertaJuego = setOferta(request, action);
                    Part imageGamePart = request.getPart("imagenJuego");
                    InputStream imageGameContent = imageGamePart.getInputStream();
                    ofertaJuego.setImagenNueva(imageGameContent);
                    ventaJuegosDao.registrarOferta(ofertaJuego, "nuevo");
                    break;
                case "exist":
                    ofertaJuego = setOferta(request, action);
                    ventaJuegosDao.registrarOferta(ofertaJuego, "existente");
                    break;
                case "change":
                    idVenta = Integer.parseInt(request.getParameter("id"));
                    BigDecimal precioNuevo = new BigDecimal(request.getParameter("precioNuevo"));
                    ventaJuegosDao.changeOfferPrice(idVenta, precioNuevo);
                    break;
                case "retire":
                    idVenta = Integer.parseInt(request.getParameter("id"));
                    ventaJuegosDao.retireOffer(idVenta);
                    break;
                case "delete":
                    idVenta = Integer.parseInt(request.getParameter("id"));
                    ventaJuegosDao.deleteOffer(idVenta);
                    break;
            }
        } catch (Exception e) {
            request.getSession().setAttribute("error", "Ocurrió un error. Intente otra vez");
        }
        response.sendRedirect(request.getContextPath() + "/TusVentas");
    }

    public VentaJuegosGeneral setOferta(HttpServletRequest request, String action) {
        VentaJuegosGeneral ofertaJuego = new VentaJuegosGeneral();
        VentaJuegosDao ventaJuegosDao = new VentaJuegosDao();
        Personas admin = new Personas();
        Personas usuario = (Personas) request.getSession().getAttribute("personaSession");
        admin.setIdPersona(ventaJuegosDao.getRandomIdAdmin());
        ofertaJuego.setAdministrador(admin);
        ofertaJuego.setUsuario(usuario);

        Consola consola = new Consola();
        consola.setIdConsola(request.getParameter("idConsola"));
        ofertaJuego.setConsola(consola);

        ofertaJuego.setPrecioUsuario(new BigDecimal(request.getParameter("precio")));
        ofertaJuego.setCantidad(Integer.parseInt(request.getParameter("stock")));

        switch (action) {
            case "new":
                ofertaJuego.setNombreNuevo(request.getParameter("nombreJuego").trim());
                ofertaJuego.setDescripcionNueva(request.getParameter("descripcion").trim());

                Categoria categoria = new Categoria();
                categoria.setIdCategorias(request.getParameter("idCategoria"));
                ofertaJuego.setCategoria(categoria);
                break;
            case "exist":
                Juegos juego = new Juegos();
                juego.setIdJuegos(Integer.parseInt(request.getParameter("idJuego")));
                ofertaJuego.setJuego(juego);
                break;
        }

        return ofertaJuego;
    }
}
