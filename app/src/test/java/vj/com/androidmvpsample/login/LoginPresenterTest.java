package vj.com.androidmvpsample.login;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import vj.com.androidmvpsample.R;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by vivekjain on 5/31/15.
 */
@RunWith(MockitoJUnitRunner.class)
public class LoginPresenterTest {
  @Mock
  private LoginView view;
  @Mock
  private LoginService service;
  private LoginPresenter presenter;

  @Before
  public void setUp() throws Exception {
    presenter = new LoginPresenter(view, service);
  }

  @Test
  public void shouldShowErrorMessageWhenUsernameIsEmpty() throws Exception {
    when(view.getUsername()).thenReturn("");
    presenter.onLoginClicked();

    verify(view).showUsernameError(R.string.username_error);
  }

  @Test
  public void shouldShowErrorMessageWhenPasswordIsEmpty() throws Exception {
    when(view.getUsername()).thenReturn("james");
    when(view.getPassword()).thenReturn("");
    presenter.onLoginClicked();

    verify(view).showPasswordError(R.string.password_error);
  }

  @Test
  public void shouldStartMainActivityWhenUsernameAndPasswordAreCorrect() throws Exception {
    when(view.getUsername()).thenReturn("james");
    when(view.getPassword()).thenReturn("bond");
    when(service.login("james", "bond")).thenReturn(true);
    presenter.onLoginClicked();

    verify(view).startMainActivity();
  }

  @Test
  public void shouldShowLoginErrorWhenUsernameAndPasswordAreInvalid() throws Exception {
    when(view.getUsername()).thenReturn("james");
    when(view.getPassword()).thenReturn("bond");
    when(service.login("james", "bond")).thenReturn(false);
    presenter.onLoginClicked();

    verify(view).showLoginError(R.string.login_failed);
  }
}