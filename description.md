# FastTLS

Hardwarebeschleunigte TLS/HTTPS-Verschlüsselung via nativem OpenSSL/SChannel ohne SSLEngine-Overhead.

---

## ⚡ Overview

**FastTLS** ersetzt Javas rechenintensive `SSLEngine` durch native Einbindung von Windows SChannel und optimiertem OpenSSL/BoringSSL.

---

## 🔑 Kernmerkmale

- **Zero-Copy Handshake & Record Layer**: TLS 1.3 mit minimalen Latenzzyklen.
- **Hardware-Cipher-Suites**: AES-GCM und ChaCha20-Poly1305 direkt im nativen Code.
- **ALPN & SNI Support**: Vollständige Unterstützung für HTTP/2 und modernste Protokolle.

---

## 🏗️ Ecosystem Integration

- **FastNet**: Direkte TLS-Schicht auf IOCP-Sockets.