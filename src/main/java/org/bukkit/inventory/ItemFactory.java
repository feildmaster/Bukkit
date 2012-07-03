package org.bukkit.inventory;

import org.bukkit.Material;
import org.bukkit.inventory.meta.ItemMeta;

public interface ItemFactory {
    ItemMeta getItemMeta(final ItemStack itemstack);
    ItemMeta getItemMeta(final Material material);

    boolean isValidMeta(final ItemMeta meta, final ItemStack stack);

    boolean equals(ItemMeta meta1, ItemMeta meta2);
}
