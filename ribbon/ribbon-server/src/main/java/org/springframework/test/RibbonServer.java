package org.springframework.test;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@RestController
@SpringBootApplication
public class RibbonServer {

  @Value("${server.port}")
  String serverPort;

  @RequestMapping(value = "/greeting")
  public String greet(HttpServletRequest request) {
    List<String> greetings = Arrays.asList("Hi there", "Greetings", "Salutations");
    Random rand = new Random();

    int randomNum = rand.nextInt(greetings.size());
    return serverPort + " : " + greetings.get(randomNum);
  }

  @RequestMapping(value = "/")
  public String home() {
    return "Hi!";
  }

  public static void main(String[] args) {
    SpringApplication.run(RibbonServer.class, args);
  }
}