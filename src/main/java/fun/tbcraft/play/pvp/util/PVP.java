package fun.tbcraft.play.pvp.util;

import io.lumine.mythic.lib.damage.DamageType;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.ArrayList;
import java.util.List;

public enum PVP {
    ENABLED,LIMITED,VERY_LIMITED,DISABLED;

    PVP(){

    }

    public boolean isLimit(){
        return this==LIMITED||this==VERY_LIMITED;
    }

}
