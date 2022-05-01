package com.thebetterchoiceminecraft.utils;

import org.bukkit.event.Listener;

public interface BaseListener extends Listener {
    boolean unregister();
    void register();

    static void throwProblem(){
        throw new RuntimeException("Invalid Base Listener!");
    }
}
