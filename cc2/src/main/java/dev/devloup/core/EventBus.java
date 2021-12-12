package dev.devloup.core;

public interface EventBus<E extends ApplicationEvent> {
  void notifyListeners(E event);

  <E1 extends ApplicationEvent> void subscribe(Class<E1> classE, ApplicationEventListener<E1> listener);
}
