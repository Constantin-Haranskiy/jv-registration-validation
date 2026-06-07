package core.basesyntax;

import core.basesyntax.exception.UserValidationException;
import core.basesyntax.model.User;
import core.basesyntax.service.RegistrationService;
import core.basesyntax.service.RegistrationServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Feel free to remove this class and create your own.
 */
public class UserTest {
  private static User user;

  @BeforeEach
  public void beforeEach() {
    user = new User();
    user.setAge(20);
    user.setLogin("login123");
    user.setPassword("password123");
  }

  @Test
  public void isUserValid_nullLogin_notOk() {
    user.setLogin(null);
    assertThrows(UserValidationException.class, () -> {
      user.validate();
    });
  }

  @Test
  public void isUserValid_emptyLogin_notOk() {
    user.setLogin("");
    assertThrows(UserValidationException.class, () -> {
      user.validate();
    });
  }

  @Test
  public void isUserValid_edgeCaseLogin_notOk() {
    user.setLogin("12345");
    assertThrows(UserValidationException.class, () -> {
      user.validate();
    });
  }

  @Test
  public void isUserValid_edgeCaseLogin_Ok() {
    user.setLogin("123456");
    assertDoesNotThrow(() -> {
      user.validate();
    });
  }

  @Test
  public void isUserValid_nullPassword_notOk() {
    user.setPassword(null);
    assertThrows(UserValidationException.class, () -> {
      user.validate();
    });
  }

  @Test
  public void isUserValid_emptyPassword_notOk() {
    user.setPassword("");
    assertThrows(UserValidationException.class, () -> {
      user.validate();
    });
  }

  @Test
  public void isUserValid_edgeCasePassword_notOk() {
    user.setPassword("12345");
    assertThrows(UserValidationException.class, () -> {
      user.validate();
    });
  }

  @Test
  public void isUserValid_edgeCasePassword_Ok() {
    user.setPassword("123456");
    assertDoesNotThrow(() -> {
      user.validate();
    });
  }

  @Test
  public void isUserValid_nullAge_notOk() {
    user.setAge(null);
    assertThrows(UserValidationException.class, () -> {
      user.validate();
    });
  }

  @Test
  public void isUserValid_negativeAge_notOk() {
    user.setAge(-10);
    assertThrows(UserValidationException.class, () -> {
      user.validate();
    });
  }

  @Test
  public void isUserValid_edgeCaseAge_notOk() {
    user.setAge(17);
    assertThrows(UserValidationException.class, () -> {
      user.validate();
    });
  }

  @Test
  public void isUserValid_edgeCaseAge_Ok() {
    user.setAge(18);
    assertDoesNotThrow(() -> {
      user.validate();
    });
  }

  @Test
  public void isUserValid_validUser_Ok() {
    assertDoesNotThrow(() -> {
      user.validate();
    });
  }
}
