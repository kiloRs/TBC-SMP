package com.thebetterchoiceminecraft.utils;

import org.bukkit.event.Listener;

public interface BaseListener extends Listener {
    public String getListenerName();
    public boolean isEnabled();
    public boolean shouldUnregisterAfterFirst(boolean state);
    boolean unregister();

    public static void throwProblem(){
        throw new RuntimeException("Invalid Base Listener!");
    }
}
