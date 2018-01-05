package org.springframework.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
@EnableDiscoveryClient
@RibbonClient(name = "eureka-workshop789-producer", configuration = EurekaConsumerConfig.class)
public class EurekaConsumerApplication {

  @LoadBalanced
  @Bean
  RestTemplate restTemplate() {
    return new RestTemplate();
  }

  @Autowired
  RestTemplate restTemplate;

  public static void main(String[] args) {
    SpringApplication.run(EurekaConsumerApplication.class, args);
  }

  @RequestMapping("/get-greeting")
  public String greeting(Model model) {
    model.addAttribute("greeting",
      this.restTemplate.getForObject("http://eureka-workshop789-producer.cfapps.io/greeting", String.class));
    return "Shown using ribbon - " + this.restTemplate.getForObject("http://eureka-workshop789-producer.cfapps.io/greeting", String.class);
  }
}