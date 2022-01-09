# Handling exception

- Deciders: Swann HERRERA
- Date: 2022-01-09

## Context and Problem Statement

The user creation command takes a lot of arguments and can lead to a failure for different reasons. The question is: do we return them one by one or do we return a list, and how?

## Considered Options

- throw custom exception for each error
- use a UserValidatorEngine for aglomerate errors
- use a system of notification

## Decision Outcome

Chosen option: "UserValidatorEgine", because i want an independant backend.

### Positive Consequences

- the text error is well formated

### Negative Consequences

- the error type not very descriptive
- it's not very harmonious with the rest of the case
