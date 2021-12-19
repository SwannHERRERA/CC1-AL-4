package dev.devloup.core;

import java.util.function.Consumer;

public interface ApplicationEventListener<E extends ApplicationEvent> extends Consumer<E> {
  void accept(E event);
}
