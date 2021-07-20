package net.fabricmc.Content.Blocks;


import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.*;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Quaternion;
import net.minecraft.util.math.Vec3f;

public class SunStone0Renderer implements BlockEntityRenderer<SunStone0Entity> 
{
    public SunStone0Renderer(BlockEntityRendererFactory.Context context)
    {
        super();
    }

    @Override
    public void render(SunStone0Entity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) 
    {
        matrices.push();

        matrices.multiply(new Quaternion(new Vec3f(1, 0, 0), 90, true));
        matrices.translate(0.5, 0.5, -5 / 32f);

        matrices.scale(0.75f, 0.75f, 0.75f);

        for(int k = 0; k < entity.size(); k++)
        {
            matrices.multiply(new Quaternion(new Vec3f(0, 0, 1), 120, true));
            matrices.translate(Math.cos(k * 60 / 360 * 6.28f) * 0.25f, Math.sin(k * 60 / 360 * 6.28f) * 0.25f, 0);

            if(!entity.getStack(k).isEmpty())
                MinecraftClient.getInstance().getItemRenderer().renderItem(entity.getStack(k), ModelTransformation.Mode.GROUND, light, overlay, matrices, vertexConsumers, 0);

            matrices.translate(-Math.cos(k * 60 / 360 * 6.28f) * 0.25f, -Math.sin(k * 60 / 360 * 6.28f) * 0.25f, 0);
        }

        matrices.pop();     
    }
}