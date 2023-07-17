package com.example.proyecto_final_base_japyld.AdministradorJapyld.ControllersJ;

import com.example.proyecto_final_base_japyld.AdministradorJapyld.ModelsJ.DaosJ.AdminDao;
import com.example.proyecto_final_base_japyld.AdministradorJapyld.ModelsJ.DaosJ.CategoriaDao;
import com.example.proyecto_final_base_japyld.AdministradorJapyld.ModelsJ.DaosJ.CrudDao;
import com.example.proyecto_final_base_japyld.AdministradorJapyld.ModelsJ.DtoJ.BarrasAdminDao;
import com.example.proyecto_final_base_japyld.BeansGenerales.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "AdminServlet", value = "/AdminServlet")
public class AdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action") == null ? "lista" :request.getParameter("action");

        RequestDispatcher view;
        AdminDao adminDao = new AdminDao();
        CrudDao crudDao = new CrudDao();
        CategoriaDao categoriaDao = new CategoriaDao();
        BarrasAdminDao barrasAdminDao = new BarrasAdminDao();

        HttpSession session = request.getSession();
        Personas administrador= (Personas)session.getAttribute("personaSession");

        switch (action){
            case "lista":
                request.setAttribute("ultimasCompras",adminDao.primeraTabla());
                request.setAttribute("ventas",adminDao.segundaTabla());
                request.setAttribute("listaBarras", barrasAdminDao.Barras());
                request.getRequestDispatcher("AdministradorJapyld/adminPaginaInicio.jsp").forward(request,response);
                break;
            case "listasPaginaVideojuegos":
                request.setAttribute("lista",adminDao.terceraTabla());
                request.setAttribute("popCategoria",adminDao.tablaPopularesxCategotia());
                request.setAttribute("juegos",adminDao.quintaTabla());
                request.getRequestDispatcher("AdministradorJapyld/adminVideojuegos.jsp").forward(request,response);
                break;
            case "listaPaginaOfertas":
                request.setAttribute("ventas",adminDao.sextaTabla(administrador.getIdPersona()));
                request.setAttribute("nuevosJuegos",adminDao.octavaTabla(administrador.getIdPersona()));
                request.setAttribute("nuevosOfertas", adminDao.setimaTabla(administrador.getIdPersona()));
                request.getRequestDispatcher("AdministradorJapyld/OfertasAdmi.jsp").forward(request,response);
                break;
            case "editar":

                if(request.getParameter("id") !=null){
                    String id = request.getParameter("id");
                    int id_int=0;
                    try{
                        id_int=Integer.parseInt(id);
                    }catch (NumberFormatException e){
                        response.sendRedirect("AdminServlet");
                    }

                    Juegos juego = crudDao.ObtenerJuego(id_int);

                    if(juego != null){

                        request.setAttribute("juego", juego);
                        request.setAttribute("categorias",categoriaDao.listaCategoria());
                        view = request.getRequestDispatcher("AdministradorJapyld/adminEditVideojuego.jsp");
                        view.forward(request,response);
                    }else{
                        response.sendRedirect("AdminServlet");
                    }

                }else {
                    response.sendRedirect("AdminServlet");
                }
                break;

            case "borrar":
                if (request.getParameter("id") != null) {
                    String idJuegosString = request.getParameter("id");
                    int idJuegos = 0;
                    try {
                        idJuegos = Integer.parseInt(idJuegosString);
                    } catch (NumberFormatException ex) {
                        request.getSession().setAttribute("err","Error al borrar el juego");
                        response.sendRedirect("AdminTodosJuegos");
                    }
                    Juegos jg = adminDao.obetenerJuego(idJuegos);
                    if (jg != null) {
                        try {
                            adminDao.borrarjuego(idJuegos);
                            request.getSession().setAttribute("info","Juego borrado exitosamente");
                            response.sendRedirect("AdminTodosJuegos");
                        } catch (SQLException e) {
                            request.getSession().setAttribute("err","Error al borrar el juego");
                            response.sendRedirect("AdminTodosJuegos");
                        }
                    }
                } else {
                    request.getSession().setAttribute("err","Error al borrar el juego");
                    response.sendRedirect("AdminTodosJuegos");
                }
                break;


            default:
                response.sendRedirect("AdminServlet");
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action") == null ? "lista" :request.getParameter("action");

        CrudDao crudDao = new CrudDao();

        AdminDao adminDao = new AdminDao();

        switch (action){

            case "actualizar":



                    if (validarPrecio(request.getParameter("precio").trim()) == true){

                            if (validar_texto(request.getParameter("descripcion").trim())==true){

                                Juegos juegos = setJuegos(request);
                                juegos.setIdJuegos(Integer.parseInt(request.getParameter("id_juego")));
                                crudDao.editarJuego(juegos);
                                request.getSession().setAttribute("info","Actualizacion exitosa");
                                response.sendRedirect("AdminTodosJuegos");
                            }else{
                                request.getSession().setAttribute("err","Cambios no hechos, asegurece de ingresar una descripcion correcta");
                                response.sendRedirect("AdminTodosJuegos");
                            }

                    }else{
                        request.getSession().setAttribute("err","Cambios no hechos,  asegurece de ingresar un precio valido y una descripcion diferente");
                        response.sendRedirect("AdminTodosJuegos");
                    }


                break;

            case "buscar":

                String textoBuscar = request.getParameter("textoBuscar");

                request.setAttribute("textoBusqueda", textoBuscar);
                RequestDispatcher view = request.getRequestDispatcher("AdministradorJapyld/adminVideojuegos.jsp");
                view.forward(request, response);

            case"agregarCategoria":

                if(validar_texto(request.getParameter("nombre").trim()) == true){

                    Categoria categoria = new Categoria();

                    String oracion = request.getParameter("nombre").trim();
                    String primeraLetraMayuscula = oracion.substring(0, 1).toUpperCase();
                    String restoOracionMinuscula = oracion.substring(1).toLowerCase();
                    String oracionFinal = primeraLetraMayuscula + restoOracionMinuscula;
                    categoria.setNombre(oracionFinal);
                    if (validar(oracionFinal, adminDao.listaCategoria()) == true){
                        adminDao.agregarCategoria(categoria);
                        request.getSession().setAttribute("info","Categoria agregada exitosamente");
                        response.sendRedirect("AdminServlet?action=listasPaginaVideojuegos");
                    }else {
                        request.getSession().setAttribute("err","Categoria ya existe");
                        response.sendRedirect("AdminServlet?action=listasPaginaVideojuegos");
                    }

                }else {
                    request.getSession().setAttribute("err","Categoria no agregada, verifique que se ingrese solo letras");
                    response.sendRedirect("AdminServlet?action=listasPaginaVideojuegos");
                }
                break;

            case"agregarPrecio":

                if(validarPrecio(request.getParameter("nombre").trim()) == true){

                    adminDao.actualizarPrecio(Integer.parseInt(request.getParameter("id_venta")) ,new BigDecimal(request.getParameter("nombre")));
                    request.getSession().setAttribute("info","Precio actualizado correctamente");
                    response.sendRedirect("AdminTodosJuegos");

                }else {
                    request.getSession().setAttribute("err","Precio no actualizado, ingrese un valor de precio valido");
                    response.sendRedirect("AdminTodosJuegos");
                }
                break;

            case"agregarDescripcion":

                if(validar_texto(request.getParameter("nombre").trim()) == true){

                    adminDao.actualizarDescripcion(Integer.parseInt(request.getParameter("id_venta")) ,request.getParameter("nombre"));
                    request.getSession().setAttribute("info","Descripción actualizada correctamente");
                    response.sendRedirect("AdminTodosJuegos");

                }else {
                    request.getSession().setAttribute("err","Descripción no actualizada, asegurese de ingresar una correctamente");
                    response.sendRedirect("AdminTodosJuegos");
                }
                break;
            case"agrergarCategoria":

                    adminDao.actualizarCategoria(Integer.parseInt(request.getParameter("id_venta")) ,request.getParameter("categoria_id"));
                    request.getSession().setAttribute("info","Categoria actualizada correctamente");
                    response.sendRedirect("AdminTodosJuegos");

                break;

            default:
                response.sendRedirect("AdminServlet");
        }

    }

    private Juegos setJuegos(HttpServletRequest request){

        Juegos juegos = new Juegos();

        juegos.setNombreJuegos(request.getParameter("nombre"));
        juegos.setPrecio(new BigDecimal(request.getParameter("precio")));
        juegos.setDescripcion(request.getParameter("descripcion"));

       // Imagen imagen = new Imagen();
        //imagen.setIdImagenes(Integer.parseInt(request.getParameter("imagenen")));
        //juegos.setImagen(imagen);

        Categoria categoria = new Categoria();
        categoria.setIdCategorias(request.getParameter("categoria_id"));
        juegos.setCategoria(categoria);

        return juegos;
    }

    public  boolean validar_texto(String input) {
        String regex = "^[a-zA-Z\\s]+$";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(input);

        return matcher.matches();
    }

    public Boolean validar(String nombre, ArrayList<Categoria> lista){

        int r=0;
        for(int i =0 ; i< lista.size() ; i++){

            if(nombre.equals(lista.get(i).getNombre())){
                r++;
            }
        }

        if (r>0){
            return false;
        }

        return true;
    }

    public Boolean validarPrecio(String nombre) {

        if (nombre.matches("\\d+") && Integer.parseInt(nombre) > 0) {
            return true;
        }
        return false;
    }

}
