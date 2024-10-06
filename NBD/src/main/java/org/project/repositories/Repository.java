package org.project.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class Repository<T> {
    protected List<T> container;

    public Repository() {
        container = new ArrayList<>();
    }

    public abstract void add(T object);

    public abstract void remove(T object);

    public abstract Optional<T> find(int id);

    public Optional<T> get(T object) {
        return container.stream()
                .filter(item -> item.equals(object))
                .findFirst();
    }
}
