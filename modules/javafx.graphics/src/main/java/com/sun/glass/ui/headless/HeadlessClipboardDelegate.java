package com.sun.glass.ui.headless;

import com.sun.glass.ui.Clipboard;
import com.sun.glass.ui.delegate.ClipboardDelegate;

final class HeadlessClipboardDelegate implements ClipboardDelegate {

    @Override
    public Clipboard createClipboard(String clipboardName) {
        if (Clipboard.DND.equals(clipboardName)) {
            return new HeadlessDnDClipboard();
        } else if (Clipboard.SYSTEM.equals(clipboardName)) {
            return new HeadlessSystemClipboard();
        } else {
            return null;
        }
    }
}
