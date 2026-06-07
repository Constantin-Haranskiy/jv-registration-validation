package core.basesyntax.model;

import core.basesyntax.exception.UserValidationException;

import java.util.Objects;

public class User {
    private static final int MIN_PASSWORD_LENGTH = 6;
    private static final int MIN_LOGIN_LENGTH = 6;
    private static final int MIN_AGE = 18;
    private Long id;
    private String login;
    private String password;
    private Integer age;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void validate() throws UserValidationException {
        if (login == null) {
            throw new UserValidationException("Login is required");
        }
        if (password == null) {
            throw new UserValidationException("Password is required");
        }
        if (age == null) {
            throw new UserValidationException("Age is required");
        }
        if (password.length() < MIN_PASSWORD_LENGTH) {
            throw new UserValidationException("Password must be at least " + MIN_PASSWORD_LENGTH + " characters");
        }
        if (login.length() < MIN_LOGIN_LENGTH) {
            throw new UserValidationException("Login must be at least " + MIN_LOGIN_LENGTH + " characters");
        }
        if (age < MIN_AGE) {
            throw new UserValidationException("Age must be at least " + MIN_AGE);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(login, user.login)
                && Objects.equals(password, user.password)
                && Objects.equals(age, user.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password, age);
    }
}
