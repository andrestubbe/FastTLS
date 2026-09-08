# FastTLS 0.1.0 [ALPHA-2026-09] — Ultra-Fast TLS Engine for Java

[![Status](https://img.shields.io/badge/status-0.1.0-brightgreen.svg)](https://github.com/andrestubbe/FastTLS/releases/tag/0.1.0)
[![Java](https://img.shields.io/badge/Java-17+-blue.svg)](https://www.java.com)
[![Platform](https://img.shields.io/badge/Platform-Windows%2010+-lightgrey.svg)]()
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![JitPack](https://img.shields.io/badge/JitPack-ready-green.svg)](https://jitpack.io/#andrestubbe/FastTLS)

---

**⚡ Ultra-fast TLS/HTTPS transport for the FastJava ecosystem.**

**FastTLS** provides a stable asynchronous handshake API for HTTPS clients, telemetry gateways and service-to-service communication. It uses the portable JDK provider today while reserving a native SChannel/OpenSSL backend for low-latency Windows deployments.

[**Run the TLS Capability Demo**](examples/Demo/src/main/java/fasttls/TlsCapabilitiesDemo.java) | [**Run the TLS Context Benchmark**](examples/Benchmark/src/main/java/fasttls/TlsContextBenchmark.java)

---

## Quick Start

```java
import fasttls.FastTLS;

public class Example {
    public static void main(String[] args) {
        try (var socket = FastTLS.connect("example.com", 443).join()) {
            System.out.println("Negotiated: " + socket.getSession().getProtocol());
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }
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

TLS is essential for modern services, but secure connection setup can become expensive when clients open many short-lived channels:

- **Handshake latency**: Repeated negotiation delays small API calls and telemetry bursts.
- **Provider overhead**: A portable provider may leave platform crypto acceleration unused.
- **Transport coupling**: Application code becomes difficult to migrate when security and sockets are inseparable.

**FastTLS** addresses this with a stable security boundary:

- **Asynchronous handshakes**: Connection setup completes through `CompletableFuture` without blocking callers.
- **Modern protocol surface**: The API is ready for TLS 1.3, SNI and ALPN-based HTTPS clients.
- **Native-ready backend**: SChannel and OpenSSL can replace the JDK provider without changing users of the facade.

---

## Features

- **⚡ Asynchronous handshakes**: Create and negotiate secure sockets without blocking application threads.
- **🔒 TLS 1.3-capable provider**: Uses the active Java security provider as a portable fallback.
- **🌐 HTTPS-ready boundary**: Supports the transport needs of SNI and ALPN-aware clients.
- **🔗 Ecosystem ready**: Sits directly above FastNet and beside FastCrypto primitives.

---

## Real-World Scenarios

- **HTTPS clients:** Establish secure connections to web APIs and service endpoints.
- **Telemetry gateways:** Encrypt device data before it reaches FastNet transport.
- **Game backends:** Protect authentication and control channels while keeping data paths separate.
- **Internal services:** Use native provider acceleration for high-volume service-to-service calls.

---

## Performance Benchmarks

FastTLS includes a local context benchmark and a capability demo; network handshake figures should be measured against the target service and provider.

| Metric / Security Type | Current Java Fallback | Native Target |
|------------------------|----------------------|---------------|
| **TLS context setup** | Measured by benchmark | SChannel/OpenSSL |
| **HTTPS handshake** | JDK provider | Native handshake path |
| **Record processing** | Provider-managed buffers | Zero-copy native path |

*The included benchmark measures local provider setup. Native handshake and record figures are reported only after the platform backend is integrated.*

---

## Measured Benchmark Run

The local benchmark completed **1,000 SSL context accesses in 402.17 ms** using the JDK provider with protocol `Default` during the verified run.

```text
run-benchmark.bat -> fasttls.TlsContextBenchmark
```

This measures provider setup access, not a remote HTTPS handshake; network handshake results depend on the target service and certificate chain.

---

## API Quick Reference

| Method | Description |
|---|---|
| Method / Type | Description |
|---------------|-------------|
| `connect(host, port)` | Creates a TLS socket and completes its handshake asynchronously. |
| `TLSException` | Wraps transport and handshake failures with a stable API error. |

---

## Technical Examples & Hero Demos

| Case | Java Example | Launcher | Description |
|---|---|---|---|
| **TLS Provider Capabilities** | [TlsCapabilitiesDemo.java](examples/Demo/src/main/java/fasttls/TlsCapabilitiesDemo.java) | `run-demo.bat` | Reports the active provider and protocol used by the local runtime. |
| **TLS Context Preparation** | [TlsContextBenchmark.java](examples/Benchmark/src/main/java/fasttls/TlsContextBenchmark.java) | `run-benchmark.bat` | Measures repeated context access for HTTPS service clients. |

---

## Installation

### Option 1: Maven (Recommended)

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
<dependencies>
    <dependency>
    <groupId>com.github.andrestubbe</groupId>
    <artifactId>FastTLS</artifactId>
    <version>0.1.0</version>
</dependency>
</dependencies>
```

### Option 2: Gradle (via JitPack)

```groovy
repositories {
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation 'com.github.andrestubbe:FastTLS:0.1.0'
}
```

### Option 3: Direct Download (No Build Tool)

Download the latest FastTLS JAR from the [GitHub releases](https://github.com/andrestubbe/FastTLS/releases) page.

---

## Documentation

* **[COMPILE.md](docs/COMPILE.md)**: Full compilation guide and launcher instructions.
* **[REFERENCE.md](docs/REFERENCE.md)**: TLS API and security contract.
* **[PHILOSOPHY.md](docs/PHILOSOPHY.md)**: Native-first security principles.
* **[ROADMAP.md](docs/ROADMAP.md)**: Planned SChannel/OpenSSL milestones.
* **[CHANGELOG.md](docs/CHANGELOG.md)**: Version history.

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

**Part of the FastJava Ecosystem** — *Making the JVM faster. Small package. Maximum speed. Zero bloat. 🚀📋*
