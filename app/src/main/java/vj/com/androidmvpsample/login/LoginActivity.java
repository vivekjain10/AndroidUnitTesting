package vj.com.androidmvpsample.login;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import vj.com.androidmvpsample.ActivityUtil;
import vj.com.androidmvpsample.R;

import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.LENGTH_SHORT;


public class LoginActivity extends AppCompatActivity {

  public static final String NO_OF_ATTEMPTS = "vj.com.androidmvpsample.login.noOfAttempts";
  private EditText usernameView;
  private EditText passwordView;
  private int noOfAttempts;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
    usernameView = (EditText) findViewById(R.id.username);
    passwordView = (EditText) findViewById(R.id.password);
  }

  @Override
  protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
    super.onRestoreInstanceState(savedInstanceState);
    noOfAttempts = savedInstanceState.getInt(NO_OF_ATTEMPTS);
  }

  @Override
  protected void onSaveInstanceState(Bundle outState) {
    outState.putInt(NO_OF_ATTEMPTS, noOfAttempts);
    super.onSaveInstanceState(outState);
  }

  public void onLoginClicked(View view) {
    if (noOfAttempts == 3) {
      Toast.makeText(this, getString(R.string.max_attempts_message),
          LENGTH_LONG).show();
      return;
    }
    String username = usernameView.getText().toString();
    String password = passwordView.getText().toString();
    if (TextUtils.isEmpty(username)) {
      usernameView.setError(getString(R.string.username_error));
      return;
    }
    if (TextUtils.isEmpty(password)) {
      passwordView.setError(getString(R.string.password_error));
      return;
    }
    boolean loggedIn = new LoginService().login(username, password);
    if (loggedIn) {
      new ActivityUtil(this).startMainActivity();
    } else {
      noOfAttempts++;
      Toast.makeText(this, getString(R.string.login_failed, (3 - noOfAttempts)),
          LENGTH_SHORT).show();
    }
  }
}
