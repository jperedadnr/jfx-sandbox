package com.sun.glass.ui.headless;

import com.sun.glass.events.WindowEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

final class HeadlessWindowManager {

    private static final HeadlessWindowManager instance = new HeadlessWindowManager();

    /** The window stack. Windows are in Z-order, from back to front. */
    private HeadlessWindow[] windows = new HeadlessWindow[0];

    private HeadlessWindow focusedWindow = null;
    private int nextID = 1;

    private HeadlessWindowManager() {
        //singleton
    }

    static HeadlessWindowManager getInstance() {
        return instance;
    }

    private int getWindowIndex(HeadlessWindow window) {
        for (int i = 0; i < windows.length; i++) {
            // Any two HeadlessWindow objects represent different windows, so
            // equality can be determined by reference comparison.
            if (windows[i] == window) {
                return i;
            }
        }
        return -1;
    }

    int addWindow(HeadlessWindow window) {
        int index = getWindowIndex(window);
        if (index == -1) {
            windows = Arrays.copyOf(windows, windows.length + 1);
            windows[windows.length - 1] = window;
        }
        return nextID++;

    }

    boolean closeWindow(HeadlessWindow window) {
        int index = getWindowIndex(window);
        if (index != -1) {
            System.arraycopy(windows, index + 1, windows, index,
                    windows.length - index - 1);
            windows = Arrays.copyOf(windows, windows.length - 1);
        }
        List<HeadlessWindow> windowsToNotify = new ArrayList<>();
        for (HeadlessWindow otherWindow : windows) {
            if (otherWindow.getOwner() == window) {
                windowsToNotify.add(otherWindow);
            }
        }
        for (int i = 0; i < windowsToNotify.size(); i++) {
            windowsToNotify.get(i).notifyClose();
        }
        window.notifyDestroy();
        if (focusedWindow == window) {
            focusedWindow = null;
        }
        return true;
    }

    void repaintAll() {
        for (int i = 0; i < windows.length; i++) {
            HeadlessView view = (HeadlessView) windows[i].getView();
            if (view != null) {
                view.notifyRepaint();
            }
        }
    }

    boolean requestFocus(HeadlessWindow window) {
        int index = getWindowIndex(window);
        if (index != -1) {
            focusedWindow = window;
            window.notifyFocus(WindowEvent.FOCUS_GAINED);
            return true;
        } else {
            return false;
        }
    }

    boolean grabFocus(HeadlessWindow window) {
        return true;
    }

    void ungrabFocus(HeadlessWindow window) {

    }

    void notifyFocusDisabled(HeadlessWindow window) {
        if (window != null) {
            window._notifyFocusDisabled();
        }
    }

    HeadlessWindow getFocusedWindow() {
        return focusedWindow;
    }

    HeadlessWindow getWindowForLocation(int x, int y) {
        for (int i = windows.length - 1; i >=0 ; i--) {
            HeadlessWindow w = windows[i];
            if (x >= w.getX() && y >= w.getY()
                    && x < w.getX() + w.getWidth()
                    && y < w.getY() + w.getHeight()
                    && w.isEnabled()) {
                return w;
            }
        }
        return null;
    }
}
