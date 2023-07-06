/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etu2010.framework;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author itu
 */
public class ModelView {
    String view ;
    HashMap<String,Object> data;
    HashMap<String,Object> session;
    boolean isJson=false;
    boolean invalidate=false;
    ArrayList<String> sessionRemove;

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public HashMap<String, Object> getData() {
        return data;
    }

    public void setData(HashMap<String, Object> data) {
        this.data = data;
    }

    public HashMap<String, Object> getSession() {
        return session;
    }

    public void setSession(HashMap<String, Object> session) {
        this.session = session;
    }

    public boolean isIsJson() {
        return isJson;
    }

    public void setIsJson(boolean isJson) {
        this.isJson = isJson;
    }

    public boolean isInvalidate() {
        return invalidate;
    }

    public void setInvalidate(boolean invalidate) {
        this.invalidate = invalidate;
    }

    public ArrayList<String> getSessionRemove() {
        return sessionRemove;
    }

    public void setSessionRemove(ArrayList<String> sessionRemove) {
        this.sessionRemove = sessionRemove;
    }

  

   //set attribut fa makn amn frontservlet 
    public void addItem(String Key,Object value)
    {
      if(this.data==null)
      {
        this.data=new HashMap<String,Object>();
      }  
      this.data.put(Key, value);
    }
    
    //miajouter session fa makn amn frontservlet 
     public void addSession(String Key,Object value)
    {
      if(this.session==null)
      {
        this.session=new HashMap<String,Object>();
      }  
      this.session.put(Key, value);
    }
     
     //mamadika data en json
     public String dataToJson()
     {
         String json=null;
          if(isJson==false)
          {
            return null;
          }
         Gson gson =new Gson(); 
          json=gson.toJson(this.getData());
         return json;
     }
    ////////////////////////////////
  //set attribut fa makn amn frontservlet 
    public void addSessionRemove(String Key)
    {
      if(this.sessionRemove==null)
      {
       this.sessionRemove=new ArrayList<>();
      }  
       sessionRemove.add(Key);
    }
    
}
