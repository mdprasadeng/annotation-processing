package com.solvevolve.atp.annotationprocessing;

import java.lang.String;
import java.util.Collection;

public class EmployeeBuilder {

  private Employee holder;

  public Employee build() {
    return holder;
  }

  public EmployeeBuilder userName(String userName) {
    this.holder.setUserName(userName);
    return this;
  }

  public EmployeeBuilder emailId(String emailId) {
    this.holder.setEmailId(emailId);
    return this;
  }

  public EmployeeBuilder phoneNumbers(Collection<String> phoneNumbers) {
    this.holder.setPhoneNumbers(phoneNumbers);
    return this;
  }
}