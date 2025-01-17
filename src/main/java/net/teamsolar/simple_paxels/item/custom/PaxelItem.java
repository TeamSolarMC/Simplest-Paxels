package net.teamsolar.simple_paxels.item.custom;

import net.teamsolar.simple_paxels.util.ModTags;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.Tier;

public class PaxelItem extends DiggerItem {
    public PaxelItem(Tier tier, float attackDamageModifier, float attackSpeedModifier, Properties properties) {
        super(
                tier,
                ModTags.Blocks.PAXEL_MINEABLE,
                properties.attributes(
                        PaxelItem.createAttributes(tier, attackDamageModifier, attackDamageModifier)
                )
        );
    }
}
