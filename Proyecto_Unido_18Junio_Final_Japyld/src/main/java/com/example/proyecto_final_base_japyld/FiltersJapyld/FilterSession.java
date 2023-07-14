package com.example.proyecto_final_base_japyld.FiltersJapyld;

import com.example.proyecto_final_base_japyld.BeansGenerales.Personas;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(filterName = "FiltroSesion",
        servletNames = {"PaginaPrincipalJuegosServlet","CompraServlet","JuegosComResServlet","JuegosXCategoriaServlet","MasDetallesServlet",
                "OfertaServlet","PerfilUsuarioServlet","VentaJuegosServlet","AdminServlet","DescuentoServlet",
                "JuegosNuevosServlet","JuegosReservadosServlet","OfertasServlet","PerfilAdmiServlet","DetalleAdminServlet",
                "DetalleUsuarioServlet","EditarJuegosServlet","ManagerServlet","MasDetallesJuegosServlet","ModuloAdminServlet","ModuloUsuarioServlet",
                "PerfilManagerServlet","PorCategoriaJuegoServlet","PorConsolaJuegoServlet","Top5MasVendidosServlet","Top5MenosVendidosServlet"})
public class FilterSession implements Filter {
    @Override
    public void doFilter(ServletRequest req,ServletResponse resp,FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        Personas persona = (Personas) request.getSession().getAttribute("personaSession");

        if (persona == null) {
            response.sendRedirect(request.getContextPath());
        } else {
            response.setHeader("Cache-Control","no-cache, no-store, must-revalidate");
            response.setHeader("Pragma","no-cache");
            response.setDateHeader("Expires",0);
            chain.doFilter(request,response);
        }
    }
}
