package org.springframework.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
@RibbonClient(name = "ribbon-server", configuration = RibbonServerConfig.class)
public class TestRibbonClient {

  @Autowired
  RestTemplate restTemplate;

  @LoadBalanced
  @Bean
  RestTemplate restTemplate() {
    return new RestTemplate();
  }

  @RequestMapping("/hi")
  public String hi() {
    String greeting = this.restTemplate.getForObject("http://ribbon-server/greeting", String.class);
    return greeting;
  }

  public static void main(String[] args) {
    SpringApplication.run(TestRibbonClient.class, args);
  }
}