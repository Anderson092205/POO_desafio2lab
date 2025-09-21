package com.editorial.editorialweb.servlets;

import com.editorial.editorialweb.beans.Autor;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/AutorServlet")
public class AutorServlet extends HttpServlet {

    // Simulación en memoria
    private static List<Autor> autoresSimulados = new ArrayList<>();
    private static int contadorId = 1;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("listar".equals(action)) {
            request.setAttribute("autores", autoresSimulados);
            request.getRequestDispatcher("/autores/listar.jsp").forward(request, response);

        } else if ("editar".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            Autor autor = null;
            for (Autor a : autoresSimulados) {
                if (a.getId() == id) {
                    autor = a;
                    break;
                }
            }
            request.setAttribute("autor", autor);
            request.getRequestDispatcher("/autores/formulario.jsp").forward(request, response);

        } else if ("eliminar".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            autoresSimulados.removeIf(a -> a.getId() == id);
            response.sendRedirect("AutorServlet?action=listar");

        } else if ("nuevo".equals(action)) {
            request.setAttribute("autor", new Autor());
            request.getRequestDispatcher("/autores/formulario.jsp").forward(request, response);
        } else {
            // Acción por defecto si no se reconoce
            response.sendRedirect("AutorServlet?action=listar");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        String nombre = request.getParameter("nombre");
        String nacionalidad = request.getParameter("nacionalidad");

        if (nombre == null || nombre.trim().isEmpty() || nacionalidad == null || nacionalidad.trim().isEmpty()) {
            // Validación básica: si falta nombre o nacionalidad, redirige al formulario
            request.setAttribute("error", "Todos los campos son obligatorios.");
            Autor autor = new Autor();
            autor.setNombre(nombre);
            autor.setNacionalidad(nacionalidad);
            request.setAttribute("autor", autor);
            request.getRequestDispatcher("/autores/formulario.jsp").forward(request, response);
            return;
        }

        Autor autor = new Autor();
        autor.setNombre(nombre);
        autor.setNacionalidad(nacionalidad);

        if (idStr == null || idStr.isEmpty()) {
            autor.setId(contadorId++);
            autoresSimulados.add(autor);
        } else {
            int id = Integer.parseInt(idStr);
            autor.setId(id);
            for (int i = 0; i < autoresSimulados.size(); i++) {
                if (autoresSimulados.get(i).getId() == id) {
                    autoresSimulados.set(i, autor);
                    break;
                }
            }
        }

        response.sendRedirect("AutorServlet?action=listar");
    }
}