<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Inicio - Sistema Editorial</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 40px;
        }
        h2 {
            color: #2c3e50;
        }
        ul {
            list-style: none;
            padding: 0;
        }
        li {
            margin: 10px 0;
        }
        a {
            text-decoration: none;
            color: #2980b9;
            font-weight: bold;
        }
        a:hover {
            color: #1abc9c;
        }
        .contenedor {
            background-color: #fff;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            max-width: 600px;
            margin: auto;
        }
    </style>
</head>
<body>
<div class="contenedor">
    <h2>Bienvenido al Sistema Editorial</h2>
    <p>Seleccione un módulo para continuar:</p>
    <ul>
        <li><a href="AutorServlet?action=listar">Gestión de Autores</a></li>
        <li><a href="CategoriaServlet?action=listar">Gestión de Categorías</a></li>
        <li><a href="MaterialServlet?accion=listar">Gestión de Materiales</a></li>
    </ul>
</div>
</body>
</html>