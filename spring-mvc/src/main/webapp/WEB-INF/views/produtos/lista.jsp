<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Formulário</title>
    </head>
    <body>
        <table>
            <thead>
                <tr>
                    <th>Título</th>
                    <th>Descrição</th>
                    <th>Páginas</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${produtos}" var="produto" varStatus="">
                    <tr>
                        <td>${produto.titulo}</td>
                        <td>${produto.descricao}</td>
                        <td>${produto.paginas}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>
