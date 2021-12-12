package dev.devloup.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dev.devloup.domain.LoggerFactory;

public class SimpleEventBus<E extends ApplicationEvent> implements EventBus<E> {

  private Map<Class<? extends ApplicationEvent>, List<ApplicationEventListener<E>>> listeners = new HashMap<>();

  @Override
  public void notifyListeners(E event) {
    final List<ApplicationEventListener<E>> associatedListeners = this.listeners.get(event.getClass());
    if (associatedListeners == null) {
      var logger = LoggerFactory.createFileLogger(Config.getLogFolder());
      logger.log("Event " + event.toString() + " have no listener on it");
    }
    for (ApplicationEventListener<E> listener : associatedListeners) {
      listener.listenTo(event);
    }
  }

  @Override
  public void subscribe(Class<E> classE, ApplicationEventListener<E> listener) {
    listeners.put(classE, List.of(listener));
  }

}
