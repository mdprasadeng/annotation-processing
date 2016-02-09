package test.test;

import java.util.Collection;

public class UserBuilder {

  private User user;

  public static UserBuilder builder() {
    return new UserBuilder();
  }

  public UserBuilder() {
    this.user = new User();
  }

  public User userName(String userName) {
    user.setUserName(userName);
  }

  public User phoneNumbers(Collection<String> phoneNumbers) {
    user.setPhoneNumbers(phoneNumbers);
  }

  public User build() {
    return user;
  }
}