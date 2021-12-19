package dev.devloup.core;

public interface EventBus<E extends Event> {

  void notifyListeners(E event);

  void registerListener(Listener<E> listener);
}
