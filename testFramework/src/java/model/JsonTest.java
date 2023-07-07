/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import etu2010.framework.ModelView;
import etu2010.framework.servlet.RestAPI;
import etu2010.framework.servlet.Url;

/**
 *
 * @author aris
 */
public class JsonTest {
    @Url(nom = "/jsonTest")
    public ModelView jsonTest() {
        ModelView modelView=new ModelView();
        modelView.setIsJson(true);
         
         modelView.addItem("hehe", 1);
         modelView.addItem("hehe1", true);
         modelView.addItem("hehe2", 1.41313243);
         
         return modelView;
    }
    
    @RestAPI
    @Url(nom = "/clients")
    public Client[] clients() {
        Client[] clientsTab = new Client[3];
        
        clientsTab[0] = new Client("hehe");
        clientsTab[1] = new Client("ffff");
        clientsTab[2] = new Client("jojo");
        
        return clientsTab;
    }
}
