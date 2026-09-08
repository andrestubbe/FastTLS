package fasttls.benchmark;

import fasttls.FastTLS;

public final class Benchmark {
    public static void main(String[] args) {
        long start = System.nanoTime();
        FastTLS.connect("example.com", 443).join().close();
        System.out.printf("TLS handshake completed in %.3f ms%n", (System.nanoTime() - start) / 1_000_000.0);
    }
}
