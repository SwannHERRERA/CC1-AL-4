package dev.devloup;

import dev.devloup.application.port.in.CreateUserEvent;
import dev.devloup.core.ApplicationEventListener;

public class DummyCreateUserEventListener implements ApplicationEventListener<CreateUserEvent> {
  @Override
  public void listenTo(CreateUserEvent event) {
    // DO Nothing
  }
}
