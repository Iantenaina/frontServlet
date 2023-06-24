/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etu2010.framework;

/**
 *
 * @author BEST
 */
public class FileUpload {
    String name;
    byte[]fileBite;
    String savePath;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getFileBite() {
        return fileBite;
    }

    public void setFileBite(byte[] fileBite) {
        this.fileBite = fileBite;
    }

    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

  
    
}
