package com.sun.glass.ui.headless;

import com.sun.glass.ui.Application;
import com.sun.glass.ui.Menu;
import com.sun.glass.ui.MenuBar;
import com.sun.glass.ui.MenuItem;
import com.sun.glass.ui.PlatformFactory;
import com.sun.glass.ui.delegate.ClipboardDelegate;
import com.sun.glass.ui.delegate.MenuBarDelegate;
import com.sun.glass.ui.delegate.MenuDelegate;
import com.sun.glass.ui.delegate.MenuItemDelegate;

public class HeadlessPlatformFactory extends PlatformFactory {

    @Override
    public Application createApplication() {
        return HeadlessApplication.getInstance();
    }

    @Override
    public MenuBarDelegate createMenuBarDelegate(MenuBar menubar) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public MenuDelegate createMenuDelegate(Menu menu) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public MenuItemDelegate createMenuItemDelegate(MenuItem menuItem) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ClipboardDelegate createClipboardDelegate() {
        return new HeadlessClipboardDelegate();
    }

}