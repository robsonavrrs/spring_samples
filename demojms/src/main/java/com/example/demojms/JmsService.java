package com.example.demojms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;

@Service
public class JmsService {

    @JmsListener(containerFactory = "containerFactory",
            destination = "TesteSystemModule-0!DistributedQueue-0")
    public void processMessage(String mensagem) throws JMSException {
        System.out.println(mensagem);
    }



}
