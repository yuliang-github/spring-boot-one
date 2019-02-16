package com.yl.exception.demo;

/**
 * @author Alex
 * @since 2019/2/14 17:50
 */
public class DaoException extends Exception{

    public DaoException() {
        super();
    }

    public DaoException(String message) {
        super(message);
    }

    public DaoException(Throwable cause) {
        super(cause);
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }

}
