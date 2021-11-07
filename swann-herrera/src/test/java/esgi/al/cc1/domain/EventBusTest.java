package esgi.al.cc1.domain;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class EventBusTest {
  private final Listener<DummyEvent> listener1;
  private final Listener<DummyEvent> listener2;
  private final EventBus<DummyEvent> bus;

  EventBusTest() {
    listener1 = Mockito.spy(new DummyEventListener());
    listener2 = Mockito.spy(new DummyEventListener());
    bus = new EventBus<>();
  }

  @Test
  void test_notify_no_listeners() {
    DummyEvent event = new DummyEvent();
    bus.notifyListeners(event);
    verify(listener1, times(0)).accept(event);
    verify(listener2, times(0)).accept(event);
  }

  @Test
  void test_notify_listener_when_registered() {
    DummyEvent event = new DummyEvent();
    bus.registerListener(listener1);
    bus.notifyListeners(event);
    verify(listener1, times(1)).accept(event);
    verify(listener2, times(0)).accept(event);
  }

  @Test
  void test_notify_multiple_listeners() {
    DummyEvent event = new DummyEvent();
    bus.registerListener(listener1);
    bus.registerListener(listener2);
    bus.notifyListeners(event);
    verify(listener1, times(1)).accept(event);
    verify(listener2, times(1)).accept(event);
  }

  @Test
  void test_notify_multiple_time() {
    DummyEvent event = new DummyEvent();
    bus.registerListener(listener1);
    bus.notifyListeners(event);
    bus.notifyListeners(event);
    verify(listener1, times(2)).accept(event);
    verify(listener2, times(0)).accept(event);
  }

  @Test
  void test_register_multiple_time() {
    DummyEvent event = new DummyEvent();
    bus.registerListener(listener1);
    bus.notifyListeners(event);
    bus.registerListener(listener2);
    bus.registerListener(listener2);
    bus.notifyListeners(event);
    bus.notifyListeners(event);
    verify(listener1, times(3)).accept(event);
    verify(listener2, times(2)).accept(event);
  }
}
