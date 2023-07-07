/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import etu2010.framework.FileUpload;
import etu2010.framework.ModelView;
import etu2010.framework.servlet.Url;

/**
 *
 * @author aris
 */
public class FichierModel {
    public FileUpload fichier;
    
    @Url(nom = "/testFichier")
    public ModelView testFichier() {
        ModelView modelView = new ModelView();
        
        modelView.addItem("bytes", fichier.getFileBite());
        modelView.addItem("filename", fichier.getName());
        
        modelView.setView("FichierResulat.jsp");
        
        return modelView;
    }
}
