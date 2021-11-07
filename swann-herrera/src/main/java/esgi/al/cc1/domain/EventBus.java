package esgi.al.cc1.domain;

public interface EventBus<E extends Event> {
  void notifyListeners(E event);

  void registerListener(Listener<E> listener);
}
