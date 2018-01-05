package org.springframework.test.controller;

import org.springframework.test.commands.simple.HelloWorldCommand;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

  public String callHelloWorldCommand(@RequestParam(value = "greeting", defaultValue = "World", required = false) String greeting) {
    HelloWorldCommand helloWorldCommand = new HelloWorldCommand(greeting);
    return helloWorldCommand.execute();
  }
}