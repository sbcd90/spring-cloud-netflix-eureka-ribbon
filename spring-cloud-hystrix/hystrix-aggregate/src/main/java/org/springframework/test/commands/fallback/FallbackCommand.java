package org.springframework.test.commands.fallback;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class FallbackCommand extends HystrixCommand<String> {

  public FallbackCommand() {
    super(HystrixCommandGroupKey.Factory.asKey("default"));
  }

  @Override
  protected String run() {
    throw new RuntimeException("Always fail");
  }

  @Override
  protected String getFallback() {
    return "Falling back";
  }
}