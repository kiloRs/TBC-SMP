package psikuvit.tbcenchant.libs;

import com.google.common.base.Enums;
import com.google.common.base.Strings;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.apache.commons.lang.WordUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public enum XEnchantment {
    ARROW_DAMAGE(true, new String[] { "POWER", "ARROW_DAMAGE", "ARROW_POWER", "AD" }),
    ARROW_FIRE(true, new String[] { "FLAME", "FLAME_ARROW", "FIRE_ARROW", "AF" }),
    ARROW_INFINITE(true, new String[] { "INFINITY", "INF_ARROWS", "INFINITE_ARROWS", "INFINITE", "UNLIMITED", "UNLIMITED_ARROWS", "AI" }),
    ARROW_KNOCKBACK(true, new String[] { "PUNCH", "ARROW_KNOCKBACK", "ARROWKB", "ARROW_PUNCH", "AK" }),
    BINDING_CURSE(true, new String[] { "BINDING_CURSE", "BIND_CURSE", "BINDING", "BIND" }),
    CHANNELING(true, new String[] { "CHANNELLING", "CHANELLING", "CHANELING", "CHANNEL" }),
    DAMAGE_ALL(true, new String[] { "SHARPNESS", "ALL_DAMAGE", "ALL_DMG", "SHARP", "DAL" }),
    DAMAGE_ARTHROPODS(true, new String[] { "BANE_OF_ARTHROPODS", "ARDMG", "BANE_OF_ARTHROPOD", "ARTHROPOD", "DAR" }),
    DAMAGE_UNDEAD(true, new String[] { "SMITE", "UNDEAD_DAMAGE", "DU" }),
    DEPTH_STRIDER(true, new String[] { "DEPTH", "STRIDER" }),
    DIG_SPEED(true, new String[] { "EFFICIENCY", "MINE_SPEED", "CUT_SPEED", "DS", "EFF" }),
    DURABILITY(true, new String[] { "UNBREAKING", "DURA" }),
    FIRE_ASPECT(true, new String[] { "FIRE", "MELEE_FIRE", "MELEE_FLAME", "FA" }),
    FROST_WALKER(true, new String[] { "FROST", "WALKER" }),
    IMPALING(true, new String[] { "IMPALE", "OCEAN_DAMAGE", "OCEAN_DMG" }),
    SOUL_SPEED(true, new String[] { "SPEED_SOUL", "SOUL_RUNNER" }),
    KNOCKBACK(true, new String[] { "K_BACK", "KB" }),
    LOOT_BONUS_BLOCKS(true, new String[] { "FORTUNE", "BLOCKS_LOOT_BONUS", "FORT", "LBB" }),
    LOOT_BONUS_MOBS(true, new String[] { "LOOTING", "MOB_LOOT", "MOBS_LOOT_BONUS", "LBM" }),
    LOYALTY(true, new String[] { "LOYAL", "RETURN" }),
    LUCK(true, new String[] { "LUCK_OF_THE_SEA", "LUCK_OF_SEA", "LUCK_OF_SEAS", "ROD_LUCK" }),
    LURE(true, new String[] { "ROD_LURE" }),
    MENDING(true, new String[0]),
    MULTISHOT(true, new String[] { "TRIPLE_SHOT" }),
    OXYGEN(true, new String[] { "RESPIRATION", "BREATH", "BREATHING", "O2", "O" }),
    PIERCING(true, new String[0]),
    PROTECTION_ENVIRONMENTAL(true, new String[] { "PROTECTION", "PROTECT", "PROT" }),
    PROTECTION_EXPLOSIONS(true, new String[] { "BLAST_PROTECTION", "BLAST_PROTECT", "EXPLOSIONS_PROTECTION", "EXPLOSION_PROTECTION", "BLAST_PROTECTION", "PE" }),
    PROTECTION_FALL(true, new String[] { "FEATHER_FALLING", "FALL_PROT", "FEATHER_FALL", "FALL_PROTECTION", "FEATHER_FALLING", "PFA" }),
    PROTECTION_FIRE(true, new String[] { "FIRE_PROTECTION", "FIRE_PROT", "FIRE_PROTECT", "FIRE_PROTECTION", "FLAME_PROTECTION", "FLAME_PROTECT", "FLAME_PROT", "PF" }),
    PROTECTION_PROJECTILE(true, new String[] { "PROJECTILE_PROTECTION", "PROJECTILE_PROTECTION", "PROJ_PROT", "PP" }),
    QUICK_CHARGE(true, new String[] { "QUICKCHARGE", "QUICK_DRAW", "FAST_CHARGE", "FAST_DRAW" }),
    RIPTIDE(true, new String[] { "RIP", "TIDE", "LAUNCH" }),
    SILK_TOUCH(true, new String[] { "SOFT_TOUCH", "ST" }),
    SWEEPING_EDGE(true, new String[] { "SWEEPING", "SWEEPING_EDGE", "SWEEP_EDGE" }),
    THORNS(true, new String[] { "HIGHCRIT", "THORN", "HIGHERCRIT", "T" }),
    VANISHING_CURSE(true, new String[] { "VANISHING_CURSE", "VANISH_CURSE", "VANISHING", "VANISH" }),
    WATER_WORKER(true, new String[] { "AQUA_AFFINITY", "WATER_WORKER", "AQUA_AFFINITY", "WATER_MINE", "WW" });

    public static final List<XEnchantment> VALUES;

    public static final Set<EntityType> EFFECTIVE_SMITE_ENTITIES;

    public static final Set<EntityType> EFFECTIVE_BANE_OF_ARTHROPODS_ENTITIES;

    @Nullable
    private final Enchantment enchantment;

    static {
        VALUES = Collections.unmodifiableList(Arrays.asList(values()));
        EntityType bee = (EntityType)Enums.getIfPresent(EntityType.class, "BEE").orNull();
        EntityType phantom = (EntityType)Enums.getIfPresent(EntityType.class, "PHANTOM").orNull();
        EntityType drowned = (EntityType)Enums.getIfPresent(EntityType.class, "DROWNED").orNull();
        EntityType witherSkeleton = (EntityType)Enums.getIfPresent(EntityType.class, "WITHER_SKELETON").orNull();
        EntityType skeletonHorse = (EntityType)Enums.getIfPresent(EntityType.class, "SKELETON_HORSE").orNull();
        EntityType stray = (EntityType)Enums.getIfPresent(EntityType.class, "STRAY").orNull();
        EntityType husk = (EntityType)Enums.getIfPresent(EntityType.class, "HUSK").orNull();
        Set<EntityType> arthorposEffective = EnumSet.of(EntityType.SPIDER, EntityType.CAVE_SPIDER, EntityType.SILVERFISH, EntityType.ENDERMITE);
        if (bee != null)
            arthorposEffective.add(bee);
        EFFECTIVE_BANE_OF_ARTHROPODS_ENTITIES = Collections.unmodifiableSet(arthorposEffective);
        Set<EntityType> smiteEffective = EnumSet.of(EntityType.ZOMBIE, EntityType.SKELETON, EntityType.WITHER);
        if (phantom != null)
            smiteEffective.add(phantom);
        if (drowned != null)
            smiteEffective.add(drowned);
        if (witherSkeleton != null)
            smiteEffective.add(witherSkeleton);
        if (skeletonHorse != null)
            smiteEffective.add(skeletonHorse);
        if (stray != null)
            smiteEffective.add(stray);
        if (husk != null)
            smiteEffective.add(husk);
        EFFECTIVE_SMITE_ENTITIES = Collections.unmodifiableSet(smiteEffective);
    }

    XEnchantment(boolean self, String... aliases) {
        Enchantment enchantment;
        Data.NAMES.put(name(), this);
        for (String legacy : aliases)
            Data.NAMES.put(legacy, this);
        if (Data.ISFLAT) {
            String vanilla = self ? name() : aliases[0];
            enchantment = Enchantment.getByKey(NamespacedKey.minecraft(vanilla.toLowerCase(Locale.ENGLISH)));
        } else {
            enchantment = Enchantment.getByName(name());
        }
        this.enchantment = enchantment;
    }

    public static boolean isSmiteEffectiveAgainst(@Nullable EntityType type) {
        return (type != null && EFFECTIVE_SMITE_ENTITIES.contains(type));
    }

    public static boolean isArthropodsEffectiveAgainst(@Nullable EntityType type) {
        return (type != null && EFFECTIVE_BANE_OF_ARTHROPODS_ENTITIES.contains(type));
    }

    @Nonnull
    private static String format(@Nonnull String name) {
        int len = name.length();
        char[] chs = new char[len];
        int count = 0;
        boolean appendUnderline = false;
        for (int i = 0; i < len; i++) {
            char ch = name.charAt(i);
            if (!appendUnderline && count != 0 && (ch == '-' || ch == ' ' || ch == '_') && chs[count] != '_') {
                appendUnderline = true;
            } else if ((ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z')) {
                if (appendUnderline) {
                    chs[count++] = '_';
                    appendUnderline = false;
                }
                chs[count++] = (char)(ch & 0x5F);
            }
        }
        return new String(chs, 0, count);
    }

    @Nonnull
    public static Optional<XEnchantment> matchXEnchantment(@Nonnull String enchantment) {
        Validate.notEmpty(enchantment, "Enchantment name cannot be null or empty");
        return Optional.ofNullable((XEnchantment)Data.NAMES.get(format(enchantment)));
    }

    @Nonnull
    public static XEnchantment matchXEnchantment(@Nonnull Enchantment enchantment) {
        Objects.requireNonNull(enchantment, "Cannot parse XEnchantment of a null enchantment");
        return Objects.<XEnchantment>requireNonNull((XEnchantment)Data.NAMES.get(enchantment.getName()), () -> "Unsupported enchantment: " + enchantment.getName());
    }

    @Nonnull
    public static ItemStack addEnchantFromString(@Nonnull ItemStack item, @Nullable String enchantment) {
        Objects.requireNonNull(item, "Cannot add enchantment to null ItemStack");
        if (Strings.isNullOrEmpty(enchantment) || enchantment.equalsIgnoreCase("none"))
            return item;
        String[] split = StringUtils.split(StringUtils.deleteWhitespace(enchantment), ',');
        if (split.length == 0)
            split = StringUtils.split(enchantment, ' ');
        Optional<XEnchantment> enchantOpt = matchXEnchantment(split[0]);
        if (!enchantOpt.isPresent())
            return item;
        Enchantment enchant = ((XEnchantment)enchantOpt.get()).parseEnchantment();
        if (enchant == null)
            return item;
        int lvl = 1;
        if (split.length > 1)
            lvl = NumberUtils.toInt(split[1]);
        item.addUnsafeEnchantment(enchant, lvl);
        return item;
    }

    @Nonnull
    public ItemStack getBook(int level) {
        ItemStack book = new ItemStack(Material.ENCHANTED_BOOK);
        EnchantmentStorageMeta meta = (EnchantmentStorageMeta)book.getItemMeta();
        meta.addStoredEnchant(parseEnchantment(), level, true);
        book.setItemMeta((ItemMeta)meta);
        return book;
    }

    @Nullable
    public Enchantment parseEnchantment() {
        return this.enchantment;
    }

    public boolean isSupported() {
        return (parseEnchantment() != null);
    }

    @Nonnull
    public String toString() {
        return WordUtils.capitalize(name().replace('_', ' ').toLowerCase(Locale.ENGLISH));
    }

    private static final class Data {
        private static final boolean ISFLAT;

        private static final Map<String, XEnchantment> NAMES = new HashMap<>();

        static {
            boolean flat;
            try {
                Class<?> namespacedKeyClass = Class.forName("org.bukkit.NamespacedKey");
                Class<?> enchantmentClass = Class.forName("org.bukkit.enchantments.Enchantment");
                enchantmentClass.getDeclaredMethod("getByKey", new Class[] { namespacedKeyClass });
                flat = true;
            } catch (ClassNotFoundException|NoSuchMethodException ex) {
                flat = false;
            }
            ISFLAT = flat;
        }

        static {
            boolean flat;
        }
    }
}
