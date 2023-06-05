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
import net.minecraftforge.fml.common.Mod;
import net.minecraft.world.entity.player.Player;
import  net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = "mcapi", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class PlayerInfo {

    private static Vec3 previousPosition = null;
    private static final Minecraft minecraft = Minecraft.getInstance();

    @SubscribeEvent
    public static Vec3 getPosition(TickEvent.PlayerTickEvent event){
        Player player = event.player;
        Vec3 currentPosition = player.position();

        if (event.phase == TickEvent.Phase.END){
            if (!currentPosition.equals(previousPosition)){
                previousPosition = currentPosition;
                return currentPosition;
            }else{
                return null;
            }
        }else{
            return null;
        }
    }

    @SubscribeEvent
    public static String getTargetedBlock(TickEvent.PlayerTickEvent event){
        Entity entity = minecraft.getCameraEntity();
        HitResult block;
        BlockState blockstate = null;
        BlockPos blockpos;

        if (event.phase == TickEvent.Phase.END){
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
        }else{
            return null;
        }
    }

    @SubscribeEvent
    public static String getTargetedFluid(TickEvent.PlayerTickEvent event) {
        Entity entity = minecraft.getCameraEntity();
        HitResult liquid;
        BlockPos blockpos;
        FluidState fluidstate = null;

        if (event.phase == TickEvent.Phase.END) {
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
        }else{
            return null;
        }
    }

    @SubscribeEvent
    public static String getTargetedEntity(TickEvent.PlayerTickEvent event){
        Entity entity = minecraft.crosshairPickEntity;

        if(event.phase == TickEvent.Phase.END){
            if(entity != null){
                return String.valueOf((Object) ForgeRegistries.ENTITY_TYPES.getKey(entity.getType()));
            }else{
                return null;
            }
        }else{
            return null;
        }
    }

    @SubscribeEvent
    public static Float getHealth(TickEvent.PlayerTickEvent event){
        Player player = event.player;
        float health = 0.0F;

        if(event.phase == TickEvent.Phase.END) {
            health = player.getHealth();
            return health;
        }else{
            return null;
        }

    }
}
