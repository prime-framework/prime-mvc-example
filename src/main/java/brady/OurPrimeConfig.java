package brady;

import org.primeframework.mvc.config.AbstractMVCConfiguration;

import javax.crypto.spec.SecretKeySpec;
import java.nio.file.Path;
import java.security.Key;
import java.security.SecureRandom;

public class OurPrimeConfig extends AbstractMVCConfiguration {
    private final SecretKeySpec cookieEncryptionKey;

    public OurPrimeConfig() {
        byte[] keyBytes = new byte[16];
        new SecureRandom().nextBytes(keyBytes);
        this.cookieEncryptionKey = new SecretKeySpec(keyBytes, "AES");
    }

    @Override
    public boolean allowUnknownParameters() {
        return false;
    }

    @Override
    public Path baseDirectory() {
        return Path.of("src/main/web");
    }

    @Override
    public Key cookieEncryptionKey() {
        return this.cookieEncryptionKey;
    }

    @Override
    public int l10nReloadSeconds() {
        return 0;
    }

    @Override
    public int templateCheckSeconds() {
        return 0;
    }
}
