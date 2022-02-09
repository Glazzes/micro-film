package jvm.glaze.actor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication(scanBasePackages = {"jvm.glaze.actor", "jvm.glaze.clients"})
public class ActorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActorApplication.class, args);
    }

}
