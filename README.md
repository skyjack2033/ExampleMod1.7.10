# Nova Engineering - ECO AE Extension

[![](https://github.com/skyjack2033/ExampleMod1.7.10/actions/workflows/build-and-test.yml/badge.svg)](https://github.com/skyjack2033/ExampleMod1.7.10/actions/workflows/build-and-test.yml)

A **Minecraft 1.7.10 GTNH** port of [NovaEngineering-ECOAEExtension](https://github.com/sddsd2332/NovaEngineering-ECOAEExtension), an AE2 (Applied Energistics 2) addon mod that adds advanced multi-block machines for crafting, storage, and calculation.

Originally developed for Minecraft 1.12.2 RetroFuturaGradle, ported to 1.7.10 GTNH Convention build system.

### Features

- **ECalculator** — Externalized AE2 Crafting CPU multi-block
- **EFabricator** — Multi-block auto-crafting with parallel processing
- **EStorage** — Extended AE2 storage multi-block with custom cells
- **Custom GUI** — Advanced monitoring and control interfaces
- **ME Pattern Integration** — Enhanced pattern encoding and storage

### Build Requirements

- **JDK 25+** (required by GTNH Convention 2.x)
- **Gradle 9.3.1** (auto-downloaded by wrapper)
- Network access to GTNH Maven repositories

### Getting Started

```bash
# Clone the repository
git clone https://github.com/skyjack2033/ExampleMod1.7.10.git
cd ExampleMod1.7.10

# Build the mod
./gradlew build

# Run Minecraft client for testing
./gradlew runClient
```

### Project Structure

| Path | Description |
|------|-------------|
| `src/main/java/github/kasuminova/ecoaeextension/` | Main mod source (ported from 1.12.2) |
| `src/main/java/appeng/` | AE2 API compatibility stubs |
| `src/main/java/hellfirepvp/` | Modular Machinery compatibility layer |
| `src/main/java/github/kasuminova/mmce/` | MMCE extended compatibility layer |
| `src/main/java/net/minecraftforge/` | Forge API compatibility stubs |
| `src/main/resources/assets/ecoaeextension/` | Mod assets (blockstates, models, textures, lang) |
| `gradle.properties` | Mod identity and build configuration |
| `dependencies.gradle` | Mod dependency declarations |

### Dependencies

- **AE2 Unofficial** (`Applied-Energistics-2-Unofficial:rv3-beta-871-GTNH`)
- **GTNH Convention** build plugin (`com.gtnewhorizons.gtnhconvention`)
- **UniMixins** (via GTNH Convention)
- **Lombok** (compile-time only)

### Porting Status

The mod is currently being ported from 1.12.2 RetroFuturaGradle to 1.7.10 GTNH Convention. 

| Component | Status |
|-----------|--------|
| Build system | Complete |
| AE2 API stubs | In progress |
| MMCE compatibility | Complete |
| Forge compat layer | Complete |
| Main mod logic | In progress |

### License

This project is licensed under the MIT License. See [LICENSE](LICENSE) for details.

### Credits

Original mod by Kasumi_Nova, sddsd2332, WI_8614_ice
1.7.10 port based on GTNH ExampleMod template by SinTh0r4s, TheElan, basdxz
