package com.solvevolve.atp.annotations.jdk;

import java.util.Arrays;
import java.util.Collection;

public class User {

  private String userName;
  private String phoneNumber;

  @Deprecated
  public String getPhoneNumber() {
    return phoneNumber;
  }

  public Collection<String> getPhoneNumbers() {
    return Arrays.asList(phoneNumber);
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getUserName() {
    return userName;
  }
}
