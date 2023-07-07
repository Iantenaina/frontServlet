/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import etu2010.framework.ModelView;
import etu2010.framework.servlet.Argument;
import etu2010.framework.servlet.Url;

/**
 *
 * @author Iante
 */
public class Client {
    String numCompte;
    
    public Client(String numCompte) {
        this.numCompte = numCompte;
    }

    public String getNumCompte() {
        return numCompte;
    }

    @Url(nom="/client")
    public ModelView setNumCompte(@Argument(nom="numCompte") int[] numCompte) {
         ModelView modelView=new ModelView();
         modelView.setView("test.jsp");
         modelView.addItem("hh", numCompte);
         System.out.println(numCompte);
         return modelView;
    }
    
    
}
