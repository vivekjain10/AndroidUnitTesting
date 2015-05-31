package vj.com.androidmvpsample.login;

/**
 * Created by vivekjain on 5/31/15.
 */
public interface LoginView {
  String getUsername();

  void showUsernameError(int resId);

  String getPassword();

  void showPasswordError(int resId);

  void startMainActivity();

  void showLoginError(int resId);
}
