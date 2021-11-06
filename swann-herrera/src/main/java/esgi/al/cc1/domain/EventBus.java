package esgi.al.cc1.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventBus {
  private Map<Class<Event>, List<Listener<Event>>> listeners;

  public EventBus() {
    this.listeners = new HashMap<>();
  }

  public void notifyListeners(Event event) {
    var viewers = this.listeners.get(event.getClass());
    if (viewers != null && !viewers.isEmpty()) {
      viewers.forEach(sub -> sub.onEvent(event));
    }
  }

  public void registerListener(Class<Event> classE, Listener<Event> listener) {
    var viewers = listeners.get(classE);
    if (viewers == null) {
      var newList = new ArrayList<Listener<Event>>();
      newList.add(listener);
      listeners.put(classE, newList);
    } else {
      viewers.add(listener);
    }
  }
}
