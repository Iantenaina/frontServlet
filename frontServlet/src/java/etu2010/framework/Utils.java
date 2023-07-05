/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etu2010.framework;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author BEST
 */
public class Utils {
    
    public static  Map <String,Object> getAllSession(HttpServletRequest request)
    {
        Map <String,Object> map=new HashMap<>();
         Enumeration <String> sessions=request.getSession().getAttributeNames();
         //fomba fibouclena enumeration
         while(sessions.hasMoreElements())
         {
            //fomba fibouclena ref manana ny ao arina de alefa ndray le ao arina
           String sessionName=sessions.nextElement();
           map.put(sessionName, request.getSession().getAttribute(sessionName));
           
         }
      return map;
    }
    
}
