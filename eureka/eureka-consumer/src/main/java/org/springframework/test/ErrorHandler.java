package org.springframework.test;

import org.springframework.boot.autoconfigure.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RestController
public class ErrorHandler implements ErrorController {

  @RequestMapping(value = "/error")
  public String error() {
    Client client = ClientBuilder.newClient();
    WebTarget target = client.target("http://eureka-workshop789-producer.cfapps.io");
    WebTarget greetingTarget = target.path("/greeting");
    Invocation.Builder requestBuilder = greetingTarget.request(MediaType.TEXT_HTML);
    Response response = requestBuilder.get();
    return "Shown as fallback - " + response.readEntity(String.class);
  }

  @Override
  public String getErrorPath() {
    return "/error";
  }
}