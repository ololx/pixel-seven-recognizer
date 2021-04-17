# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](http://keepachangelog.com/) and this project adheres to [Semantic Versioning](http://semver.org/).

## [Unreleased] - yyyy-mm-dd

### Changed

- Refactor project code.

## [2.1.0] - 2021-04-17

### Changed
- The training process - init simple trainer realization.

### Added
- The new optional argument, which specifies the probability for training the network.

## [2.0.0] - 2021-04-13

### Changed
- The loading training data process from specific format into standard `MNIST database` folder format (`mnist_png/training/*`). Files with images of numbers should no longer be named in a certain way and lie in the same folder.

## [1.0.0] - 2021-03-16

### Added
- Initial first public release of the simple digit recognizer, which allows to draw one digit and get the answer on a question "Is this digit intended?"