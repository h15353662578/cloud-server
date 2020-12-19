package com.hs.cloudapi.service.impl;

import com.hs.cloudapi.service.EmailCodeService;
import org.apache.dubbo.config.annotation.Service;

/**
 * @author 华生
 * 2020/12/19
 */
@Service
public class EmailCodeServiceImpl implements EmailCodeService {

    @Override
    public boolean email(String email,String code){
        System.out.println("发送邮件");
        return true;
    }
}
