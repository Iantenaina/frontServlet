/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etu2010.framework;

import etu2010.framework.servlet.Argument;
import etu2010.framework.servlet.Scope;
import etu2010.framework.servlet.Url;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//////////////////////////////////////////////////////////////////////////////////////////////////////////////
import java.lang.reflect.Field;

/**
 *
 * @author itu
 */
public class FonctionURL {
    public  static Map<String,Mapping> fonction()
    {
       Map<String,Mapping> MappingUrls=new HashMap<>();
       List<Class<?>>li= AnnotationFunction.getModelClasses("model");
         System.out.println(li.size());
       for(int i =0;i<li.size();i++)
       {
         
           Method[] m=li.get(i).getMethods();
            for(int j=0;j<m.length;j++)
            {
               if(m[j].isAnnotationPresent(Url.class))
               {
                                  
                   Url u=m[j].getAnnotation(Url.class);
                   MappingUrls.put(u.nom(),new Mapping(li.get(i).getName(),m[j].getName()));
               }
            }
       }
        return MappingUrls;
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////
     public  static Map<Class<?>,Object> singleton() throws InstantiationException, IllegalAccessException
    {
       Map<Class<?>,Object> MappingUrls=new HashMap<>();
       List<Class<?>>li= AnnotationFunction.getModelClasses("model");
         System.out.println(li.size());
       for(int i =0;i<li.size();i++)
       {
           if(li.get(i).isAnnotationPresent(Scope.class))
           {
              Scope scope=li.get(i).getAnnotation(Scope.class);
               if(scope.type().equals("Singleton"))
               {
                 Object obj=li.get(i).newInstance();     
                 MappingUrls.put(li.get(i), obj);
               }

           }
       }
        return MappingUrls;
    }

    public static void resetObject(Object obj) throws IllegalAccessException {
        Class<?> objClass = obj.getClass();
        Field[] fields = objClass.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            Class<?> fieldType = field.getType();

            if (fieldType.isPrimitive()) {
                // Réinitialiser les types primitifs à leurs valeurs par défaut
                if (fieldType == boolean.class) {
                    field.setBoolean(obj, false);
                } else if (fieldType == byte.class) {
                    field.setByte(obj, (byte) 0);
                } else if (fieldType == short.class) {
                    field.setShort(obj, (short) 0);
                } else if (fieldType == int.class) {
                    field.setInt(obj, 0);
                } else if (fieldType == long.class) {
                    field.setLong(obj, 0L);
                } else if (fieldType == float.class) {
                    field.setFloat(obj, 0.0f);
                } else if (fieldType == double.class) {
                    field.setDouble(obj, 0.0);
                } else if (fieldType == char.class) {
                    field.setChar(obj, '\u0000');
                }
            } else {
                // Réinitialiser les objets référencés à null
                field.set(obj, null);
            }
        }
    }
}

