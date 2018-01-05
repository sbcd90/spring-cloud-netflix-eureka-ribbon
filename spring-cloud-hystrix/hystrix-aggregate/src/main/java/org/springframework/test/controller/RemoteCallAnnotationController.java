package org.springframework.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.commands.remote.RemoteMessageAnnotationClient;
import org.springframework.test.domain.Message;
import org.springframework.test.domain.MessageAcknowledgement;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RemoteCallAnnotationController {

  @Autowired
  private RemoteMessageAnnotationClient remoteServiceClient;

  @RequestMapping("/messageAnnotation")
  public MessageAcknowledgement sendMessage(Message message) {
    return remoteServiceClient.sendMessage(message);
  }
}