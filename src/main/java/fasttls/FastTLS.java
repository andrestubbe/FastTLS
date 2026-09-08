package fasttls;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

/** TLS facade using the JDK provider until native SChannel/OpenSSL is selected. */
public final class FastTLS {
    private static final SSLContext DEFAULT_CONTEXT = createDefaultContext();

    private FastTLS() {
    }

    public static CompletableFuture<SSLSocket> connect(String host, int port) {
        Objects.requireNonNull(host, "host");
        return CompletableFuture.supplyAsync(() -> {
            try {
                SSLSocket socket = (SSLSocket) DEFAULT_CONTEXT.getSocketFactory().createSocket(host, port);
                socket.startHandshake();
                return socket;
            } catch (IOException exception) {
                throw new TLSException("TLS connection failed", exception);
            }
        });
    }

    private static SSLContext createDefaultContext() {
        try {
            return SSLContext.getDefault();
        } catch (GeneralSecurityException exception) {
            throw new ExceptionInInitializerError(exception);
        }
    }

    public static final class TLSException extends RuntimeException {
        public TLSException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
