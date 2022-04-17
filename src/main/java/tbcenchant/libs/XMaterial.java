package psikuvit.tbcenchant.libs;

import java.util.HashMap;

import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum XMaterial {
    ACACIA_BOAT(0, new String[] { "BOAT_ACACIA" }),
    ACACIA_BUTTON(0, new String[] { "WOOD_BUTTON" }),
    ACACIA_DOOR(0, new String[] { "ACACIA_DOOR", "ACACIA_DOOR_ITEM" }),
    ACACIA_FENCE(0, new String[] { "ACACIA_FENCE" }),
    ACACIA_FENCE_GATE(0, new String[] { "ACACIA_FENCE_GATE" }),
    ACACIA_LEAVES(0, new String[] { "LEAVES_2" }),
    ACACIA_LOG(0, new String[] { "LOG_2" }),
    ACACIA_PLANKS(4, new String[] { "WOOD" }),
    ACACIA_PRESSURE_PLATE(0, new String[] { "WOOD_PLATE" }),
    ACACIA_SAPLING(4, new String[] { "SAPLING" }),
    ACACIA_SLAB(4, new String[] { "WOODEN_SLAB", "WOOD_STEP", "WOOD_DOUBLE_STEP" }),
    ACACIA_STAIRS(4, new String[] { "ACACIA_STAIRS" }),
    ACACIA_TRAPDOOR(0, new String[] { "TRAP_DOOR" }),
    ACACIA_WOOD(0, new String[] { "LOG_2" }),
    ACTIVATOR_RAIL(0, new String[] { "ACTIVATOR_RAIL" }),
    AIR(0, new String[] { "AIR" }),
    ALLIUM(2, new String[] { "RED_ROSE" }),
    ANDESITE(5, new String[] { "STONE" }),
    ANVIL(0, new String[] { "ANVIL" }),
    APPLE(0, new String[] { "APPLE" }),
    ARMOR_STAND(0, new String[] { "ARMOR_STAND" }),
    ARROW(0, new String[] { "ARROW" }),
    ATTACHED_MELON_STEM(7, new String[] { "MELON_STEM" }),
    ATTACHED_PUMPKIN_STEM(7, new String[] { "PUMPKIN_STEM" }),
    AZURE_BLUET(3, new String[] { "RED_ROSE" }),
    BAKED_POTATO(0, new String[] { "BAKED_POTATO" }),
    BARRIER(0, new String[] { "BARRIER" }),
    BAT_SPAWN_EGG(0, new String[] { "MONSTER_EGG" }),
    BEACON(0, new String[] { "BEACON" }),
    BEDROCK(0, new String[] { "BEDROCK" }),
    BEEF(0, new String[] { "RAW_BEEF" }),
    BEETROOT(0, new String[] { "BEETROOT" }),
    BEETROOTS(0, new String[] { "BEETROOT", "BEETROOT_BLOCK" }),
    BEETROOT_SEEDS(0, new String[] { "BEETROOT_SEEDS" }),
    BEETROOT_SOUP(0, new String[] { "BEETROOT_SOUP" }),
    BIRCH_BOAT(0, new String[] { "BOAT_BIRCH" }),
    BIRCH_BUTTON(0, new String[] { "WOOD_BUTTON" }),
    BIRCH_DOOR(0, new String[] { "BIRCH_DOOR", "BIRCH_DOOR_ITEM" }),
    BIRCH_FENCE(0, new String[] { "BIRCH_FENCE" }),
    BIRCH_FENCE_GATE(0, new String[] { "BIRCH_FENCE_GATE" }),
    BIRCH_LEAVES(2, new String[] { "LEAVES" }),
    BIRCH_LOG(2, new String[] { "LOG" }),
    BIRCH_PLANKS(2, new String[] { "WOOD" }),
    BIRCH_PRESSURE_PLATE(0, new String[] { "WOOD_PLATE" }),
    BIRCH_SAPLING(2, new String[] { "SAPLING" }),
    BIRCH_SLAB(2, new String[] { "WOODEN_SLAB", "WOOD_STEP", "WOOD_DOUBLE_STEP" }),
    BIRCH_STAIRS(0, new String[] { "BIRCH_WOOD_STAIRS" }),
    BIRCH_TRAPDOOR(0, new String[] { "TRAP_DOOR" }),
    BIRCH_WOOD(2, new String[] { "LOG" }),
    BLACK_BANNER(0, new String[] { "BANNER", "STANDING_BANNER" }),
    BLACK_BED(15, new String[] { "BED", "BED_BLOCK" }),
    BLACK_CARPET(15, new String[] { "CARPET" }),
    BLACK_CONCRETE(15, new String[] { "CONCRETE" }),
    BLACK_CONCRETE_POWDER(15, new String[] { "CONCRETE_POWDER" }),
    BLACK_GLAZED_TERRACOTTA(0, new String[] { "BLACK_GLAZED_TERRACOTTA" }),
    BLACK_SHULKER_BOX(0, new String[] { "BLACK_SHULKER_BOX" }),
    BLACK_STAINED_GLASS(15, new String[] { "STAINED_GLASS" }),
    BLACK_STAINED_GLASS_PANE(15, new String[] { "STAINED_GLASS_PANE" }),
    BLACK_TERRACOTTA(15, new String[] { "STAINED_CLAY" }),
    BLACK_WALL_BANNER(0, new String[] { "WALL_BANNER" }),
    BLACK_WOOL(15, new String[] { "WOOL" }),
    BLAZE_POWDER(0, new String[] { "BLAZE_POWDER" }),
    BLAZE_ROD(0, new String[] { "BLAZE_ROD" }),
    BLAZE_SPAWN_EGG(0, new String[] { "MONSTER_EGG" }),
    BLUE_BANNER(11, new String[] { "BANNER", "STANDING_BANNER" }),
    BLUE_BED(4, new String[] { "BED", "BED_BLOCK" }),
    BLUE_CARPET(11, new String[] { "CARPET" }),
    BLUE_CONCRETE(11, new String[] { "CONCRETE" }),
    BLUE_CONCRETE_POWDER(11, new String[] { "CONCRETE_POWDER" }),
    BLUE_GLAZED_TERRACOTTA(0, new String[] { "BLUE_GLAZED_TERRACOTTA" }),
    BLUE_ICE(0, new String[] { "PACKED_ICE" }),
    BLUE_ORCHID(1, new String[] { "RED_ROSE" }),
    BLUE_SHULKER_BOX(0, new String[] { "BLUE_SHULKER_BOX" }),
    BLUE_STAINED_GLASS(11, new String[] { "STAINED_GLASS" }),
    BLUE_STAINED_GLASS_PANE(11, new String[] { "STAINED_GLASS_PANE" }),
    BLUE_TERRACOTTA(11, new String[] { "STAINED_CLAY" }),
    BLUE_WALL_BANNER(11, new String[] { "WALL_BANNER" }),
    BLUE_WOOL(11, new String[] { "WOOL" }),
    BONE(0, new String[] { "BONE" }),
    BONE_BLOCK(0, new String[] { "BONE_BLOCK" }),
    BONE_MEAL(15, new String[] { "INK_SACK" }),
    BOOK(0, new String[] { "BOOK" }),
    BOOKSHELF(0, new String[] { "BOOKSHELF" }),
    BOW(0, new String[] { "BOW" }),
    BOWL(0, new String[] { "BOWL" }),
    BRAIN_CORAL(0, new String[] { "STONE" }),
    BRAIN_CORAL_BLOCK(0, new String[] { "STONE" }),
    BRAIN_CORAL_FAN(0, new String[] { "STONE" }),
    BREAD(0, new String[] { "BREAD" }),
    BREWING_STAND(0, new String[] { "BREWING_STAND", "BREWING_STAND_ITEM" }),
    BRICK(0, new String[] { "CLAY_BRICK" }),
    BRICKS(0, new String[] { "BRICK" }),
    BRICK_SLAB(4, new String[] { "STEP" }),
    BRICK_STAIRS(0, new String[] { "BRICK_STAIRS" }),
    BROWN_BANNER(3, new String[] { "BANNER", "STANDING_BANNER" }),
    BROWN_BED(12, new String[] { "BED", "BED_BLOCK" }),
    BROWN_CARPET(12, new String[] { "CARPET" }),
    BROWN_CONCRETE(12, new String[] { "CONCRETE" }),
    BROWN_CONCRETE_POWDER(12, new String[] { "CONCRETE_POWDER" }),
    BROWN_GLAZED_TERRACOTTA(0, new String[] { "BROWN_GLAZED_TERRACOTTA" }),
    BROWN_MUSHROOM(0, new String[] { "BROWN_MUSHROOM" }),
    BROWN_MUSHROOM_BLOCK(0, new String[] { "BROWN_MUSHROOM", "HUGE_MUSHROOM_1" }),
    BROWN_SHULKER_BOX(0, new String[] { "BROWN_SHULKER_BOX" }),
    BROWN_STAINED_GLASS(12, new String[] { "STAINED_GLASS" }),
    BROWN_STAINED_GLASS_PANE(12, new String[] { "STAINED_GLASS_PANE" }),
    BROWN_TERRACOTTA(12, new String[] { "STAINED_CLAY" }),
    BROWN_WALL_BANNER(3, new String[] { "WALL_BANNER" }),
    BROWN_WOOL(12, new String[] { "WOOL" }),
    BUBBLE_COLUMN(0, new String[] { "STONE" }),
    BUBBLE_CORAL(0, new String[] { "STONE" }),
    BUBBLE_CORAL_BLOCK(0, new String[] { "STONE" }),
    BUBBLE_CORAL_FAN(0, new String[] { "STONE" }),
    BUCKET(0, new String[] { "BUCKET" }),
    CACTUS(0, new String[] { "CACTUS" }),
    CACTUS_GREEN(2, new String[] { "INK_SACK" }),
    CAKE(0, new String[] { "CAKE", "CAKE_BLOCK" }),
    CARROT(0, new String[] { "CARROT_ITEM" }),
    CARROTS(0, new String[] { "CARROT" }),
    CARROT_ON_A_STICK(0, new String[] { "CARROT_STICK" }),
    CARVED_PUMPKIN(0, new String[] { "PUMPKIN" }),
    CAULDRON(0, new String[] { "CAULDRON", "CAULDRON_ITEM" }),
    CAVE_AIR(0, new String[] { "AIR" }),
    CAVE_SPIDER_SPAWN_EGG(0, new String[] { "MONSTER_EGG" }),
    CHAINMAIL_BOOTS(0, new String[] { "CHAINMAIL_BOOTS" }),
    CHAINMAIL_CHESTPLATE(0, new String[] { "CHAINMAIL_CHESTPLATE" }),
    CHAINMAIL_HELMET(0, new String[] { "CHAINMAIL_HELMET" }),
    CHAINMAIL_LEGGINGS(0, new String[] { "CHAINMAIL_LEGGINGS" }),
    CHAIN_COMMAND_BLOCK(0, new String[] { "COMMAND_CHAIN" }),
    CHARCOAL(1, new String[] { "COAL" }),
    CHEST(0, new String[] { "CHEST", "LOCKED_CHEST" }),
    CHEST_MINECART(0, new String[] { "STORAGE_MINECART" }),
    CHICKEN(0, new String[] { "RAW_CHICKEN" }),
    CHICKEN_SPAWN_EGG(0, new String[] { "MONSTER_EGG" }),
    CHIPPED_ANVIL(1, new String[] { "ANVIL" }),
    CHISELED_QUARTZ_BLOCK(1, new String[] { "QUARTZ_BLOCK" }),
    CHISELED_RED_SANDSTONE(1, new String[] { "RED_SANDSTONE" }),
    CHISELED_SANDSTONE(1, new String[] { "SANDSTONE" }),
    CHISELED_STONE_BRICKS(3, new String[] { "SMOOTH_BRICK" }),
    CHORUS_FLOWER(0, new String[] { "CHORUS_FLOWER" }),
    CHORUS_FRUIT(0, new String[] { "CHORUS_FRUIT" }),
    CHORUS_PLANT(0, new String[] { "CHORUS_PLANT" }),
    CLAY(0, new String[] { "CLAY" }),
    CLAY_BALL(0, new String[] { "CLAY_BALL" }),
    CLOCK(0, new String[] { "WATCH" }),
    COAL(0, new String[] { "COAL" }),
    COAL_BLOCK(0, new String[] { "COAL_BLOCK" }),
    COAL_ORE(0, new String[] { "COAL_ORE" }),
    COARSE_DIRT(1, new String[] { "DIRT" }),
    COBBLESTONE(0, new String[] { "COBBLESTONE" }),
    COBBLESTONE_SLAB(3, new String[] { "STEP" }),
    COBBLESTONE_STAIRS(0, new String[] { "COBBLESTONE_STAIRS" }),
    COBBLESTONE_WALL(0, new String[] { "COBBLE_WALL" }),
    COBWEB(0, new String[] { "WEB" }),
    COCOA(0, new String[] { "COCOA" }),
    COCOA_BEANS(3, new String[] { "INK_SACK" }),
    COD(0, new String[] { "RAW_FISH" }),
    COD_BUCKET(0, new String[] { "BUCKET" }),
    COD_SPAWN_EGG(0, new String[] { "MONSTER_EGG" }),
    COMMAND_BLOCK(0, new String[] { "COMMAND" }),
    COMMAND_BLOCK_MINECART(0, new String[] { "COMMAND_MINECART" }),
    COMPARATOR(0, new String[] { "REDSTONE_COMPARATOR", "REDSTONE_COMPARATOR_ON", "REDSTONE_COMPARATOR_OFF" }),
    COMPASS(0, new String[] { "COMPASS" }),
    CONDUIT(0, new String[] { "STONE" }),
    COOKED_BEEF(0, new String[] { "COOKED_BEEF" }),
    COOKED_CHICKEN(0, new String[] { "COOKED_CHICKEN" }),
    COOKED_COD(0, new String[] { "COOKED_FISH" }),
    COOKED_MUTTON(0, new String[] { "COOKED_MUTTON" }),
    COOKED_PORKCHOP(0, new String[] { "GRILLED_PORK" }),
    COOKED_RABBIT(0, new String[] { "COOKED_RABBIT" }),
    COOKED_SALMON(1, new String[] { "COOKED_FISH" }),
    COOKIE(0, new String[] { "COOKIE" }),
    COW_SPAWN_EGG(0, new String[] { "MONSTER_EGG" }),
    CRACKED_STONE_BRICKS(2, new String[] { "SMOOTH_BRICK" }),
    CRAFTING_TABLE(0, new String[] { "WORKBENCH" }),
    CREEPER_HEAD(0, new String[] { "SKULL", "SKULL_ITEM" }),
    CREEPER_SPAWN_EGG(0, new String[] { "MONSTER_EGG" }),
    CREEPER_WALL_HEAD(0, new String[] { "SKULL", "SKULL_ITEM" }),
    CUT_RED_SANDSTONE(0, new String[] { "STONE" }),
    CUT_SANDSTONE(0, new String[] { "STONE" }),
    CYAN_BANNER(6, new String[] { "BANNER", "STANDING_BANNER" }),
    CYAN_BED(9, new String[] { "BED", "BED_BLOCK" }),
    CYAN_CARPET(9, new String[] { "CARPET" }),
    CYAN_CONCRETE(9, new String[] { "CONCRETE" }),
    CYAN_CONCRETE_POWDER(9, new String[] { "CONCRETE_POWDER" }),
    CYAN_DYE(6, new String[] { "INK_SACK" }),
    CYAN_GLAZED_TERRACOTTA(0, new String[] { "CYAN_GLAZED_TERRACOTTA" }),
    CYAN_SHULKER_BOX(0, new String[] { "CYAN_SHULKER_BOX" }),
    CYAN_STAINED_GLASS(9, new String[] { "STAINED_GLASS" }),
    CYAN_STAINED_GLASS_PANE(9, new String[] { "STAINED_GLASS_PANE" }),
    CYAN_TERRACOTTA(9, new String[] { "STAINED_CLAY" }),
    CYAN_WALL_BANNER(0, new String[] { "WALL_BANNER" }),
    CYAN_WOOL(9, new String[] { "WOOL" }),
    DAMAGED_ANVIL(2, new String[] { "ANVIL" }),
    DANDELION(0, new String[] { "YELLOW_FLOWER" }),
    DANDELION_YELLOW(11, new String[] { "INK_SACK" }),
    DARK_OAK_BOAT(0, new String[] { "BOAT_DARK_OAK" }),
    DARK_OAK_BUTTON(0, new String[] { "WOOD_BUTTON" }),
    DARK_OAK_DOOR(0, new String[] { "DARK_OAK_DOOR", "DARK_OAK_DOOR_ITEM" }),
    DARK_OAK_FENCE(0, new String[] { "DARK_OAK_FENCE" }),
    DARK_OAK_FENCE_GATE(0, new String[] { "DARK_OAK_FENCE_GATE" }),
    DARK_OAK_LEAVES(1, new String[] { "LEAVES_2" }),
    DARK_OAK_LOG(1, new String[] { "LOG_2" }),
    DARK_OAK_PLANKS(5, new String[] { "WOOD" }),
    DARK_OAK_PRESSURE_PLATE(0, new String[] { "WOOD_PLATE" }),
    DARK_OAK_SAPLING(5, new String[] { "SAPLING" }),
    DARK_OAK_SLAB(0, new String[] { "WOODEN_SLAB", "WOOD_STEP", "WOOD_DOUBLE_STEP" }),
    DARK_OAK_STAIRS(0, new String[] { "DARK_OAK_STAIRS" }),
    DARK_OAK_TRAPDOOR(0, new String[] { "TRAP_DOOR" }),
    DARK_OAK_WOOD(1, new String[] { "LOG_2" }),
    DARK_PRISMARINE(2, new String[] { "PRISMARINE" }),
    DARK_PRISMARINE_SLAB(0, new String[] { "STONE" }),
    DARK_PRISMARINE_STAIRS(0, new String[] { "STONE" }),
    DAYLIGHT_DETECTOR(0, new String[] { "DAYLIGHT_DETECTOR", "DAYLIGHT_DETECTOR_INVERTED" }),
    DEAD_BRAIN_CORAL_BLOCK(0, new String[] { "STONE" }),
    DEAD_BUBBLE_CORAL_BLOCK(0, new String[] { "STONE" }),
    DEAD_BUSH(0, new String[] { "DEAD_BUSH" }),
    DEAD_FIRE_CORAL_BLOCK(0, new String[] { "STONE" }),
    DEAD_HORN_CORAL_BLOCK(0, new String[] { "STONE" }),
    DEAD_TUBE_CORAL_BLOCK(0, new String[] { "STONE" }),
    DEBUG_STICK(0, new String[] { "STICK" }),
    DETECTOR_RAIL(0, new String[] { "DETECTOR_RAIL" }),
    DIAMOND(0, new String[] { "DIAMOND" }),
    DIAMOND_AXE(0, new String[] { "DIAMOND_AXE" }),
    DIAMOND_BLOCK(0, new String[] { "DIAMOND_BLOCK" }),
    DIAMOND_BOOTS(0, new String[] { "DIAMOND_BOOTS" }),
    DIAMOND_CHESTPLATE(0, new String[] { "DIAMOND_CHESTPLATE" }),
    DIAMOND_HELMET(0, new String[] { "DIAMOND_HELMET" }),
    DIAMOND_HOE(0, new String[] { "DIAMOND_HOE" }),
    DIAMOND_HORSE_ARMOR(0, new String[] { "DIAMOND_BARDING" }),
    DIAMOND_LEGGINGS(0, new String[] { "DIAMOND_LEGGINGS" }),
    DIAMOND_ORE(0, new String[] { "DIAMOND_ORE" }),
    DIAMOND_PICKAXE(0, new String[] { "DIAMOND_PICKAXE" }),
    DIAMOND_SHOVEL(0, new String[] { "DIAMOND_SPADE" }),
    DIAMOND_SWORD(0, new String[] { "DIAMOND_SWORD" }),
    DIORITE(3, new String[] { "STONE" }),
    DIRT(0, new String[] { "DIRT" }),
    DISPENSER(0, new String[] { "DISPENSER" }),
    DOLPHIN_SPAWN_EGG(0, new String[] { "MONSTER_EGG" }),
    DONKEY_SPAWN_EGG(0, new String[] { "MONSTER_EGG" }),
    DRAGON_BREATH(0, new String[] { "DRAGONS_BREATH" }),
    DRAGON_EGG(0, new String[] { "DRAGON_EGG" }),
    DRAGON_HEAD(5, new String[] { "SKULL", "SKULL_ITEM" }),
    DRAGON_WALL_HEAD(0, new String[] { "SKULL", "SKULL_ITEM" }),
    DRIED_KELP(0, new String[] { "STONE" }),
    DRIED_KELP_BLOCK(0, new String[] { "STONE" }),
    DROPPER(0, new String[] { "DROPPER" }),
    DROWNED_SPAWN_EGG(0, new String[] { "MONSTER_EGG" }),
    EGG(0, new String[] { "EGG" }),
    ELDER_GUARDIAN_SPAWN_EGG(0, new String[] { "MONSTER_EGG" }),
    ELYTRA(0, new String[] { "ELYTRA" }),
    EMERALD(0, new String[] { "EMERALD" }),
    EMERALD_BLOCK(0, new String[] { "EMERALD_BLOCK" }),
    EMERALD_ORE(0, new String[] { "EMERALD_ORE" }),
    ENCHANTED_BOOK(0, new String[] { "ENCHANTED_BOOK" }),
    ENCHANTED_GOLDEN_APPLE(1, new String[] { "GOLDEN_APPLE" }),
    ENCHANTING_TABLE(0, new String[] { "ENCHANTMENT_TABLE" }),
    ENDERMAN_SPAWN_EGG(0, new String[] { "MONSTER_EGG" }),
    ENDERMITE_SPAWN_EGG(0, new String[] { "MONSTER_EGG" }),
    ENDER_CHEST(0, new String[] { "ENDER_CHEST" }),
    ENDER_EYE(0, new String[] { "EYE_OF_ENDER" }),
    ENDER_PEARL(0, new String[] { "ENDER_PEARL" }),
    END_CRYSTAL(0, new String[] { "END_CRYSTAL" }),
    END_GATEWAY(0, new String[] { "END_GATEWAY" }),
    END_PORTAL(0, new String[] { "ENDER_PORTAL" }),
    END_PORTAL_FRAME(0, new String[] { "ENDER_PORTAL_FRAME" }),
    END_ROD(0, new String[] { "END_ROD" }),
    END_STONE(0, new String[] { "ENDER_STONE" }),
    END_STONE_BRICKS(0, new String[] { "END_BRICKS" }),
    EVOKER_SPAWN_EGG(0, new String[] { "MONSTER_EGG" }),
    EXPERIENCE_BOTTLE(0, new String[] { "EXP_BOTTLE" }),
    FARMLAND(0, new String[] { "SOIL" }),
    FEATHER(0, new String[] { "FEATHER" }),
    FERMENTED_SPIDER_EYE(0, new String[] { "FERMENTED_SPIDER_EYE" }),
    FERN(2, new String[] { "LONG_GRASS" }),
    FILLED_MAP(0, new String[] { "MAP" }),
    FIRE(0, new String[] { "FIRE" }),
    FIREWORK_ROCKET(0, new String[] { "FIREWORK" }),
    FIREWORK_STAR(0, new String[] { "FIREWORK_CHARGE" }),
    FIRE_CHARGE(0, new String[] { "FIREBALL" }),
    FIRE_CORAL(0, new String[] { "STONE" }),
    FIRE_CORAL_BLOCK(0, new String[] { "STONE" }),
    FIRE_CORAL_FAN(0, new String[] { "STONE" }),
    FISHING_ROD(0, new String[] { "FISHING_ROD" }),
    FLINT(0, new String[] { "FLINT" }),
    FLINT_AND_STEEL(0, new String[] { "FLINT_AND_STEEL" }),
    FLOWER_POT(0, new String[] { "FLOWER_POT", "FLOWER_POT_ITEM" }),
    FROSTED_ICE(0, new String[] { "FROSTED_ICE" }),
    FURNACE(0, new String[] { "FURNACE", "BURNING_FURNACE" }),
    FURNACE_MINECART(0, new String[] { "POWERED_MINECART" }),
    GHAST_SPAWN_EGG(0, new String[] { "MONSTER_EGG" }),
    GHAST_TEAR(0, new String[] { "GHAST_TEAR" }),
    GLASS(0, new String[] { "GLASS" }),
    GLASS_BOTTLE(0, new String[] { "GLASS_BOTTLE" }),
    GLASS_PANE(0, new String[] { "THIN_GLASS" }),
    GLISTERING_MELON_SLICE(0, new String[] { "SPECKLED_MELON" }),
    GLOWSTONE(0, new String[] { "GLOWSTONE" }),
    GLOWSTONE_DUST(0, new String[] { "GLOWSTONE_DUST" }),
    GOLDEN_APPLE(0, new String[] { "GOLDEN_APPLE" }),
    GOLDEN_AXE(0, new String[] { "GOLD_AXE" }),
    GOLDEN_BOOTS(0, new String[] { "GOLD_BOOTS" }),
    GOLDEN_CARROT(0, new String[] { "GOLDEN_CARROT" }),
    GOLDEN_CHESTPLATE(0, new String[] { "GOLD_CHESTPLATE" }),
    GOLDEN_HELMET(0, new String[] { "GOLD_HELMET" }),
    GOLDEN_HOE(0, new String[] { "GOLD_HOE" }),
    GOLDEN_HORSE_ARMOR(0, new String[] { "GOLD_BARDING" }),
    GOLDEN_LEGGINGS(0, new String[] { "GOLD_LEGGINGS" }),
    GOLDEN_PICKAXE(0, new String[] { "GOLD_PICKAXE" }),
    GOLDEN_SHOVEL(0, new String[] { "GOLD_SPADE" }),
    GOLDEN_SWORD(0, new String[] { "GOLD_SWORD" }),
    GOLD_BLOCK(0, new String[] { "GOLD_BLOCK" }),
    GOLD_INGOT(0, new String[] { "GOLD_INGOT" }),
    GOLD_NUGGET(0, new String[] { "GOLD_NUGGET" }),
    GOLD_ORE(0, new String[] { "GOLD_ORE" }),
    GRANITE(1, new String[] { "STONE" }),
    GRASS(0, new String[] { "GRASS" }),
    GRASS_BLOCK(0, new String[] { "GRASS" }),
    GRASS_PATH(0, new String[] { "GRASS_PATH" }),
    GRAVEL(0, new String[] { "GRAVEL" }),
    GRAY_BANNER(8, new String[] { "BANNER", "STANDING_BANNER" }),
    GRAY_BED(7, new String[] { "BED", "BED_BLOCK" }),
    GRAY_CARPET(7, new String[] { "CARPET" }),
    GRAY_CONCRETE(7, new String[] { "CONCRETE" }),
    GRAY_CONCRETE_POWDER(7, new String[] { "CONCRETE_POWDER" }),
    GRAY_DYE(8, new String[] { "INK_SACK" }),
    GRAY_GLAZED_TERRACOTTA(0, new String[] { "GRAY_GLAZED_TERRACOTTA" }),
    GRAY_SHULKER_BOX(0, new String[] { "GRAY_SHULKER_BOX" }),
    GRAY_STAINED_GLASS(7, new String[] { "STAINED_GLASS" }),
    GRAY_STAINED_GLASS_PANE(7, new String[] { "STAINED_GLASS_PANE" }),
    GRAY_TERRACOTTA(7, new String[] { "STAINED_CLAY" }),
    GRAY_WALL_BANNER(0, new String[] { "WALL_BANNER" }),
    GRAY_WOOL(7, new String[] { "WOOL" }),
    GREEN_BANNER(2, new String[] { "BANNER", "STANDING_BANNER" }),
    GREEN_BED(13, new String[] { "BED", "BED_BLOCK" }),
    GREEN_CARPET(13, new String[] { "CARPET" }),
    GREEN_CONCRETE(13, new String[] { "CONCRETE" }),
    GREEN_CONCRETE_POWDER(13, new String[] { "CONCRETE_POWDER" }),
    GREEN_GLAZED_TERRACOTTA(0, new String[] { "GREEN_GLAZED_TERRACOTTA" }),
    GREEN_SHULKER_BOX(0, new String[] { "GREEN_SHULKER_BOX" }),
    GREEN_STAINED_GLASS(13, new String[] { "STAINED_GLASS" }),
    GREEN_STAINED_GLASS_PANE(13, new String[] { "STAINED_GLASS_PANE" }),
    GREEN_TERRACOTTA(13, new String[] { "STAINED_CLAY" }),
    GREEN_WALL_BANNER(0, new String[] { "WALL_BANNER" }),
    GREEN_WOOL(13, new String[] { "WOOL" }),
    GUARDIAN_SPAWN_EGG(0, new String[] { "MONSTER_EGG" }),
    GUNPOWDER(0, new String[] { "SULPHUR" }),
    HAY_BLOCK(0, new String[] { "HAY_BLOCK" }),
    HEART_OF_THE_SEA(0, new String[] { "STONE" }),
    HEAVY_WEIGHTED_PRESSURE_PLATE(0, new String[] { "IRON_PLATE" }),
    HOPPER(0, new String[] { "HOPPER" }),
    HOPPER_MINECART(0, new String[] { "HOPPER_MINECART" }),
    HORN_CORAL(0, new String[] { "STONE" }),
    HORN_CORAL_BLOCK(0, new String[] { "STONE" }),
    HORN_CORAL_FAN(0, new String[] { "STONE" }),
    HORSE_SPAWN_EGG(0, new String[] { "MONSTER_EGG" }),
    HUSK_SPAWN_EGG(0, new String[] { "MONSTER_EGG" }),
    ICE(0, new String[] { "ICE" }),
    INFESTED_CHISELED_STONE_BRICKS(5, new String[] { "MONSTER_EGGS" }),
    INFESTED_COBBLESTONE(1, new String[] { "MONSTER_EGGS" }),
    INFESTED_CRACKED_STONE_BRICKS(4, new String[] { "MONSTER_EGGS" }),
    INFESTED_MOSSY_STONE_BRICKS(3, new String[] { "MONSTER_EGGS" }),
    INFESTED_STONE(0, new String[] { "MONSTER_EGGS" }),
    INFESTED_STONE_BRICKS(2, new String[] { "MONSTER_EGGS" }),
    INK_SAC(0, new String[] { "INK_SACK" }),
    IRON_AXE(0, new String[] { "IRON_AXE" }),
    IRON_BARS(0, new String[] { "IRON_FENCE" }),
    IRON_BLOCK(0, new String[] { "IRON_BLOCK" }),
    IRON_BOOTS(0, new String[] { "IRON_BOOTS" }),
    IRON_CHESTPLATE(0, new String[] { "IRON_CHESTPLATE" }),
    IRON_DOOR(0, new String[] { "IRON_DOOR", "IRON_DOOR_BLOCK" }),
    IRON_HELMET(0, new String[] { "IRON_HELMET" }),
    IRON_HOE(0, new String[] { "IRON_HOE" }),
    IRON_HORSE_ARMOR(0, new String[] { "IRON_BARDING" }),
    IRON_INGOT(0, new String[] { "IRON_INGOT" }),
    IRON_LEGGINGS(0, new String[] { "IRON_LEGGINGS" }),
    IRON_NUGGET(0, new String[] { "IRON_NUGGET" }),
    IRON_ORE(0, new String[] { "IRON_ORE" }),
    IRON_PICKAXE(0, new String[] { "IRON_PICKAXE" }),
    IRON_SHOVEL(0, new String[] { "IRON_SPADE" }),
    IRON_SWORD(0, new String[] { "IRON_SWORD" }),
    IRON_TRAPDOOR(0, new String[] { "IRON_TRAPDOOR" }),
    ITEM_FRAME(0, new String[] { "ITEM_FRAME" }),
    JACK_O_LANTERN(0, new String[] { "JACK_O_LANTERN" }),
    JUKEBOX(0, new String[] { "JUKEBOX" }),
    JUNGLE_BOAT(0, new String[] { "BOAT_JUNGLE" }),
    JUNGLE_BUTTON(0, new String[] { "WOOD_BUTTON" }),
    JUNGLE_DOOR(0, new String[] { "JUNGLE_DOOR", "JUNGLE_DOOR_ITEM" }),
    JUNGLE_FENCE(0, new String[] { "JUNGLE_FENCE" }),
    JUNGLE_FENCE_GATE(0, new String[] { "JUNGLE_FENCE_GATE" }),
    JUNGLE_LEAVES(3, new String[] { "LEAVES" }),
    JUNGLE_LOG(3, new String[] { "LOG" }),
    JUNGLE_PLANKS(3, new String[] { "WOOD" }),
    JUNGLE_PRESSURE_PLATE(0, new String[] { "WOOD_PLATE" }),
    JUNGLE_SAPLING(3, new String[] { "SAPLING" }),
    JUNGLE_SLAB(3, new String[] { "WOODEN_SLAB", "WOOD_STEP", "WOOD_DOUBLE_STEP" }),
    JUNGLE_STAIRS(0, new String[] { "JUNGLE_WOOD_STAIRS" }),
    JUNGLE_TRAPDOOR(0, new String[] { "TRAP_DOOR" }),
    JUNGLE_WOOD(3, new String[] { "LOG" }),
    KELP(0, new String[] { "STONE" }),
    KELP_PLANT(0, new String[] { "STONE" }),
    KNOWLEDGE_BOOK(0, new String[] { "KNOWLEDGE_BOOK" }),
    LADDER(0, new String[] { "LADDER" }),
    LAPIS_BLOCK(0, new String[] { "LAPIS_BLOCK" }),
    LAPIS_LAZULI(4, new String[] { "INK_SACK" }),
    LAPIS_ORE(0, new String[] { "LAPIS_ORE" }),
    LARGE_FERN(3, new String[] { "DOUBLE_PLANT" }),
    LAVA(0, new String[] { "LAVA", "STATIONARY_LAVA" }),
    LAVA_BUCKET(0, new String[] { "LAVA_BUCKET" }),
    LEAD(0, new String[] { "LEASH" }),
    LEATHER(0, new String[] { "LEATHER" }),
    LEATHER_BOOTS(0, new String[] { "LEATHER_BOOTS" }),
    LEATHER_CHESTPLATE(0, new String[] { "LEATHER_CHESTPLATE" }),
    LEATHER_HELMET(0, new String[] { "LEATHER_HELMET" }),
    LEATHER_LEGGINGS(0, new String[] { "LEATHER_LEGGINGS" }),
    LEVER(0, new String[] { "LEVER" }),
    LIGHT_BLUE_BANNER(12, new String[] { "BANNER", "STANDING_BANNER" }),
    LIGHT_BLUE_BED(3, new String[] { "BED", "BED_BLOCK" }),
    LIGHT_BLUE_CARPET(3, new String[] { "CARPET" }),
    LIGHT_BLUE_CONCRETE(3, new String[] { "CONCRETE" }),
    LIGHT_BLUE_CONCRETE_POWDER(3, new String[] { "CONCRETE_POWDER" }),
    LIGHT_BLUE_DYE(12, new String[] { "INK_SACK" }),
    LIGHT_BLUE_GLAZED_TERRACOTTA(0, new String[] { "LIGHT_BLUE_GLAZED_TERRACOTTA" }),
    LIGHT_BLUE_SHULKER_BOX(0, new String[] { "LIGHT_BLUE_SHULKER_BOX" }),
    LIGHT_BLUE_STAINED_GLASS(3, new String[] { "STAINED_GLASS" }),
    LIGHT_BLUE_STAINED_GLASS_PANE(3, new String[] { "STAINED_GLASS_PANE" }),
    LIGHT_BLUE_TERRACOTTA(3, new String[] { "STAINED_CLAY" }),
    LIGHT_BLUE_WALL_BANNER(0, new String[] { "BANNER", "STANDING_BANNER" }),
    LIGHT_BLUE_WOOL(3, new String[] { "WOOL" }),
    LIGHT_GRAY_BANNER(7, new String[] { "BANNER", "STANDING_BANNER" }),
    LIGHT_GRAY_BED(8, new String[] { "BED", "BED_BLOCK" }),
    LIGHT_GRAY_CARPET(8, new String[] { "CARPET" }),
    LIGHT_GRAY_CONCRETE(8, new String[] { "CONCRETE" }),
    LIGHT_GRAY_CONCRETE_POWDER(8, new String[] { "CONCRETE_POWDER" }),
    LIGHT_GRAY_DYE(7, new String[] { "INK_SACK" }),
    LIGHT_GRAY_GLAZED_TERRACOTTA(0, new String[] { "SILVER_GLAZED_TERRACOTTA" }),
    LIGHT_GRAY_SHULKER_BOX(0, new String[] { "SILVER_SHULKER_BOX" }),
    LIGHT_GRAY_STAINED_GLASS(8, new String[] { "STAINED_GLASS" }),
    LIGHT_GRAY_STAINED_GLASS_PANE(8, new String[] { "STAINED_GLASS_PANE" }),
    LIGHT_GRAY_TERRACOTTA(8, new String[] { "STAINED_CLAY" }),
    LIGHT_GRAY_WALL_BANNER(0, new String[] { "WALL_BANNER" }),
    LIGHT_GRAY_WOOL(8, new String[] { "WOOL" }),
    LIGHT_WEIGHTED_PRESSURE_PLATE(0, new String[] { "GOLD_PLATE" }),
    LILAC(1, new String[] { "DOUBLE_PLANT" }),
    LILY_PAD(0, new String[] { "WATER_LILY" }),
    LIME_BANNER(10, new String[] { "BANNER", "STANDING_BANNER" }),
    LIME_BED(5, new String[] { "BED", "BED_BLOCK" }),
    LIME_CARPET(5, new String[] { "CARPET" }),
    LIME_CONCRETE(5, new String[] { "CONCRETE" }),
    LIME_CONCRETE_POWDER(5, new String[] { "CONCRETE_POWDER" }),
    LIME_DYE(10, new String[] { "INK_SACK" }),
    LIME_GLAZED_TERRACOTTA(0, new String[] { "LIME_GLAZED_TERRACOTTA" }),
    LIME_SHULKER_BOX(0, new String[] { "LIME_SHULKER_BOX" }),
    LIME_STAINED_GLASS(5, new String[] { "STAINED_GLASS" }),
    LIME_STAINED_GLASS_PANE(5, new String[] { "STAINED_GLASS_PANE" }),
    LIME_TERRACOTTA(5, new String[] { "STAINED_CLAY" }),
    LIME_WALL_BANNER(0, new String[] { "WALL_BANNER" }),
    LIME_WOOL(5, new String[] { "WOOL" }),
    LINGERING_POTION(0, new String[] { "LINGERING_POTION" }),
    LLAMA_SPAWN_EGG(0, new String[] { "MONSTER_EGG" }),
    MAGENTA_BANNER(13, new String[] { "BANNER", "STANDING_BANNER" }),
    MAGENTA_BED(2, new String[] { "BED", "BED_BLOCK" }),
    MAGENTA_CARPET(2, new String[] { "CARPET" }),
    MAGENTA_CONCRETE(2, new String[] { "CONCRETE" }),
    MAGENTA_CONCRETE_POWDER(2, new String[] { "CONCRETE_POWDER" }),
    MAGENTA_DYE(13, new String[] { "INK_SACK" }),
    MAGENTA_GLAZED_TERRACOTTA(0, new String[] { "MAGENTA_GLAZED_TERRACOTTA" }),
    MAGENTA_SHULKER_BOX(0, new String[] { "MAGENTA_SHULKER_BOX" }),
    MAGENTA_STAINED_GLASS(2, new String[] { "STAINED_GLASS" }),
    MAGENTA_STAINED_GLASS_PANE(2, new String[] { "STAINED_GLASS_PANE" }),
    MAGENTA_TERRACOTTA(2, new String[] { "STAINED_CLAY" }),
    MAGENTA_WALL_BANNER(0, new String[] { "WALL_BANNER" }),
    MAGENTA_WOOL(2, new String[] { "WOOL" }),
    MAGMA_BLOCK(0, new String[] { "MAGMA" }),
    MAGMA_CREAM(0, new String[] { "MAGMA_CREAM" }),
    MAGMA_CUBE_SPAWN_EGG(0, new String[] { "MONSTER_EGG" }),
    MAP(0, new String[] { "MAP", "EMPTY_MAP" }),
    MELON(0, new String[] { "MELON_BLOCK" }),
    MELON_SEEDS(0, new String[] { "MELON_SEEDS" }),
    MELON_SLICE(0, new String[] { "MELON" }),
    MELON_STEM(0, new String[] { "MELON_STEM" }),
    MILK_BUCKET(0, new String[] { "MILK_BUCKET" }),
    MINECART(0, new String[] { "MINECART" }),
    MOOSHROOM_SPAWN_EGG(0, new String[] { "MONSTER_EGG" }),
    MOSSY_COBBLESTONE(0, new String[] { "MOSSY_COBBLESTONE" }),
    MOSSY_COBBLESTONE_WALL(1, new String[] { "COBBLE_WALL" }),
    MOSSY_STONE_BRICKS(1, new String[] { "SMOOTH_BRICK" }),
    MOVING_PISTON(0, new String[] { "PISTON_MOVING_PIECE" }),
    MULE_SPAWN_EGG(0, new String[] { "MONSTER_EGG" }),
    MUSHROOM_STEM(0, new String[] { "BROWN_MUSHROOM" }),
    MUSHROOM_STEW(0, new String[] { "MUSHROOM_SOUP" }),
    MUSIC_DISC_11(0, new String[] { "GOLD_RECORD" }),
    MUSIC_DISC_13(0, new String[] { "GREEN_RECORD" }),
    MUSIC_DISC_BLOCKS(0, new String[] { "RECORD_3" }),
    MUSIC_DISC_CAT(0, new String[] { "RECORD_4" }),
    MUSIC_DISC_CHIRP(0, new String[] { "RECORD_5" }),
    MUSIC_DISC_FAR(0, new String[] { "RECORD_6" }),
    MUSIC_DISC_MALL(0, new String[] { "RECORD_7" }),
    MUSIC_DISC_MELLOHI(0, new String[] { "RECORD_8" }),
    MUSIC_DISC_STAL(0, new String[] { "RECORD_9" }),
    MUSIC_DISC_STRAD(0, new String[] { "RECORD_10" }),
    MUSIC_DISC_WAIT(0, new String[] { "RECORD_11" }),
    MUSIC_DISC_WARD(0, new String[] { "RECORD_12" }),
    MUTTON(0, new String[] { "MUTTON" }),
    MYCELIUM(0, new String[] { "MYCEL" }),
    NAME_TAG(0, new String[] { "NAME_TAG" }),
    NAUTILUS_SHELL(0, new String[] { "STONE" }),
    NETHERRACK(0, new String[] { "NETHERRACK" }),
    NETHER_BRICK(0, new String[] { "NETHER_BRICK" }),
    NETHER_BRICKS(0, new String[] { "NETHER_BRICK" }),
    NETHER_BRICK_FENCE(0, new String[] { "NETHER_FENCE" }),
    NETHER_BRICK_SLAB(6, new String[] { "STEP" }),
    NETHER_BRICK_STAIRS(0, new String[] { "NETHER_BRICK_STAIRS" }),
    NETHER_PORTAL(0, new String[] { "PORTAL" }),
    NETHER_QUARTZ_ORE(0, new String[] { "QUARTZ_ORE" }),
    NETHER_STAR(0, new String[] { "NETHER_STAR" }),
    NETHER_WART(0, new String[] { "NETHER_STALK" }),
    NETHER_WART_BLOCK(0, new String[] { "NETHER_WART_BLOCK", "NETHER_WARTS" }),
    NOTE_BLOCK(0, new String[] { "NOTE_BLOCK" }),
    OAK_BOAT(0, new String[] { "BOAT" }),
    OAK_BUTTON(0, new String[] { "WOOD_BUTTON" }),
    OAK_DOOR(0, new String[] { "WOODEN_DOOR", "WOOD_DOOR" }),
    OAK_FENCE(0, new String[] { "FENCE" }),
    OAK_FENCE_GATE(0, new String[] { "FENCE_GATE" }),
    OAK_LEAVES(0, new String[] { "LEAVES" }),
    OAK_LOG(0, new String[] { "LOG" }),
    OAK_PLANKS(0, new String[] { "WOOD" }),
    OAK_PRESSURE_PLATE(0, new String[] { "WOOD_PLATE" }),
    OAK_SAPLING(0, new String[] { "SAPLING" }),
    OAK_SLAB(0, new String[] { "WOODEN_SLAB", "WOOD_STEP", "WOOD_DOUBLE_STEP" }),
    OAK_STAIRS(0, new String[] { "WOOD_STAIRS" }),
    OAK_TRAPDOOR(0, new String[] { "TRAP_DOOR" }),
    OAK_WOOD(0, new String[] { "LOG" }),
    OBSERVER(0, new String[] { "OBSERVER" }),
    OBSIDIAN(0, new String[] { "OBSIDIAN" }),
    OCELOT_SPAWN_EGG(0, new String[] { "RECORD_12" }),
    ORANGE_BANNER(14, new String[] { "BANNER", "STANDING_BANNER" }),
    ORANGE_BED(1, new String[] { "BED", "BED_BLOCK" }),
    ORANGE_CARPET(1, new String[] { "CARPET" }),
    ORANGE_CONCRETE(1, new String[] { "CONCRETE" }),
    ORANGE_CONCRETE_POWDER(1, new String[] { "CONCRETE_POWDER" }),
    ORANGE_DYE(14, new String[] { "INK_SACK" }),
    ORANGE_GLAZED_TERRACOTTA(0, new String[] { "ORANGE_GLAZED_TERRACOTTA" }),
    ORANGE_SHULKER_BOX(0, new String[] { "ORANGE_SHULKER_BOX" }),
    ORANGE_STAINED_GLASS(1, new String[] { "STAINED_GLASS" }),
    ORANGE_STAINED_GLASS_PANE(1, new String[] { "STAINED_GLASS_PANE" }),
    ORANGE_TERRACOTTA(1, new String[] { "STAINED_CLAY" }),
    ORANGE_TULIP(5, new String[] { "RED_ROSE" }),
    ORANGE_WALL_BANNER(0, new String[] { "WALL_BANNER" }),
    ORANGE_WOOL(1, new String[] { "WOOL" }),
    OXEYE_DAISY(8, new String[] { "RED_ROSE" }),
    PACKED_ICE(0, new String[] { "PACKED_ICE" }),
    PAINTING(0, new String[] { "PAINTING" }),
    PAPER(0, new String[] { "PAPER" }),
    PARROT_SPAWN_EGG(0, new String[] { "MONSTER_EGG" }),
    PEONY(5, new String[] { "DOUBLE_PLANT" }),
    PETRIFIED_OAK_SLAB(0, new String[] { "STONE" }),
    PHANTOM_MEMBRANE(0, new String[] { "STONE" }),
    PHANTOM_SPAWN_EGG(0, new String[] { "MONSTER_EGG" }),
    PIG_SPAWN_EGG(0, new String[] { "MONSTER_EGG" }),
    PINK_BANNER(9, new String[] { "BANNER", "STANDING_BANNER" }),
    PINK_BED(6, new String[] { "BED", "BED_BLOCK" }),
    PINK_CARPET(6, new String[] { "CARPET" }),
    PINK_CONCRETE(6, new String[] { "CONCRETE" }),
    PINK_CONCRETE_POWDER(6, new String[] { "CONCRETE_POWDER" }),
    PINK_DYE(9, new String[] { "INK_SACK" }),
    PINK_GLAZED_TERRACOTTA(0, new String[] { "PINK_GLAZED_TERRACOTTA" }),
    PINK_SHULKER_BOX(0, new String[] { "PINK_SHULKER_BOX" }),
    PINK_STAINED_GLASS(6, new String[] { "STAINED_GLASS" }),
    PINK_STAINED_GLASS_PANE(6, new String[] { "STAINED_GLASS_PANE" }),
    PINK_TERRACOTTA(6, new String[] { "STAINED_CLAY" }),
    PINK_TULIP(7, new String[] { "RED_ROSE" }),
    PINK_WALL_BANNER(0, new String[] { "WALL_BANNER" }),
    PINK_WOOL(6, new String[] { "WOOL" }),
    PISTON(0, new String[] { "PISTON_BASE" }),
    PISTON_HEAD(0, new String[] { "PISTON_EXTENSION" }),
    PLAYER_HEAD(0, new String[] { "SKULL", "SKULL_ITEM" }),
    PLAYER_WALL_HEAD(0, new String[] { "SKULL", "SKULL_ITEM" }),
    PODZOL(2, new String[] { "DIRT" }),
    POISONOUS_POTATO(0, new String[] { "POISONOUS_POTATO" }),
    POLAR_BEAR_SPAWN_EGG(0, new String[] { "MONSTER_EGG" }),
    POLISHED_ANDESITE(6, new String[] { "STONE" }),
    POLISHED_DIORITE(4, new String[] { "STONE" }),
    POLISHED_GRANITE(2, new String[] { "STONE" }),
    POPPED_CHORUS_FRUIT(0, new String[] { "CHORUS_FRUIT_POPPED" }),
    POPPY(0, new String[] { "RED_ROSE" }),
    PORKCHOP(0, new String[] { "PORK" }),
    POTATO(0, new String[] { "POTATO_ITEM" }),
    POTATOES(0, new String[] { "POTATO" }),
    POTION(0, new String[] { "POTION" }),
    POTTED_ACACIA_SAPLING(0, new String[] { "FLOWER_POT" }),
    POTTED_ALLIUM(0, new String[] { "FLOWER_POT" }),
    POTTED_AZURE_BLUET(0, new String[] { "FLOWER_POT" }),
    POTTED_BIRCH_SAPLING(0, new String[] { "FLOWER_POT" }),
    POTTED_BLUE_ORCHID(0, new String[] { "FLOWER_POT" }),
    POTTED_BROWN_MUSHROOM(0, new String[] { "FLOWER_POT" }),
    POTTED_CACTUS(0, new String[] { "FLOWER_POT" }),
    POTTED_DANDELION(0, new String[] { "FLOWER_POT" }),
    POTTED_DARK_OAK_SAPLING(0, new String[] { "FLOWER_POT" }),
    POTTED_DEAD_BUSH(0, new String[] { "FLOWER_POT" }),
    POTTED_FERN(0, new String[] { "FLOWER_POT" }),
    POTTED_JUNGLE_SAPLING(0, new String[] { "FLOWER_POT" }),
    POTTED_OAK_SAPLING(0, new String[] { "FLOWER_POT" }),
    POTTED_ORANGE_TULIP(0, new String[] { "FLOWER_POT" }),
    POTTED_OXEYE_DAISY(0, new String[] { "FLOWER_POT" }),
    POTTED_PINK_TULIP(0, new String[] { "FLOWER_POT" }),
    POTTED_POPPY(0, new String[] { "FLOWER_POT" }),
    POTTED_RED_MUSHROOM(0, new String[] { "FLOWER_POT" }),
    POTTED_RED_TULIP(0, new String[] { "FLOWER_POT" }),
    POTTED_SPRUCE_SAPLING(0, new String[] { "FLOWER_POT" }),
    POTTED_WHITE_TULIP(0, new String[] { "FLOWER_POT" }),
    POWERED_RAIL(0, new String[] { "POWERED_RAIL" }),
    PRISMARINE(0, new String[] { "PRISMARINE" }),
    PRISMARINE_BRICKS(1, new String[] { "PRISMARINE" }),
    PRISMARINE_BRICK_SLAB(0, new String[] { "STONE" }),
    PRISMARINE_BRICK_STAIRS(0, new String[] { "STONE" }),
    PRISMARINE_CRYSTALS(0, new String[] { "PRISMARINE_CRYSTALS" }),
    PRISMARINE_SHARD(0, new String[] { "PRISMARINE_SHARD" }),
    PRISMARINE_SLAB(0, new String[] { "STONE" }),
    PRISMARINE_STAIRS(0, new String[] { "STONE" }),
    PUFFERFISH(3, new String[] { "RAW_FISH" }),
    PUFFERFISH_BUCKET(0, new String[] { "STONE" }),
    PUFFERFISH_SPAWN_EGG(0, new String[] { "MONSTER_EGG" }),
    PUMPKIN(0, new String[] { "PUMPKIN" }),
    PUMPKIN_PIE(0, new String[] { "PUMPKIN_PIE" }),
    PUMPKIN_SEEDS(0, new String[] { "PUMPKIN_SEEDS" }),
    PUMPKIN_STEM(0, new String[] { "PUMPKIN_STEM" }),
    PURPLE_BANNER(5, new String[] { "BANNER", "STANDING_BANNER" }),
    PURPLE_BED(10, new String[] { "BED", "BED_BLOCK" }),
    PURPLE_CARPET(10, new String[] { "CARPET" }),
    PURPLE_CONCRETE(10, new String[] { "CONCRETE" }),
    PURPLE_CONCRETE_POWDER(10, new String[] { "CONCRETE_POWDER" }),
    PURPLE_DYE(5, new String[] { "INK_SACK" }),
    PURPLE_GLAZED_TERRACOTTA(0, new String[] { "PURPLE_GLAZED_TERRACOTTA" }),
    PURPLE_SHULKER_BOX(0, new String[] { "PURPLE_SHULKER_BOX" }),
    PURPLE_STAINED_GLASS(10, new String[] { "STAINED_GLASS" }),
    PURPLE_STAINED_GLASS_PANE(10, new String[] { "STAINED_GLASS_PANE" }),
    PURPLE_TERRACOTTA(10, new String[] { "STAINED_CLAY" }),
    PURPLE_WALL_BANNER(0, new String[] { "WALL_BANNER" }),
    PURPLE_WOOL(10, new String[] { "WOOL" }),
    PURPUR_BLOCK(0, new String[] { "PURPUR_BLOCK" }),
    PURPUR_PILLAR(0, new String[] { "PURPUR_PILLAR" }),
    PURPUR_SLAB(0, new String[] { "PURPUR_SLAB", "PURPUR_DOUBLE_SLAB" }),
    PURPUR_STAIRS(0, new String[] { "PURPUR_STAIRS" }),
    QUARTZ(0, new String[] { "QUARTZ" }),
    QUARTZ_BLOCK(0, new String[] { "QUARTZ_BLOCK" }),
    QUARTZ_PILLAR(2, new String[] { "QUARTZ_BLOCK" }),
    QUARTZ_SLAB(7, new String[] { "STEP" }),
    QUARTZ_STAIRS(0, new String[] { "QUARTZ_STAIRS" }),
    RABBIT(0, new String[] { "RABBIT" }),
    RABBIT_FOOT(0, new String[] { "RABBIT_FOOT" }),
    RABBIT_HIDE(0, new String[] { "RABBIT_HIDE" }),
    RABBIT_SPAWN_EGG(0, new String[] { "MONSTER_EGG" }),
    RABBIT_STEW(0, new String[] { "RABBIT_STEW" }),
    RAIL(0, new String[] { "RAILS" }),
    REDSTONE(0, new String[] { "REDSTONE" }),
    REDSTONE_BLOCK(0, new String[] { "REDSTONE_BLOCK" }),
    REDSTONE_LAMP(0, new String[] { "REDSTONE_LAMP_ON", "REDSTONE_LAMP_OFF" }),
    REDSTONE_ORE(0, new String[] { "REDSTONE_ORE", "GLOWING_REDSTONE_ORE" }),
    REDSTONE_TORCH(0, new String[] { "REDSTONE_TORCH_ON", "REDSTONE_TORCH_OFF" }),
    REDSTONE_WALL_TORCH(1, new String[] { "REDSTONE_TORCH_ON", "REDSTONE_TORCH_OFF" }),
    REDSTONE_WIRE(0, new String[] { "REDSTONE_WIRE" }),
    RED_BANNER(1, new String[] { "BANNER", "STANDING_BANNER" }),
    RED_BED(14, new String[] { "BED", "BED_BLOCK" }),
    RED_CARPET(14, new String[] { "CARPET" }),
    RED_CONCRETE(14, new String[] { "CONCRETE" }),
    RED_CONCRETE_POWDER(14, new String[] { "CONCRETE_POWDER" }),
    RED_GLAZED_TERRACOTTA(0, new String[] { "RED_GLAZED_TERRACOTTA" }),
    RED_MUSHROOM(0, new String[] { "RED_MUSHROOM" }),
    RED_MUSHROOM_BLOCK(0, new String[] { "RED_MUSHROOM", "HUGE_MUSHROOM_2" }),
    RED_NETHER_BRICKS(0, new String[] { "RED_NETHER_BRICK" }),
    RED_SAND(1, new String[] { "SAND" }),
    RED_SANDSTONE(0, new String[] { "RED_SANDSTONE" }),
    RED_SANDSTONE_SLAB(0, new String[] { "STONE_SLAB2", "DOUBLE_STONE_SLAB2" }),
    RED_SANDSTONE_STAIRS(0, new String[] { "RED_SANDSTONE_STAIRS" }),
    RED_SHULKER_BOX(0, new String[] { "RED_SHULKER_BOX" }),
    RED_STAINED_GLASS(14, new String[] { "STAINED_GLASS" }),
    RED_STAINED_GLASS_PANE(14, new String[] { "STAINED_GLASS_PANE" }),
    RED_TERRACOTTA(14, new String[] { "STAINED_CLAY" }),
    RED_TULIP(4, new String[] { "RED_ROSE" }),
    RED_WALL_BANNER(0, new String[] { "WALL_BANNER" }),
    RED_WOOL(14, new String[] { "WOOL" }),
    REPEATER(0, new String[] { "DIODE", "DIODE_BLOCK_ON", "DIODE_BLOCK_OFF" }),
    REPEATING_COMMAND_BLOCK(0, new String[] { "COMMAND_REPEATING" }),
    ROSE_BUSH(4, new String[] { "DOUBLE_PLANT" }),
    ROSE_RED(1, new String[] { "INK_SACK" }),
    ROTTEN_FLESH(0, new String[] { "ROTTEN_FLESH" }),
    SADDLE(0, new String[] { "SADDLE" }),
    SALMON(1, new String[] { "RAW_FISH" }),
    SALMON_BUCKET(0, new String[] { "BUCKET" }),
    SALMON_SPAWN_EGG(0, new String[] { "MONSTER_EGG" }),
    SAND(0, new String[] { "SAND" }),
    SANDSTONE(0, new String[] { "SANDSTONE" }),
    SANDSTONE_SLAB(1, new String[] { "STONE_SLAB", "STEP", "DOUBLE_STEP" }),
    SANDSTONE_STAIRS(0, new String[] { "SANDSTONE_STAIRS" }),
    SCUTE(0, new String[] { "STONE" }),
    SEAGRASS(0, new String[] { "STONE" }),
    SEA_LANTERN(0, new String[] { "SEA_LANTERN" }),
    SEA_PICKLE(0, new String[] { "STONE" }),
    SHEARS(0, new String[] { "SHEARS" }),
    SHEEP_SPAWN_EGG(0, new String[] { "MONSTER_EGG" }),
    SHIELD(0, new String[] { "SHIELD" }),
    SHULKER_BOX(0, new String[] { "PURPLE_SHULKER_BOX" }),
    SHULKER_SHELL(0, new String[] { "SHULKER_SHELL" }),
    SHULKER_SPAWN_EGG(0, new String[] { "MONSTER_EGG" }),
    SIGN(0, new String[] { "SIGN" }),
    SILVERFISH_SPAWN_EGG(0, new String[] { "MONSTER_EGG" }),
    SKELETON_HORSE_SPAWN_EGG(0, new String[] { "MONSTER_EGG" }),
    SKELETON_SKULL(0, new String[] { "SKULL", "SKULL_ITEM" }),
    SKELETON_SPAWN_EGG(0, new String[] { "MONSTER_EGG" }),
    SKELETON_WALL_SKULL(0, new String[] { "SKULL", "SKULL_ITEM" }),
    SLIME_BALL(0, new String[] { "SLIME_BALL" }),
    SLIME_BLOCK(0, new String[] { "SLIME_BLOCK" }),
    SLIME_SPAWN_EGG(0, new String[] { "MONSTER_EGG" }),
    SMOOTH_QUARTZ(0, new String[] { "STONE" }),
    SMOOTH_RED_SANDSTONE(2, new String[] { "RED_SANDSTONE" }),
    SMOOTH_SANDSTONE(2, new String[] { "SANDSTONE" }),
    SMOOTH_STONE(0, new String[] { "STEP" }),
    SNOW(0, new String[] { "SNOW" }),
    SNOWBALL(0, new String[] { "SNOW_BALL" }),
    SNOW_BLOCK(0, new String[] { "SNOW_BLOCK" }),
    SOUL_SAND(0, new String[] { "SOUL_SAND" }),
    SPAWNER(0, new String[] { "MOB_SPAWNER" }),
    SPECTRAL_ARROW(0, new String[] { "SPECTRAL_ARROW" }),
    SPIDER_EYE(0, new String[] { "SPIDER_EYE" }),
    SPIDER_SPAWN_EGG(0, new String[] { "MONSTER_EGG" }),
    SPLASH_POTION(0, new String[] { "SPLASH_POTION" }),
    SPONGE(0, new String[] { "SPONGE" }),
    SPRUCE_BOAT(0, new String[] { "BOAT_SPRUCE" }),
    SPRUCE_BUTTON(0, new String[] { "WOOD_BUTTON" }),
    SPRUCE_DOOR(0, new String[] { "SPRUCE_DOOR", "SPRUCE_DOOR_ITEM" }),
    SPRUCE_FENCE(0, new String[] { "SPRUCE_FENCE" }),
    SPRUCE_FENCE_GATE(0, new String[] { "SPRUCE_FENCE_GATE" }),
    SPRUCE_LEAVES(1, new String[] { "LEAVES" }),
    SPRUCE_LOG(1, new String[] { "LOG" }),
    SPRUCE_PLANKS(1, new String[] { "WOOD" }),
    SPRUCE_PRESSURE_PLATE(0, new String[] { "WOOD_PLATE" }),
    SPRUCE_SAPLING(1, new String[] { "SAPLING" }),
    SPRUCE_SLAB(1, new String[] { "WOODEN_SLAB", "WOOD_STEP", "WOOD_DOUBLE_STEP" }),
    SPRUCE_STAIRS(0, new String[] { "SPRUCE_WOOD_STAIRS" }),
    SPRUCE_TRAPDOOR(0, new String[] { "TRAP_DOOR" }),
    SPRUCE_WOOD(1, new String[] { "LOG" }),
    SQUID_SPAWN_EGG(0, new String[] { "MONSTER_EGG" }),
    STICK(0, new String[] { "STICK" }),
    STICKY_PISTON(0, new String[] { "PISTON_STICKY_BASE" }),
    STONE(0, new String[] { "STONE" }),
    STONE_AXE(0, new String[] { "STONE_AXE" }),
    STONE_BRICKS(0, new String[] { "SMOOTH_BRICK" }),
    STONE_BRICK_SLAB(5, new String[] { "STONE_SLAB", "STEP", "DOUBLE_STEP" }),
    STONE_BRICK_STAIRS(0, new String[] { "SMOOTH_STAIRS" }),
    STONE_BUTTON(0, new String[] { "STONE_BUTTON" }),
    STONE_HOE(0, new String[] { "STONE_HOE" }),
    STONE_PICKAXE(0, new String[] { "STONE_PICKAXE" }),
    STONE_PRESSURE_PLATE(0, new String[] { "STONE_PLATE" }),
    STONE_SHOVEL(0, new String[] { "STONE_SPADE" }),
    STONE_SLAB(0, new String[] { "STONE_SLAB", "STEP", "DOUBLE_STEP" }),
    STONE_SWORD(0, new String[] { "STONE_SWORD" }),
    STRAY_SPAWN_EGG(0, new String[] { "MONSTER_EGG" }),
    STRING(0, new String[] { "STRING" }),
    STRIPPED_ACACIA_LOG(0, new String[] { "STONE" }),
    STRIPPED_ACACIA_WOOD(0, new String[] { "STONE" }),
    STRIPPED_BIRCH_LOG(0, new String[] { "STONE" }),
    STRIPPED_BIRCH_WOOD(0, new String[] { "STONE" }),
    STRIPPED_DARK_OAK_LOG(0, new String[] { "STONE" }),
    STRIPPED_DARK_OAK_WOOD(0, new String[] { "STONE" }),
    STRIPPED_JUNGLE_LOG(0, new String[] { "STONE" }),
    STRIPPED_JUNGLE_WOOD(0, new String[] { "STONE" }),
    STRIPPED_OAK_LOG(0, new String[] { "STONE" }),
    STRIPPED_OAK_WOOD(0, new String[] { "STONE" }),
    STRIPPED_SPRUCE_LOG(0, new String[] { "STONE" }),
    STRIPPED_SPRUCE_WOOD(0, new String[] { "STONE" }),
    STRUCTURE_BLOCK(0, new String[] { "STRUCTURE_BLOCK" }),
    STRUCTURE_VOID(0, new String[] { "STRUCTURE_VOID" }),
    SUGAR(0, new String[] { "SUGAR" }),
    SUGAR_CANE(0, new String[] { "SUGAR_CANE", "SUGAR_CANE_BLOCK" }),
    SUNFLOWER(0, new String[] { "DOUBLE_PLANT" }),
    TALL_GRASS(2, new String[] { "DOUBLE_PLANT" }),
    TALL_SEAGRASS(0, new String[] { "STONE" }),
    TERRACOTTA(0, new String[] { "HARD_CLAY" }),
    TIPPED_ARROW(0, new String[] { "TIPPED_ARROW" }),
    TNT(0, new String[] { "TNT" }),
    TNT_MINECART(0, new String[] { "EXPLOSIVE_MINECART" }),
    TORCH(0, new String[] { "TORCH" }),
    TOTEM_OF_UNDYING(0, new String[] { "TOTEM" }),
    TRAPPED_CHEST(0, new String[] { "TRAPPED_CHEST" }),
    TRIDENT(0, new String[] { "STONE" }),
    TRIPWIRE(0, new String[] { "TRIPWIRE" }),
    TRIPWIRE_HOOK(0, new String[] { "TRIPWIRE_HOOK" }),
    TROPICAL_FISH(0, new String[] { "RAW_FISH" }),
    TROPICAL_FISH_BUCKET(0, new String[] { "BUCKET" }),
    TROPICAL_FISH_SPAWN_EGG(0, new String[] { "MONSTER_EGG" }),
    TUBE_CORAL(0, new String[] { "STONE" }),
    TUBE_CORAL_BLOCK(0, new String[] { "STONE" }),
    TUBE_CORAL_FAN(0, new String[] { "STONE" }),
    TURTLE_EGG(0, new String[] { "MONSTER_EGG" }),
    TURTLE_HELMET(0, new String[] { "STONE" }),
    TURTLE_SPAWN_EGG(0, new String[] { "MONSTER_EGG" }),
    VEX_SPAWN_EGG(0, new String[] { "MONSTER_EGG" }),
    VILLAGER_SPAWN_EGG(0, new String[] { "MONSTER_EGG" }),
    VINDICATOR_SPAWN_EGG(0, new String[] { "MONSTER_EGG" }),
    VINE(0, new String[] { "VINE" }),
    VOID_AIR(0, new String[] { "AIR" }),
    WALL_SIGN(0, new String[] { "WALL_SIGN", "SIGN_POST" }),
    WALL_TORCH(1, new String[] { "TORCH" }),
    WATER(0, new String[] { "WATER", "STATIONARY_WATER" }),
    WATER_BUCKET(0, new String[] { "WATER_BUCKET" }),
    WET_SPONGE(1, new String[] { "SPONGE" }),
    WHEAT(0, new String[] { "WHEAT", "CROPS" }),
    WHEAT_SEEDS(0, new String[] { "WHEAT_SEEDS", "SEEDS" }),
    WHITE_BANNER(15, new String[] { "BANNER", "STANDING_BANNER" }),
    WHITE_BED(0, new String[] { "BED", "BED_BLOCK" }),
    WHITE_CARPET(0, new String[] { "CARPET" }),
    WHITE_CONCRETE(0, new String[] { "CONCRETE" }),
    WHITE_CONCRETE_POWDER(0, new String[] { "CONCRETE_POWDER" }),
    WHITE_GLAZED_TERRACOTTA(0, new String[] { "WHITE_GLAZED_TERRACOTTA" }),
    WHITE_SHULKER_BOX(0, new String[] { "WHITE_SHULKER_BOX" }),
    WHITE_STAINED_GLASS(0, new String[] { "STAINED_GLASS" }),
    WHITE_STAINED_GLASS_PANE(0, new String[] { "STAINED_GLASS_PANE" }),
    WHITE_TERRACOTTA(0, new String[] { "TERRACOTTA" }),
    WHITE_TULIP(6, new String[] { "RED_ROSE" }),
    WHITE_WALL_BANNER(0, new String[] { "WALL_BANNER" }),
    WHITE_WOOL(0, new String[] { "WOOL" }),
    WITCH_SPAWN_EGG(0, new String[] { "MONSTER_EGG" }),
    WITHER_SKELETON_SKULL(0, new String[] { "SKULL", "SKULL_ITEM" }),
    WITHER_SKELETON_SPAWN_EGG(0, new String[] { "MONSTER_EGG" }),
    WITHER_SKELETON_WALL_SKULL(0, new String[] { "SKULL", "SKULL_ITEM" }),
    WOLF_SPAWN_EGG(0, new String[] { "MONSTER_EGG" }),
    WOODEN_AXE(0, new String[] { "WOOD_AXE" }),
    WOODEN_HOE(0, new String[] { "WOOD_HOE" }),
    WOODEN_PICKAXE(0, new String[] { "WOOD_PICKAXE" }),
    WOODEN_SHOVEL(0, new String[] { "WOOD_SPADE" }),
    WOODEN_SWORD(0, new String[] { "WOOD_SWORD" }),
    WRITABLE_BOOK(0, new String[] { "BOOK_AND_QUILL" }),
    WRITTEN_BOOK(0, new String[] { "WRITTEN_BOOK" }),
    YELLOW_BANNER(11, new String[] { "BANNER", "STANDING_BANNER" }),
    YELLOW_BED(4, new String[] { "BED", "BED_BLOCK" }),
    YELLOW_CARPET(4, new String[] { "CARPET" }),
    YELLOW_CONCRETE(4, new String[] { "CONCRETE" }),
    YELLOW_CONCRETE_POWDER(4, new String[] { "CONCRETE_POWDER" }),
    YELLOW_GLAZED_TERRACOTTA(0, new String[] { "YELLOW_GLAZED_TERRACOTTA" }),
    YELLOW_SHULKER_BOX(0, new String[] { "YELLOW_SHULKER_BOX" }),
    YELLOW_STAINED_GLASS(4, new String[] { "STAINED_GLASS" }),
    YELLOW_STAINED_GLASS_PANE(4, new String[] { "STAINED_GLASS_PANE" }),
    YELLOW_TERRACOTTA(4, new String[] { "STAINED_CLAY" }),
    YELLOW_WALL_BANNER(0, new String[] { "WALL_BANNER" }),
    YELLOW_WOOL(4, new String[] { "WOOL" }),
    ZOMBIE_HEAD(0, new String[] { "SKULL", "SKULL_ITEM" }),
    ZOMBIE_HORSE_SPAWN_EGG(0, new String[] { "MONSTER_EGG" }),
    ZOMBIE_PIGMAN_SPAWN_EGG(0, new String[] { "MONSTER_EGG" }),
    ZOMBIE_SPAWN_EGG(0, new String[] { "MONSTER_EGG" }),
    ZOMBIE_VILLAGER_SPAWN_EGG(0, new String[] { "MONSTER_EGG" }),
    ZOMBIE_WALL_HEAD(0, new String[] { "SKULL", "SKULL_ITEM" });

    String[] m;

    int data;

    private static HashMap<String, XMaterial> cachedSearch;

    XMaterial(int data, String... m) {
        this.m = m;
        this.data = data;
    }

    public ItemStack parseItem() {
        Material mat = parseMaterial();
        if (isNewVersion())
            return new ItemStack(mat);
        return new ItemStack(mat, 1, (byte)this.data);
    }

    public static boolean isNewVersion() {
        Material mat = Material.getMaterial("RED_WOOL");
        if (mat != null)
            return true;
        return false;
    }

    static {
        cachedSearch = new HashMap<>();
    }

    public static XMaterial requestXMaterial(String name, byte data) {
        if (cachedSearch.containsKey(String.valueOf(name.toUpperCase()) + "," + data))
            return cachedSearch.get(String.valueOf(name.toUpperCase()) + "," + data);
        byte b;
        int i;
        XMaterial[] arrayOfXMaterial;
        for (i = (arrayOfXMaterial = values()).length, b = 0; b < i; ) {
            XMaterial mat = arrayOfXMaterial[b];
            byte b1;
            int j;
            String[] arrayOfString;
            for (j = (arrayOfString = mat.m).length, b1 = 0; b1 < j; ) {
                String test = arrayOfString[b1];
                if (name.toUpperCase().equals(test) && (byte)mat.data == data) {
                    cachedSearch.put(String.valueOf(test) + "," + data, mat);
                    return mat;
                }
                b1++;
            }
            b++;
        }
        return null;
    }

    public boolean isSameMaterial(ItemStack comp) {
        if (isNewVersion())
            return (comp.getType() == parseMaterial());
        if (comp.getType() == parseMaterial() &&
                comp.getData().getData() == this.data)
            return true;
        XMaterial xmat = fromMaterial(comp.getType());
        if (isDamageable(xmat) &&
                parseMaterial() == comp.getType())
            return true;
        return false;
    }

    public XMaterial fromMaterial(Material mat) {
        try {
            return valueOf(mat.toString());
        } catch (IllegalArgumentException e) {
            byte b;
            int i;
            XMaterial[] arrayOfXMaterial;
            for (i = (arrayOfXMaterial = values()).length, b = 0; b < i; ) {
                XMaterial xmat = arrayOfXMaterial[b];
                byte b1;
                int j;
                String[] arrayOfString;
                for (j = (arrayOfString = xmat.m).length, b1 = 0; b1 < j; ) {
                    String test = arrayOfString[b1];
                    if (test.equalsIgnoreCase(mat.toString()))
                        return xmat;
                    b1++;
                }
                b++;
            }
            return null;
        }
    }

    public static XMaterial fromString(String key) {
        XMaterial xmat = null;
        try {
            xmat = valueOf(key);
            return xmat;
        } catch (IllegalArgumentException e) {
            String[] split = key.split(":");
            if (split.length == 1) {
                xmat = requestXMaterial(key, (byte)0);
            } else {
                xmat = requestXMaterial(split[0], (byte)Integer.parseInt(split[1]));
            }
            return xmat;
        }
    }

    public boolean isDamageable(XMaterial type) {
        if (type == null)
            return false;
        String[] split = type.toString().split("_");
        int length = split.length;
        String str;
        switch ((str = split[length - 1]).hashCode()) {
            case -1850133582:
                if (!str.equals("SHEARS"))
                    break;
                return true;
            case -1849815901:
                if (!str.equals("SHOVEL"))
                    break;
                return true;
            case -1776664470:
                if (!str.equals("LEGGINGS"))
                    break;
                return true;
            case -829199152:
                if (!str.equals("TURTLE_HELMET"))
                    break;
                return true;
            case -342000110:
                if (!str.equals("TRIDENT"))
                    break;
                return true;
            case 65262:
                if (!str.equals("AXE"))
                    break;
                return true;
            case 71710:
                if (!str.equals("HOE"))
                    break;
                return true;
            case 63384481:
                if (!str.equals("BOOTS"))
                    break;
                return true;
            case 79322589:
                if (!str.equals("SWORD"))
                    break;
                return true;
            case 139953965:
                if (!str.equals("PICKAXE"))
                    break;
                return true;
            case 1456344605:
                if (!str.equals("HORSE_ARMOR"))
                    break;
                return true;
            case 1555044533:
                if (!str.equals("CHESTPLATE"))
                    break;
                return true;
            case 2048333745:
                if (!str.equals("ELYTRA"))
                    break;
                return true;
            case 2127362157:
                if (!str.equals("HELMET"))
                    break;
                return true;
        }
        return false;
    }

    public Material parseMaterial() {
        Material mat = Material.matchMaterial(toString());
        if (mat != null)
            return mat;
        return Material.matchMaterial(this.m[0]);
    }

    public static boolean supports(int version) {
        return (Data.VERSION >= version);
    }

    public static int getVersion() {
        return Data.VERSION;
    }
    private static final class Data {
        private static final int VERSION = Integer.parseInt(XMaterial.getMajorVersion(Bukkit.getVersion()).substring(2));

        private static final boolean ISFLAT = XMaterial.supports(13);
    }
    public static String getMajorVersion( String version) {
        Validate.notEmpty(version, "Cannot get major Minecraft version from null or empty string");
        int index = version.lastIndexOf("MC:");
        if (index != -1) {
            version = version.substring(index + 4, version.length() - 1);
        } else if (version.endsWith("SNAPSHOT")) {
            index = version.indexOf('-');
            version = version.substring(0, index);
        }
        int lastDot = version.lastIndexOf('.');
        if (version.indexOf('.') != lastDot)
            version = version.substring(0, lastDot);
        return version;
    }
}
