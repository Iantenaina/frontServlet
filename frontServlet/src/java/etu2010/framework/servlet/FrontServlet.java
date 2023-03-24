package etu2010.framework.servlet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import etu2010.framework.FonctionURL;
import etu2010.framework.Mapping;
import etu2010.framework.ModelView;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ITU
 */
public class FrontServlet extends HttpServlet {
   Map<String,Mapping> MappingUrls=new HashMap<>();

    @Override
    public void init() throws ServletException {
        MappingUrls=FonctionURL.fonction();
        
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
            throws ServletException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
            out.println(request.getContextPath()+request.getServletPath()+ "?" +request.getQueryString());
           
            
        
                   System.out.println(request.getRequestURI().replace(request.getContextPath(), ""));

                   String currentUrl = request.getRequestURI().replace(request.getContextPath(), "");
       if (MappingUrls.containsKey(currentUrl))
       {
           Mapping m=MappingUrls.get(currentUrl);
           Class<?> classe=Class.forName(m.getClassName());
           Object objet =classe.newInstance();
           ModelView model=(ModelView)objet.getClass().getMethod(m.getMethod()).invoke(objet);
           response.getWriter().print(model.getView());
           System.out.println("huhuuhuhuhuhuhuh");
           
           
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
       } catch (ClassNotFoundException ex) {
           Logger.getLogger(FrontServlet.class.getName()).log(Level.SEVERE, null, ex);
       } catch (InstantiationException ex) {
           Logger.getLogger(FrontServlet.class.getName()).log(Level.SEVERE, null, ex);
       } catch (IllegalAccessException ex) {
           Logger.getLogger(FrontServlet.class.getName()).log(Level.SEVERE, null, ex);
       } catch (NoSuchMethodException ex) {
           Logger.getLogger(FrontServlet.class.getName()).log(Level.SEVERE, null, ex);
       } catch (IllegalArgumentException ex) {
           Logger.getLogger(FrontServlet.class.getName()).log(Level.SEVERE, null, ex);
       } catch (InvocationTargetException ex) {
           Logger.getLogger(FrontServlet.class.getName()).log(Level.SEVERE, null, ex);
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
       } catch (ClassNotFoundException ex) {
           Logger.getLogger(FrontServlet.class.getName()).log(Level.SEVERE, null, ex);
       } catch (InstantiationException ex) {
           Logger.getLogger(FrontServlet.class.getName()).log(Level.SEVERE, null, ex);
       } catch (IllegalAccessException ex) {
           Logger.getLogger(FrontServlet.class.getName()).log(Level.SEVERE, null, ex);
       } catch (NoSuchMethodException ex) {
           Logger.getLogger(FrontServlet.class.getName()).log(Level.SEVERE, null, ex);
       } catch (IllegalArgumentException ex) {
           Logger.getLogger(FrontServlet.class.getName()).log(Level.SEVERE, null, ex);
       } catch (InvocationTargetException ex) {
           Logger.getLogger(FrontServlet.class.getName()).log(Level.SEVERE, null, ex);
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
