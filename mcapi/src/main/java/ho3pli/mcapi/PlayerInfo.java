package ho3pli.mcapi;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraft.world.entity.player.Player;
import  net.minecraftforge.registries.ForgeRegistries;

public class PlayerInfo {

    private static Vec3 previousPosition = null;
    private static final Minecraft minecraft = Minecraft.getInstance();

    public static Vec3 getPosition(){
        Player player = minecraft.player;
        Vec3 currentPosition = player.position();

        if (!currentPosition.equals(previousPosition)){
            previousPosition = currentPosition;
            return currentPosition;
        }else{
            return null;
        }
    }

    public static String getTargetedBlock(){
        Entity entity = minecraft.getCameraEntity();
        HitResult block;
        BlockState blockstate = null;
        BlockPos blockpos;

        if (entity != null){
            block = entity.pick(20.0D, 0.0F, false);
            if (block.getType() == HitResult.Type.BLOCK) {
                blockpos = ((BlockHitResult) block).getBlockPos();
                blockstate = minecraft.level.getBlockState(blockpos);
                return String.valueOf((Object) ForgeRegistries.BLOCKS.getKey(blockstate.getBlock()));
            }else{
                return null;
            }
        }else{
            return null;
        }
    }

    public static String getTargetedFluid() {
        Entity entity = minecraft.getCameraEntity();
        HitResult liquid;
        BlockPos blockpos;
        FluidState fluidstate = null;

        if (entity != null) {
            liquid = entity.pick(20.0D, 0.0F, true);
            if (liquid.getType() == HitResult.Type.BLOCK) {
                blockpos = ((BlockHitResult) liquid).getBlockPos();
                fluidstate = minecraft.level.getFluidState(blockpos);
                return String.valueOf((Object) ForgeRegistries.FLUIDS.getKey(fluidstate.getType()));
            }else{
                return null;
            }
        }else{
            return null;
        }
    }

    public static String getTargetedEntity(){
        Entity entity = minecraft.crosshairPickEntity;

        if(entity != null){
            return String.valueOf((Object) ForgeRegistries.ENTITY_TYPES.getKey(entity.getType()));
        }else{
            return null;
        }
    }

    public static Float getHealth(){
        Player player = minecraft.player;
        float health = 0.0F;

        health = player.getHealth();
        return health;

    }
}
