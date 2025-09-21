<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h3>Formulario de Material</h3>
<form action="MaterialServlet" method="post">
    <input type="hidden" name="id" value="${material.id}">

    Título: <input type="text" name="titulo" value="${material.titulo}" required><br><br>

    Tipo:
    <select name="tipo" required>
        <option value="">-- Seleccione --</option>
        <option value="Libro" ${material.tipo == 'Libro' ? 'selected' : ''}>Libro</option>
        <option value="Revista" ${material.tipo == 'Revista' ? 'selected' : ''}>Revista</option>
    </select><br><br>

    Categoría:
    <select name="id_categoria" required>
        <option value="">-- Seleccione --</option>
        <c:forEach var="c" items="${categorias}">
            <option value="${c.id}" ${material.categoria != null && material.categoria.id == c.id ? 'selected' : ''}>
                    ${c.nombre}
            </option>
        </c:forEach>
    </select><br><br>

    Autor:
    <select name="id_autor" required>
        <option value="">-- Seleccione --</option>
        <c:forEach var="a" items="${autores}">
            <option value="${a.id}" ${material.autor != null && material.autor.id == a.id ? 'selected' : ''}>
                    ${a.nombre} (${a.nacionalidad})
            </option>
        </c:forEach>
    </select><br><br>

    <input type="submit" value="Guardar">
</form>