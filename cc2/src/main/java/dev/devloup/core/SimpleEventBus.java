package dev.devloup.core;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public final class SimpleEventBus<E extends Event> implements EventBus<E> {
  private Map<Class<? extends Event>, Set<Listener<? extends E>>> associatedListeners;

  public SimpleEventBus() {
    this.associatedListeners = new HashMap<>();
  }

  @SuppressWarnings("all")
  @Override
  public void notifyListeners(E event) {
    var eventListeners = this.associatedListeners.get(event.getClass());
    if (eventListeners == null) {
      // Do nothing
      return;
    }

    for (Listener eventListener : eventListeners) {
      eventListener.accept(event);
    }
  }

  @Override
  public void registerListener(Listener<? extends E> listener, Class<? extends E> eventClass) {
    Set<Listener<? extends E>> list = this.associatedListeners.get(eventClass);
    if (list == null) {
      list = new HashSet<>();
      list.add(listener);
      this.associatedListeners.put(eventClass, list);
    } else {
      list.add(listener);
    }

  }
}