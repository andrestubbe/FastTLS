package fasttls;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

/** TLS facade using the JDK provider until native SChannel/OpenSSL is selected. */
public final class FastTLS {
    private FastTLS() {
    }

    public static CompletableFuture<SSLSocket> connect(String host, int port) {
        Objects.requireNonNull(host, "host");
        return CompletableFuture.supplyAsync(() -> {
            try {
                SSLSocket socket = (SSLSocket) SSLContext.getDefault().getSocketFactory().createSocket(host, port);
                socket.startHandshake();
                return socket;
            } catch (IOException | GeneralSecurityException exception) {
                throw new TLSException("TLS connection failed", exception);
            }
        });
    }

    public static final class TLSException extends RuntimeException {
        public TLSException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
