# pixel seven recognizer

The Pixel Seven Recognizer is a simple recognizer, which allows to draw one digit and get the answer on a question "Is this digit intended?"

[![status](https://img.shields.io/badge/status-completed-inactive?style=flat-square)](BADGES_GUIDE.md#status) [![version](https://img.shields.io/badge/version-1.0.0-informational?style=flat-square)](BADGES_GUIDE.md#version) [![oss lifecycle](https://img.shields.io/badge/oss_lifecycle-archived-important?style=flat-square)](BADGES_GUIDE.md#oss-lifecycle) [![maintenance](https://img.shields.io/badge/maintenance-yes-informational?style=flat-square)](BADGES_GUIDE.md#maintenance) [![latest release date](https://img.shields.io/badge/latest_release_date-March_16,_2021-informational?style=flat-square)](BADGES_GUIDE.md#release-date) [![last commit](https://img.shields.io/badge/last_commit-March_16,_2021-informational?style=flat-square)](BADGES_GUIDE.md#commit-date)

[![license](https://img.shields.io/badge/license-MIT-informational?style=flat-square)](LICENSE) [![Contributor Covenant](https://img.shields.io/badge/Contributor%20Covenant-v2.0%20adopted-ff69b4.svg?style=flat-square)](CODE_OF_CONDUCT.md)

---

## 📇 Table of Contents

- [About](#about)
- [Demo](#demo)
- [Features](#feature)
- [Getting Started](#getting-started)
- [Built With](#built-with)
- [Contributing](#contributing)
- [Code of Conduct](#code-of-conduct)
- [Versioning](#versioning)
- [Authors](#authors)
- [Licensing](#licensing)

##  📖 About

The Pixel Seven Recognizer is a simple recognizer, which allows to draw one digit and get the answer on a question "Is this digit intended?". This recognizer based on the single layer perceptron and could be trained to recognition of one digit (from 1 to 9). The following image demonstrates this recognition.

<img src="https://github.com/ololx/pixel-seven-recognizer/blob/assets/demo/recognition-schema.png?raw=true" width="700"/>

## 📸 Demo

This image demonstrates the recognizer usage. The green background means that the digit is intended, the red background - no.

<img src="https://github.com/ololx/pixel-seven-recognizer/blob/assets/demo/recognition-demo-1.gif?raw=true" width="700"/>

This image  demonstrates the visualisation of the recognizer training process. Each pixel of the image is colored according to its weight for the neural network. Green means positive weight for a given pixel, red means negative weight. A more saturated color means a higher value, black means 0.

<img src="https://github.com/ololx/pixel-seven-recognizer/blob/assets/demo/training-demo-1.gif?raw=true" width="700"/>

## 🎚 Features

- The simple funny digit recognizer, which allows to draw one digit and to get the answer on a question "Is this digit intended?". 

### To Do

- ~~For more information on an upcoming development, please read the todo list.~~ No plans.

### Changelog

- For more information on a releases, a features and a changes, please read the [changelog](CHANGELOG.md) notes.

## 🚦 Getting Started

These instructions allow to get a copy of this project and run it on a local machine.

### Prerequisites

Before using it, make sure that follows software are installed on the local machine:

- **[Oracle JDK 9+](https://www.oracle.com/java/technologies/javase-downloads.html)** - the java development kit.

If any of the listed software is not installed, then it can be installed by instruction as described below.

1. #### Oracle JDK 11+

   - Install Oracle JDK 11+ according to instructions from an [official](https://www.oracle.com/java/technologies/javase-downloads.html) instruction.

### Installing

In order to install it is quite simple to clone or download this repository, or download `jar` file from release assets.

### Cloning

For the cloning this repository to a local machine, just use the follows link:

```http
https://github.com/ololx/pixel-seven-recognizer.git
```

### Using

To use it is necessary to:
1. Build the project.
2. Launch the instance .jar with the follows args:
```bash
{digit} path_to_training_data
```
where {digit} - is a digit from 0 to 9; path_to_training_data - path to directory with a png files (the name should contains from 11 symbols, where 11s symbol is a digit; fro instance `000000-num5.png` for png with `5` ).
*For* instance:` java -jar pixel-seven-recognizer.jar 2 /Volumes/disk1/traindata`
*!Important: the data set could be downloaded by the following link https://pjreddie.com/media/files/mnist_train.tar.gz* (author's repository link -  https://github.com/pjreddie/mnist-csv-png)

3. Wait until the training data is loaded and the training process is complete (after training will be a black frame).
4. Draw digit and clear it, and  again:
    4.1. Use the first mouse button to draw. 
    4.2. Use the second mouse button to clear.
    4.3. Use the mouse wheel (up/down) to resize the brush stroke.

## 🛠 Built With

**[Oracle JDK](https://www.oracle.com/java/technologies/javase-downloads.html)** -  java development kit;

## 🎉 Contributing

If you want to contribute this project - you are welcome and have fun.
Please visit the [contributing](CONTRIBUTING.md) section for details on this code of conduct, and the process for submitting pull requests.

## 📝 Code of Conduct

In order to ensure that all is welcoming, please review and abide by the [code of conduct](CODE_OF_CONDUCT.md).

## 🗒 Versioning

For the versioning is used [Semantic Versioning](http://semver.org/). For the versions available, see the [changelog](CHANGELOG.md) or the tags on this repository.

## ©️ Authors

* **Alexander A. Kropotin** - *Initial work* - [ololx](https://github.com/ololx).

## 🔏 Licensing

This project is licensed under the MIT license - see the [lisence](LICENSE) document for details.
