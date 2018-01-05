package org.springframework.test.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MessageAcknowledgement {

  private String id;
  private String received;
  private String payload;

  public MessageAcknowledgement() {}

  @JsonCreator
  public MessageAcknowledgement(@JsonProperty("id") String id,
                                @JsonProperty("received") String received,
                                @JsonProperty("payload") String payload) {
    this.id = id;
    this.received = received;
    this.payload = payload;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getReceived() {
    return received;
  }

  public void setReceived(String received) {
    this.received = received;
  }

  public String getPayload() {
    return payload;
  }

  public void setPayload(String payload) {
    this.payload = payload;
  }
}