package com.service.services.Exception;

public class PhoneNotExist extends RuntimeException{
    public PhoneNotExist(String msg){
        super(msg);
    }
}
