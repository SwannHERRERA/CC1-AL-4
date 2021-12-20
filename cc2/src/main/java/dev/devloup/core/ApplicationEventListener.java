package dev.devloup.core;

public interface ApplicationEventListener<E extends ApplicationEvent> extends Listener<E> {
  @Override
  void accept(E event);
}
