package base;

public class BaseTest {

    protected static boolean isPageLoaded(String currentUrl, String expectedPath) {
        return currentUrl != null && currentUrl.contains(expectedPath);
    }
}

