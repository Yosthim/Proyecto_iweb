package com.example.proyecto_final_base_japyld.AdministradorJapyld.ControllersJ;

import com.example.proyecto_final_base_japyld.AdministradorJapyld.ModelsJ.DaosJ.CrudDao;
import com.example.proyecto_final_base_japyld.AdministradorJapyld.ModelsJ.DaosJ.OfertasDao;
import com.example.proyecto_final_base_japyld.BeansGenerales.Imagen;
import com.example.proyecto_final_base_japyld.BeansGenerales.Juegos;
import com.example.proyecto_final_base_japyld.BeansGenerales.Personas;
import com.example.proyecto_final_base_japyld.BeansGenerales.VentaJuegosGeneral;
import com.example.proyecto_final_base_japyld.SistemaJapyld.ModelsJ.DaosJ.CorreoDao;
import com.example.proyecto_final_base_japyld.UsuarioJapyld.ModelsJ.DaosJ.PerfilDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "OfertasServlet", value = "/OfertasServlet")
public class OfertasServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        OfertasDao ofertasDao = new OfertasDao();
        CorreoDao correoDao = new CorreoDao();
        PerfilDao perfilDao1 = new PerfilDao();
        RequestDispatcher view;
        String action = request.getParameter("action") == null ? "lista" :request.getParameter("action");
        switch (action){

            case "ofertas":
                String id_venta = request.getParameter("id");
                int id_veta_int = Integer.parseInt(id_venta);
                request.setAttribute("venta_3_meses",ofertasDao.venta_3_meses(id_veta_int));
                request.setAttribute("ventaJuegosGeneral", ofertasDao.obtenerJuego(id_veta_int));
                request.setAttribute("listaFotoPerfil",perfilDao1.listarFotoPerfil());
                request.getRequestDispatcher("AdministradorJapyld/NuevaOfertaAdmi.jsp").forward(request,response);
                break;

            case "aceptar":

                if (request.getParameter("id") != null) {
                    String id_venta1 = request.getParameter("id");
                    int id_veta_int1 = 0;
                    try{
                        id_veta_int1=Integer.parseInt(id_venta1);
                    }catch (NumberFormatException e){
                        response.sendRedirect("OfertasServlet");
                    }

                    VentaJuegosGeneral ventaJuegosGeneral = ofertasDao.obtenerJuego(id_veta_int1);

                    if(ventaJuegosGeneral != null){

                        request.setAttribute("ventaJuegosGeneral",ventaJuegosGeneral);
                        request.setAttribute("listaFotoPerfil",perfilDao1.listarFotoPerfil());
                        view = request.getRequestDispatcher("AdministradorJapyld/AceptarOfertaAdmi.jsp");
                        view.forward(request,response);
                    }else{
                        response.sendRedirect("OfertasServlet");
                    }

                }else{
                    response.sendRedirect("OfertasServlet");
                }

                break;

            case "guardar":

                if (request.getParameter("id") != null) {
                    String id_venta1 = request.getParameter("id");
                    int id_veta_int1 = 0;
                    try{
                        id_veta_int1=Integer.parseInt(id_venta1);
                    }catch (NumberFormatException e){
                        response.sendRedirect("OfertasServlet");
                    }

                    VentaJuegosGeneral ventaJuegosGeneral = ofertasDao.obtenerJuego(id_veta_int1);

                    if(ventaJuegosGeneral != null){

                        ofertasDao.actualizarStock(ventaJuegosGeneral);
                        ofertasDao.borrar(ventaJuegosGeneral);
                        // agregar sotk general y que el sotk inicial sea igual a la cantidad

                        if (ofertasDao.validaExistenciaConsola(ventaJuegosGeneral) != 0){

                            // se debe actualizar el stock en la tabla de juegos por consolas y stock general
                            ofertasDao.actualizarStockConsola(ventaJuegosGeneral);
                            request.setAttribute("listaFotoPerfil",perfilDao1.listarFotoPerfil());
                            correoDao.correo(ventaJuegosGeneral.getUsuario().getCorreo(),"Estado de Oferta","Le enformamos que su oferta del juego "+ ventaJuegosGeneral.getJuego().getNombreJuegos()+" ha sido aceptada" + "\n\nGracias por su preferencia\n"
                                    +"Atentamente,\n" +
                                    "Japyld\n");
                            request.getSession().setAttribute("info","Compra realizada exitosamente");
                            response.sendRedirect(request.getContextPath() + "/AdminServlet?action=listaPaginaOfertas");

                        }else{
                            // se debe agregar ala tabla esa relacion en JUEGOSXCONSOLA
                            ofertasDao.agregarJuegoXconsola(ventaJuegosGeneral);
                            request.setAttribute("listaFotoPerfil",perfilDao1.listarFotoPerfil());
                            correoDao.correo(ventaJuegosGeneral.getUsuario().getCorreo(),"Estado de Oferta","Le enformamos que su oferta del juego "+ ventaJuegosGeneral.getJuego().getNombreJuegos()+" ha sido aceptada");
                            request.getSession().setAttribute("info","Compra realizada exitosamente");
                            response.sendRedirect(request.getContextPath() + "/AdminServlet?action=listaPaginaOfertas");
                        }


                    }else{
                        response.sendRedirect("OfertasServlet");
                    }

                }else{
                    response.sendRedirect("OfertasServlet");
                }

                break;

            case "rechazar":

                if (request.getParameter("id") != null) {
                    String id_venta2 = request.getParameter("id");
                    int id_veta_int2 = 0;
                    try{
                        id_veta_int2=Integer.parseInt(id_venta2);
                    }catch (NumberFormatException e){
                        response.sendRedirect("OfertasServlet");
                    }

                    VentaJuegosGeneral ventaJuegosGeneral = ofertasDao.obtenerJuego(id_veta_int2);

                    if(ventaJuegosGeneral != null){

                        request.setAttribute("ventaJuegosGeneral",ventaJuegosGeneral);
                        request.setAttribute("listaFotoPerfil",perfilDao1.listarFotoPerfil());
                        view = request.getRequestDispatcher("AdministradorJapyld/RechazarOferta.jsp");
                        view.forward(request,response);
                    }else{
                        response.sendRedirect("OfertasServlet");
                    }

                }else{
                    response.sendRedirect("OfertasServlet");
                }

                break;

            case "contraoferta":

                if (request.getParameter("id") != null) {
                    String id_venta3 = request.getParameter("id");
                    int id_veta_int3 = 0;
                    try{
                        id_veta_int3=Integer.parseInt(id_venta3);
                    }catch (NumberFormatException e){
                        response.sendRedirect("OfertasServlet");
                    }

                    VentaJuegosGeneral ventaJuegosGeneral = ofertasDao.obtenerJuego(id_veta_int3);

                    if(ventaJuegosGeneral != null){

                        request.setAttribute("ventaJuegosGeneral",ventaJuegosGeneral);
                        request.setAttribute("listaFotoPerfil",perfilDao1.listarFotoPerfil());
                        view = request.getRequestDispatcher("AdministradorJapyld/ContraofertaAdmi.jsp");
                        view.forward(request,response);
                    }else{
                        response.sendRedirect("OfertasServlet");
                    }

                }else{
                    response.sendRedirect("OfertasServlet");
                }

                break;

            default:
                response.sendRedirect("OfertasServlet");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "lista" :request.getParameter("action");
        OfertasDao ofertasDao = new OfertasDao();
        CorreoDao correoDao = new CorreoDao();


        switch (action){

            case"actualizar":

                    VentaJuegosGeneral ventaJuegosGeneral = setVenta(request);
                    ventaJuegosGeneral.setIdVenta(Integer.parseInt(request.getParameter("id_venta").trim()));
                    ofertasDao.editarVenta(ventaJuegosGeneral);
                    VentaJuegosGeneral ventaJuegosGeneral1 = ofertasDao.obtenerJuego(Integer.parseInt(request.getParameter("id_venta")));
                    correoDao.correo(ventaJuegosGeneral1.getUsuario().getCorreo(),"Estado de Oferta","Lamentablemente su oferta del juego "+ ventaJuegosGeneral1.getJuego().getNombreJuegos()+" ha sido rechazada.Verifique la razón propuesta por el admistrador en su pagina de ofertas" + "\n\nGracias por su preferencia\n"
                            +"Atentamente,\n" +
                            "Japyld\n");
                    request.getSession().setAttribute("info","Mensaje de rechazo enviado exitosamente");
                    response.sendRedirect("AdminServlet?action=listaPaginaOfertas");



                break;
            case "actualizarC":



                    if(valida_precio(request.getParameter("precioAdmi").trim())==true){
                        VentaJuegosGeneral ventaJuegosGeneralC = setVentaC(request);

                        ventaJuegosGeneralC.setIdVenta(Integer.parseInt(request.getParameter("id_venta")));
                        ofertasDao.editarVentaC(ventaJuegosGeneralC);
                        correoDao.correo(ofertasDao.obtenerJuego(Integer.parseInt(request.getParameter("id_venta").trim())).getUsuario().getCorreo(),"Estado de Oferta","Lamentablemente su oferta del juego "+ ofertasDao.obtenerJuego(Integer.parseInt(request.getParameter("id_venta").trim())).getJuego().getNombreJuegos()+" ha sido rechazada porque " + ofertasDao.obtenerJuego(Integer.parseInt(request.getParameter("id_venta").trim())).getRazonRechazo()+ ", pero recibio una propuesta de compra. Verifiquelo en su pagina de ofertas"+ "\n\nGracias por su preferencia\n"
                                +"Atentamente,\n" +
                                "Japyld\n");
                        request.getSession().setAttribute("info","Contraoferta enviada exitosamente");
                        response.sendRedirect("AdminServlet?action=listaPaginaOfertas");
                    }else{
                        request.getSession().setAttribute("err","Contraoferta no enviada, ingrese un valor de precio valido");
                        response.sendRedirect("AdminServlet?action=listaPaginaOfertas");
                    }

                break;

            default:
                response.sendRedirect("OfertasServlet");
        }


    }

    private VentaJuegosGeneral setVenta(HttpServletRequest request){

        VentaJuegosGeneral ventaJuegosGeneral = new VentaJuegosGeneral();
        ventaJuegosGeneral.setPrecioUsuario(new BigDecimal(request.getParameter("precio").trim()));
        ventaJuegosGeneral.setRazonRechazo(request.getParameter("rechazo").trim());

        Juegos juegos = new Juegos();
        juegos.setIdJuegos(Integer.parseInt(request.getParameter("juego").trim()));

        Imagen imagen = new Imagen();
        imagen.setIdImagenes(Integer.parseInt(request.getParameter("imagen").trim()));
        juegos.setImagen(imagen);

        ventaJuegosGeneral.setJuego(juegos);

        return  ventaJuegosGeneral;
    }

    private VentaJuegosGeneral setVentaC(HttpServletRequest request){

        VentaJuegosGeneral ventaJuegosGeneral = new VentaJuegosGeneral();
        ventaJuegosGeneral.setPrecioUsuario(new BigDecimal(request.getParameter("precio").trim()));
        ventaJuegosGeneral.setPrecioAdmi(new BigDecimal(request.getParameter("precioAdmi").trim()));
        ventaJuegosGeneral.setRazonRechazo(request.getParameter("rechazo").trim());

        Juegos juegos = new Juegos();
        juegos.setIdJuegos(Integer.parseInt(request.getParameter("juego").trim()));

        Imagen imagen = new Imagen();
        imagen.setIdImagenes(Integer.parseInt(request.getParameter("imagen").trim()));
        juegos.setImagen(imagen);

        ventaJuegosGeneral.setJuego(juegos);

        return  ventaJuegosGeneral;
    }

    public  boolean validar_texto(String input) {
        String regex = "^[a-zA-Z\\s]+$";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(input);

        return matcher.matches();
    }

    public boolean valida_precio(String input){
        String regex = "\\d+";
        if (!input.matches(regex)) {
            return false;
        }

        int numero = Integer.parseInt(input);
        if (numero <= 0) {
            return false;
        }

        return true;
    }

}
