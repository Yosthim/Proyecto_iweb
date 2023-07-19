package com.example.proyecto_final_base_japyld.UsuarioJapyld.ControllersJ;

import com.example.proyecto_final_base_japyld.BeansGenerales.*;
import com.example.proyecto_final_base_japyld.ManagerJapyld.ModelsJ.DaosJ.ModuloAdminDao;
import com.example.proyecto_final_base_japyld.ManagerJapyld.ModelsJ.DtoJ.ModuloAdmin;
import com.example.proyecto_final_base_japyld.SistemaJapyld.ModelsJ.DaosJ.CorreoDao;
import com.example.proyecto_final_base_japyld.UsuarioJapyld.ModelsJ.DaosJ.CompraDao;

import com.example.proyecto_final_base_japyld.UsuarioJapyld.ModelsJ.DaosJ.PerfilDao;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

@WebServlet(name = "CompraServlet", value = {"/PaginaCompra"})
public class CompraServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CompraDao comprajuego = new CompraDao();
        PerfilDao perfilDao1 = new PerfilDao();

        int idjuego = Integer.parseInt(request.getParameter("idjuego"));
        String consola = request.getParameter("consola");

        request.setAttribute("listaDetallesCompra", comprajuego.listarDetallesCompra(idjuego,consola));
        request.setAttribute("listaFotoPerfil",perfilDao1.listarFotoPerfil());
        RequestDispatcher view = request.getRequestDispatcher("UsuarioJapyld/PaginaCompra.jsp");
        view.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Juegos juego = new Juegos();
        JuegosXConsola juegoxconsola = new JuegosXConsola();
        CompraDao crearJuegoCompradoReservadoDao = new CompraDao();
        CorreoDao correoDao = new CorreoDao();

        //1era validacion Direccion de entrega
        String direccion = request.getParameter("Direccion");
        int validacion1 = 0;
        if (!direccion.equals("") && direccion.contains("Perú")) {
            validacion1 = 1;
        }

        //2da validacion Numero de tarjetas
        String numeroTarjeta = request.getParameter("NumeroTarjeta");
        int validacion2 = 0;

        String regex2 = "^[45]\\d{3}-\\d{4}-\\d{4}-\\d{4}$";
        String digitRegex = "\\d+";
        if (!numeroTarjeta.equals("") && numeroTarjeta.matches(regex2) && numeroTarjeta.replaceAll("-", "").matches(digitRegex)) {
            validacion2 = 1;
        }

        //3ra validación CVV
        String CVV = request.getParameter("CVV");
        int validacion3 = 0;
        String regex3 = "^\\d{3}$";
        if (CVV != null && CVV.matches(regex3)) {
            validacion3 = 1;
        }

        String idJuegosActualizar = request.getParameter("idJuego");
        int idJuegosActualizado = Integer.parseInt(idJuegosActualizar);
        String idConsolaActualizar = request.getParameter("idConsola");

        if((validacion1==1) && (validacion2==1) && (validacion3==1)){
            JuegosCompradosReservados juegoCompradoReservado = parseJuegoCompradoReservado(request);
            crearJuegoCompradoReservadoDao.guardarJuegoComprado(juegoCompradoReservado);

            //Primero se obtiene el juego al que le restaré su stock
            juego = crearJuegoCompradoReservadoDao.obtenerJuego(idJuegosActualizado);
            //Aqui se lo paso y lo actualizo
            crearJuegoCompradoReservadoDao.actualizarStockJuegos(juego,idJuegosActualizado);

            //Primero obtengo la línea de juegos por consola
            juegoxconsola = crearJuegoCompradoReservadoDao.obtenerJuegoXConsola(idConsolaActualizar,idJuegosActualizado);
            //Aqui le paso esa linea y actualizo, falta pasarle juegosconsola
            crearJuegoCompradoReservadoDao.actualizarStockJuegosConsola(juegoxconsola,idConsolaActualizar,idJuegosActualizado);
            request.getSession().setAttribute("exitoCompra","La reserva de tu juego fue exitosa. Gracias por Comprar!");
            correoDao.sendGameReservationEmail(juegoCompradoReservado);
            response.sendRedirect(request.getContextPath() + "/TusJuegos");
        }else{
            if(validacion1 != 1){
                response.sendRedirect(request.getContextPath()+"/PaginaCompra?idjuego=" + idJuegosActualizar + "&consola=" + idConsolaActualizar +"&error=error1");
            }else{
                if(validacion2 != 1){
                    response.sendRedirect(request.getContextPath()+"/PaginaCompra?idjuego="+ idJuegosActualizar + "&consola=" + idConsolaActualizar +"&error=error2");
                }else{
                    if(validacion3 != 1){
                        response.sendRedirect(request.getContextPath()+ "/PaginaCompra?idjuego="+ idJuegosActualizar + "&consola=" + idConsolaActualizar +"&error=error3");
                    }
                }
            }
        }
    }

    public JuegosCompradosReservados parseJuegoCompradoReservado(HttpServletRequest request){

        ModuloAdminDao moduloAdminDao = new ModuloAdminDao();
        ArrayList<ModuloAdmin> listaAdmin = moduloAdminDao.listarAdminMenosReservas();

        JuegosCompradosReservados juegosCompradosReservados = new JuegosCompradosReservados();

        //Datos que recibo del forms
        String idUsuario = request.getParameter("idUsuario");
        String idConsola = request.getParameter("idConsola");
        String idJuegos = request.getParameter("idJuego");
        String precio = request.getParameter("Precio");
        String precio_nuevo = request.getParameter("PrecioNuevo");
        String CVV = request.getParameter("CVV");
        String NumeroTarjeta = request.getParameter("NumeroTarjeta");
        String Direccion = request.getParameter("Direccion");

        //Datos que debo poner yo
        //Fecha Compra
        Date fechaCompra = new Date();

        DateFormat fechaFormato = new SimpleDateFormat("yyyy-MM-dd");

        String fechaFormateada = fechaFormato.format(fechaCompra);

        //Estado Compra por defecto
        String estadoCompra = "Reservado";

        //Id Administrador random para que sea justo
        int idAdmin = 1;

        for (ModuloAdmin admin : listaAdmin) {
            int id = admin.getId();
            idAdmin = id;
        }


        //Terminamos con todos los datos que quiero para crear el juego

        try {
            int IdUsuario1 = Integer.parseInt(idUsuario);
            int IdJuegos1 = Integer.parseInt(idJuegos);
            int PrecioNuevo1 = Integer.parseInt(precio_nuevo);
            int Precio1 = Integer.parseInt(precio);
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            try{
                Date fechaCompraDate = formato.parse(fechaFormateada);

                juegosCompradosReservados.setFechaCompraJuego(fechaCompraDate);
                juegosCompradosReservados.setEstadoCompraJuego(estadoCompra);

                Personas usuario = new Personas();
                usuario.setIdPersona(IdUsuario1);
                juegosCompradosReservados.setUsuario(usuario);

                Personas admin = new Personas();
                admin.setIdPersona(idAdmin);
                juegosCompradosReservados.setAdministrador(admin);

                Juegos juego = new Juegos();
                juego.setIdJuegos(IdJuegos1);
                juegosCompradosReservados.setJuego(juego);

                Consola consola = new Consola();
                consola.setIdConsola(idConsola);
                juegosCompradosReservados.setConsola(consola);

                if(PrecioNuevo1 == 0){
                    juegosCompradosReservados.setPrecio_compra(Precio1);
                }else{
                    juegosCompradosReservados.setPrecio_compra(PrecioNuevo1);
                }

                juegosCompradosReservados.setDireccion_compra(Direccion);
                return juegosCompradosReservados;
            }catch (ParseException p){

            }
            return juegosCompradosReservados;
        } catch (NumberFormatException e){

        }
        return juegosCompradosReservados;
    }
}

