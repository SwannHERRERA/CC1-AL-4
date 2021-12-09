package dev.devloup;

import dev.devloup.application.port.in.CreateUserEvent;
import dev.devloup.domain.Listener;

public class DummyCreateUserEventListener implements Listener<CreateUserEvent> {
  @Override
  public void accept(CreateUserEvent e) {
    // DO Nothing
  }
}
