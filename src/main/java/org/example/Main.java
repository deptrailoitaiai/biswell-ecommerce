package org.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@SpringBootApplication()
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Main.class, args);
        Port port = context.getBean(Port.class);
        System.out.println("http://localhost:" + port.getPort());
    }

    @Component
    class Port {
        @Value("${server.port}")
        private String port;

        public String getPort() {
            return port;
        }
    }
}