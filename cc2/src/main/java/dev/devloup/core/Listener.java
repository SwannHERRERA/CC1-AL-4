package dev.devloup.core;

import java.util.function.Consumer;

public interface Listener<E extends Event> extends Consumer<E> {
  void accept(E event);
}
