# ITACraft

ITACraft is a Minecraft mod project created using **MCreator** and built
with **Gradle**. The project includes custom blocks, items, models, and
other mod elements defined through MCreator's workspace system.

## Project Structure

-   `.mcreator/` -- MCreator workspace files\
-   `elements/` -- Mod elements such as blocks, items, and procedures\
-   `models/` -- Block and entity model resources\
-   `src/main/` -- Java source code generated or added manually\
-   `build.gradle`, `settings.gradle` -- Gradle configuration files\
-   `ITACraft.mcreator` -- Main MCreator project definition

## Getting Started

### Requirements

-   MCreator\
-   Java Development Kit (JDK)\
-   Gradle (or use the included `gradlew` wrapper)

### Importing into MCreator

1.  Open MCreator\
2.  Choose **Open existing workspace**\
3.  Select the `.mcreator` folder in this repository

### Building the Mod

Run the build command:

    ./gradlew build

The compiled mod JAR will appear in `build/libs/`.

### Testing

-   Use MCreator's **Run Client** option, or\
-   Place the built JAR file into the Minecraft `mods` folder for manual
    testing.

## Contributing

1.  Fork the repository\
2.  Create a feature branch\
3.  Make and test changes\
4.  Submit a pull request

Keep naming and structure consistent with the existing project layout.

## License

Add your license of choice here.
