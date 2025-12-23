package com.qolcraft.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import static net.minecraft.world.level.block.FarmBlock.turnToDirt;

@Mixin(FarmBlock.class)
public class FarmlandTrampleMixin extends Block {

    public FarmlandTrampleMixin(Properties properties) {
        super(properties);
    }

    /**
     * @author furyng
     * @reason ignore trample with feather falling
     */
    @Overwrite
    public void fallOn(Level level, BlockState blockState, BlockPos blockPos, Entity entity, double d) {
        if (level instanceof ServerLevel serverLevel

                && entity instanceof LivingEntity livingEntity
                && level.random.nextFloat() < d - 0.5
                && level.random.nextFloat() > 0.25 * EnchantmentHelper.getEnchantmentLevel(level.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.FEATHER_FALLING), livingEntity)
                && (entity instanceof Player || serverLevel.getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING))
                && entity.getBbWidth() * entity.getBbWidth() * entity.getBbHeight() > 0.512F
        ) {
            turnToDirt(entity, blockState, level, blockPos);
        }

        super.fallOn(level, blockState, blockPos, entity, d);
    }
}