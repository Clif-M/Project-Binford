package com.nashss.se.musicplaylistservice.exceptions;

/**
 * Exception to throw when a given UserRole already exist in the database.
 */
public class UserRoleAlreadyExistException extends RuntimeException {

    private static final long serialVersionUID = -8113415980375543801L;

    /**
     * Exception with no message or cause.
     */
    public UserRoleAlreadyExistException() {
    }

    /**
            * Exception with a message, but no cause.
            * @param message A descriptive message for this exception.
     */
    public UserRoleAlreadyExistException(String message) {
        super(message);
    }

    /**
     * Exception with message and cause.
     * @param message A descriptive message for this exception.
     * @param cause The original throwable resulting in this exception.
     */
    public UserRoleAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Exception with no message, but with a cause.
     * @param cause The original throwable resulting in this exception.
     */
    public UserRoleAlreadyExistException(Throwable cause) {
        super(cause);
    }

}
