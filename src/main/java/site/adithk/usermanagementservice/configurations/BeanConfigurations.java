package site.adithk.usermanagementservice.configurations;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;


@Configuration
public class BeanConfigurations {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }



}
