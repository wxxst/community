package com.nowcoder.community.service;

import com.nowcoder.community.dao.AlphaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
//@Scope("prototype")
public class AlphaService {
    @Autowired
    private AlphaDao alphaDao;

    public AlphaService(){
        System.out.println("实例化Alphaservice");
    }

    @PostConstruct
    //这个方法会在构造器之后构造
    public void init(){
        System.out.println("初始化AlphaService");
    }

    @PreDestroy
    //在销毁对象之前调用它
    public void destroy(){
        System.out.println("销毁Alphaservice");
    }

    public String find(){
        return alphaDao.select();
    }
}
