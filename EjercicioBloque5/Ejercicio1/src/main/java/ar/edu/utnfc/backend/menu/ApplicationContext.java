package ar.edu.utnfc.backend.menu;

import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

public class ApplicationContext {

    private final Map<String, Object> data =
            new ConcurrentHashMap<>();

    private ApplicationContext() {
    }

    private static class Holder {

        private static final ApplicationContext INSTANCE =
                new ApplicationContext();

    }

    public static ApplicationContext getInstance() {
        return Holder.INSTANCE;
    }

    public void put(String key, Object value) {
        data.put(key, value);
    }

    public Object get(String key) {
        return data.get(key);
    }

    public <T> T get(String key, Class<T> type) {

        Object value = data.get(key);

        if (value == null) {
            return null;
        }

        return type.cast(value);
    }

    public boolean contains(String key) {
        return data.containsKey(key);
    }

    public void remove(String key) {
        data.remove(key);
    }

    public void set(String key, Object newValue) {

        if (!data.containsKey(key)) {
            throw new IllegalArgumentException(
                    "La clave no existe: " + key
            );
        }

        data.put(key, newValue);
    }
}
