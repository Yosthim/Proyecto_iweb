<%--
  Created by IntelliJ IDEA.
  User: PABLO
  Date: 29/06/2023
  Time: 13:51
  To change this template use File | Settings | File Templates.
--%>
<jsp:useBean id="personaSession" type="com.example.proyecto_final_base_japyld.BeansGenerales.Personas" scope="session" class="com.example.proyecto_final_base_japyld.BeansGenerales.Personas"/>
<jsp:useBean id="textoBusqueda" scope="request" type="java.lang.String" class="java.lang.String"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

    <!-- Sidebar Toggle (Topbar) -->
    <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
        <i class="fa fa-bars"></i>
    </button>

    <!-- Topbar Search -->
    <form class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search" method="POST" action="<%=request.getContextPath()%>/PaginaPrincipal">
        <div class="input-group">
            <input type="text" class="form-control bg-light border-0 small" placeholder="Busca el juego que desees en nuestro catálogo"
                   aria-label="Search" aria-describedby="basic-addon2" name = "textoBuscar" value = "<%=textoBusqueda%>"/>
            <div class="input-group-append">
                <button class="btn btn-primary" type="submit">
                    <i class="fas fa-search fa-sm"></i>
                </button>
                <a class="btn btn-danger" href="<%=request.getContextPath()%>/PaginaPrincipal">
                    <i class="fas fa-window-close fa-sm"></i>
                </a>
            </div>
        </div>
    </form>

    <!-- Topbar Navbar -->
    <ul class="navbar-nav ml-auto">
        <!-- Nav Item - Search Dropdown (Visible Only XS) -->
        <li class="nav-item dropdown no-arrow d-sm-none">
            <a class="nav-link dropdown-toggle" href="#" id="searchDropdown" role="button"
               data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <i class="fas fa-search fa-fw"></i>
            </a>
            <!-- Dropdown - Messages -->
            <div class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in"
                 aria-labelledby="searchDropdown">
                <form class="form-inline" method="POST" action="<%=request.getContextPath()%>/PaginaPrincipal">
                    <div class="input-group">
                        <input type="text" class="form-control bg-light border-0 small" placeholder="Busca el juego que desees en nuestro catálogo"
                               aria-label="Search" aria-describedby="basic-addon2" name="textoBuscar" value="<%=textoBusqueda%>">
                        <div class="input-group-append">
                            <button class="btn btn-primary" type="submit">
                                <i class="fas fa-search fa-sm"></i>
                            </button>
                            <a class="btn btn-danger" href="<%=request.getContextPath()%>/PaginaPrincipal">
                                <i class="fas fa-window-close fa-sm"></i>
                            </a>
                        </div>
                    </div>
                </form>
            </div>
        </li>


        <!-- Nav Item - Alerts -->

        <!-- Nav Item - Messages -->

        <div class="topbar-divider d-none d-sm-block"></div>

        <!-- Nav Item - User Information -->
        <li class="nav-item dropdown no-arrow">
            <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
               data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <span class="mr-2 d-none d-lg-inline text-gray-600 small">Bienvenid@ <%=personaSession.getNombre()%></span>
                <img class="img-profile rounded-circle"
                     src="recursos/img/profile-1.png">
            </a>
            <!-- Dropdown - User Information -->
            <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                 aria-labelledby="userDropdown">
                <a class="dropdown-item" href="<%=request.getContextPath()%>/PerfilUsuarioServlet">
                    <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                    Profile
                </a>

                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="<%=request.getContextPath()%>/LoginServlet?action=logout" data-toggle="modal" data-target="#logoutModal">
                    <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                    Logout
                </a>
            </div>
        </li>

    </ul>

</nav>