package com.example.proyecto_final_base_japyld.AdministradorJapyld.ControllersJ;

import com.example.proyecto_final_base_japyld.AdministradorJapyld.ModelsJ.DaosJ.AdminDao;
import com.example.proyecto_final_base_japyld.AdministradorJapyld.ModelsJ.DaosJ.CategoriaDao;
import com.example.proyecto_final_base_japyld.AdministradorJapyld.ModelsJ.DaosJ.CrudDao;
import com.example.proyecto_final_base_japyld.BeansGenerales.Categoria;
import com.example.proyecto_final_base_japyld.BeansGenerales.Imagen;
import com.example.proyecto_final_base_japyld.BeansGenerales.Juegos;
import com.example.proyecto_final_base_japyld.BeansGenerales.Personas;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.math.BigDecimal;

@WebServlet(name = "AdminServlet", value = "/AdminServlet")
public class AdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action") == null ? "lista" :request.getParameter("action");

        RequestDispatcher view;
        AdminDao adminDao = new AdminDao();
        CrudDao crudDao = new CrudDao();
        CategoriaDao categoriaDao = new CategoriaDao();
        ConsolaDao consolaDao = new ConsolaDao();
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

            default:
                response.sendRedirect("AdminServlet");
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action") == null ? "lista" :request.getParameter("action");

        CrudDao crudDao = new CrudDao();

        Juegos juegos = setJuegos(request);

        switch (action){
            case "actualizar":
                juegos.setIdJuegos(Integer.parseInt(request.getParameter("id_juego")));

                crudDao.editarJuego(juegos);

                response.sendRedirect("AdminServlet");

                break;
            case "buscar":

                String textoBuscar = request.getParameter("textoBuscar");
                request.setAttribute("textoBusqueda", textoBuscar);
                request.setAttribute("listaJuegos", adminDao.buscarJuegosPorNombre(textoBuscar));
                RequestDispatcher view = request.getRequestDispatcher("AdministradorJapyld/adminVideojuegos.jsp");
                view.forward(request, response);
                break;
            case "borrar":
                if (request.getParameter("id") != null) {
                    String idJuegosString = request.getParameter("id");
                    int idJuegos = 0;
                    try {
                        idJuegos = Integer.parseInt(idJuegosString);
                    } catch (NumberFormatException ex) {
                        response.sendRedirect("AdminServlet?err=Error al borrar el Juego");
                    }

                    Juegos jg = adminDao.obetenerJuego(idJuegos);

                    if (jg != null) {
                        try {
                            adminDao.borrarjuego(idJuegos);
                            response.sendRedirect("AdminServlet?msg=Empleado borrado exitosamente");
                        } catch (SQLException e) {
                            response.sendRedirect("AdminServlet?err=Error al borrar el empleado");
                        }
                    }
                } else {
                    response.sendRedirect("AdminServlet?err=Error al borrar el empleado");
                }
                break;
        }

    }

    private Juegos setJuegos(HttpServletRequest request){

        Juegos juegos = new Juegos();

        juegos.setNombreJuegos(request.getParameter("nombre"));
        juegos.setPrecio(new BigDecimal(request.getParameter("precio")));
        juegos.setDescripcion(request.getParameter("descripcion"));

        Imagen imagen = new Imagen();
        imagen.setIdImagenes(Integer.parseInt(request.getParameter("imagenen")));
        juegos.setImagen(imagen);

        Categoria categoria = new Categoria();
        categoria.setIdCategorias(request.getParameter("categoria_id"));
        juegos.setCategoria(categoria);

        return juegos;
    }
}
