package immersive_armors.mixin;

import immersive_armors.config.Config;
import immersive_armors.Items;
import immersive_armors.item.ExtendedArmorMaterial;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.function.Supplier;

@Mixin(MobEntity.class)
public class MobEntityMixin {
    private static final Random equipmentRandom = new Random();

    @Inject(method = "getEquipmentForSlot(Lnet/minecraft/entity/EquipmentSlot;I)Lnet/minecraft/item/Item;", at = @At("HEAD"), cancellable = true)
    private static void immersiveArmors$injectGetEquipmentForSlot(EquipmentSlot equipmentSlot, int equipmentLevel, CallbackInfoReturnable<Item> cir) {
        final Map<Integer, ExtendedArmorMaterial> items = new HashMap<>() {{
            put(0, Items.WARRIOR_ARMOR);
            put(1, Items.HEAVY_ARMOR);
            put(2, Items.DIVINE_ARMOR);
            put(3, Items.PRISMARINE_ARMOR);
        }};

        if (items.containsKey(equipmentLevel) && equipmentRandom.nextFloat() < Config.getInstance().mobEntityUseImmersiveArmorChance) {
            String name = items.get(equipmentLevel).getName();

            Supplier<Item> item = switch (equipmentSlot) {
                case HEAD -> Items.items.get(name + "_helmet");
                case CHEST -> Items.items.get(name + "_chestplate");
                case LEGS -> Items.items.get(name + "_leggings");
                case FEET -> Items.items.get(name + "_boots");
                default -> null;
            };

            if (item != null) {
                cir.setReturnValue(item.get());
            }
        }
    }
}
