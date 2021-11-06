package esgi.al.cc1.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventBus<E extends Event> {
  private Map<Class<E>, List<Listener<E>>> listeners;

  public EventBus() {
    this.listeners = new HashMap<>();
  }

  public void notifyListeners(E event) {
    var viewers = this.listeners.get(event.getClass());
    if (viewers != null && !viewers.isEmpty()) {
      viewers.forEach(sub -> sub.onEvent(event));
    }
  }

  public void registerListener(Class<E> classE, Listener<E> listener) throws NullPointerException {
    var viewers = listeners.get(classE);
    if (viewers == null) {
      var newList = new ArrayList<Listener<E>>();
      newList.add(listener);
      listeners.put(classE, newList);
    } else {
      viewers.add(listener);
    }
  }
}
