# Container for dependency injection

- Deciders: Swann HERRERA
- Date: 2022-01-09

## Context and Problem Statement

I want go have some object in my application and use inversion of control on them by a container
For this i use the framework quarkus but with a configuration object.

## Considered Options

- use IOC controller just for Domain and Infra class
- make all class in container
- make many configuration class

## Decision Outcome

Chosen option: "{use IOC controller just for Domain and Infra class}", because i dont want the configuration class to be to large and i m not very confident with the idea of many configuration class. I dont see issue while declaring quarkus controller in scoop inside them because they have already dependency with quarkus

### Positive Consequences

- shorter configuration
- more common

### Negative Consequences

- Not centralized
