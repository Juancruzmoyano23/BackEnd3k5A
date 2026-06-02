package ar.edu.utnfc.backend.data;

import ar.edu.utnfc.backend.model.*;

import java.util.*;

public class DataContext {

    public static Map<String, User> users =
            new HashMap<>();

    public static Map<String, Language> languages =
            new HashMap<>();

    public static Map<String, Tag> tags =
            new HashMap<>();

    public static List<Repository> repositories =
            new ArrayList<>();
}
