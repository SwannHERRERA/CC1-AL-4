package esgi.al.cc1.domain;

import java.util.function.Consumer;

public interface Listener<E extends Event> extends Consumer<E> {
  void onEvent(E event);
}
