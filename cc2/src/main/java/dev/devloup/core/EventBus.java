package dev.devloup.core;

public interface EventBus<E extends Event> {

  public void notifyListeners(E event);

  public void registerListener(Listener<? extends E> listener, Class<? extends E> eventClass);
}
