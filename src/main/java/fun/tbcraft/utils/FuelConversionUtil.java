package fun.tbcraft.utils;

import fun.tbcraft.play.TBCPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Material;


public class FuelConversionUtil {
    private final Material stack;
    private final int timeToCook;
    private boolean throwErrors = true;

    public FuelConversionUtil(Material stack, int timeToCook, boolean ebug){
        this.stack = stack;
        this.timeToCook = timeToCook;
        this.throwErrors = (TBCPlugin.getMainConfig().exists("Debug.Level") && (TBCPlugin.getMainConfig().getInt("Debug.Level") >= 2)) || ebug;
        if (stack==Material.AIR){
            throw new RuntimeException("Invalid Fuel Exception for Air");
        }
        if (stack==Material.COMMAND_BLOCK){
            throw new RuntimeException("Invalid Fuel Exception for Command Block");
        }
        if (stack==Material.BARRIER){
            throw new RuntimeException("Invalid Fuel Exception for Barrier Block");
        }
        try {
            Bukkit.addFuel(stack,timeToCook);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void addFuel(){
        Bukkit.addFuel(stack,timeToCook);
    }
    public void removeFuel(){
        Bukkit.removeFuel(stack);
    }
}
