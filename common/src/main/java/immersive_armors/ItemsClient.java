package immersive_armors;

import immersive_armors.client.render.entity.model.*;
import immersive_armors.client.render.entity.piece.*;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Quaternion;
import net.minecraft.util.math.Vec3f;

import static immersive_armors.Items.*;

public class ItemsClient {
    public static void setupPieces() {
        BONE_ARMOR
                .lower(new MiddleLeggingsLayerPiece())
                .upper(new MiddleBodyLayerPiece());

        WARRIOR_ARMOR
                .hidesSecondLayer(true, true, true, true)
                .lower(new LowerLeggingsLayerPiece())
                .upper(new LowerBodyLayerPiece())
                .lower(new MiddleLeggingsLayerPiece())
                .upper(new MiddleBodyLayerPiece())
                .lower(new UpperLeggingsLayerPiece())
                .upper(new UpperBodyLayerPiece())
                .head(new ModelPiece(new HorizontalHeadModel()).texture("horizontal"))
                .chest(new CapePiece<>(new CapeModel<>()));

        HEAVY_ARMOR
                .hidesSecondLayer(true, true, true, true)
                .lower(new LowerLeggingsLayerPiece())
                .upper(new LowerBodyLayerPiece())
                .upper(new MiddleBodyLayerPiece())
                .lower(new UpperLeggingsLayerPiece())
                .upper(new UpperBodyLayerPiece())
                .head(new ModelPiece(new VerticalHeadModel()).texture("vertical"));

        ROBE_ARMOR
                .hidesSecondLayer(true, true, true, true)
                .lower(new LowerLeggingsLayerPiece().colored())
                .upper(new LowerBodyLayerPiece().colored())
                .lower(new MiddleLeggingsLayerPiece().colored())
                .upper(new MiddleBodyLayerPiece().colored());

        DIVINE_ARMOR
                .hidesSecondLayer(true, true, true, true)
                .lower(new LowerLeggingsLayerPiece().colored())
                .upper(new LowerBodyLayerPiece().colored())
                .upper(new MiddleBodyLayerPiece().glint())
                .upper(new UpperBodyLayerPiece().colored())
                .chest(new CapePiece<>(new CapeModel<>()).colored());

        PRISMARINE_ARMOR
                .lower(new MiddleLeggingsLayerPiece())
                .upper(new MiddleBodyLayerPiece())
                .upper(new UpperBodyLayerPiece())
                .full(new ModelPiece(new PrismarineModel()).texture("prismarine"));

        Quaternion flip = new Quaternion(new Vec3f(1.0f, 0.0f, 0.0f), -90.0f, true);
        Quaternion rotate = new Quaternion(new Vec3f(0.0f, 1.0f, 0.0f), 180.0f, true);
        rotate.hamiltonProduct(flip);

    }
}
