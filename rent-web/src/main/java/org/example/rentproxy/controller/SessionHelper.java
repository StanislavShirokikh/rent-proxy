package org.example.rentproxy.controller;

import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpSession;
public class SessionHelper {
    @Nullable
    public static String handleCurrencySessionAttribute(String currency, HttpSession session) {
        if (currency != null) {
            session.setAttribute(SessionAttributes.DEFAULT_CURRENCY.name(), currency);
            return currency;
        }

        return getCurrencySessionAttribute(session);
    }

    private static String getCurrencySessionAttribute(HttpSession session) {
        String value = null;
        if (isExpiredSession(session)) {
            value = (String) session.getAttribute(SessionAttributes.DEFAULT_CURRENCY.name());
        }
        return value;
    }

    private static boolean isExpiredSession(HttpSession session) {
        long currentTime = System.currentTimeMillis();
        long lastAccessedTime = session.getLastAccessedTime();
        long sessionTimeout = session.getMaxInactiveInterval() * 1000L;

        return currentTime - lastAccessedTime < sessionTimeout;
    }
}

