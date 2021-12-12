package dev.devloup.core;

public interface ApplicationEventListener<E extends ApplicationEvent> {
  void listenTo(E event);
}
