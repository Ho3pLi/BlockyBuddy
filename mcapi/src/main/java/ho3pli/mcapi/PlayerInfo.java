package ho3pli.mcapi;

import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.world.entity.player.Player;


@Mod.EventBusSubscriber(modid = "mcapi", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class PlayerInfo {

    private static Vec3 previousPosition = null;

    @SubscribeEvent
    public static Vec3 onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        Vec3 currentPosition = player.position();
        if (event.phase == TickEvent.Phase.START) {
            if (!currentPosition.equals(previousPosition)) {

                previousPosition = currentPosition;

                System.out.println(currentPosition);
            }
        }
        return currentPosition;
    }
}
