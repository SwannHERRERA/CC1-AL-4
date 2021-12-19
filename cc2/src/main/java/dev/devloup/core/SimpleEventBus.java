package dev.devloup.core;

import java.util.HashSet;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SimpleEventBus<E extends ApplicationEvent> {
  private Set<ApplicationEventListener<E>> listeners;

  public SimpleEventBus() {
    this.listeners = new HashSet<>();
  }

  public void notifyListeners(E event) {
    if (!listeners.isEmpty()) {
      listeners.forEach(sub -> sub.accept(event));
    }
  }

  public void registerListener(ApplicationEventListener<E> listener) {
    listeners.add(listener);
  }
}