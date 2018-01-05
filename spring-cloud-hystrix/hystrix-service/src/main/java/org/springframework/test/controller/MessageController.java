package org.springframework.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.models.Message;
import org.springframework.test.models.MessageAcknowledgement;
import org.springframework.test.service.MessageHandlerService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
public class MessageController {

  private final MessageHandlerService messageHandlerService;

  @Autowired
  public MessageController(MessageHandlerService messageHandlerService) {
    this.messageHandlerService = messageHandlerService;
  }

  @RequestMapping(value = "/message", method = RequestMethod.POST)
  public DeferredResult<MessageAcknowledgement> pongMessage(@RequestBody Message input) {
    DeferredResult<MessageAcknowledgement> deferred = new DeferredResult<>();
    this.messageHandlerService.handleMessage(input)
      .subscribe(m -> deferred.setResult(m), e -> deferred.setErrorResult(e));

    return deferred;
  }
}