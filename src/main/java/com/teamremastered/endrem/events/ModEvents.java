package com.teamremastered.endrem.events;

import com.teamremastered.endrem.EndRemastered;
import com.teamremastered.endrem.config.ERConfig;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.EnderEyeItem;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber
public class ModEvents {

    // Enable/Disable placing of vanilla Ender Eyes depending on configuration
    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        if (event.isCancelable() && !ERConfig.USE_ENDER_EYES_ENABLED.getRaw()) {
            if (event.getPlayer().getInventory().getSelected().getItem() instanceof EnderEyeItem) {
                if (event.getWorld().getBlockState(event.getPos()).getBlock() == Blocks.END_PORTAL_FRAME) {
                    event.setCanceled(true);
                    event.getPlayer().displayClientMessage(new TranslatableComponent("block.endrem.ender_eye.warning"), true);
                }
            }
        }
    }

    // Enable/Disable throwing of vanilla Ender Eyes depending on configuration
    @SubscribeEvent
    public static void onRightClickItem(PlayerInteractEvent.RightClickItem event) {
        if (event.isCancelable() && !ERConfig.USE_ENDER_EYES_ENABLED.getRaw()) {
            if (event.getPlayer().getInventory().getSelected().getItem() instanceof EnderEyeItem) {
                event.setCanceled(true);
                if (event.getWorld().getBlockState(event.getPos()).getBlock() != Blocks.END_PORTAL_FRAME)
                    event.getPlayer().displayClientMessage(new TranslatableComponent("block.endrem.ender_eye.warning"), true);
            }
        }
    }
}
