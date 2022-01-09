# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

## [0.1.0] - 2021-10-31

### Added 2021-10-31

- start the project
- entity user

### Changed 2021-10-31

- stop using UserId for wrapping UUID use UUID directly

## [0.1.1] - 2021-11-03

I think now my main job is to create the command for apply for membership
The apply for membership can be see in many maner. I want to bring out two :

- request for register
- request upgrade of the user status

### Added [0.1.1]

- create a command
- create custom Domain exception
- move forward for userRepository and UserService

No TDD yet only structures

### Changed [0.1.1]

- stop using UserId for wrapping UUID use UUID directly

### Removed

### Fixed

## 2021-11-04

### Added 2021-11-04

- user Service implementation
- test
- fake Implementation of repo in test

## 2021-11-06

### Added 2021-11-06

- add event bus system
- test for event bus
- mockito

### Changed 2021-11-06

- Event bus system now supports only one Event Type by Bus

## 2021-11-21

### Added 2021-11-21

- docs
- logging system
- Gestion du cas ou l'utilisateur est refusé pour manque d'argent.

## [1.0.0] - 2021-12-04

Rendu du CC1

## [2.0.0] - 2022-01-09

### Added

- Quarkus web framework
- scheduler
- userBuilder
- Transaction system
- shared package
- Single eventbus
- addMoney command flow

### Changed

- now i use a single event bus
- Package separation by feature

[unreleased]: https://github.com/SwannHERRERA/CC1-AL-4/commits/develop/changelog.md
