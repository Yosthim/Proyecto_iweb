<%@ page import="com.example.proyecto_final_base_japyld.ManagerJapyld.ModelsJ.DtoJ.JuegosManager" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.proyecto_final_base_japyld.UsuarioJapyld.ModelsJ.DtoJ.ConsolasDetallesDto" %>
<%@ page import="com.example.proyecto_final_base_japyld.BeansGenerales.JuegosCompradosReservados" %><%--
  Created by IntelliJ IDEA.
  User: milene
  Date: 14/06/2023
  Time: 20:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="personaSession" type="com.example.proyecto_final_base_japyld.BeansGenerales.Personas" scope="session" class="com.example.proyecto_final_base_japyld.BeansGenerales.Personas"/>
<% ArrayList<JuegosManager> MasDetallesJuego = (ArrayList<JuegosManager>) request.getAttribute("MasDetallesJuego"); %>
<% ArrayList<ConsolasDetallesDto> listaConsolaPorJuego = (ArrayList<ConsolasDetallesDto>) request.getAttribute("listaConsolaPorJuego");%>
<% ArrayList<JuegosCompradosReservados> listaRating = (ArrayList<JuegosCompradosReservados>) request.getAttribute("listaRating"); %>

<html>
  <head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Detalles de Juegos</title>

    <!-- Custom fonts for this template-->
    <link href="recursos/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="recursos/css/sb-admin-2.min.css" rel="stylesheet">
    <style>
      .img_juego_grande {
        width: 275px;
        height: 340px;
      }

      .img-gaga {
        display: inline-block; /* Hacemos que el contenedor sea inline-block para que solo ocupe el tamaño de la imagen */
        width: 50px; /* Ajusta el tamaño del círculo según tus necesidades */
        height: 50px;
        border-radius: 50%; /* Convertimos el cuadro en un círculo */
        background-size: cover; /* Ajustamos la imagen para que cubra el círculo */
        background-position: center center; /* Centramos la imagen dentro del círculo horizontal y verticalmente */
        background-repeat: no-repeat; /* Evitamos que la imagen se repita */
        margin: 0; /* Quitamos cualquier margen */
        padding: 0; /* Quitamos cualquier relleno */
        border: none; /* Quitamos cualquier borde */
      }


      .nav-link.dropdown-toggle {
        align-items: center;
        padding: 1px 5px; /* Ajustamos el padding horizontal y vertical */
      }
      .user-name {
        color: #333; /* Color del nombre de usuario */
        font-size: 14px; /* Tamaño del texto del nombre de usuario */
      }
    </style>


  </head>

  <body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">

      <!-- Sidebar -->
      <jsp:include page="/Includes/ManagerJapyld/SideBarManager.jsp">
        <jsp:param name="title" value="SideBar General"/>
      </jsp:include>
      <!-- End of Sidebar -->

      <!-- Content Wrapper -->
      <div id="content-wrapper" class="d-flex flex-column">

        <!-- Main Content -->
        <div id="content">

          <jsp:include page="/Includes/ManagerJapyld/topbarManager.jsp">
            <jsp:param name="title" value="Mas detalles"/>
          </jsp:include>

          <% for (JuegosManager juego :MasDetallesJuego){%>
          <div class="container-fluid">

            <!-- Page Heading -->
            <h1 class="h3 mb-2 text-gray-700">Más detalles</h1>
            <!-- Content Row -->
            <div class="row">
              <div class="col-xl-4 col-lg-4">
                <!-- Donut Chart -->
                <div class="card shadow mb-4 border-left-primary">
                  <div class="card-header py-3">
                    <h6 class="m-0 font-weight-bold text-primary"><%=juego.getNombreJuegos()%></h6>
                  </div>
                  <div class="card-body text-center">
                    <img src="<%=request.getContextPath()%>/Image?act=juego&id=<%=juego.getId_imagen()%>" class="img-fluid img_juego" alt="Imagen">
                    <div class="mt-3">
                      <%int contador = 0;%>
                      <%int suma = 0;%>
                      <%int puntuacion = 0;%>
                      <% for (JuegosCompradosReservados rating :listaRating){
                        if(rating.getRating() != 0) {
                          contador = contador + 1;
                          suma = suma + rating.getRating();
                        }
                      }%>
                      <%
                        if(suma != 0 && contador != 0) {
                          puntuacion = suma/contador;
                        }
                      %>
                      <%
                        int validPunt = 0;
                        if(puntuacion >= 0 && puntuacion <= 1){
                          validPunt = 1;
                        }
                        if(puntuacion >= 1 && puntuacion <= 2){
                          validPunt = 2;
                        }
                        if(puntuacion >= 2 && puntuacion <= 3){
                          validPunt = 3;
                        }
                        if(puntuacion >= 3 && puntuacion <= 4){
                          validPunt = 4;
                        }
                        if(puntuacion >= 4 && puntuacion <= 5){
                          validPunt = 5;
                        }
                      %>

                      <h5 class="mb-0">Rating:</h5>
                      <div class="rating">
                        <%if(validPunt == 1){%>
                        <span class="star">&#9733;</span>
                        <%}%>
                        <%if(validPunt == 2){%>
                        <span class="star">&#9733;</span>
                        <span class="star">&#9733;</span>
                        <%}%>
                        <%if(validPunt == 3){%>
                        <span class="star">&#9733;</span>
                        <span class="star">&#9733;</span>
                        <span class="star">&#9733;</span>
                        <%}%>
                        <%if(validPunt == 4){%>
                        <span class="star">&#9733;</span>
                        <span class="star">&#9733;</span>
                        <span class="star">&#9733;</span>
                        <span class="star">&#9733;</span>

                        <%}%>
                        <%if(validPunt == 5){%>
                        <span class="star">&#9733;</span>
                        <span class="star">&#9733;</span>
                        <span class="star">&#9733;</span>
                        <span class="star">&#9733;</span>
                        <span class="star">&#9733;</span>
                        <%}%>
                      </div>
                      <h5>Categoría: <%=juego.getCategoria()%> </h5>
                    </div>
                  </div>
                </div>
              </div>
              <div class="col-xl-8 col-lg-8">
                <!-- Area Chart -->
                <div class="card shadow mb-4">
                  <div class="card-header py-3">
                    <h6 class="m-0 font-weight-bold text-primary">Información del Juego</h6>
                  </div>
                  <div class="card-body">
                    <p class="text-justify"><%=juego.getDescripcion_juego()%></p>
                    <hr>
                    <div class="d-flex align-items-center mb-3">
                      <h6 class="mr-3">Consola:</h6>
                      <div class="row">
                        <% for (ConsolasDetallesDto consola: listaConsolaPorJuego){%>
                        <% if(consola.getConsola1() != null){ %>
                        <div class="col">
                          <div class="btn btn-primary disabled" style="padding: 0.375rem 0.75rem; border: 1px solid transparent; border-radius: 0.25rem; background:#4e73de ">PS5</div>
                        </div>
                        <% } %>
                        <% if(consola.getConsola2() != null){ %>
                        <div class="col">
                          <div class="btn btn-primary disabled" style="padding: 0.375rem 0.75rem; border: 1px solid transparent; border-radius: 0.25rem; background:#4e73de ">PS4</div>
                        </div>
                        <% } %>
                        <% if(consola.getConsola3() != null){ %>
                        <div class="col">
                          <div class="btn btn-primary disabled" style="padding: 0.375rem 0.75rem; border: 1px solid transparent; border-radius: 0.25rem; background:#4e73de ">XB3</div>
                        </div>
                        <% } %>
                        <% if(consola.getConsola4() != null){ %>
                        <div class="col">
                          <div class="btn btn-primary disabled" style="padding: 0.375rem 0.75rem; border: 1px solid transparent; border-radius: 0.25rem; background:#4e73de ">XBO</div>
                        </div>
                        <% } %>
                        <% if(consola.getConsola5() != null){ %>
                        <div class="col">
                          <div class="btn btn-primary disabled" style="padding: 0.375rem 0.75rem; border: 1px solid transparent; border-radius: 0.25rem; background:#4e73de ">SWI</div>
                        </div>
                        <% } %>
                        <% if(consola.getConsola6() != null){ %>
                        <div class="col">
                          <div class="btn btn-primary disabled" style="padding: 0.375rem 0.75rem; border: 1px solid transparent; border-radius: 0.25rem; background:#4e73de ">WIU</div>
                        </div>
                        <% } %>
                        <% } %>
                      </div>
                    </div>
                    <hr>
                    <div class="d-flex justify-content-between">
                      <div>
                        <% if (juego.getPrecio_nuevo() == 0) { %>
                        <div class="btn-success disabled" style="padding: 0.375rem 0.75rem; border: 1px solid transparent; border-radius: 0.25rem; background:#1cc88a ">
                          Precio: S/. <%= juego.getPrecio() %>
                        </div>
                        <% } else { %>
                        <div class="btn-success disabled" style="padding: 0.375rem 0.75rem; border: 1px solid transparent; border-radius: 0.25rem; background-image:#1cc88a ">
                          Precio: S/. <%= juego.getPrecio_nuevo() %>
                        </div>
                        <% } %>
                      </div>

                      <div id="comprarButton">
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>

          </div>

          <% } %>
        </div>
        <!-- End of Main Content -->

        <!-- Footer -->
        <footer class="sticky-footer bg-white">
          <div class="container my-auto">
            <div class="copyright text-center my-auto">
              <span>Copyright &copy; Your Website 2020</span>
            </div>
          </div>
        </footer>
        <!-- End of Footer -->

      </div>
      <!-- End of Content Wrapper -->

    </div>
    <!-- End of Page Wrapper -->

    <!-- Scroll to Top Button-->
    <jsp:include page="/Includes/ManagerJapyld/Adicionales.jsp">
      <jsp:param name="title" value="Adicionales"/>
    </jsp:include>

  </body>
</html>
