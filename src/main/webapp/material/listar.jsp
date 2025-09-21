<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h3>Listado de Materiales</h3>
<a href="MaterialServlet?action=nuevo">Nuevo</a>
<table border="1">
    <tr><th>ID</th><th>Título</th><th>Tipo</th><th>Categoría</th><th>Autor</th><th>Acciones</th></tr>
    <c:forEach var="m" items="${materiales}">
        <tr>
            <td>${m.id}</td>
            <td>${m.titulo}</td>
            <td>${m.tipo}</td>
            <td>${m.categoria.nombre}</td>
            <td>${m.autor.nombre}</td>
            <td>
                <a href="MaterialServlet?action=editar&id=${m.id}">Editar</a>
                <a href="MaterialServlet?action=eliminar&id=${m.id}">Eliminar</a>
            </td>
        </tr>
    </c:forEach>
</table>