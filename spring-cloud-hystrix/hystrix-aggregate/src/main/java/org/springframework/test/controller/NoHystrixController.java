package org.springframework.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.domain.Message;
import org.springframework.test.domain.MessageAcknowledgement;
import org.springframework.test.feign.RemoteServicesClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoHystrixController {

  @Autowired
  private RemoteServicesClient remoteServicesClient;

  @RequestMapping("/noHystrix")
  public MessageAcknowledgement sendMessage(Message message) {
    return this.remoteServicesClient.sendMessage(message);
  }
}