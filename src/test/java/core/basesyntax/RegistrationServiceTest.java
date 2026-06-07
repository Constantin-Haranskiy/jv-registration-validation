package core.basesyntax;

import core.basesyntax.dao.StorageDao;
import core.basesyntax.dao.StorageDaoImpl;
import core.basesyntax.db.Storage;
import core.basesyntax.exception.RegistrationException;
import core.basesyntax.model.User;
import core.basesyntax.service.RegistrationService;
import core.basesyntax.service.RegistrationServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Feel free to remove this class and create your own.
 */
public class RegistrationServiceTest {
  private static RegistrationService registrationService;
  private static StorageDao storage;
  private static User user;

  @BeforeAll
  public static void setUp() {
    registrationService = new RegistrationServiceImpl();
    storage = new StorageDaoImpl();
  }

  @BeforeEach
  public void beforeEach() {
    user = new User();
    user.setAge(20);
    user.setLogin("login123");
    user.setPassword("password123");

    Storage.people = new ArrayList<>();
  }

  @Test
  public void register_nullUser_notOk() {
    assertThrows(RegistrationException.class, () -> {
      registrationService.register(null);
    });
  }

  @Test
  public void register_notValidUser_notOk() {
    user.setAge(17);
    assertThrows(RegistrationException.class, () -> {
      registrationService.register(user);
    });
  }

  @Test
  public void register_existsInStorage_notOk() {
    storage.add(user);
    assertThrows(RegistrationException.class, () -> {
      registrationService.register(user);
    });
  }

  @Test
  public void register_validUserInDb_Ok() {
    assertDoesNotThrow(() -> {
      registrationService.register(user);
    });

    assertEquals(user, storage.get(user.getLogin()));
  }

  @Test
  public void register_validUserReturned_Ok() {
    AtomicReference<User> actual = new AtomicReference<>();
    assertDoesNotThrow(() -> {
      actual.set(registrationService.register(user));
    });
    assertEquals(user, actual.get());
  }
}
