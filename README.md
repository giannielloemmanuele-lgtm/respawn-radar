# 🧭 RespawnRadar

**Your compass points to where you died. Never lose your stuff again.**

RespawnRadar is a lightweight, zero-config [Paper](https://papermc.io) plugin. When you die, it remembers where — and when you respawn, your compass automatically points to your death location so you can walk back and recover your items before they despawn.

## ✨ Features

- 🧭 **Automatic compass targeting** — respawn and just follow your compass
- ☠ **Death coordinates in chat** — X, Y, Z and world name, right when you respawn
- 📏 **`/deathpoint` command** — check your last death location and the distance to it anytime (aliases: `/dp`)
- 🪶 **Lightweight** — one tiny jar, no dependencies, no database
- ⚙️ **One config option** — turn chat messages off if you want the compass to work silently

## 📦 Installation

1. Download the latest `RespawnRadar-x.x.x.jar` from the [Releases](../../releases) page
2. Drop it into your server's `plugins/` folder
3. Restart the server. Done!

Requires a Paper (or compatible) server.

## 🎮 Usage

Just play. When you die and respawn:

```
☠ You died at X: 128  Y: 64  Z: -305 (world)
🧭 Your compass now points to your death location. Type /deathpoint anytime.
```

| Command | Description |
|---|---|
| `/deathpoint` (or `/dp`) | Shows your last death coordinates, distance, and re-points your compass |

## ⚙️ Configuration

`plugins/RespawnRadar/config.yml`:

```yaml
# Show death coordinates in chat when the player respawns.
show-coordinates: true
```

## 🔨 Building from source

Requires JDK 25 and Maven.

```
mvn package
```

The jar will be in `target/RespawnRadar-x.x.x.jar`.

## 🗺 Roadmap

- [ ] Persist death locations across server restarts
- [ ] Configurable messages (with translations)
- [ ] Optional particle trail to the death point
- [ ] Support for multiple stored deaths

Suggestions and pull requests are very welcome!

## 📄 License

[MIT](LICENSE)

## Development Notes

This plugin was initially scaffolded with AI assistance (Claude),
but all testing, compilation, and manual verification has been done independently.
The code is fully functional and has been thoroughly reviewed.
