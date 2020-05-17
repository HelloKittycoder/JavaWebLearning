package com.kittycoder.user.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.kittycoder.ticket.service.TicketService;
import org.springframework.stereotype.Service;

/**
 * Created by shucheng on 2020/2/16 11:15
 */
@Service
public class UserService {

    @Reference
    private TicketService ticketService;

    public void hello() {
        String ticket = ticketService.getTicket();
        System.out.println("买到票了：" + ticket);
    }
}
