package org.primeframework.mvc.sampleapp.session;

import java.time.Clock;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import io.fusionauth.http.server.HTTPRequest;
import io.fusionauth.http.server.HTTPResponse;
import org.primeframework.mvc.sampleapp.domain.ExampleUser;
import org.primeframework.mvc.security.BaseUserIdCookieSecurityContext;
import org.primeframework.mvc.security.Encryptor;
import org.primeframework.mvc.security.UserIdSessionContext;

public class ExampleLoginContext extends BaseUserIdCookieSecurityContext<UUID> {
  @Inject
  public ExampleLoginContext(HTTPRequest request,
                             HTTPResponse response,
                             Encryptor encryptor,
                             ObjectMapper objectMapper) {
    super(request,
        response,
        encryptor,
        objectMapper,
        Clock.systemUTC(),
        Duration.ofMinutes(30),
        Duration.ofHours(24));
  }

  @Override
  public Set<String> getCurrentUsersRoles() {
    return Set.of();
  }

  @Override
  protected UserIdSessionContext<UUID> createUserIdSessionContext(UUID uuid, ZonedDateTime loginInstant) {
    return new ExampleUserIdSessionContext(uuid, loginInstant);
  }

  @Override
  protected UUID getIdFromUser(Object user) {
    return ((ExampleUser) user).id;
  }

  @Override
  protected Class<? extends UserIdSessionContext<UUID>> getUserIdSessionContextClass() {
    return ExampleUserIdSessionContext.class;
  }

  @Override
  protected Object retrieveUserById(UUID uuid) {
    var user = new ExampleUser();
    user.id = uuid;
    return user;
  }
}
