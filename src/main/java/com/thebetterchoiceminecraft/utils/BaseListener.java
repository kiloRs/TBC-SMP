package com.thebetterchoiceminecraft.utils;

import org.bukkit.event.Listener;

public interface BaseListener extends Listener {
    void unregisterAfter(long timeZone, boolean offForGood);
    boolean unregister();
    public void register();

    public static void throwProblem(){
        throw new RuntimeException("Invalid Base Listener!");
    }
}
