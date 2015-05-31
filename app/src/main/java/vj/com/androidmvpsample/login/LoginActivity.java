package vj.com.androidmvpsample.login;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import vj.com.androidmvpsample.ActivityUtil;
import vj.com.androidmvpsample.R;

import static android.widget.Toast.LENGTH_SHORT;


public class LoginActivity extends AppCompatActivity implements LoginView {
  private EditText usernameView;
  private EditText passwordView;
  private LoginPresenter presenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
    usernameView = (EditText) findViewById(R.id.username);
    passwordView = (EditText) findViewById(R.id.password);
    presenter = new LoginPresenter(this, new LoginService());
  }

  @Override
  protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
    super.onRestoreInstanceState(savedInstanceState);
  }

  @Override
  protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
  }

  public void onLoginClicked(View view) {
    presenter.onLoginClicked();
  }

  @Override
  public String getUsername() {
    return usernameView.getText().toString();
  }

  @Override
  public void showUsernameError(int resId) {
    usernameView.setError(getString(resId));
  }

  @Override
  public String getPassword() {
    return passwordView.getText().toString();
  }

  @Override
  public void showPasswordError(int resId) {
    passwordView.setError(getString(resId));
  }

  @Override
  public void startMainActivity() {
    new ActivityUtil(this).startMainActivity();
  }

  @Override
  public void showLoginError(int resId) {
    Toast.makeText(this, getString(resId), LENGTH_SHORT).show();
  }
}
