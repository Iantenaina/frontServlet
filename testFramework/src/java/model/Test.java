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
 * @author itu
 */
public class Test {
     @Url(nom="/suite")
 public ModelView test()
  {
      ModelView m=new ModelView();
      m.setView("test.jsp");
    return m;
  }
}
