package com.TiHom.studentmanager.exception;

/**
 * 2018/4/8 18:51
 * 自定义异常抛出
 * @author TiHom
 */
public class DaoException extends RuntimeException{
    public DaoException() {
    }

    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoException(Throwable cause) {
        super(cause);
    }
}
