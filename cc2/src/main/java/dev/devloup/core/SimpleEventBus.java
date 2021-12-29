package dev.devloup.core;

import java.util.HashSet;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public final class SimpleEventBus<E extends Event> implements EventBus<E> {
  private Set<Listener<E>> listeners;

  public SimpleEventBus() {
    this.listeners = new HashSet<>();
  }

  public void notifyListeners(E event) {
    if (!listeners.isEmpty()) {
      listeners.forEach(sub -> sub.accept(event));
    }
  }

  @Override
  public void registerListener(Listener<E> listener) {
    listeners.add(listener);

  }
}