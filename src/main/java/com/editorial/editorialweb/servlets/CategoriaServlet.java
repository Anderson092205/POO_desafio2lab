package com.editorial.editorialweb.servlets;

import com.editorial.editorialweb.beans.Categoria;
import com.editorial.editorialweb.dao.CategoriaDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class CategoriaServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CategoriaDAO dao = new CategoriaDAO();
        String action = request.getParameter("action");

        if ("listar".equals(action)) {
            List<Categoria> categorias = dao.listar();
            request.setAttribute("categorias", categorias);
            request.getRequestDispatcher("/categoria/listar.jsp").forward(request, response);
        } else if ("editar".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            Categoria categoria = dao.obtenerPorId(id);
            request.setAttribute("categoria", categoria);
            request.getRequestDispatcher("/categoria/formulario.jsp").forward(request, response);
        } else if ("eliminar".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            dao.eliminar(id);
            response.sendRedirect("CategoriaServlet?action=listar");
        } else if ("nuevo".equals(action)) {
            request.setAttribute("categoria", new Categoria());
            request.getRequestDispatcher("/categoria/formulario.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CategoriaDAO dao = new CategoriaDAO();
        String idStr = request.getParameter("id");
        String nombre = request.getParameter("nombre");

        Categoria categoria = new Categoria();
        categoria.setNombre(nombre);

        if (idStr == null || idStr.isEmpty()) {
            dao.insertar(categoria);
        } else {
            categoria.setId(Integer.parseInt(idStr));
            dao.actualizar(categoria);
        }

        response.sendRedirect("CategoriaServlet?action=listar");
    }
}