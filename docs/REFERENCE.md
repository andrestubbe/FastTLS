# FastTLS Reference

- `connect(host, port)` creates an `SSLSocket` and completes its handshake asynchronously.
- The active JDK provider supplies TLS negotiation in the portable fallback.
- Handshake and I/O failures are represented by `FastTLS.TLSException`.
- Native SChannel/OpenSSL backends must preserve these completion semantics.
