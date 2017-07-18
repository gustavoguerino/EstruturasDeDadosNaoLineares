<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Coleguinha's Book</title>
        <script
            src="https://code.jquery.com/jquery-2.2.4.js"
            integrity="sha256-iT6Q9iMJYuQiMWNd9lDyBUStIq/8PuOW33aOqmvFpqI="
        crossorigin="anonymous"></script>
        <link rel='stylesheet' href='jquery.toast.min.css' />
        <link rel="stylesheet" href="site.css" />
    </head>
    <body>
        <div class='content center'>
            <h1>Coleguinha's Book</h1>
            <form class="container autenticacao" method="post" action="AutenticacaoServlet">
                <img alt='user.png' src='user.png' id='user-img'/>
                <input type="text" placeholder="Usuário" name="user" />
                <input type="password" placeholder="Senha" name="password" />
                <input type='submit' value="AUTENTICAR" />
            </form>
        </div>
        <script src="jquery.toast.min.js" type="text/javascript"></script>
        <script>
            // https://github.com/kamranahmedse/jquery-toast-plugin
            var message = '<%= request.getAttribute("mensagem")%>';
            if (message !== 'null') {
                var heading = message.startsWith("S:") ? "Sucesso" : "Erro!";
                var icon = message.startsWith("S:") ? "success" : "error";
                $.toast({
                    heading: heading,
                    text: message,
                    showHideTransition: 'slide',
                    icon: icon,
                    position: 'top-left'
                });
            }
        </script>
    </body>
</html>
