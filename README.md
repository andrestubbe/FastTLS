# FastTLS 0.1.0 [ALPHA-2026-09] — Ultra-Fast TLS Engine for Java

[![Status](https://img.shields.io/badge/status-0.1.0-brightgreen.svg)](https://github.com/andrestubbe/FastTLS)
[![Java](https://img.shields.io/badge/Java-17+-blue.svg)](https://www.java.com)
[![Platform](https://img.shields.io/badge/Platform-Windows%2010+-lightgrey.svg)]()
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

---

**Asynchronous TLS/HTTPS transport for the FastJava ecosystem.** FastTLS provides a stable handshake API today and a native SChannel/OpenSSL backend boundary for low-latency encrypted services.

## Quick Start

```java
try (var socket = FastTLS.connect("example.com", 443).join()) {
    System.out.println(socket.getSession().getProtocol());
}
```

## Table of Contents

- [Why FastTLS?](#why-fasttls)
- [Quick Start](#quick-start)
- [Features](#features)
- [Real-World Scenarios](#real-world-scenarios)
- [Performance Benchmarks](#performance-benchmarks)
- [API Quick Reference](#api-quick-reference)
- [Technical Examples & Hero Demos](#technical-examples--hero-demos)
- [Installation](#installation)
- [Documentation](#documentation)
- [Platform Support](#platform-support)
- [License](#license)
- [Related Projects](#related-projects)

---

## Why FastTLS?

TLS is essential for modern services, but repeated handshake setup, provider transitions and buffer copies can dominate small real-time requests. FastTLS isolates the secure transport contract so native SChannel/OpenSSL acceleration can replace the portable provider without changing application code.

---

## Features

- Asynchronous TLS socket creation and handshake.
- TLS 1.3-capable provider negotiation through the active SSL context.
- ALPN/SNI-ready transport boundary for HTTPS and HTTP/2 clients.
- Direct integration point for FastNet transport and FastCrypto primitives.

---

## Real-World Scenarios

- **HTTPS clients:** Establish secure connections to web APIs and service endpoints.
- **Telemetry gateways:** Encrypt device data before it reaches FastNet transport.
- **Game backends:** Protect authentication and control channels while keeping data paths separate.
- **Internal services:** Use native provider acceleration for high-volume service-to-service calls.

---

## Performance Benchmarks

The included benchmark measures a real HTTPS handshake; native provider figures must be measured after SChannel/OpenSSL integration.

| Operation | Current Java fallback | Native target |
|---|---:|---:|
| TLS handshake | Measured by `run-benchmark.bat` | SChannel/OpenSSL |
| Record processing | JDK provider | Zero-copy native path |

---

## API Quick Reference

| Method | Description |
|---|---|
| `connect(host, port)` | Creates a TLS socket and completes its handshake asynchronously. |
| `TLSException` | Wraps transport and handshake failures with a stable API error. |

---

## Technical Examples & Hero Demos

| Case | Java Example | Launcher | Description |
|---|---|---|---|
| **HTTPS Handshake** | [Demo.java](examples/Demo/src/main/java/fasttls/Demo.java) | `run-demo.bat` | Connects to example.com and reports the negotiated TLS protocol. |
| **Handshake Timing** | [Benchmark.java](examples/Benchmark/src/main/java/fasttls/benchmark/Benchmark.java) | `run-benchmark.bat` | Measures a complete HTTPS handshake. |

---

## Installation

### Option 1: Maven (Recommended)

```xml
<dependency>
    <groupId>com.github.andrestubbe</groupId>
    <artifactId>FastTLS</artifactId>
    <version>0.1.0</version>
</dependency>
```

### Option 2: Gradle (via JitPack)

```groovy
implementation 'com.github.andrestubbe:FastTLS:0.1.0'
```

### Option 3: Direct Download (No Build Tool)

Download the latest FastTLS JAR from the [GitHub releases](https://github.com/andrestubbe/FastTLS/releases) page.

---

## Documentation

- [COMPILE.md](docs/COMPILE.md): Build and launcher instructions.
- [REFERENCE.md](docs/REFERENCE.md): TLS API and security contract.
- [PHILOSOPHY.md](docs/PHILOSOPHY.md): Native-first security principles.
- [ROADMAP.md](docs/ROADMAP.md): Planned SChannel/OpenSSL milestones.
- [CHANGELOG.md](docs/CHANGELOG.md): Version history.

---

## Platform Support

| Platform | Status |
|---|---|
| Windows 10/11 x64 | Native SChannel planned |
| Linux | JDK TLS fallback |
| macOS | JDK TLS fallback |

---

## License

MIT License — See [LICENSE](LICENSE) for details.

---

## Related Projects

- [FastNet](https://github.com/andrestubbe/FastNet) — Asynchronous transport
- [FastDNS](https://github.com/andrestubbe/FastDNS) — Endpoint resolution
- [FastCrypto](https://github.com/andrestubbe/FastCrypto) — Cryptographic primitives

---

**Part of the FastJava Ecosystem** — Making the JVM faster. Small package. Maximum speed.
