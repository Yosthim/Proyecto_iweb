<%--
  Created by IntelliJ IDEA.
  User: PABLO
  Date: 29/06/2023
  Time: 18:06
  To change this template use File | Settings | File Templates.
--%>
<jsp:useBean id="personaSession" type="com.example.proyecto_final_base_japyld.BeansGenerales.Personas" scope="session" class="com.example.proyecto_final_base_japyld.BeansGenerales.Personas"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- Sidebar -->
<ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

    <!-- Sidebar - Brand -->
    <a class="sidebar-brand d-flex align-items-center justify-content-center" href="<%=request.getContextPath()%>/PaginaPrincipal">
        <div class="sidebar-brand-icon rotate-n-15">
            <i class="fas fa-laugh-wink"></i>
        </div>
        <div class="sidebar-brand-text mx-3">JAPYLD <sup>TM</sup></div>
    </a>

    <!-- Divider -->

    <!-- Divider -->
    <hr class="sidebar-divider">

    <!-- Heading -->
    <div class="sidebar-heading">
        MÓDULOS
    </div>

    <!-- Nav Item - Pages Collapse Menu -->
    <li class="nav-item">
        <a class="nav-link" href="<%=request.getContextPath()%>/PaginaPrincipal">
            <i class="fas fa-home"></i>
            <span>Página Principal</span></a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="<%=request.getContextPath()%>/TusJuegos?idusuario=<%=personaSession.getIdPersona()%>">
            <i class="fas fa-gamepad"></i>
            <span>Juegos Comprados y Reservados</span>
        </a>

    </li>
    <!-- Nav Item - Charts -->
    <li class="nav-item">
        <a class="nav-link" href="<%=request.getContextPath()%>/TusVentas?idusuario=<%=personaSession.getIdPersona()%>">
            <i class="far fa-money-bill-alt"></i>
            <span>Venta de juegos</span></a>
    </li>

    <!-- Nav Item - Tables -->

    <hr class="sidebar-divider">

    <!-- Sidebar Toggler (Sidebar) -->
    <div class="text-center d-none d-md-inline">
        <button class="rounded-circle border-0" id="sidebarToggle"></button>
    </div>
</ul>
<!-- End of Sidebar -->
