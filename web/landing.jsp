<%@page import="dominio.Amizade"%>
<%@page import="java.util.ArrayList"%>
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
            <div class='container'>
                <img alt='user.png' src='user.png' id='user-img'/>
                <p><span id='user-name'><%= String.format("%s ID #%d", request.getAttribute("userName").toString(), Integer.parseInt(request.getAttribute("userId").toString()))%></span></p>
                <p>
                    <%
                        ArrayList<Amizade> sugestoesAmizades = (ArrayList<Amizade>) request.getAttribute("sugestoesAmizades");
                        for (Amizade amizade : sugestoesAmizades) {
                            out.println(String.format("<p><b>%s#%d</b>, amigo de %s#%d. Valor da amizade =  %.1f.</p>",
                                    amizade.getPessoaDestino().getValor(),
                                    amizade.getPessoaDestino().getChave(),
                                    amizade.getPessoaOrigem().getValor(),
                                    amizade.getPessoaOrigem().getChave(),
                                    amizade.getValorAmizade()));
                        }
                    %>
                </p>
            </div>
        </div>
    </body>
</html>
