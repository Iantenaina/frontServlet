/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import etu2010.framework.ModelView;
import etu2010.framework.servlet.Auth;
import etu2010.framework.servlet.Url;

/**
 *
 * @author aris
 */
public class LoginTest {
    @Url(nom = "/login")
    public ModelView Login() {
        ModelView modelView=new ModelView();
         modelView.setView("Logged.jsp");
         
         modelView.addSession("isCoS", true);
         modelView.addSession("isCoP", "admin");
         
         return modelView;
    }
    
    @Auth(profil = "admin")
    @Url(nom = "/access")
    public ModelView Access() {
        ModelView modelView=new ModelView();
         modelView.setView("Access.jsp");
         
         return modelView;
    }
}
