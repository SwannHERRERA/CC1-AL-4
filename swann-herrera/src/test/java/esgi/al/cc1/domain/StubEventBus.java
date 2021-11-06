package esgi.al.cc1.domain;

public class StubEventBus<E extends Event> extends EventBus<E> {
  @Override
  public void notifyListeners(E event) {
  }
}
