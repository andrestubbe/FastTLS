package fasttls;

import javax.net.ssl.SSLContext;

public final class TlsCapabilitiesDemo {
    private TlsCapabilitiesDemo() {
    }

    public static void main(String[] args) throws Exception {
        SSLContext context = SSLContext.getDefault();
        System.out.printf("provider=%s, protocol=%s%n", context.getProvider().getName(), context.getProtocol());
    }
}
