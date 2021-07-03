package br.com.basis.colatina.gcz.keep;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class KeepApplication {

    public static void main(String[] args) {
        SpringApplication.run(KeepApplication.class, args);
    }

}
