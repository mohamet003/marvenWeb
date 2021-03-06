/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import simplejdbc.CustomerEntity;
import simplejdbc.DAO;
import simplejdbc.DAOException;
import simplejdbc.DataSourceFactory;

/**
 *
 * @author Mohamet Kone
 */
@WebServlet(name = "CustomerByState", urlPatterns = {"/CustomerByState"})
public class CustomerByState extends HttpServlet {

       
    
        DAO dao = new DAO(DataSourceFactory.getDataSource());
        
        List<CustomerEntity> lcustomer;

    public CustomerByState() {
       
    }
        

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
            throws ServletException, IOException, Exception {
        
                String val = request.getParameter("state");
                if (val == null) {
                    throw new Exception("La paramètre State n'a pas été transmis");
                }
 
                DAO dao = new DAO(DataSourceFactory.getDataSource());
                lcustomer = dao.customersInState(val);
                if (lcustomer == null) {
                    throw new Exception("State inconnu");
                }
                
                
      response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ShowClient By state</title>");
            out.println("<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css\" integrity=\"sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS\" crossorigin=\"anonymous\">");
            out.println("</head>");
            out.println("<body>");
            try {   // Trouver la valeur du paramètre HTTP customerID

    out.println("<div class=\"container\" style='margin-top:10vw'>"
            + "<h3>List of customer (s) who lives in "+val+"  </h3>"
            + "<div class='row' >");        
 out.println("<div class='col'>"
         + "<table class='table'>\n" +
"  <tr>\n" +
"    <th>ID</th>\n" +
"    <th>Name</th>\n" +
"    <th>Address</th>\n" +
"  </tr>");
                // Afficher les propriétés du client  
           for (CustomerEntity customerEntity : lcustomer) {
               
  out.println("<tr>\n" +
"    <td>"+customerEntity.getCustomerId()+"</td>\n" +
"    <td>"+customerEntity.getName()+"</td>\n" +
"    <td>"+customerEntity.getAddressLine1()+"</td>\n" +
"  </tr>");
                }
out.println(" " +
"</table>"
        + "</div>"
  + "</div>"
  + " <form action='States'>"
+ "<button type='submit' class=\"btn btn-info\" style='margin-top:2vw'>Retour</button> "
 + "</form>"
        + "</div>");
            } catch (Exception e) {
                out.printf("Erreur : %s", e.getMessage());
            }
            out.println("</body>");
            out.println("<script src=\"https://code.jquery.com/jquery-3.3.1.slim.min.js\" integrity=\"sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo\" crossorigin=\"anonymous\"></script>\n" +
"<script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js\" integrity=\"sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut\" crossorigin=\"anonymous\"></script>\n" +
"<script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js\" integrity=\"sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k\" crossorigin=\"anonymous\"></script>");
            out.println("</html>");
        } catch (Exception ex) {
            Logger.getLogger("servlet").log(Level.SEVERE, "Erreur de traitement", ex);
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
     
            try {
                processRequest(request, response);
            } catch (Exception ex) {
                Logger.getLogger(CustomerByState.class.getName()).log(Level.SEVERE, null, ex);
            }
            
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
            try {
                processRequest(request, response);
            } catch (Exception ex) {
                Logger.getLogger(CustomerByState.class.getName()).log(Level.SEVERE, null, ex);
            }
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
