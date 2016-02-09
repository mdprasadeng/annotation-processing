package com.solvevolve.atp.annotationprocessing;

import java.util.Collection;

@Builder
public class Employee {

  private String userName;
  private String emailId;
  private Collection<String> phoneNumbers;

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getEmailId() {
    return emailId;
  }

  public void setEmailId(String emailId) {
    this.emailId = emailId;
  }

  public Collection<String> getPhoneNumbers() {
    return phoneNumbers;
  }

  public void setPhoneNumbers(Collection<String> phoneNumbers) {
    this.phoneNumbers = phoneNumbers;
  }
}

