package com.yl.exception.common;

/**
 * @author Alex
 * @since 2019/2/19 15:40
 */
public class MvcException extends RuntimeException{

    public MvcException(String message){
        super(message);
    }

    public MvcException(String message,Throwable t){
        super(message,t);
    }

    public MvcException(Throwable t){
        super(t);
    }

}
