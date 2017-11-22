package org.springframework.test;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("eureka-producer")
public interface GreetingClient {
  @RequestMapping("/greeting")
  String greeting();
}