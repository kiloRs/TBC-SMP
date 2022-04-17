package psikuvit.tbcenchant.API;

import org.bukkit.inventory.ItemStack;
import psikuvit.tbcenchant.Enchs.TBCEnchants;

import java.util.List;
import java.util.Map;

public interface TBCEnchantAPI {
    ItemStack enchantItem(ItemStack paramItemStack, TBCEnchants paramTBCEnchant, int paramInt, boolean paramBoolean);

    ItemStack removeEnchant(ItemStack paramItemStack, TBCEnchants paramTBCEnchant);

    boolean hasReforgedEnchantment(ItemStack paramItemStack, TBCEnchants paramTBCEnchant);

    TBCEnchants getEnchantmentInstance(String paramString);

    boolean itemCanBeEnchanted(ItemStack paramItemStack, TBCEnchants paramTBCEnchant);

    boolean itemIsEnchanted(ItemStack paramItemStack, TBCEnchants paramTBCEnchant, int paramInt);

    List<TBCEnchants> getAvailableEnchantments(ItemStack paramItemStack);

    Map<TBCEnchants, Integer> getItemEnchantments(ItemStack paramItemStack);

    List<TBCEnchants> getAllEnchantments();

    ItemStack getEnchantedBook(TBCEnchants paramTBCEnchant, int paramInt);
}
