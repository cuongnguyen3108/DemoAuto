package vn.devpro.assignment67.utils;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageUtils {

    private static final Map<String, String> MESSAGE_CACHE = new HashMap<>();

    /**
     * Excel format:
     * Row 0: key1 | key2 | key3
     * Row 1: msg1 | msg2 | msg3
     */
    public static void loadFromExcel(String filePath, String sheetName) {

        MESSAGE_CACHE.clear();

        List<List<String>> rows =
                ExelUtils.readRawSheet(filePath, sheetName);

        if (rows.size() < 2) {
            throw new IllegalStateException(
                    "❌ Messages sheet phải có ít nhất 2 dòng (key + message)"
            );
        }

        List<String> keys = rows.get(0);
        List<String> messages = rows.get(1);

        int size = Math.min(keys.size(), messages.size());

        for (int i = 0; i < size; i++) {

            String key = keys.get(i);
            String message = messages.get(i);

            if (key == null || key.isEmpty()) continue;
            if (message == null || message.isEmpty()) continue;

            key = key.trim();
            message = message.trim();

            if (MESSAGE_CACHE.containsKey(key)) {
                throw new IllegalStateException("❌ Duplicate message key: " + key);
            }

            MESSAGE_CACHE.put(key, message);
        }
    }

    // 👉 Dùng trong Page
    public static String get(String key, Object... args) {
        String template = MESSAGE_CACHE.get(key);
        if (template == null) {
            throw new IllegalArgumentException("❌ Message key not found: " + key);
        }
        return MessageFormat.format(template, args);
    }
}
