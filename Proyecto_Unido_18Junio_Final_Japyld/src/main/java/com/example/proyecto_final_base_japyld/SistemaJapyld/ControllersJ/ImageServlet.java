package com.example.proyecto_final_base_japyld.SistemaJapyld.ControllersJ;

import com.example.proyecto_final_base_japyld.SistemaJapyld.ModelsJ.DaosJ.ImageDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "ImageServlet", value = "/Image")
public class ImageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ImageDao imageDao = new ImageDao();
        String action = request.getParameter("act");
        int idToUse = Integer.parseInt(request.getParameter("id"));

        byte[] content = null;
        if (action.equals("juego")) {
            content = imageDao.getGameImage(idToUse);
        } else if (action.equals("venta")) {
            content = imageDao.getOfertimage(idToUse);
        }

        if (content.length == 1 && content[0] == 0) {
            System.out.println("Hubo un error a nivel de SQL o BASE DE DATOS");
        } else if (content.length == 1 && content[0] == 1) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        } else {
            response.setContentType("image/png");
            response.setContentLength(content.length);
            response.getOutputStream().write(content);
        }
    }
}
