package servlets;

import com.google.gson.Gson;
import dominio.Amizade;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import matriz_adjacencia.RedeSocial;

@WebServlet(name = "SugerirAmizadesServlet", urlPatterns = {"/SugerirAmizadesServlet"})
public class SugerirAmizadesServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        ArrayList<Amizade> sugestoesAmizades = RedeSocial.sugerirAmizades(0);
        try (PrintWriter out = response.getWriter()) {
            out.println(new Gson().toJson(sugestoesAmizades));
        }
        /*
            String strToBuild = "";
            for (Amizade sugestao : sugestoesAmizades) {
                strToBuild += String.format("<p><b>Amigo %s: </b>%s (valor da amizade: %.1f)</p>", sugestao.getPessoaOrigem().getValor(), sugestao.getPessoaDestino().getValor(), sugestao.getValorAmizade());
            }
            try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet SugerirAmizadesServlet</title>");
                out.println("</head>");
                out.println("<body>");
                out.println(new Gson().toJson(sugestoesAmizades));
                out.println("</body>");
                out.println("</html>");
            }
         */
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
