package test.test;

import com.solvevolve.atp.annotationprocessing.Builder;
import java.util.Collection;

@Builder
public class User {

  private String userName;
  private Collection<String> phoneNumbers;

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public Collection<String> getPhoneNumbers() {
    return phoneNumbers;
  }

  public void setPhoneNumbers(Collection<String> phoneNumbers) {
    this.phoneNumbers = phoneNumbers;
  }
}
