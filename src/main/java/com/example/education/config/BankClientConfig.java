package com.example.education.config;

import com.example.education.client.BankClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class BankClientConfig {

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.example.consumingwebservice.wsdl");
        return marshaller;
    }

    @Bean
    public BankClient bankClient(Jaxb2Marshaller marshaller) {
        BankClient bankClient = new BankClient();
        bankClient.setDefaultUri("http://bankservice:8081/ws");
        bankClient.setMarshaller(marshaller);
        bankClient.setUnmarshaller(marshaller);
        return bankClient;
    }
}
