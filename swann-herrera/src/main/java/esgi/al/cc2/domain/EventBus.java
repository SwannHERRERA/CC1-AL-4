package esgi.al.cc2.domain;

import java.util.HashSet;
import java.util.Set;

public class EventBus<E extends Event> {
  private Set<Listener<E>> listeners;

  public EventBus() {
    this.listeners = new HashSet<>();
  }

  public void notifyListeners(E event) {
    if (!listeners.isEmpty()) {
      listeners.forEach(sub -> sub.accept(event));
    }
  }

  public void registerListener(Listener<E> listener) {
    listeners.add(listener);
  }
}
