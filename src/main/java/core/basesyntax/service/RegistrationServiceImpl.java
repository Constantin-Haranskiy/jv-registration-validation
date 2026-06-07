package core.basesyntax.service;

import core.basesyntax.dao.StorageDao;
import core.basesyntax.dao.StorageDaoImpl;
import core.basesyntax.exception.RegistrationException;
import core.basesyntax.exception.UserValidationException;
import core.basesyntax.model.User;

public class RegistrationServiceImpl implements RegistrationService {
    private final StorageDao storageDao = new StorageDaoImpl();

    @Override
    public User register(User user) {
        if (user == null) {
            throw new RegistrationException("User cannot be null");
        }

        try {
            user.validate();
        } catch (UserValidationException e) {
            throw new RegistrationException("User not valid. " + e.getMessage());
        }

        if (storageDao.get(user.getLogin()) != null) {
          throw new RegistrationException("User is already registered");
        }

        storageDao.add(user);
        return user;
    }
}
