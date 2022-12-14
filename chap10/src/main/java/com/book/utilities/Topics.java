package com.book.utilities;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

public class Topics implements Serializable {

    private Optional<Topic> _activeTopic = Optional.empty();
    private final Set<Topic> _topics = new LinkedHashSet<>();

    public Set<Topic> getTopics() {
        return _topics;
    }

    public void clear() {
        _topics.clear();
    }

    public void addTopic(String title) {
        addTopic(Topic.TopicBuilder.createBuilder(title).build());
    }

    public void addTopic(Topic topic) {
        _topics.add(topic);
    }

    public void remove(String key) {
        Optional<Topic> topic = findTopic(key);
        topic.ifPresent(t -> _topics.remove(t));
    }

    public void remove(Topic topic) {
        _topics.remove(topic);
    }

    public Optional<Topic> findTopic(String key) {
        return _topics.stream()
                .filter(topic -> topic.getKey().equals(key))
                .findAny();
    }

    /* pre-Java 8 code example
   public Topic findTopic(String key) {
   for (Topic topic : _topics) {
   if (topic.getKey().equals(key)) {
   return topic;
   }
   }
   return Topic.TopicBuilder.createBuilder("").build();
   }
     */
    public Optional<Topic> getActiveTopic() {
        return _activeTopic;
    }

    public void setActive(String key) {
        _activeTopic = findTopic(key);
    }

    public boolean isActive(Topic topic) {
        Optional<Topic> activeTopic = getActiveTopic();
        if (activeTopic.isPresent()) {
            return activeTopic.get().equals(topic);
        }
        return false;
    }
}
