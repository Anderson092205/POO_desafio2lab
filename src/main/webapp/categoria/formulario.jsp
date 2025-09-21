<%@ page contentType="text/html;charset=UTF-8" %>
<form action="CategoriaServlet" method="post">
    <input type="hidden" name="id" value="${categoria.id}">
    Nombre: <input type="text" name="nombre" value="${categoria.nombre}"><br>
    <input type="submit" value="Guardar">
</form>