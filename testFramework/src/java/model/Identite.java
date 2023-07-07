/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import etu2010.framework.ModelView;
import etu2010.framework.servlet.Url;

/**
 *
 * @author Iante
 */
public class Identite
{
    String nom;
    String prenom;
    int age;
    
    @Url(nom = "/InsertIdentite")
    public ModelView getIdentite()
    {
       ModelView modelView =new ModelView();
       modelView.setView("testIdentite.jsp");
       modelView.addItem("nom",nom);
       modelView.addItem("prenom",prenom);
       modelView.addItem("age",age);
       return modelView;
    }
}
