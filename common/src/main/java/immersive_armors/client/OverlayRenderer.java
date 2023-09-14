package immersive_armors.client;

import immersive_armors.config.Config;
import immersive_armors.Main;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Arm;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class OverlayRenderer {
    private static final ItemStack clock = new ItemStack(Items.CLOCK);
    private static final ItemStack compass = new ItemStack(Items.COMPASS);

    public static void renderOverlay() {
        MinecraftClient client = MinecraftClient.getInstance();
        if (!client.options.hudHidden && client.interactionManager != null) {
            if (client.player != null) {
                for (ItemStack item : client.player.getArmorItems()) {
                    Identifier id = Registry.ITEM.getId(item.getItem());
                }
            }
        }
    }
}
