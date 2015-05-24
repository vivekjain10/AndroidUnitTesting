package vj.com.androidmvpsample.login;

public class LoginService {
  public boolean login(String username, String password) {
    return "james".equals(username) && "bond".equals(password);
  }
}
