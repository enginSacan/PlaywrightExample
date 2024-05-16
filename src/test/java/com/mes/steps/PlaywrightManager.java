package com.mes.steps;

import com.microsoft.playwright.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.mes.generic.PropertyNames.RUNNING_BROWSER;

public class PlaywrightManager {
    private static final ThreadLocal<Browser> threadLocalBrowser = new ThreadLocal<>();
    private static final ThreadLocal<BrowserContext> threadLocalContext = new ThreadLocal<>();
    private static final ThreadLocal<Page> threadLocalPage = new ThreadLocal<>();
    private static final Logger logger = LogManager.getLogger(PlaywrightManager.class);
    public static void initializePlaywright() {
        if (threadLocalBrowser.get() == null) {
            Playwright playwright = Playwright.create();
            if (RUNNING_BROWSER.equalsIgnoreCase("chrome")) {
                BrowserType chromium = playwright.chromium();
                Browser browser = chromium.launch(new BrowserType.LaunchOptions()
                        .setHeadless(false));
                threadLocalBrowser.set(browser);
                BrowserContext context = browser.newContext();
                threadLocalContext.set(context);
            } else if (RUNNING_BROWSER.equalsIgnoreCase("firefox")) {
                BrowserType firefox = playwright.firefox();
                Browser browser = firefox.launch(new BrowserType.LaunchOptions()
                        .setHeadless(false));
                threadLocalBrowser.set(browser);
                BrowserContext context = browser.newContext();
                threadLocalContext.set(context);
            } else if (RUNNING_BROWSER.equalsIgnoreCase("edge")) {
                BrowserType edge = playwright.chromium();
                Browser browser = edge.launch(new BrowserType.LaunchOptions()
                        .setChannel("msedge")
                        .setHeadless(false));
                threadLocalBrowser.set(browser);
                BrowserContext context = browser.newContext();
                threadLocalContext.set(context);
            } else if (RUNNING_BROWSER.equalsIgnoreCase("headless")) {
                BrowserType chromium = playwright.chromium();
                Browser browser = chromium.launch(new BrowserType.LaunchOptions()
                        .setHeadless(true));
                threadLocalBrowser.set(browser);
                BrowserContext context = browser.newContext();
                threadLocalContext.set(context);
            }

        }
    }

    public static void newPage() {
        Page page = getContext().newPage();
        threadLocalPage.set(page);
    }

    public static Browser getBrowser() {
        return threadLocalBrowser.get();
    }

    public static BrowserContext getContext() {
        return threadLocalContext.get();
    }

    public static Page getPage() {
        return threadLocalPage.get();
    }

    public static void closePage() {
        if (threadLocalPage.get() != null) {
            threadLocalPage.get().close();
            threadLocalPage.remove();
        }
    }
    public static void closeAll() {
        if (threadLocalContext.get() != null) {
            threadLocalContext.get().close();
            threadLocalContext.remove();
        }
        if (threadLocalBrowser.get() != null) {
            threadLocalBrowser.get().close();
            threadLocalBrowser.remove();
        }
    }
}
