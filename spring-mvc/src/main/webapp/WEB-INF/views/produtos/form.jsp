<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Formulário</title>
    </head>
    <body>
        <form action="/spring-mvc/produtos" method="POST"> 
            <div>
                <label>Título</label>
                <input type="text" name="titulo"/>
            </div>
            <div>
                <label>Descricao</label>
                <textarea rows="10" cols="20" name="descricao"></textarea>
            </div>
            <div>
                <label>Páginas </label>
                <input type="text" name="paginas"/>
            </div>
            <button type="submit">Cadastrar</button>
        </form>
    </body>
</html>