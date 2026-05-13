# CodeLink


CodeLink is an IntelliJ IDEA plugin that allows developers to generate and navigate to code links. These code links are URLs that point to a specific line in a specific file in your project.

<!-- Plugin description -->
CodeLink is an IntelliJ IDEA plugin that allows developers to generate and navigate to code links. These code links are URLs that point to a specific line in a specific file in your project.
<!-- Plugin description end -->

## Features

- Generate a code link for the current cursor position with `Alt+C`.
- Navigate to a code link with `Alt+G`.

## Installation

- Using the IDE built-in plugin system:

  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>Marketplace</kbd> > <kbd>Search for "CodeLink"</kbd> >
  <kbd>Install</kbd>

- Manually:

  Download the [latest release](https://github.com/kentzhang-geek/codelink_idea/releases/latest) and install it manually using
  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>⚙️</kbd> > <kbd>Install plugin from disk...</kbd>

## Usage

1. To generate a code link, place your cursor on the line you want to link to and press `Alt+C`. The code link will be copied to your clipboard.
2. To navigate to a code link, press `Alt+G` and paste the code link into the input dialog.

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## License

This project is licensed under the terms of the MIT license.

## Build and package

You can build a distributable plugin ZIP locally with:

```bash
./gradlew buildPlugin
```

The artifact is generated under `build/distributions/*.zip`.

Or use the helper script to optionally bump version and package in one step:

```bash
# package with current version
./scripts/package-plugin.sh

# bump version then package
./scripts/package-plugin.sh 0.0.3
```

