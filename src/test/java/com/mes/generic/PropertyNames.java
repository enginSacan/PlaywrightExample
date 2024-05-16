package com.mes.generic;

public class PropertyNames {
    private static final String BROWSER = "browser";
    private static final String APPLICATION_URL = "todo.url";

    public static final String RUNNING_BROWSER = ProjectProperties.getRestProperties().getProperty(BROWSER);
    public static final String TO_DO_URL = ProjectProperties.getRestProperties().getProperty(APPLICATION_URL);
}
