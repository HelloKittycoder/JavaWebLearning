package com.kittycoder.ticket.service;

import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

/**
 * Created by shucheng on 2020/2/16 11:13
 */
@Component
@Service // 将服务发布出去
public class TicketServiceImpl implements TicketService {
    @Override
    public String getTicket() {
        return "hehe，ticket";
    }
}
