package com.thebetterchoiceminecraft.play.hooks;

import com.thebetterchoiceminecraft.play.TBCPlugin;
import io.lumine.mythic.lib.MythicLib;
import io.lumine.mythic.lib.manager.SkillManager;
import org.bukkit.plugin.Plugin;

public class MythicLibHook {
    private final MythicLib theLib;
    private final boolean load;
    private final int times;
    private Plugin usingPlugin;

    public MythicLibHook(Plugin plugin){
        this(plugin,0);
    }
    public MythicLibHook(Plugin usingPlugin, int time){
        this.usingPlugin = usingPlugin;
        theLib = MythicLib.inst();
        load = MythicLib.inst().isEnabled();
        times = time;
    }

    public boolean loadAll(boolean logAl){
        if (usingPlugin==null){
            return false;
        }
        if (!usingPlugin.getName().equalsIgnoreCase(TBCPlugin.getPlugin().getName())){
            return false;
        }

        theLib.getSkills().

    }

    public int getTimes() {
        return times;
    }
}
