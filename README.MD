# ImageFireworksPro

ImageFireworksPro is a Spigot plugin that enables the creation of custom fireworks that display images as particle effects in Minecraft.

## Installation

1. Place `ImageFireworksPro.jar` into the server's `plugins` directory.
2. Restart the server or load using Plugman.

## Adding Images

1. Put `.png` or `.jpg` image files into the `plugins/ImageFireworksPro/images` directory on your server. Make sure images are no larger then 100x100 pixels to avoid performance issues due to the large number of particles.
2. Define each image in the `config.yml` in the `ImageFireworksPro` plugin directory with the appropriate `customModelData`.

Example entry in [config.yml](src/main/resources/config.yml):

```yaml
Fireworks:
  identifier:
    name: "Display Name"
    imageName: "filename.png"
    customModelData: 1234
```

## Usage
In-game, launch a firework rocket with the `customModelData` to display the image.

## Support
Report issues or request features by opening an issue on the GitHub repository.


## License
This project is licensed under the GNU General Public License v3.0 - see the [LICENSE](LICENSE) file for details.