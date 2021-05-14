package com.tsystems.javaschool.util;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public class ControllerUtil {
    public static int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }

    public static String getType(HttpServletRequest request) {
        String type = Objects.requireNonNull(request.getParameter("type"));
        return type;
    }
}
