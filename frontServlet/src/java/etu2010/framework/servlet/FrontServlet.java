package etu2010.framework.servlet;

import com.google.gson.Gson;
import etu2010.framework.FileUpload;
import etu2010.framework.FonctionURL;
import etu2010.framework.Mapping;
import etu2010.framework.ModelView;
import etu2010.framework.Utils;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
/**
 *
 * @author ITU
 */
@MultipartConfig()
public class FrontServlet extends HttpServlet {
   Map<String,Mapping> MappingUrls=new HashMap<>();
   Map <Class<?>,Object> map=new HashMap<>();
    @Override
    
    public void init() throws ServletException {
        MappingUrls=FonctionURL.fonction();
       try 
       {
           map=FonctionURL.singleton();
       } 
       catch (InstantiationException ex) {
           Logger.getLogger(FrontServlet.class.getName()).log(Level.SEVERE, null, ex);
       } 
       catch (IllegalAccessException ex) {
           Logger.getLogger(FrontServlet.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
/////////////////////////////////////////////////////////////////8    
    public Object[] convertArray(Type type,String[]parametre)
    {
        Object[] obj;
        obj=new Object[parametre.length];
        for(int j=0;j<parametre.length;j++)
        {
         if(type ==int.class)
            {
                 obj[j]= (Object)Integer.parseInt(parametre[j]);
            }
         if(type==String.class)
            {
                 obj[j]= (Object)String.valueOf(parametre[j]);
            }
         if(type==double.class)
            {
                 obj[j]= (Object)Double.parseDouble(parametre[j]);
            }
        }
        return obj;
    }
//////////////////////////////////////////////////////
    public byte[] convertInputStream (InputStream inputStream) throws IOException {
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    byte[] buffer = new byte[4096]; // Taille du tampon temporaire

    int bytesRead;
    while ((bytesRead = inputStream.read(buffer)) != -1) {
        byteArrayOutputStream.write(buffer, 0, bytesRead);
    }

    return byteArrayOutputStream.toByteArray();
}
//////////////////////////////////////////////////////////////////
   public boolean  checkAuth( HttpServletRequest request,Method method)
   {
      String isConnected=getServletContext().getInitParameter("isCoSession");
      String profil= getServletContext().getInitParameter("isCoProfl");
        if(!method.isAnnotationPresent(Auth.class))
        {
          return true;
        }
        if(request.getSession().getAttribute(isConnected)==null )
        {
            return false;
        }
        if((boolean)request.getSession().getAttribute(isConnected)==false)
        {
           return false;
        }
         Auth auth=method.getAnnotation(Auth.class); 
        if( auth.profil()=="")
        {   
          if(auth.profil().equals((String)request.getSession().getAttribute(profil)))
          {
             return false;
          }
        }
        
       return true; 
   }
    
////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
            out.println(request.getContextPath()+request.getServletPath()+ "?" +request.getQueryString());
             String currentUrl = request.getRequestURI().replace(request.getContextPath(), "");
             
           out.print(MappingUrls.size());
             
       if (MappingUrls.containsKey(currentUrl))
       {
           Mapping m=MappingUrls.get(currentUrl);
           Class<?> classe=Class.forName(m.getClassName());
           Object objet =  null;
           if(map.containsKey(classe))
           {
               FonctionURL.resetObject(objet);
               objet=map.get(classe);
           }
           else
           {
               objet=classe.newInstance();
           }
           
          Method method =null;
          Method[]methods=objet.getClass().getMethods();
          for(int u=0;u<methods.length;u++)
          {
              if(methods[u].getName().equals( m.getMethod()))
              {
                  method=methods[u];
                  break;
              }
              
          }
/////////////////////////////////////////////////////////////////////////////////////////////
          if(method.isAnnotationPresent(Session.class))
          {
            Field[] at= objet.getClass().getDeclaredFields();
            for(int i=0;i<at.length;i++)
            {
                if(at[i].getName().equals("session") && at[i].getType()==HashMap.class)
                {
                    at[i].setAccessible(true);
                    at[i].set(objet, Utils.getAllSession(request));
                }
            }
          }
/////////////////////////////////////////////////////////////////////////////////////////////
            for(Map.Entry<String,String[]> mapForm : request.getParameterMap().entrySet())
            {
                Field[]at=objet.getClass().getDeclaredFields();
                for(int i=0;i<at.length;i++)
                {
                    if(at[i].getName().equalsIgnoreCase(mapForm.getKey()))
                    {
                      at[i].setAccessible(true);
                      if(at[i].getType()==String.class)
                      { at[i].set(objet, String.valueOf(mapForm.getValue()[0]));}
                        
                      if(at[i].getType()==int.class)
                        { at[i].setInt(objet, Integer.parseInt(mapForm.getValue()[0]));}
                      
                      if(at[i].getType()==Double.class)
                        { at[i].setDouble(objet, Double.valueOf(mapForm.getValue()[0]));}  
                      
                      if(at[i].getType()==FileUpload.class)
                        { 
                            Part part=request.getPart(at[i].getName());
                            FileUpload fileUpload=new FileUpload();
                            fileUpload.setName(part.getSubmittedFileName());
                            fileUpload.setFileBite(convertInputStream (part.getInputStream()));
                            at[i].set(objet,fileUpload);
                        }                      

                    }
                }
            }
 //////////////////////////////////////////////////////////////////////////      
                Object[] obj;
                 Parameter[]parametre;
                 parametre= method.getParameters();
                 obj=new Object[parametre.length];
                  for(int j=0;j<parametre.length;j++)
                    {
                      if(parametre[j].isAnnotationPresent(Argument.class))
                      {
                          Argument argument=parametre[j].getAnnotation(Argument.class);                  
                            for(Map.Entry<String,String[]> mapForm : request.getParameterMap().entrySet())
                            { 
                                if(argument.nom().equals(mapForm.getKey()))
                                {
                                    if (parametre[j].getType().isArray())
                                    {
                                        Class<?> componentType =parametre[j].getType().getComponentType();
                                        Object arrayParam =Array.newInstance(componentType,mapForm.getValue().length);
                                        
                                        for(int u=0;u<mapForm.getValue().length;u++)
                                        {
                                            Object convertedValue = null;
                                            
                                            if(componentType==int.class)
                                              {
                                                   convertedValue= (Object)Integer.parseInt(mapForm.getValue()[u]);
                                              }
                                           if(componentType==String.class)
                                              {
                                                   convertedValue= (Object)String.valueOf(mapForm.getValue()[u]);
                                              }
                                           if(componentType==double.class)
                                              {
                                                   convertedValue= (Object)Double.parseDouble(mapForm.getValue()[u]);
                                              }

                                     Array.set(arrayParam,u,convertedValue);
                                        }
                                        obj[j]=arrayParam;
                                    } 
                                    
                                    else
                                    {
                                      if(parametre[j].getType()==int.class)
                                        {
                                             obj[j]= (Object)Integer.parseInt(mapForm.getValue()[0]);
                                        }
                                     if(parametre[j].getType()==String.class)
                                        {
                                             obj[j]= (Object)String.valueOf(mapForm.getValue()[0]);
                                        }
                                     if(parametre[j].getType()==double.class)
                                        {
                                             obj[j]= (Object)Double.parseDouble(mapForm.getValue()[0]);
                                        }
                                    }
                                    
                                   
              
                                }
                            }
                      }
                   } 
            if(this.checkAuth(request,method) == false)
            {
               throw  new Exception("aller");
            }
            
            if(method.isAnnotationPresent(RestAPI.class))
            {
              Object o=method.invoke(objet, obj);
               Gson gson=new Gson();
               String jsonObject=gson.toJson(o);
               response.getWriter().print(jsonObject);
            }
            else
            {
                            
           ModelView model=(ModelView)method.invoke(objet,obj);
            
            
           if(model.getData()!=null)
           {
              for(Map.Entry<String,Object> newMap :model.getData().entrySet())
              {
                  request.setAttribute(newMap.getKey(),newMap.getValue());
              }
           }
           
           if(model.getSession()!=null)
           {
              for(Map.Entry<String,Object> newMap :model.getSession().entrySet())
              {
                  request.getSession().setAttribute(newMap.getKey(),newMap.getValue());
              }
           }
           //sprint13
           if(model.isIsJson()==true)
           { 
               response.getWriter().print(model.dataToJson());
           }
           else
           {
                response.getWriter().print(model.getView());           
                RequestDispatcher disp = request.getRequestDispatcher(model.getView());
                 disp.forward(request, response);
           }
           
                
            }
        
           

           
           
       }
    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
       } catch (Exception ex) {
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
       } catch (Exception ex) {
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
