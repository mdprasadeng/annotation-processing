package com.solvevolve.atp.annotations.jdk;


public class Seller extends User {

  private String sellerName;

  @Override
  public void setUserName(String userName) {
    super.setUserName(userName);
    this.sellerName = userName + "_seller";
  }

  @SuppressWarnings({"deprecation"})
  public String getIdentifier() {
    return super.getPhoneNumber() + this.sellerName;
  }
}
