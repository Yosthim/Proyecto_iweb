<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Por Genero de Juego</title>

    <title>Gráfico de Barras con Bootstrap 4</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">

    <style>
        /* Estilos personalizados */
        .custom-textbox {
            border: 2px solid #ffffff;
            border-radius: 10px;
            padding: 10px;
            font-size: 18px;
            color: #333;
            background-color: #F1F7F6;
            outline: none;
        }

        .custom-textbox:focus {
            border-color: #29B6A5;
            box-shadow: 0 0 0 3px rgba(41, 182, 165, 0.2);
        }
    </style>

    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

    <!-- Custom fonts for this template-->
    <link href="recursos/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="recursos/css/sb-admin-2.min.css" rel="stylesheet">

</head>

<body id="page-top">

<!-- Page Wrapper -->
<div id="wrapper">

    <jsp:include page="/Includes/ManagerJapyld/SideBarManager.jsp">
        <jsp:param name="title" value="SideBar General"/>
    </jsp:include>

    <!-- Sidebar -->

    <!-- End of Sidebar -->

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">
        <!-- Main Content -->
        <div id="content">

            <jsp:include page="/Includes/ManagerJapyld/topbarManager.jsp">
                <jsp:param name="title" value="Mas detalles"/>
            </jsp:include>
            <!-- Begin Page Content -->
            <div class="container-xl px-4 mt-4">
                <!-- Account page navigation-->
                <br>

                <div class="container-xl px-4 mt-4">
                    <br>
                    <br>

                    <div class="row justify-content-center">
                        <div class="col-xl-10">
                            <!-- Account details card -->
                            <div class="card mb-4">
                                <div class="card-header m-0 font-weight-bold text">MENSAJE NUEVO</div>
                                <form action="<%=request.getContextPath()%>/CorreoAdminServlet" method="post">
                                    <div class="container">
                                        <!-- Destinatario -->
                                        <br>
                                        <h6 class="text-primary" style="color:#31a290;">Destinatario</h6>
                                        <div class="d-flex">
                                            <div id="texto-editable" contenteditable="true" class="flex-grow-1 pr-3 custom-textbox" oninput="updateDestinoField()"></div>
                                            <input type="hidden" name="destino" id="asuntoInput">
                                        </div>
                                        <br>
                                        <!-- Asunto -->
                                        <h6 class="text-primary" style="color:#31a290;">Asunto</h6>

                                        <div class="d-flex">
                                            <div id="texto-editable2" contenteditable="true" class="flex-grow-1 pr-3 custom-textbox" oninput="updateAsuntoField()"></div>
                                            <input type="hidden" name="asunto" id="asuntoInput2">
                                        </div>

                                        <br>
                                        <h6 class="text-primary" style="color:#31a290;">Mensaje</h6>
                                        <div class="d-flex">
                                            <div id="texto-editable3" contenteditable="true" class="flex-grow-1 pr-3 custom-textbox" oninput="updateMensajeField()"></div>
                                            <input type="hidden" name="mensaje" id="asuntoInput3">
                                        </div>

                                        <script>
                                            function updateDestinoField() {
                                                var textoEditable = document.getElementById("texto-editable");
                                                var destinoInput = document.getElementById("asuntoInput");
                                                destinoInput.value = textoEditable.innerText.trim();
                                            }

                                            function updateAsuntoField() {
                                                var textoEditable2 = document.getElementById("texto-editable2");
                                                var asuntoInput = document.getElementById("asuntoInput2");
                                                asuntoInput.value = textoEditable2.innerText.trim();
                                            }

                                            function updateMensajeField() {
                                                var textoEditable3 = document.getElementById("texto-editable3");
                                                var mensajeInput = document.getElementById("asuntoInput3");
                                                mensajeInput.value = textoEditable3.innerText.trim();
                                            }
                                        </script>

                                    </div>
                                    <!-- Resto del código del formulario -->
                                    <div class="card-body center-h center-h">
                                        <div class="col-12 col-xl-auto mb-3">
                                            <div class="d-flex justify-content-left">
                                                <div class="d-flex justify-content-between">
                                                    <div class="row">
                                                        <!-- Cancelar -->
                                                        <a href="<%= request.getContextPath()%>/ModuloAdminServlet">
                                                            <button class="btn btn-light alert-danger" type="button" style="margin-right: 20px;">Cancelar</button>
                                                        </a>
                                                        <!-- Enviar (Modificar el botón para que sea un submit) -->
                                                        <button class="btn btn-primary" type="submit">Enviar</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                </form>
                            </div>
                        </div>
                    </div>

                    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
                    <script>
                        $(document).ready(function() {
                            // Obtener el cuadro de texto editable y los botones
                            var textoEditable = $('#texto-editable');
                            var editarBtn = $('#editar-btn');
                            var guardarBtn = $('#guardar-btn');

                            // Al hacer clic en el botón "Editar"
                            editarBtn.click(function() {
                                // Habilitar la edición del cuadro de texto
                                textoEditable.attr('contenteditable', 'true');
                                textoEditable.focus();

                                // Ocultar el botón "Editar" y mostrar el botón "Guardar"
                                editarBtn.hide();
                                guardarBtn.show();
                            });

                            // Al hacer clic en el botón "Guardar"
                            guardarBtn.click(function() {
                                // Deshabilitar la edición del cuadro de texto
                                textoEditable.attr('contenteditable', 'false');

                                // Ocultar el botón "Guardar" y mostrar el botón "Editar"
                                guardarBtn.hide();
                                editarBtn.show();
                            });
                        });
                    </script>
                    <script2 src="https://code.jquery.com/jquery-3.6.0.min.js"></script2>
                    <script>
                        $(document).ready(function() {
                            // Obtener el cuadro de texto editable y los botones
                            var textoEditable2 = $('#texto-editable');
                            var editarBtn2 = $('#editar-btn');
                            var guardarBtn2 = $('#guardar-btn');

                            // Al hacer clic en el botón "Editar"
                            editarBtn2.click(function() {
                                // Habilitar la edición del cuadro de texto
                                textoEditable2.attr('contenteditable', 'true');
                                textoEditable2.focus();

                                // Ocultar el botón "Editar" y mostrar el botón "Guardar"
                                editarBtn2.hide();
                                guardarBtn2.show();
                            });

                            // Al hacer clic en el botón "Guardar"
                            guardarBtn2.click(function() {
                                // Deshabilitar la edición del cuadro de texto
                                textoEditable2.attr('contenteditable', 'false');

                                // Ocultar el botón "Guardar" y mostrar el botón "Editar"
                                guardarBtn2.hide();
                                editarBtn2.show();
                            });
                        });
                    </script>
                </div>
                <br>
                <br>
            </div>
            <br>
        </div>
        <!-- End of Main Content -->

        <!-- Footer -->
        <footer class="sticky-footer bg-white">
            <div class="container my-auto">
                <div class="copyright text-center my-auto">
                    <span>Copyright &copy; Your Website 2021</span>
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

<!-- Logout Modal-->


</body>

</html>
