package sample.Exceptions;

/**
 * This is an exception, which signal that there already exist a user which same login.
 */
public class ExistingLogin extends Exception {
    public ExistingLogin(String message) {
        super(message);
    }
}
