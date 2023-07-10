package com.example.proyecto_final_base_japyld.UsuarioJapyld.ControllersJ;

import com.example.proyecto_final_base_japyld.BeansGenerales.*;
import com.example.proyecto_final_base_japyld.ManagerJapyld.ModelsJ.DaosJ.ModuloAdminDao;
import com.example.proyecto_final_base_japyld.ManagerJapyld.ModelsJ.DtoJ.ModuloAdmin;
import com.example.proyecto_final_base_japyld.UsuarioJapyld.ModelsJ.DaosJ.CompraDao;

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

        int idjuego = Integer.parseInt(request.getParameter("idjuego"));
        String consola = request.getParameter("consola");

        request.setAttribute("listaDetallesCompra", comprajuego.listarDetallesCompra(idjuego,consola));
        RequestDispatcher view = request.getRequestDispatcher("UsuarioJapyld/PaginaCompra.jsp");
        view.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Juegos juego = new Juegos();
        JuegosXConsola juegoxconsola = new JuegosXConsola();

        CompraDao crearJuegoCompradoReservadoDao = new CompraDao();
        JuegosCompradosReservados juegoCompradoReservado = parseJuegoCompradoReservado(request);
        crearJuegoCompradoReservadoDao.guardarJuegoComprado(juegoCompradoReservado);

        String idJuegosActualizar = request.getParameter("idJuego");
        int idJuegosActualizado = Integer.parseInt(idJuegosActualizar);
        //Primero se obtiene el juego al que le restaré su stock
        juego = crearJuegoCompradoReservadoDao.obtenerJuego(idJuegosActualizado);
        //Aqui se lo paso y lo actualizo
        crearJuegoCompradoReservadoDao.actualizarStockJuegos(juego,idJuegosActualizado);

        String idConsolaActualizar = request.getParameter("idConsola");
        //Primero obtengo la línea de juegos por consola
        juegoxconsola = crearJuegoCompradoReservadoDao.obtenerJuegoXConsola(idConsolaActualizar,idJuegosActualizado);
        //Aqui le paso esa linea y actualizo, falta pasarle juegosconsola
        crearJuegoCompradoReservadoDao.actualizarStockJuegosConsola(juegoxconsola,idConsolaActualizar,idJuegosActualizado);

        response.sendRedirect(request.getContextPath() + "/TusJuegos");

    }


    public JuegosCompradosReservados parseJuegoCompradoReservado(HttpServletRequest request){

        ModuloAdminDao moduloAdminDao = new ModuloAdminDao();
        ArrayList<ModuloAdmin> listaAdmin = moduloAdminDao.listarAdmin();

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
        ArrayList<Integer> listaIds = new ArrayList<>();

        for (ModuloAdmin admin : listaAdmin) {
            int id = admin.getId();
            listaIds.add(id);
        }

        Random random = new Random();
        int randomIndex = random.nextInt(listaIds.size());
        int idAdmin = listaIds.get(randomIndex);

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
                return juegosCompradosReservados;
            }catch (ParseException p){

            }
            return juegosCompradosReservados;
        } catch (NumberFormatException e){

        }
        return juegosCompradosReservados;
    }
}

