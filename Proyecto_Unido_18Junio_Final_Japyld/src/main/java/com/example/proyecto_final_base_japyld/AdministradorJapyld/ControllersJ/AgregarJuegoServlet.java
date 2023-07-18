package com.example.proyecto_final_base_japyld.AdministradorJapyld.ControllersJ;

import com.example.proyecto_final_base_japyld.AdministradorJapyld.ModelsJ.DaosJ.AdminDao;
import com.example.proyecto_final_base_japyld.AdministradorJapyld.ModelsJ.DaosJ.AgregarDao;
import com.example.proyecto_final_base_japyld.BeansGenerales.*;
import com.example.proyecto_final_base_japyld.UsuarioJapyld.ModelsJ.DaosJ.PerfilDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "AgregarJuegoServlet", value = "/AgregarJuegoServlet")
@MultipartConfig
public class AgregarJuegoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        RequestDispatcher view;
        PerfilDao perfilDao1 = new PerfilDao();

        String action = request.getParameter("act") == null ? "listar" : request.getParameter("act");
        AgregarDao agregarDao = new AgregarDao();

        switch (action){
            case "agregar":
                request.setAttribute("listaFotoPerfil",perfilDao1.listarFotoPerfil());
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
        AdminDao adminDao = new AdminDao();
        String action = request.getParameter("act");

        if (validar_texto(request.getParameter("nombreJuego").trim()) == true){

            if (validar(request.getParameter("nombreJuego").trim(),adminDao.quintaTabla())){

                if (validar_texto(request.getParameter("descripcion").trim()) == true){

                    if (validarPrecio(request.getParameter("precio").trim()) == true){

                        if (validarPrecio(request.getParameter("stock").trim()) == true){

                            if (request.getParameter("idConsola").equals("Consolas")){

                                request.getSession().setAttribute("err","Debe seleccionar una consola");
                                response.sendRedirect("AdminTodosJuegos");

                            }else{

                                if (request.getParameter("idCategoria").equals("Categorias")){
                                    request.getSession().setAttribute("err","Debe seleccionar una categoria");
                                    response.sendRedirect("AdminTodosJuegos");
                                }else {


                                    Juegos juegos = setJuego(request);

                                    JuegosXConsola juegosXConsola = setConsola(request);

                                    switch (action){
                                        case "new":
                                            Part imageGamePart = request.getPart("imagenJuego");

                                            InputStream imageGameContent = imageGamePart.getInputStream();
                                            juegos.getImagen().setImagem(imageGameContent);
                                            agregarDao.registrarJuego(juegos);
                                            agregarDao.registrarJuegoXCategoria(juegosXConsola);
                                            request.getSession().setAttribute("info","Juego agregado correctamente");
                                            response.sendRedirect("AdminTodosJuegos");
                                            break;

                                    }
                                }
                            }

                        }else{
                            request.getSession().setAttribute("err","Juego no agregado, ingrese un valor de stock valido");
                            response.sendRedirect("AdminTodosJuegos");
                        }

                    }else{
                        request.getSession().setAttribute("err","Juego no agregado, ingrese un valor de precio valido");
                        response.sendRedirect("AdminTodosJuegos");
                    }

                }else{
                    request.getSession().setAttribute("err","Juego no agregado, ingrese una descripcion valida");
                    response.sendRedirect("AdminTodosJuegos");
                }
            }else{
                request.getSession().setAttribute("err","Juego no agregado, ya existe ");
                response.sendRedirect("AdminTodosJuegos");
            }

        }else{
            request.getSession().setAttribute("err","Juego no agregado, ingrese un nombre valido");
            response.sendRedirect("AdminTodosJuegos");
        }

    }



    public Juegos setJuego(HttpServletRequest request) {

        Juegos juegos = new Juegos();

        juegos.setNombreJuegos(request.getParameter("nombreJuego"));
        juegos.setPrecio(new BigDecimal(request.getParameter("precio")));
        juegos.setDescripcion(request.getParameter("descripcion").trim());
        juegos.setStock(Integer.parseInt(request.getParameter("stock")));

        Categoria categoria = new Categoria();
        categoria.setIdCategorias(request.getParameter("idCategoria"));
        juegos.setCategoria(categoria);

        Imagen imagen = new Imagen();
        juegos.setImagen(imagen);

        return juegos;
    }
    public JuegosXConsola setConsola(HttpServletRequest request){

        JuegosXConsola juegosXConsola = new JuegosXConsola();

        Consola consola = new Consola();
        consola.setIdConsola(request.getParameter("idConsola"));

        juegosXConsola.setConsolaDeJuego(consola);
        juegosXConsola.setStockXConsola(Integer.parseInt(request.getParameter("stock")));
        return juegosXConsola;
    }

    public  boolean validar_texto(String input) {
        String regex = "^[a-zA-Z\\s]+$";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(input);

        return matcher.matches();
    }

    public Boolean validar(String nombre, ArrayList<Juegos> lista){

        int r=0;
        for(int i =0 ; i< lista.size() ; i++){

            if(nombre.equals(lista.get(i).getNombreJuegos())){
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
