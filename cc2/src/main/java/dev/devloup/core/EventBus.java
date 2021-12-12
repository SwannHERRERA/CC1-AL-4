package dev.devloup.core;

public interface EventBus<E extends ApplicationEvent> {
  void notifyListeners(E event);

  void subscribe(Class<E> classE, ApplicationEventListener<E> listener);
}
