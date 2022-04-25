package com.thebetterchoiceminecraft.play.commands;

import de.jeff_media.jefflib.TextUtils;
import org.bukkit.*;
import org.bukkit.block.data.BlockData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SimpleCommand implements BaseCommand{

    private final String id;
    private boolean registered = false;
    private final Command mainCommand = new Command("TBC") {
        @Override
        public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
            return sender.isOp();
        }
    };

    public SimpleCommand(){
        this("TBC");
    }
    public SimpleCommand(String id){
        this.id = id;
        register();

        if (!mainCommand.isRegistered()) {
            mainCommand.register(Bukkit.getCommandMap());
        }
        registered = Bukkit.getCommandMap().getKnownCommands().containsKey(this.getCommand())||Bukkit.getCommandMap().getKnownCommands().containsValue(Bukkit.getPluginCommand(getCommand()));
    }
    @Override
    public String getCommand() {
        return mainCommand.getName();
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player p)) {
            return mainCommand.execute(sender,command.getUsage(),args);
        }

        //todo - fix commands
        p.sendRawMessage(TextUtils.color("&e... Staging Command ..."));

        if (args.length >= 2){

            String one = args[0];
            String sec = args[1];
            if (one.startsWith("-") && args.length > 2){
                String first = one.split("-")[1];

                if (first.equalsIgnoreCase("w") || first.equalsIgnoreCase("world")){
                    World possibleWorld = Bukkit.getWorld(one);

                    if (possibleWorld == null){
                        p.sendRawMessage(TextUtils.color("&cWorld Not Found " + one));
                        return false;
                    }

                    String second = args[2];

                    Player foundPlayer = Bukkit.getPlayer(second);

                    if (foundPlayer == null){
                        p.sendRawMessage("&cPlayer Not Found " + second);
                        return false;
                    }

                    if (foundPlayer.getWorld().getName().equalsIgnoreCase(possibleWorld.getName())){
                        foundPlayer.spawnParticle(Particle.END_ROD,foundPlayer.getLocation(),100);
                        p.sendRawMessage(TextUtils.color("&aSend Particles"));
                        return true;
                    }

                    p.sendRawMessage(TextUtils.color("&cFailed Command"));
                    return false;
                }
            }
            else if (args.length == 2){

                //todo Check on generator and arguments.
                boolean generator = false;
                if (one.startsWith("-")) {
                    one = one.split("-")[1];

                    if (one.equalsIgnoreCase("g") || one.equalsIgnoreCase("gen")){
                        generator = true;
                    }
                }

                if (generator){
                    p.sendRawMessage(TextUtils.color("&eGeneration! --->"));
                }
                World possibleWorld = Bukkit.getWorld(sec);

                if (possibleWorld == null){
                    p.sendRawMessage(TextUtils.color("&4World Not Located " + sec));
                    return false;
                }

                Location loc = p.getLocation();
                ChunkSnapshot chunk = possibleWorld.getEmptyChunkSnapshot(loc.getBlockX(), loc.getBlockZ(), true, true);

                BlockData lightBlockData = Bukkit.createBlockData(Material.LIGHT);
                if (!chunk.contains(lightBlockData)){
                    World world = Bukkit.getWorld(chunk.getWorldName());

                    if (world !=null){
                        try {
                            p.sendRawMessage(TextUtils.color("&l&bWorld Found for Light"));
                            world.spawnFallingBlock(loc,lightBlockData).setHurtEntities(true);
                        } catch (IllegalArgumentException e) {
                            e.printStackTrace();
                        }
                        p.sendRawMessage(TextUtils.color("&eSpawning Light Block"));
                    }
                }

                //TODO - Fix Commands

                String o = args[0];

                World w = Bukkit.getWorld(o);

                if (w == null){
                    p.sendRawMessage("Bad World");
                    throw new RuntimeException("Invalid World");
                }

                Integer amount = Integer.getInteger(args[1]);

                if (amount>0){
                    w.setChunkForceLoaded(p.getLocation().getBlockX(),p.getLocation().getBlockZ(),true);
                    for (int i = 0; i <= amount; i++) {

                        FallingBlock fallingBlock = w.spawnFallingBlock(loc, lightBlockData);
                        if (p.getPassengers().isEmpty()){
                            try {
                                p.addPassenger(fallingBlock);
                                p.sendRawMessage(TextUtils.color("&aAdded Passenger Light"));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        fallingBlock.setGlowing(true);
                        fallingBlock.setDropItem(true);

                        if (p.hasRider()){
                            p.sendRawMessage(TextUtils.color("&aRider Found!"));
                        }
                    }
                }
                w.setBlockData(loc.subtract(0,-1,0),lightBlockData.clone());
            }
        }



        return command.getUsage().equalsIgnoreCase(getCommand());
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        ArrayList<String> list = new ArrayList<>();



        list.add("Reload");
        list.add("Generate");
        list.add("Regenerate");
        if (command.getName().equalsIgnoreCase(mainCommand.getName())){
            return list;
        }
        if (!sender.isOp()){
            ArrayList<String> nonOP = new ArrayList<>();

            nonOP.add("Teleport");
            nonOP.add("Home");
            nonOP.add("Fire");

            return nonOP;
        }
        return this.id.equalsIgnoreCase(command.getName())? list:null;
    }

    public boolean isRegistered() {
        return registered;
    }
}
