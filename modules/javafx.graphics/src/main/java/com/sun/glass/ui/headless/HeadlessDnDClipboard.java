package com.sun.glass.ui.headless;

import com.sun.glass.ui.Application;
import com.sun.glass.ui.Clipboard;
import com.sun.glass.ui.SystemClipboard;

import java.util.HashMap;

final class HeadlessDnDClipboard extends SystemClipboard {

    HeadlessDnDClipboard() {
        super(Clipboard.DND);
    }

    @Override
    protected boolean isOwner() {
        return true;
    }

    @Override
    protected  void pushToSystem(HashMap<String, Object> cacheData, int supportedActions) {
        MouseInput.getInstance().notifyDragStart();
        ((HeadlessApplication) Application.GetApplication()).enterDnDEventLoop();
        actionPerformed(Clipboard.ACTION_COPY_OR_MOVE);
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
        return Clipboard.ACTION_COPY_OR_MOVE;
    }

    @Override
    protected String[] mimesFromSystem() {
        return new String[0];
    }

}
