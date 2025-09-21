package com.editorial.editorialweb.servlets;

import com.editorial.editorialweb.beans.*;
import com.editorial.editorialweb.dao.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class MaterialServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MaterialDAO dao = new MaterialDAO();
        String action = request.getParameter("action");

        if ("listar".equals(action)) {
            List<Material> materiales = dao.listar();
            request.setAttribute("materiales", materiales);
            request.getRequestDispatcher("/material/listar.jsp").forward(request, response);
        } else if ("editar".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            Material material = dao.obtenerPorId(id);
            List<Categoria> categorias = new CategoriaDAO().listar();
            List<Autor> autores = new AutorDAO().listar();
            request.setAttribute("material", material);
            request.setAttribute("categorias", categorias);
            request.setAttribute("autores", autores);
            request.getRequestDispatcher("/material/formulario.jsp").forward(request, response);
        } else if ("eliminar".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            dao.eliminar(id);
            response.sendRedirect("MaterialServlet?action=listar");
        } else if ("nuevo".equals(action)) {
            List<Categoria> categorias = new CategoriaDAO().listar();
            List<Autor> autores = new AutorDAO().listar();
            request.setAttribute("material", new Material());
            request.setAttribute("categorias", categorias);
            request.setAttribute("autores", autores);
            request.getRequestDispatcher("/material/formulario.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MaterialDAO dao = new MaterialDAO();
        String idStr = request.getParameter("id");
        String titulo = request.getParameter("titulo");
        String tipo = request.getParameter("tipo");
        int idCategoria = Integer.parseInt(request.getParameter("id_categoria"));
        int idAutor = Integer.parseInt(request.getParameter("id_autor"));

        Categoria categoria = new CategoriaDAO().obtenerPorId(idCategoria);
        Autor autor = new AutorDAO().obtenerPorId(idAutor);

        Material material = new Material();
        material.setTitulo(titulo);
        material.setTipo(tipo);
        material.setCategoria(categoria);
        material.setAutor(autor);

        if (idStr == null || idStr.isEmpty()) {
            dao.insertar(material);
        } else {
            material.setId(Integer.parseInt(idStr));
            dao.actualizar(material);
        }

        response.sendRedirect("MaterialServlet?action=listar");
    }
}