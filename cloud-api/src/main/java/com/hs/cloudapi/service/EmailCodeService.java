package com.hs.cloudapi.service;

/**
 * @author 华生
 * 2020/12/19
 */
public interface EmailCodeService {

    boolean email(String email ,String code);
}
