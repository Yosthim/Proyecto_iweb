package com.example.proyecto_final_base_japyld.AdministradorJapyld.ModelsJ.DaosJ;

import com.example.proyecto_final_base_japyld.AdministradorJapyld.ModelsJ.DtoJ.JuegosPopulares;
import com.example.proyecto_final_base_japyld.AdministradorJapyld.ModelsJ.DtoJ.JuegosxCategoria;
import com.example.proyecto_final_base_japyld.AdministradorJapyld.ModelsJ.DtoJ.TodosJuegosDto;
import com.example.proyecto_final_base_japyld.BaseDao;
import com.example.proyecto_final_base_japyld.BeansGenerales.*;
import com.example.proyecto_final_base_japyld.UsuarioJapyld.ModelsJ.DtoJ.PaginaPrincipalDto;

import java.sql.*;
import java.util.ArrayList;


// DAO DE LAS LISTAS

public class AdminDao extends BaseDao {

    // PAGINA INICIO
    public ArrayList<JuegosCompradosReservados> primeraTabla(){

            ArrayList<JuegosCompradosReservados> ultimasCompras= new ArrayList<>();

        String sql = "SELECT * FROM juegoscompradosreservados c\n" +
                "                LEFT JOIN personas p ON c.id_usuario = p.idPersona\n" +
                "                LEFT JOIN juegos j ON c.id_juego = j.idJuegos\n" +
                "                WHERE c.estadoCompraJuego LIKE 'Comprado'\n" +
                "                ORDER BY c.fechaCompraJuego DESC\n" +
                "                LIMIT 5;";

        try(Connection connection = this.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql)){

            while (resultSet.next()){

                JuegosCompradosReservados compras =new JuegosCompradosReservados();
                compras.setIdJuegosCompradosReservados(resultSet.getInt(1));
                compras.setEstadoCompraJuego(resultSet.getString(3));

                Personas usuario = new Personas();
                usuario.setIdPersona(resultSet.getInt("p.idPersona"));
                usuario.setNombre(resultSet.getString("nombre"));
                compras.setUsuario(usuario);

                Juegos juego = new Juegos();
                juego.setIdJuegos((resultSet.getInt("j.idJuegos")));
                juego.setNombreJuegos(resultSet.getString("nombreJuegos"));

                compras.setJuego(juego);

                ultimasCompras.add(compras);

            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        return ultimasCompras;
    }

    public ArrayList<VentaJuegosGeneral> segundaTabla(){

        ArrayList<VentaJuegosGeneral> ventas= new ArrayList<>();

        String sql1 = "SELECT * FROM ventajuegosgeneral v\n" +
                " join personas p on v.id_usuario = p.idPersona\n" +
                " left join juegos j on v.id_juego = j.idJuegos\n" +
                " WHERE estadoJuego like  'Oferta'\n" +
                " ORDER BY v.fechaPublicacion DESC \n" +
                "LIMIT 5;";


        try(Connection connection = this.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql1)){

            while (resultSet.next()){

                VentaJuegosGeneral venta = new VentaJuegosGeneral();
                venta.setIdVenta(resultSet.getInt(1));
                venta.setPrecioUsuario(resultSet.getBigDecimal(5));

                Personas usuario = new Personas();
                usuario.setIdPersona(resultSet.getInt("p.idPersona"));
                usuario.setNombre(resultSet.getString("nombre"));
                venta.setUsuario(usuario);

                Juegos juego = new Juegos();
                juego.setIdJuegos((resultSet.getInt("j.idJuegos")));
                juego.setNombreJuegos(resultSet.getString("nombreJuegos"));
                venta.setJuego(juego);

                ventas.add(venta);

            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return ventas;
    }

    // pagina videojuegos

    public ArrayList<JuegosPopulares> terceraTabla(){

        ArrayList<JuegosPopulares> lista= new ArrayList<>();


        String sql1 = "SELECT COUNT(nombreJuegos) as 'Juegos_comprados_por_título', rating, nombreJuegos, estadoCompraJuego, idJuegos\n" +
                "                FROM juegoscompradosreservados jc\n" +
                "                INNER JOIN juegos j ON jc.id_juego = j.idJuegos\n" +
                "                WHERE jc.estadoCompraJuego = 'Comprado'\n" +
                "                GROUP BY j.nombreJuegos, rating, estadoCompraJuego, idJuegos\n" +
                "                ORDER BY count(nombreJuegos) desc\n" +
                "                limit 5;";


        try(Connection connection = this.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql1)){

            while (resultSet.next()){

                JuegosPopulares videoJuego2 = new JuegosPopulares();
                videoJuego2.setNombre(resultSet.getString(3));
                videoJuego2.setCantidadVentasJuegos(resultSet.getInt(1));
                lista.add(videoJuego2);
            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return lista;
    }


    public ArrayList<JuegosxCategoria> tablaPopularesxCategotia(){

        ArrayList<JuegosxCategoria> popCategoria= new ArrayList<>();


        String sql1 = "SELECT c.nombre AS nombreCategoria, COUNT(*) AS cantidadRepetida\n" +
                "FROM juegoscompradosreservados jc\n" +
                "INNER JOIN juegos j ON j.idJuegos = jc.id_juego\n" +
                "INNER JOIN categorias c ON j.id_categoria = c.idCategorias\n" +
                "WHERE jc.fechaCompraJuego >= DATE_SUB(NOW(), INTERVAL 1 MONTH) AND jc.estadoCompraJuego = 'Comprado'\n" +
                "GROUP BY c.nombre;";

        try(Connection connection = this.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql1)){

            while (resultSet.next()){
                JuegosxCategoria cat = new JuegosxCategoria();
                cat.setNombreCategoria(resultSet.getString(1));
                cat.setCantidadRepetida(resultSet.getInt(2));
                popCategoria.add(cat);
            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return popCategoria;
    }

    // Lista de categorias

    public ArrayList<Categoria> listaCategoria(){

        ArrayList<Categoria> lista = new ArrayList<>();

        String sql = "SELECT * FROM categorias;";

        try(Connection connection = this.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql)){

            while (resultSet.next()){
                Categoria categoria = new Categoria();
                categoria.setIdCategorias(resultSet.getString(1));
                categoria.setNombre(resultSet.getString(2));
                lista.add(categoria);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return lista;
    }

    // Agregar categoria

    public void agregarCategoria(Categoria categoria){

        String sql = "INSERT INTO categorias (idCategorias,nombre)\n" +
                "                VALUES (?,?);\n";

        try(Connection connection = this.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            String palabra = categoria.getNombre();
            String tresPrimerasLetras = palabra.substring(0, 3);
            String tresPrimerasMayusculas = tresPrimerasLetras.toUpperCase();
            preparedStatement.setString(1,tresPrimerasMayusculas);
            preparedStatement.setString(2,categoria.getNombre());
            preparedStatement.executeUpdate();


        }catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

    public ArrayList<Juegos> quintaTabla(){

        ArrayList<Juegos> juegos= new ArrayList<>();

        String sql = "SELECT * FROM juegos j\n" +
                "left join categorias c on j.id_categoria = c.idCategorias;";
        try(Connection connection = this.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql)){

            while (resultSet.next()){

                Juegos juego =new Juegos();
                juego.setIdJuegos(resultSet.getInt(1));
                juego.setNombreJuegos((resultSet.getString(2)));
                juego.setPrecio(resultSet.getBigDecimal(4));
                juego.setStock(resultSet.getInt(3));

                Categoria categoria = new Categoria();
                categoria.setIdCategorias(resultSet.getString("c.idCategorias"));
                categoria.setNombre((resultSet.getString("nombre")));

                juego.setCategoria(categoria);

                juegos.add(juego);

            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return juegos;
    }
    //Buscar Juego

    private void fetchJuegosData(Juegos juegos, ResultSet rs) throws SQLException {
        juegos.setNombreJuegos(rs.getString(2));
        juegos.setPrecio(rs.getBigDecimal(4));
        juegos.setStock(rs.getInt(3));
        juegos.setIdJuegos(rs.getInt(1));
        Categoria categoria = new Categoria();
        categoria.setNombre(rs.getString(10));

        juegos.setCategoria(categoria);
    }


// PAGINA DE OFERTAS GENERALES

    public ArrayList<VentaJuegosGeneral> sextaTabla(int id){

        ArrayList<VentaJuegosGeneral> ventas = new ArrayList<>();

        String sql = "SELECT * FROM ventajuegosgeneral c\n" +
                "left join personas p on c.id_usuario = p.idPersona\n" +
                "left join juegos j on c.id_juego = j.idJuegos\n" +
                "WHERE c.estadoVenta = 'Aceptado' and c.id_administrador =? \n" +
                "ORDER BY c.fechaPublicacion DESC;";

        try(Connection connection = this.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setInt(1,id);

            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()){

                    VentaJuegosGeneral venta = new VentaJuegosGeneral();
                    venta.setIdVenta(resultSet.getInt(1));

                    Juegos juego = new Juegos();
                    juego.setIdJuegos((resultSet.getInt("j.idJuegos")));
                    juego.setNombreJuegos(resultSet.getString("nombreJuegos"));
                    juego.setStock(resultSet.getInt("stock"));
                    venta.setJuego(juego);

                    Personas usuario = new Personas();
                    usuario.setIdPersona(resultSet.getInt("p.idPersona"));
                    usuario.setNombre(resultSet.getString("nombre"));
                    venta.setUsuario(usuario);

                    ventas.add(venta);

                }
            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return ventas;
    }

    public ArrayList<VentaJuegosGeneral> setimaTabla(int id){

        ArrayList<VentaJuegosGeneral> nuevosJuegos = new ArrayList<>();

        String sql = "SELECT * FROM ventajuegosgeneral c\n" +
                "                left join personas p on c.id_usuario = p.idPersona\n" +
                                "left join juegos j on c.id_juego = j.idJuegos\n" +
                "                WHERE c.estadoVenta = 'Espera' and disponibilidad = 'Existente' and c.id_administrador =? \n" +
                "                ORDER BY c.fechaPublicacion DESC;";
        try(Connection connection = this.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setInt(1,id);

            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()){

                    VentaJuegosGeneral venta = new VentaJuegosGeneral();
                    venta.setIdVenta(resultSet.getInt(1));
                    venta.setPrecioUsuario(resultSet.getBigDecimal(5));

                    Juegos juego = new Juegos();
                    juego.setIdJuegos((resultSet.getInt("j.idJuegos")));
                    juego.setNombreJuegos(resultSet.getString("nombreJuegos"));
                    venta.setJuego(juego);

                    Personas usuario = new Personas();
                    usuario.setIdPersona(resultSet.getInt("p.idPersona"));
                    usuario.setNombre(resultSet.getString("nombre"));
                    venta.setUsuario(usuario);

                    nuevosJuegos.add(venta);

                }
            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return nuevosJuegos;
    }

    public ArrayList<VentaJuegosGeneral> octavaTabla(int id){

        ArrayList<VentaJuegosGeneral> nuevosOfertas = new ArrayList<>();


        String sql = "SELECT * FROM ventajuegosgeneral c\n" +
                "                left join personas p on c.id_usuario = p.idPersona\n" +
                "                   left join juegos j on c.id_juego = j.idJuegos\n" +
                "                WHERE c.estadoVenta = 'Espera' and disponibilidad = 'Nuevo' and c.id_administrador =? \n" +
                "                ORDER BY c.fechaPublicacion DESC;";

        try(Connection connection = this.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setInt(1,id);

            try(ResultSet resultSet =preparedStatement.executeQuery()){
                while (resultSet.next()){

                    VentaJuegosGeneral venta = new VentaJuegosGeneral();
                    venta.setIdVenta(resultSet.getInt(1));
                    venta.setNombreNuevo(resultSet.getString(13));
                    venta.setDescripcionNueva(resultSet.getString(12));

                    venta.setPrecioUsuario(resultSet.getBigDecimal(5));

                    Juegos juego = new Juegos();
                    juego.setIdJuegos((resultSet.getInt("j.idJuegos")));
                    juego.setNombreJuegos(resultSet.getString("nombreJuegos"));
                    venta.setJuego(juego);

                    Personas usuario = new Personas();
                    usuario.setIdPersona(resultSet.getInt("p.idPersona"));
                    usuario.setNombre(resultSet.getString("nombre"));
                    venta.setUsuario(usuario);

                    nuevosOfertas.add(venta);

                }
            }


        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return nuevosOfertas;
    }
    //Todos los Juegos
    public ArrayList<TodosJuegosDto> todosJuegos(){
        ArrayList<TodosJuegosDto> tjuegos = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String sql = "SELECT idJuegos, nombreJuegos, precio,direccion_archivo, stock, estadoJuego\n" +
                "FROM juegos j\n" +
                "LEFT JOIN descuentos d ON j.idJuegos = d.id_juego\n" +
                "INNER JOIN imagenes i ON j.id_imagen = i.idImagenes";

        String url = "jdbc:mysql://localhost:3306/japyld";
        try (Connection connection = DriverManager.getConnection(url, "root", "root");
             Statement stmt = connection.createStatement();
             ResultSet resultSet = stmt.executeQuery(sql)) {

            while(resultSet.next()){
                TodosJuegosDto jp = new TodosJuegosDto();
                jp.setIdJuegos(resultSet.getInt(1));
                jp.setNombreJuegos(resultSet.getString(2));
                jp.setPrecio(resultSet.getInt(3));
                jp.setDireccion_imagen(resultSet.getString(4));
                jp.setStock(resultSet.getInt(5));
                jp.setEstado_juego(resultSet.getString(6));
                tjuegos.add(jp);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return tjuegos;
    }
    //Buscar
    public ArrayList<PaginaPrincipalDto> buscarJuegoPorNombre(String name) {

        ArrayList<PaginaPrincipalDto> listaJuegos = new ArrayList<>();

        String sql = "SELECT idJuegos, nombreJuegos, precio, direccion_archivo\n" +
                "FROM juegos j\n" +
                "INNER JOIN imagenes i ON j.id_imagen = i.idImagenes\n" +
                "WHERE j.nombreJuegos LIKE ?;";


        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            name = '%'+ name +'%';
            pstmt.setString(1, name);

            try (ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {
                    PaginaPrincipalDto juego = new PaginaPrincipalDto();
                    juego.setIdJuegos(rs.getInt(1));
                    juego.setNombreJuegos(rs.getString(2));
                    juego.setPrecio(rs.getInt(3));
                    juego.setDireccion_imagen(rs.getString(4));

                    listaJuegos.add(juego);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listaJuegos;
    }

    // Borrar juego
    public void borrarjuego(int idJuegos) throws SQLException {

        String sql = "UPDATE juegos  \n" +
                "SET estadoJuego = 'Eliminado'\n" +
                "WHERE idJuegos = ?;";
        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setInt(1, idJuegos);
            pstmt.executeUpdate();
        }
    }
    public Juegos obetenerJuego(int idJuegos) {

        Juegos juegos = null;

        String sql = "SELECT * FROM juegos j \n" +
                "left join categorias c on j.id_categoria = c.idCategorias\n" +
                "where j.idJuegos = ?;";

        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idJuegos);

            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {
                    juegos = new Juegos();
                    fetchJuegosData(juegos, rs);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return juegos;
    }



}
