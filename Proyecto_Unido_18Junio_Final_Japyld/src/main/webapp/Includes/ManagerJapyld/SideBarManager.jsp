<%--
  Created by IntelliJ IDEA.
  User: milene
  Date: 16/07/2023
  Time: 19:16
  To change this template use File | Settings | File Templates.
--%>
<jsp:useBean id="personaSession" type="com.example.proyecto_final_base_japyld.BeansGenerales.Personas" scope="session" class="com.example.proyecto_final_base_japyld.BeansGenerales.Personas"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!-- Sidebar -->
<ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

    <!-- Sidebar - Brand -->


    <a class="sidebar-brand d-flex align-items-center justify-content-center" href="<%=request.getContextPath()%>/ManagerServlet">
        <div class="sidebar-brand-icon rotate-n-15">
            <i class="fas fa-laugh-wink"></i>
        </div>
        <div class="sidebar-brand-text mx-3">JAPYLD <sup>TM</sup></div>
    </a>

    <!-- Divider -->
    <hr class="sidebar-divider my-0">
    <!-- Nav Item - Pages Collapse Menu -->
    <li class="nav-item active">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTwo"
           aria-expanded="true" aria-controls="collapseTwo">
            <i class="fas fa-home"></i>
            <span>Panel Principal</span></a>
        </a>
        <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
            <div class="bg-white py-2 collapse-inner rounded">
                <h6 class="collapse-header">Detalles de JAPYLD:</h6>
                <a class="collapse-item" href="<%=request.getContextPath()%>/ManagerServlet?a=resumen">Resumen</a>
                <a class="collapse-item" href="<%=request.getContextPath()%>/ManagerServlet?a=productos">Productos</a>
                <a class="collapse-item" href="<%=request.getContextPath()%>/ManagerServlet?a=metas">Metas Mensuales</a>
                <a class="collapse-item" href="<%=request.getContextPath()%>/ManagerServlet?a=estadisticasVentas">Estadísticas</a>
            </div>
        </div>
    </li>

    <!-- Divider -->
    <hr class="sidebar-divider">

    <!-- Heading -->
    <div class="sidebar-heading">
        MÓDULOS
    </div>

    <!-- Nav Item - Pages Collapse Menu -->
    <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapsePages"
           aria-expanded="true" aria-controls="collapsePages">
            <i class="fas fa-gamepad"></i>
            <span>Juegos</span>
        </a>
        <div id="collapsePages" class="collapse" aria-labelledby="headingPages" data-parent="#accordionSidebar">
            <div class="bg-white py-2 collapse-inner rounded">
                <h6 class="collapse-header">Estadísticas:</h6>
                <a class="collapse-item" href="<%=request.getContextPath()%>/PorCategoriaJuegoServlet">Por categoría de Juego</a>
                <a class="collapse-item" href="<%=request.getContextPath()%>/PorConsolaJuegoServlet">Por consola de Juego</a>
                <h6 class="collapse-header">TOP 5:</h6>
                <a class="collapse-item" href="<%=request.getContextPath()%>/Top5MasVendidosServlet">Más vendidos</a>
                <a class="collapse-item" href="<%=request.getContextPath()%>/Top5MenosVendidosServlet">Menos vendidos</a>
                <div class="collapse-divider"></div>
                <h6 class="collapse-header">Más opciones:</h6>
                <a class="collapse-item" href="<%=request.getContextPath()%>/EditarJuegosServlet?a=l">Eliminar Juegos</a>
            </div>
        </div>
    </li>

    <!-- Nav Item - Charts -->
    <li class="nav-item">
        <a class="nav-link" href="<%=request.getContextPath()%>/ModuloAdminServlet">
            <i class="fas fa-user-shield"></i>
            <span>Administrador</span></a>
    </li>

    <!-- Nav Item - Tables -->
    <li class="nav-item">
        <a class="nav-link" href="<%=request.getContextPath()%>/ModuloUsuarioServlet">
            <i class="fas fa-user"></i>
            <span>Usuario</span></a>
    </li>

    <!-- Divider -->
    <hr class="sidebar-divider d-none d-md-block">
    <br>

    <!-- Sidebar Toggler (Sidebar) -->
    <div class="text-center d-none d-md-inline">
        <button class="rounded-circle border-0" id="sidebarToggle"></button>
    </div>

</ul>
