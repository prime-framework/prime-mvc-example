package org.primeframework.mvc.sampleapp.session;

import java.time.ZonedDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.primeframework.mvc.security.UserIdSessionContext;

public class ExampleUserIdSessionContext implements UserIdSessionContext<UUID> {
  @JsonProperty
  private String loginInstant;

  @JsonProperty
  private String sessionId;

  @JsonProperty
  private UUID userId;

  public ExampleUserIdSessionContext(UUID userId, ZonedDateTime loginInstant) {
    sessionId = UUID.randomUUID().toString();
    this.userId = userId;
    this.loginInstant = loginInstant.toString();
  }

  @JsonCreator
  private ExampleUserIdSessionContext() {
  }

  @JsonIgnore
  @Override
  public ZonedDateTime getLoginInstant() {
    return ZonedDateTime.parse(loginInstant);
  }

  @Override
  public String getSessionId() {
    return sessionId;
  }

  @Override
  public UUID getUserId() {
    return userId;
  }
}
