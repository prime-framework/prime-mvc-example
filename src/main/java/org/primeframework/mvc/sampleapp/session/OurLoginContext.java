package org.primeframework.mvc.sampleapp.session;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import io.fusionauth.http.server.HTTPRequest;
import io.fusionauth.http.server.HTTPResponse;
import org.primeframework.mvc.security.BaseUserIdCookieSecurityContext;
import org.primeframework.mvc.security.Encryptor;
import org.primeframework.mvc.security.UserIdSessionContext;

import java.time.Clock;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.Set;
import java.util.UUID;

public class OurLoginContext extends BaseUserIdCookieSecurityContext<UUID> {
    @Inject
    public OurLoginContext(HTTPRequest request,
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
    protected UserIdSessionContext<UUID> createUserIdSessionContext(UUID uuid, ZonedDateTime loginInstant) {
        return new OurUserIdSessionContext(uuid, loginInstant);
    }

    @Override
    protected UUID getIdFromUser(Object user) {
        return ((OurUser) user).id;
    }

    @Override
    protected Class<? extends UserIdSessionContext<UUID>> getUserIdSessionContextClass() {
        return OurUserIdSessionContext.class;
    }

    @Override
    protected Object retrieveUserById(UUID uuid) {
        var user = new OurUser();
        user.id = uuid;
        return user;
    }

    @Override
    public Set<String> getCurrentUsersRoles() {
        return Set.of();
    }
}
