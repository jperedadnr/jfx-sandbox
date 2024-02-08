package com.sun.glass.ui.headless;

import com.sun.glass.ui.Clipboard;
import com.sun.glass.ui.SystemClipboard;

import java.util.HashMap;

final class HeadlessSystemClipboard extends SystemClipboard {

    HeadlessSystemClipboard() {
        super(Clipboard.SYSTEM);
    }

    @Override
    protected boolean isOwner() {
        return true;
    }

    @Override
    protected void pushToSystem(HashMap<String, Object> cacheData,
                                int supportedActions) {
    }

    @Override
    protected void pushTargetActionToSystem(int actionDone) {
    }
    @Override
    protected Object popFromSystem(String mimeType) {
        return null;
    }

    @Override
    protected int supportedSourceActionsFromSystem() {
        return Clipboard.ACTION_NONE;
    }

    @Override
    protected String[] mimesFromSystem() {
        return new String[0];
    }

}