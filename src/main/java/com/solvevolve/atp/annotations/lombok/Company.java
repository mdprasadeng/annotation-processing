package com.solvevolve.atp.annotations.lombok;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "custom")
public class Company {

  public static void main(String[] args) {

    //Setter example
    //Noargs constructor
    Employee employee = new Employee();
    employee.setEmailId("test@flipkart.com");
    employee.setUserName("test");

    System.out.println("Employee 1 has detials :" + employee.toString());

    //Getter example
    String emailId = employee.getEmailId();
    String userName = employee.getUserName();

    //Required args constructor
    Employee reqEmployee = new Employee("test", "test@flipkart.com");

    System.out.println("Req Employee has detials :" + reqEmployee.toString());

    System.out.println("Equals of employees " + reqEmployee.equals(employee));


    //log.info("testing @Sl4fj");

  }
}
