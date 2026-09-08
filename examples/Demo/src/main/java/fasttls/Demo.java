package fasttls;

import fasttls.FastTLS;

public final class Demo {
    public static void main(String[] args) {
        try (var socket = FastTLS.connect("example.com", 443).join()) {
            System.out.println("TLS session: " + socket.getSession().getProtocol());
        } catch (Exception exception) {
            System.err.println("TLS demo unavailable: " + exception.getMessage());
        }
    }
}
