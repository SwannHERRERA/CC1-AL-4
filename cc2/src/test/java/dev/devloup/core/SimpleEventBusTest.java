package dev.devloup.core;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import dev.devloup.dummys.DummyEvent;
import dev.devloup.dummys.DummyEvent2;
import dev.devloup.dummys.DummyEventListener;
import dev.devloup.dummys.DummyEventListener2;

final class SimpleEventBusTest {
  private final Listener<DummyEvent> listener1;
  private final Listener<DummyEvent> listener2;
  private final Listener<DummyEvent2> listener3;
  private final EventBus<ApplicationEvent> bus;

  public SimpleEventBusTest() {
    listener1 = Mockito.spy(new DummyEventListener());
    listener2 = Mockito.spy(new DummyEventListener());
    listener3 = Mockito.spy(new DummyEventListener2());
    bus = new SimpleEventBus<ApplicationEvent>();
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
    bus.registerListener(listener1, DummyEvent.class);
    bus.notifyListeners(event);
    verify(listener1, times(1)).accept(event);
    verify(listener2, times(0)).accept(event);
  }

  @Test
  void test_notify_multiple_listeners() {
    DummyEvent event = new DummyEvent();
    bus.registerListener(listener1, DummyEvent.class);
    bus.registerListener(listener2, DummyEvent.class);
    bus.notifyListeners(event);
    verify(listener1, times(1)).accept(event);
    verify(listener2, times(1)).accept(event);
  }

  @Test
  void test_notify_multiple_time() {
    DummyEvent event = new DummyEvent();
    bus.registerListener(listener1, DummyEvent.class);
    bus.notifyListeners(event);
    bus.notifyListeners(event);
    verify(listener1, times(2)).accept(event);
    verify(listener2, times(0)).accept(event);
  }

  @Test
  void test_register_multiple_time() {
    DummyEvent event = new DummyEvent();
    bus.registerListener(listener1, DummyEvent.class);
    bus.notifyListeners(event);
    bus.registerListener(listener2, DummyEvent.class);
    bus.registerListener(listener2, DummyEvent.class);
    bus.notifyListeners(event);
    bus.notifyListeners(event);
    verify(listener1, times(3)).accept(event);
    verify(listener2, times(2)).accept(event);
  }

  @Test
  void test_with_multiple_event() {
    DummyEvent event1 = new DummyEvent();
    DummyEvent2 event2 = new DummyEvent2();
    bus.registerListener(listener1, DummyEvent.class);
    bus.registerListener(listener2, DummyEvent.class);
    bus.registerListener(listener3, DummyEvent2.class);
    bus.notifyListeners(event1);
    bus.notifyListeners(event2);
    verify(listener1, times(1)).accept(event1);
    verify(listener2, times(1)).accept(event1);
    verify(listener3, times(1)).accept(event2);

  }
}
