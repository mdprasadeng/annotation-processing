package com.solvevolve.atp.annotations.target;


import com.solvevolve.atp.annotations.jdk.Seller;

@Three
public class MyClass {

  //@Two
  public MyClass(String myField) {
    this.myField = myField;
  }

  //@Four
  private String myField;

  @One
  public boolean myMethod(@Five String argument) {
    new Seller().getIdentifier();
    return this.myField.equals(argument);
  }

}

