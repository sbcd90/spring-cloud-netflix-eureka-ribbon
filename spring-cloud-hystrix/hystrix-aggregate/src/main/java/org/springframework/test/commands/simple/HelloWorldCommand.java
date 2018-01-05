package org.springframework.test.commands.simple;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class HelloWorldCommand extends HystrixCommand<String> {

  private final String name;

  public HelloWorldCommand(String name) {
    super(HystrixCommandGroupKey.Factory.asKey("HelloWorldCommand"));
    this.name = name;
  }

  @Override
  protected String run() {
    return "Hello " + name;
  }
}