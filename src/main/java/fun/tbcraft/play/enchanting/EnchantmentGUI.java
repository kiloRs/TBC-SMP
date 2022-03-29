package fun.tbcraft.play.enchanting;

import org.bukkit.Bukkit;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.RenderType;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class EnchantmentGUI {
    private Scoreboard scoreboard;
    private boolean open;
    private Objective objective;

    public EnchantmentGUI(){
        ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
        if (scoreboardManager == null){
            return;
        }
        this.scoreboard = scoreboardManager.getNewScoreboard();
        this.objective = scoreboard.registerNewObjective("enchantment","gui","enchantGUI", RenderType.INTEGER);

        }
    public String  getObjectiveId(){
        return this.objective.getName();
    }
    public Objective getObjective(){
        return objective;
    }
    public Scoreboard getScoreboard(){
        return scoreboard;
    }
}
