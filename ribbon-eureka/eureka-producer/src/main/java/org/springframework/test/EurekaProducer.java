package org.springframework.test;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@RestController
@SpringBootApplication
@EnableDiscoveryClient
public class EurekaProducer {

  @Value("${server.port}")
  String serverPort;

  @RequestMapping(value = "/greeting")
  public String greet() {
    List<String> greetings = Arrays.asList("Hi there", "Greetings", "Salutations");
    Random rand = new Random();

    int randomNum = rand.nextInt(greetings.size());
    return serverPort + " : " + greetings.get(randomNum);
  }

  @RequestMapping("/")
  public String home() {
    return "Hi!";
  }

  public static void main(String[] args) {
    SpringApplication.run(EurekaProducer.class, args);
  }
}