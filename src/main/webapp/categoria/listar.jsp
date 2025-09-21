<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h3>Listado de Categorías</h3>
<a href="CategoriaServlet?action=nuevo">Nuevo</a>
<table border="1">
    <tr><th>ID</th><th>Nombre</th><th>Acciones</th></tr>
    <c:forEach var="c" items="${categorias}">
        <tr>
            <td>${c.id}</td>
            <td>${c.nombre}</td>
            <td>
                <a href="CategoriaServlet?action=editar&id=${c.id}">Editar</a>
                <a href="CategoriaServlet?action=eliminar&id=${c.id}">Eliminar</a>
            </td>
        </tr>
    </c:forEach>
</table>