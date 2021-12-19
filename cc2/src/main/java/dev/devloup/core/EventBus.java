package dev.devloup.core;

public interface EventBus {

  void notifyListeners(ApplicationEvent event);

  void subscribe(Class<ApplicationEvent> classE, ApplicationEventListener<ApplicationEvent> listener);
}
