package com.bjsxt.mapper;

import com.bjsxt.pojo.Airport;
import com.bjsxt.service.AirportService;
import com.bjsxt.service.impl.AirportServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

import static org.junit.Assert.*;

public class AirportMapperTest {

    private ApplicationContext ac;

    @Before
    public void init() {
        ac = new ClassPathXmlApplicationContext("applicationContext.xml");
    }

    @Test
    public void selAll() {
        /*String[] names = ac.getBeanDefinitionNames();
        for (String str : names) {
            System.out.println(str);
        }*/
        AirportService bean = ac.getBean("airportService", AirportService.class);
        List<Airport> list = bean.show();
        System.out.println(list);
    }
}