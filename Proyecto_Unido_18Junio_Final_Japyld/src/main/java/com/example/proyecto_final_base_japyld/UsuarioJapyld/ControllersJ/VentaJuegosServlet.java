package com.example.proyecto_final_base_japyld.UsuarioJapyld.ControllersJ;

import com.example.proyecto_final_base_japyld.BeansGenerales.*;
import com.example.proyecto_final_base_japyld.SistemaJapyld.Exceptions.RegisterException;
import com.example.proyecto_final_base_japyld.SistemaJapyld.ModelsJ.DaosJ.CorreoDao;
import com.example.proyecto_final_base_japyld.UsuarioJapyld.ModelsJ.DaosJ.PerfilDao;
import com.example.proyecto_final_base_japyld.UsuarioJapyld.ModelsJ.DaosJ.VentaJuegosDao;
import com.example.proyecto_final_base_japyld.UsuarioJapyld.ModelsJ.DtoJ.InfoVentaDto;
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
        PerfilDao perfilDao1 = new PerfilDao();

        switch (action) {
            case "listar":
                Personas usuario = (Personas) request.getSession().getAttribute("personaSession");
                request.setAttribute("listaOfertas", ventaJuegosDao.listarOfertas(usuario.getIdPersona()));
                request.setAttribute("listaFotoPerfil",perfilDao1.listarFotoPerfil());
                view = request.getRequestDispatcher("UsuarioJapyld/TusVentas.jsp");
                view.forward(request, response);
                break;
            case "exist":
                request.setAttribute("listaJuegos", ventaJuegosDao.listarNombreJuegos());
                request.setAttribute("consolas", ventaJuegosDao.listarConsolas());
                request.setAttribute("listaFotoPerfil",perfilDao1.listarFotoPerfil());
                view = request.getRequestDispatcher("UsuarioJapyld/JuegoExistente.jsp");
                view.forward(request, response);
                break;
            case "new":
                request.setAttribute("categorias", ventaJuegosDao.listarCategorias());
                request.setAttribute("consolas", ventaJuegosDao.listarConsolas());
                request.setAttribute("listaFotoPerfil",perfilDao1.listarFotoPerfil());
                view = request.getRequestDispatcher("UsuarioJapyld/JuegoNuevo.jsp");
                view.forward(request, response);
                break;
            case "change":
                try {
                    Integer idOferta = Integer.parseInt(request.getParameter("id"));
                    BigDecimal precioAdmin = new BigDecimal(request.getParameter("p"));
                    request.setAttribute("idOferta", idOferta);
                    request.setAttribute("precioAdmin", precioAdmin);
                    request.setAttribute("listaFotoPerfil",perfilDao1.listarFotoPerfil());
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

        VentaJuegosDao ventaJuegosDao = new VentaJuegosDao(); //Para las partes relacionadas a la tabla de ventas
        CorreoDao correoDao = new CorreoDao(); //Para enviar los correos correspondientes
        String action = request.getParameter("act");

        VentaJuegosGeneral ofertaJuego;
        int idVenta;

        try {
            switch (action) {
                case "new":
                    try {
                        ofertaJuego = setOferta(request, action);
                        Part imageGamePart = request.getPart("imagenJuego");
                        InputStream imageGameContent = imageGamePart.getInputStream();
                        if (imageGameContent.available() > 0) {//Se verfica que se halla subido una imagen
                            ofertaJuego.setImagenNueva(imageGameContent);
                            if (ventaJuegosDao.registrarOferta(ofertaJuego, "nuevo")) {
                                correoDao.sendNewOfferEmail(ofertaJuego.getUsuario(), ofertaJuego.getAdministrador().getIdPersona());
                                request.getSession().setAttribute("exito", "Oferta publicada con éxito");
                            }else {
                                throw new Exception();
                            }
                        } else {
                            request.getSession().setAttribute("failRegister", "Imagen obligatoria. El tamaño no debe superar los 2 Mb");
                            response.sendRedirect(request.getContextPath() + "/TusVentas?act=new");
                        }
                    } catch (RegisterException e) {
                        request.getSession().setAttribute("failRegister", e.mensajeError(1));
                        response.sendRedirect(request.getContextPath() + "/TusVentas?act=new");
                    }
                    break;
                case "exist":
                    try {
                        ofertaJuego = setOferta(request, action);
                        if (ventaJuegosDao.registrarOferta(ofertaJuego, "existente")) {
                            correoDao.sendNewOfferEmail(ofertaJuego.getUsuario(), ofertaJuego.getAdministrador().getIdPersona());
                            request.getSession().setAttribute("exito", "Oferta publicada con éxito");
                        } else {
                            throw new Exception();
                        }
                    } catch (RegisterException e) {
                        request.getSession().setAttribute("failRegister", e.mensajeError(2));
                        response.sendRedirect(request.getContextPath() + "/TusVentas?act=exist");
                    }
                    break;
                case "change":
                    idVenta = Integer.parseInt(request.getParameter("id"));
                    BigDecimal precioNuevo = new BigDecimal(request.getParameter("precioNuevo"));
                    if (ventaJuegosDao.changeOfferPrice(idVenta, precioNuevo)) {
                        InfoVentaDto info = ventaJuegosDao.getOfferInfo(idVenta);
                        correoDao.sendChangeOfferEmail(info);
                        request.getSession().setAttribute("exito", "El precio de la oferta se modificó con éxito");
                    }
                    break;
                case "retire":
                    idVenta = Integer.parseInt(request.getParameter("id"));
                    if (ventaJuegosDao.retireOffer(idVenta)) {
                        request.getSession().setAttribute("exito", "Oferta retirada exitosamente");
                    }
                    break;
                case "delete":
                    idVenta = Integer.parseInt(request.getParameter("id"));
                    boolean resultado = ventaJuegosDao.deleteOffer(idVenta);
                    if (resultado) {
                        request.getSession().setAttribute("exito", "Oferta eliminada de la lista exitosamente");
                    }
                    break;
            }
        } catch (Exception e) {
            request.getSession().setAttribute("error", "Ocurrió un error. Intente otra vez");
        }
        response.sendRedirect(request.getContextPath() + "/TusVentas");
    }

    public VentaJuegosGeneral setOferta(HttpServletRequest request, String action) throws RegisterException {
        VentaJuegosGeneral ofertaJuego = new VentaJuegosGeneral();
        VentaJuegosDao ventaJuegosDao = new VentaJuegosDao();
        try {
            Personas admin = new Personas();
            Personas usuario = (Personas) request.getSession().getAttribute("personaSession");
            admin.setIdPersona(ventaJuegosDao.getRandomIdAdmin());
            ofertaJuego.setAdministrador(admin);
            ofertaJuego.setUsuario(usuario);

            Consola consola = new Consola();
            consola.setIdConsola(request.getParameter("idConsola"));
            ofertaJuego.setConsola(consola);
            double precio = Double.parseDouble(request.getParameter("precio"));
            if (precio > 0) {
                ofertaJuego.setPrecioUsuario(BigDecimal.valueOf(precio));
            } else {
                throw new Exception();
            }
            ofertaJuego.setCantidad(Integer.parseInt(request.getParameter("stock")));

            switch (action) {
                case "new":
                    if (ventaJuegosDao.validateIfNameExist(request.getParameter("nombreJuego").trim())) {
                        ofertaJuego.setNombreNuevo(request.getParameter("nombreJuego").trim());
                    } else {
                        throw new Exception();
                    }
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
        }catch (Exception e) {
            throw new RegisterException();
        }
        return ofertaJuego;
    }
}
