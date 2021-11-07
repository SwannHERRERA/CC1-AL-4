package esgi.al.cc1.domain;

import java.util.HashSet;
import java.util.Set;

public class CreateUserEventBus<E extends Event> implements EventBus<E> {
  private Set<Listener<E>> listeners;

  public CreateUserEventBus() {
    this.listeners = new HashSet<>();
  }

  @Override
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
