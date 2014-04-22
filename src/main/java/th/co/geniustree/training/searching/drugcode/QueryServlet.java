/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.geniustree.training.searching.drugcode;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 *
 * @author Rain
 */
@WebServlet(name = "test" , urlPatterns = "/test")
public class QueryServlet extends HttpServlet {

    @Resource(lookup = "jdbc/TestPool")
    private DataSource dataSource;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Drug> drugs = new ArrayList<>();
        Drug drug = null;

        String inputDrugCode = request.getParameter("drugCode");
        try (PrintWriter out = response.getWriter(); Connection con = dataSource.getConnection()) {

            Statement sm = con.createStatement();
            String query;
            query = "SELECT * FROM DO_ITEM WHERE DRUGCODE LIKE \'" + inputDrugCode + "%\'";
//            query = "SELECT * FROM DO_ITEM WHERE ID = 3165";
            ResultSet rs = sm.executeQuery(query);

            
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet NewServlet</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet NewServlet at " + request.getContextPath() + "</h1>");
            while (rs.next()) {
                drug = new Drug();
                drug.setId(rs.getString("ID"));
                drug.setDrugCode(rs.getString("DRUGCODE"));
                drugs.add(drug);
//                out.println("DrugCode : " + drug.getDrugCode() + 
//                        "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
//                        + "Drug Id : " + drug.getId() + "<br/>");
                
            }

//            out.println("</body>");
//            out.println("</html>");
            
            request.setAttribute("drug", drugs);
            request.getRequestDispatcher("renderdrugcode.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(QueryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

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
