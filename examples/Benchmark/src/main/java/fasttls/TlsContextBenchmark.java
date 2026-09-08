package fasttls;

import javax.net.ssl.SSLContext;

public final class TlsContextBenchmark {
    private TlsContextBenchmark() {
    }

    public static void main(String[] args) throws Exception {
        long start = System.nanoTime();
        SSLContext context = null;
        for (int index = 0; index < 1000; index++) {
            context = SSLContext.getDefault();
        }
        long elapsed = System.nanoTime() - start;
        System.out.printf("contexts=1,000, protocol=%s, elapsedMs=%.2f%n", context.getProtocol(), elapsed / 1_000_000.0);
    }
}
