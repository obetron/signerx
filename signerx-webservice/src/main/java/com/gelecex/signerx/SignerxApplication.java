package com.gelecex.signerx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableFeignClients
public class SignerxApplication {
    public static void main( String[] args ) {
        SpringApplication.run(SignerxApplication.class, args);
    }
}
