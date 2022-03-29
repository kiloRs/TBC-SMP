import fun.tbcraft.play.TBCPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class FuelConversionUtil {
    private Material stack;
    private int timeToCook;
    private boolean throwErrors = true;

    public FuelConversionUtil(Material stack, int timeToCook){
        this.stack = stack;
        this.timeToCook = timeToCook;
        this.throwErrors = TBCPlugin.getMainConfig().exists("")
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
        Bukkit.addFuel();
    }
    public void removeFuel(){

    }
}
