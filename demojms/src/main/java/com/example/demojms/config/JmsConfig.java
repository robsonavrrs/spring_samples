package com.example.demojms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.destination.BeanFactoryDestinationResolver;
import org.springframework.jndi.JndiObjectFactoryBean;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.naming.NamingException;

@Configuration
@EnableJms
public class JmsConfig {


    @Bean
    public ConnectionFactory connectionFactory() {
        JndiObjectFactoryBean bean = new JndiObjectFactoryBean();
        bean.setJndiName("com/teste/cf");
        bean.setCache(true);
        bean.setProxyInterface(javax.jms.ConnectionFactory.class);
        try {
            bean.afterPropertiesSet();
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }

        return (ConnectionFactory)bean.getObject();
    }

    @Bean
    public DefaultJmsListenerContainerFactory containerFactory(ConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory =
                new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        return factory;
    }


    @Bean
    public JmsTemplate jmsTemplate(ConnectionFactory connectionFactory) throws JMSException {
        return new JmsTemplate(connectionFactory);
    }


}
