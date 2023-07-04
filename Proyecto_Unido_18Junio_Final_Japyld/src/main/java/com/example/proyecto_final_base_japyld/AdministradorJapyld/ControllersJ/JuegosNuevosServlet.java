package com.example.proyecto_final_base_japyld.AdministradorJapyld.ControllersJ;

import com.example.proyecto_final_base_japyld.AdministradorJapyld.ModelsJ.DaosJ.OfertasDao;
import com.example.proyecto_final_base_japyld.BeansGenerales.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "JuegosNuevosServlet", value = "/JuegosNuevosServlet")
public class JuegosNuevosServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OfertasDao ofertasDao = new OfertasDao();
        RequestDispatcher view;
        String action = request.getParameter("action") == null ? "lista" :request.getParameter("action");

        switch (action){
            case"ofertas":
                String id_venta = request.getParameter("id");
                int id_veta_int = Integer.parseInt(id_venta);
                request.setAttribute("venta_3_meses",ofertasDao.venta_3_meses(id_veta_int));
                request.setAttribute("ventaJuegosGeneral", ofertasDao.obtenerVenta(id_veta_int));
                request.getRequestDispatcher("AdministradorJapyld/NuevaOfertaAdmiJuegoNuevo.jsp").forward(request,response);
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

                    VentaJuegosGeneral ventaJuegosGeneral = ofertasDao.obtenerVenta(id_veta_int1);

                    if(ventaJuegosGeneral != null){

                        request.setAttribute("ventaJuegosGeneral",ventaJuegosGeneral);
                        view = request.getRequestDispatcher("AdministradorJapyld/AceptarOfertaAdmiNuevo.jsp");
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

                    VentaJuegosGeneral ventaJuegosGeneral = ofertasDao.obtenerVenta(id_veta_int1);

                    if(ventaJuegosGeneral != null){
                        ofertasDao.borrar(ventaJuegosGeneral);
                        ofertasDao.aceptarVenta(ventaJuegosGeneral);
                        request.getSession().setAttribute("info","Compra realizada exitosamente");
                        response.sendRedirect(request.getContextPath() + "/AdminServlet?action=listaPaginaOfertas");

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

                    VentaJuegosGeneral ventaJuegosGeneral = ofertasDao.obtenerVenta(id_veta_int2);

                    if(ventaJuegosGeneral != null){

                        request.setAttribute("ventaJuegosGeneral",ventaJuegosGeneral);
                        view = request.getRequestDispatcher("AdministradorJapyld/RechazarOfertaNuevo.jsp");
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

                    VentaJuegosGeneral ventaJuegosGeneral = ofertasDao.obtenerVenta(id_veta_int3);

                    if(ventaJuegosGeneral != null){

                        request.setAttribute("ventaJuegosGeneral",ventaJuegosGeneral);
                        view = request.getRequestDispatcher("AdministradorJapyld/ContraofertaAdmiNuevo.jsp");
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

        switch (action){
            case "actualizar":

                if(validar_texto(request.getParameter("rechazo").trim()) == true){

                    VentaJuegosGeneral ventaJuegosGeneral = setVenta(request);
                    ventaJuegosGeneral.setIdVenta(Integer.parseInt(request.getParameter("id_venta").trim()));
                    ofertasDao.editarVenta(ventaJuegosGeneral);
                    request.getSession().setAttribute("info","Mensaje de rechazo enviado exitosamente");
                    response.sendRedirect("AdminServlet?action=listaPaginaOfertas");
                }else {
                    request.getSession().setAttribute("info","Mensaje no enviado, verifique que se ingrese solo letras");
                    response.sendRedirect("AdminServlet?action=listaPaginaOfertas");
                }

                break;
            case "actualizarC":

                if(validar_texto(request.getParameter("rechazo").trim()) == true) {

                    if(valida_precio(request.getParameter("precioAdmi").trim())==true){

                        VentaJuegosGeneral ventaJuegosGeneralC = setVentaC(request);
                        ventaJuegosGeneralC.setIdVenta(Integer.parseInt(request.getParameter("id_venta")));
                        ofertasDao.editarVentaC(ventaJuegosGeneralC);
                        request.getSession().setAttribute("info","Contraoferta enviada exitosamente");
                        response.sendRedirect("AdminServlet?action=listaPaginaOfertas");
                    }else{
                        request.getSession().setAttribute("info","Contraoferta no enviada, ingrese un valor de precio valido");
                        response.sendRedirect("AdminServlet?action=listaPaginaOfertas");
                    }

                }else{
                    request.getSession().setAttribute("info","Contraoferta no enviada, verifique que se ingrese solo letras en la razon de rechazo");
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
        // como llamar a l imagen?
        Consola consola = new Consola();
        consola.setIdConsola(request.getParameter("consola").trim());
        ventaJuegosGeneral.setConsola(consola);

        Categoria categoria = new Categoria();
        categoria.setIdCategorias(request.getParameter("categoria").trim());
        ventaJuegosGeneral.setCategoria(categoria);

        ventaJuegosGeneral.setNombreNuevo(request.getParameter("nombre").trim());
        ventaJuegosGeneral.setDescripcionNueva(request.getParameter("descripcion").trim());

        return  ventaJuegosGeneral;
    }

    private VentaJuegosGeneral setVentaC(HttpServletRequest request){

        VentaJuegosGeneral ventaJuegosGeneral = new VentaJuegosGeneral();
        ventaJuegosGeneral.setPrecioUsuario(new BigDecimal(request.getParameter("precio").trim()));
        ventaJuegosGeneral.setPrecioAdmi(new BigDecimal(request.getParameter("precioAdmi").trim()));
        ventaJuegosGeneral.setRazonRechazo(request.getParameter("rechazo").trim());
        // como llamar a l imagen?
        Consola consola = new Consola();
        consola.setIdConsola(request.getParameter("consola").trim());
        ventaJuegosGeneral.setConsola(consola);

        Categoria categoria = new Categoria();
        categoria.setIdCategorias(request.getParameter("categoria").trim());
        ventaJuegosGeneral.setCategoria(categoria);

        ventaJuegosGeneral.setNombreNuevo(request.getParameter("nombre").trim());
        ventaJuegosGeneral.setDescripcionNueva(request.getParameter("descripcion").trim());


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
