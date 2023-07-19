<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.proyecto_final_base_japyld.UsuarioJapyld.ModelsJ.DtoJ.MasDetallesDto" %>
<%@ page import="com.example.proyecto_final_base_japyld.UsuarioJapyld.ModelsJ.DtoJ.CompraDto" %>
<%@ page import="com.example.proyecto_final_base_japyld.UsuarioJapyld.ModelsJ.DtoJ.ImagenPerfilDto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="textoBusqueda" scope="request" type="java.lang.String" class="java.lang.String"/>
<% ArrayList<CompraDto> listaDetallesCompra = (ArrayList<CompraDto>) request.getAttribute("listaDetallesCompra"); %>
<% ArrayList<ImagenPerfilDto> listaFotoPerfil = (ArrayList<ImagenPerfilDto>) request.getAttribute("listaFotoPerfil"); %>
<jsp:useBean id="personaSession" type="com.example.proyecto_final_base_japyld.BeansGenerales.Personas" scope="session" class="com.example.proyecto_final_base_japyld.BeansGenerales.Personas"/>

<html>

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="stylesheet" href="https://unpkg.com/leaflet@1.9.4/dist/leaflet.css" integrity="sha256-p4NxAoJBhIIN+hmNHrzRCf9tD/miZyoHS5obTRR9BMY=" crossorigin="" />
    <script src="https://unpkg.com/leaflet@1.9.4/dist/leaflet.js" integrity="sha256-20nQCchB9co0qIjJZRGuk2/Z9VM+kNiyxNV1lvTlZBo=" crossorigin=""></script>
    <!-- Agrega el enlace a Leaflet.css -->
    <link rel="stylesheet" href="https://unpkg.com/leaflet@1.9.4/dist/leaflet.css" integrity="sha256-p4NxAoJBhIIN+hmNHrzRCf9tD/miZyoHS5obTRR9BMY=" crossorigin="">

    <!-- Agrega el enlace a Leaflet.js -->
    <script src="https://unpkg.com/leaflet@1.9.4/dist/leaflet.js" integrity="sha256-20nQCchB9co0qIjJZRGuk2/Z9VM+kNiyxNV1lvTlZBo=" crossorigin=""></script>

    <!-- Agrega el enlace a Leaflet.Geocoder.js -->
    <script src="https://unpkg.com/leaflet-control-geocoder/dist/Control.Geocoder.js"></script>


    <title>SB Admin 2 - Charts</title>

    <!-- Custom fonts for this template-->
    <link href="recursos/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="recursos/css/sb-admin-2.min.css" rel="stylesheet">

    <style>
        .img_juego_grande {
            width: 230px;
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
    <jsp:include page="/Includes/UsuarioJapyld/SidebarGeneral.jsp">
        <jsp:param name="title" value="Pagina Principal"/>
    </jsp:include>
    <!-- End of Sidebar -->

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

        <!-- Main Content -->
        <div id="content">

            <!-- Topbar -->
            <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">
                <!-- Sidebar Toggle (Topbar) -->
                <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                    <i class="fa fa-bars"></i>
                </button>

                <!-- Topbar Search -->

                <!-- Topbar Navbar -->
                <ul class="navbar-nav ml-auto"> <!-- Eliminamos "mr-auto" y "ml-auto" -->
                    <!-- Nav Item - Search Dropdown (Visible Only XS) -->
                    <!-- Nav Item - Alerts -->
                    <!-- Nav Item - Messages -->
                    <div class="topbar-divider d-none d-sm-block"></div>

                    <!-- Nav Item - User Information -->
                    <li class="nav-item dropdown no-arrow">
                        <a class="nav-link dropdown-toggle d-flex align-items-center" href="#" id="userDropdown" role="button"
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <span class="mr-2 d-none d-lg-inline text-gray-600 small user-name"><%=personaSession.getNombre()%></span>
                            <% for (ImagenPerfilDto fotoperfil : listaFotoPerfil) { %>
                            <% if (personaSession.getIdPersona() == fotoperfil.getIdPersona()) { %>
                            <img class="img-gaga"
                                 src="<%=fotoperfil.getDireccion_archivo()%>"
                                 alt="Avatar">
                            <% } %>
                            <% } %>
                        </a>
                        <!-- Dropdown - User Information -->
                        <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                             aria-labelledby="userDropdown">
                            <a class="dropdown-item" href="<%=request.getContextPath()%>/PerfilUsuarioServlet">
                                <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                                Ver Perfil
                            </a>

                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="<%=request.getContextPath()%>/LoginServlet?action=logout" data-toggle="modal" data-target="#logoutModal">
                                <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                Salir
                            </a>
                        </div>
                    </li>
                </ul>
            </nav>
            <!-- End of Topbar -->


            <!-- Begin Page Content -->
            <div class="container-fluid">
                <!-- Inicio Form Compra Juego -->
                <form method="POST" action="<%=request.getContextPath()%>/PaginaCompra">
                    <!-- Page Heading -->
                    <% for (CompraDto juegocompra : listaDetallesCompra){ %>
                    <h1 class="h3 mb-2 text-gray-700">Página de Compra</h1>
                    <!-- Content Row -->
                    <div class="row">
                        <div class="col-xl-4 col-lg-4">
                            <!-- Donut Chart -->
                            <div class="card shadow mb-4 border-left-primary">
                                <div class="card-header py-3">
                                    <h6 class="m-0 font-weight-bold text-primary"><%=juegocompra.getNombreJuego()%></h6>
                                </div>
                                <div class="card-body text-center">
                                    <img src="<%=request.getContextPath()%>/Image?act=juego&id=<%=juegocompra.getIdImagen()%>" class="img-fluid img_juego_grande" alt="Imagen">
                                    <div class="mt-3">
                                    </div>
                                </div>
                            </div>
                            <div class="card shadow mb-4 border-left-primary">
                                <div class="card-header py-3">
                                    <h6 class="m-0 font-weight-bold text-primary">Datos de pago:</h6>
                                </div>
                                <div class="card-body text-center">
                                    <div class="form-group">
                                        <label class="font-weight-bold h4">Introduzca su número de Tarjeta</label>
                                        <input type="text" class="form-control bg-gradient-light" name="NumeroTarjeta" placeholder="XXXX-XXXX-XXXX-XXXX">
                                    </div>
                                    <div class="form-group">
                                        <label class="font-weight-bold h4">CVV</label>
                                        <input type="text" class="form-control bg-gradient-light" name="CVV" placeholder="XXX">
                                    </div>
                                    <!-- Ahora si pondremos los datos para mandar en el forms -->
                                    <input type="hidden" name="idUsuario" value="<%=personaSession.getIdPersona()%>">
                                    <input type="hidden" name="idConsola" value="<%=juegocompra.getIdConsola()%>">
                                    <input type="hidden" name="Precio" value="<%=juegocompra.getPrecio()%>">
                                    <input type="hidden" name="PrecioNuevo" value="<%=juegocompra.getPrecio_nuevo()%>">
                                    <input type="hidden" name="idJuego" value="<%=juegocompra.getIdJuego()%>">
                                    <button class ="btn btn-lg btn-success btn-block" type="submit">Comprar</button>
                                    <%if(request.getParameter("error") == null) {%>
                                    <%}else{%>
                                    <% if (request.getParameter("error").equals("error1")) { %>
                                    <div class = "text-danger justify-content-center"><span>La dirección de entrega de tu juego debe estar completada.</span></div>
                                    <% } %>
                                    <% if (request.getParameter("error").equals("error2")) { %>
                                    <div class = "text-danger justify-content-center"><span>Tu número de tarjeta no es válido. Recuerda que debes seguir el formato y empezar con 4 o 5.</span></div>
                                    <% } %>
                                    <% if (request.getParameter("error").equals("error3")) { %>
                                    <div class = "text-danger justify-content-center"><span>Tu CVV no es válido.</span></div>
                                    <% } %>
                                    <%}%>
                                </div>
                            </div>
                        </div>
                        <div class="col-xl-8 col-lg-8">
                            <!-- Primer cuadro -->
                            <div class="card shadow mb-4">
                                <div class="card-header py-3">
                                    <h6 class="m-0 font-weight-bold text-primary">Resumen del Juego</h6>
                                </div>
                                <div class="card-body">
                                    <div class="d-flex flex-column">
                                        <div class="btn-info mb-2 disabled text-center" style="padding: 0.375rem 0.75rem; border: 1px solid transparent; border-radius: 0.25rem; background-color: #36b9cc;">
                                            Categoría:
                                        </div>
                                        <div class="btn-light mb-2 disabled text-center" style="padding: 0.375rem 0.75rem; border: 1px solid transparent; border-radius: 0.25rem; background-color: #f8f9fc;">
                                            <%=juegocompra.getNombreCategoria()%>
                                        </div>
                                        <div class="btn-info mb-2 disabled text-center" style="padding: 0.375rem 0.75rem; border: 1px solid transparent; border-radius: 0.25rem; background-color: #36b9cc;">
                                            Consola:
                                        </div>
                                        <div class="btn-light mb-2 disabled text-center" style="padding: 0.375rem 0.75rem; border: 1px solid transparent; border-radius: 0.25rem; background-color: #f8f9fc;">
                                            <%=juegocompra.getIdConsola()%>
                                        </div>
                                        <% if (juegocompra.getPrecio_nuevo() == 0) { %>
                                        <div class="btn-info mb-2 disabled text-center" style="padding: 0.375rem 0.75rem; border: 1px solid transparent; border-radius: 0.25rem; background-color: #36b9cc;">
                                            Precio:
                                        </div>
                                        <div class="btn-light mb-2 disabled text-center" style="padding: 0.375rem 0.75rem; border: 1px solid transparent; border-radius: 0.25rem; background-color: #f8f9fc;">
                                            S/.<%=juegocompra.getPrecio()%>
                                        </div>
                                        <% } else { %>
                                        <div class="btn-info mb-2 disabled text-center" style="padding: 0.375rem 0.75rem; border: 1px solid transparent; border-radius: 0.25rem; background-color: #36b9cc;">
                                            Precio:
                                        </div>
                                        <div class="btn-light mb-2 disabled text-center" style="padding: 0.375rem 0.75rem; border: 1px solid transparent; border-radius: 0.25rem; background-color: #f8f9fc;">
                                            S/.<%=juegocompra.getPrecio_nuevo()%>
                                        </div>
                                        <% } %>
                                    </div>
                                </div>
                            </div>
                            <% } %>
                            <!-- Segundo cuadro -->
                            <div class="card shadow mb-4">
                                <div class="card-header py-3">
                                    <h6 class="m-0 font-weight-bold text-primary">Dirección de entrega</h6>
                                </div>
                                <div class="card-body">
                                    <div id="address-container">
                                        <input type="text" id="address" name="Direccion" placeholder="Dirección" class="form-control">
                                    </div>
                                    <hr>
                                    <div id="map" style="height: 400px;"></div>
                                </div>
                            </div>

                        </div>

                    </div>
                </form>
                <!-- Form para comprar juego -->
            </div>

        </div>
        <!-- /.container-fluid -->

    </div>
    <!-- End of Main Content -->

    <!-- Footer -->

    <!-- End of Footer -->

</div>
<!-- End of Content Wrapper -->

</div>
<!-- End of Page Wrapper -->

<!-- Scroll to Top Button-->
<a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
</a>

<!-- Logout Modal-->
<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Cerrar sesión</h5>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div>
            <div class="modal-body">Seleccione "Confirmar" si desera salir de su cuenta.</div>
            <div class="modal-footer">
                <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancelar</button>
                <a class="btn btn-primary" href="<%=request.getContextPath()%>/LoginServlet?action=logout">Confirmar</a>
            </div>
        </div>
    </div>
</div>

<script>
    var map = L.map('map').setView([-12.0691658, -77.0799348336087], 15);
    var marker;

    L.tileLayer('https://api.mapbox.com/styles/v1/mapbox/streets-v11/tiles/{z}/{x}/{y}?access_token=pk.eyJ1IjoicGFibG8xMjN4IiwiYSI6ImNsanRuZ3N0OTB2dmYzZWtjODhneWQxaHgifQ.eu01nLDmz8SJzzYbUwJfKQ', {
        attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
        tileSize: 512,
        zoomOffset: -1
    }).addTo(map);


    // Función para mostrar la ubicación en el mapa
    function showLocationOnMap(address) {
        // Elimina el marcador anterior, si existe
        if (marker) {
            marker.remove();
        }

        // Convierte la dirección a coordenadas
        var geocodeService = L.Control.Geocoder.nominatim();
        geocodeService.geocode(address, function(results) {
            if (results.length > 0) {
                var location = results[0].center;
                var latlng = L.latLng(location.lat, location.lng);

                // Crea un nuevo marcador en la ubicación
                marker = L.marker(latlng).addTo(map);
                map.setView(latlng, 20);
            }
        });
    }

    // Agrega un controlador de clic al mapa
    map.on('click', function(event) {
        var latlng = event.latlng;

        // Convierte las coordenadas a una dirección
        var geocodeService = L.Control.Geocoder.nominatim();
        geocodeService.reverse(latlng, map.options.crs.scale(map.getZoom()), function(results) {
            if (results.length > 0) {
                var address = results[0].name;
                var addressBox = document.getElementById('address');
                addressBox.value = address;

                // Muestra la ubicación de la dirección en el mapa
                showLocationOnMap(address);
            }
        });
    });

    // Agrega un controlador de evento al cuadro de texto
    var addressBox = document.getElementById('address');
    addressBox.addEventListener('change', function() {
        var address = addressBox.value;
        if (address) {
            showLocationOnMap(address);
        } else {
            // Si el cuadro de texto está vacío, borra el marcador
            if (marker) {
                marker.remove();
                marker = null;
            }
        }
    });
</script>






<!-- Bootstrap core JavaScript-->
<script src="recursos/vendor/jquery/jquery.min.js"></script>
<script src="recursos/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="recursos/vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="recursos/js/sb-admin-2.min.js"></script>

<!-- Page level plugins -->
<script src="recursos/vendor/chart.js/Chart.min.js"></script>

<!-- Page level custom scripts -->
<script src="recursos/js/demo/chart-area-demo.js"></script>
<script src="recursos/js/demo/chart-pie-demo.js"></script>
<script src="recursos/js/demo/chart-bar-demo.js"></script>

</body>

</html>
