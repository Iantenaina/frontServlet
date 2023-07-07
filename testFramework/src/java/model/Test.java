/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import etu2010.framework.ModelView;
import etu2010.framework.servlet.Scope;
import etu2010.framework.servlet.Url;

/**
 *
 * @author itu
 */
@Scope(type = "Singleton")
public class Test {
    int miverina = 0;
 @Url(nom="/suite")
 public ModelView test()
  {
      miverina++;
      ModelView m=new ModelView();
      m.addItem("huhu",miverina);
      m.setView("test.jsp");
    return m;
  }
}
