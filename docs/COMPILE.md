# Building FastTLS

Requires JDK 17+ and Maven 3.9+.

- `mvn clean test` compiles the library and unit tests.
- `run-demo.bat` performs a real HTTPS/TLS handshake with example.com.
- `run-benchmark.bat` measures one connection handshake.

The current backend uses the JDK TLS provider; SChannel/OpenSSL support can replace it behind the same contract.
