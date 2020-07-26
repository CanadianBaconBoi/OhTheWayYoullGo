package ca.endpath.ohthewayyoullgo.backend

import ca.endpath.ohthewayyoullgo.OhTheWayYoullGo
import net.minecraft.entity.player.PlayerEntity
import net.minecraftforge.event.entity.player.PlayerEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import org.apache.logging.log4j.Level

object EventsHost {

    @SubscribeEvent
    public fun onPlayerLoggedIn(eventArgs: PlayerEvent.PlayerLoggedInEvent) {
        if (eventArgs.player != null && !eventArgs.player.isUser) {
            OhTheWayYoullGo.logger.log(Level.INFO, "Detected Player Join World!")
            val worldID : String
            eventArgs.player.server!!.let {
                worldID = "${it.worldName}_${it.serverHostname}"
                        .replace('/', '-')
                        .replace('\\', '-')
                        .replace('.', '-')
                        .replace('$', '-')
            }
            if (Map.isMapLoaded())
                Map.saveMap()

            Map.loadMap(worldID)
        }
    }

    @SubscribeEvent
    public fun onPlayerLoggedOut(eventArgs: PlayerEvent.PlayerLoggedOutEvent) {
        if (eventArgs.player != null && !eventArgs.player.isUser) {
            OhTheWayYoullGo.logger.log(Level.INFO, "Detected Player Join World!")
            val worldID : String
            eventArgs.player.server!!.let {
                worldID = "${it.worldName}_${it.serverHostname}"
                        .replace('/', '-')
                        .replace('\\', '-')
                        .replace('.', '-')
                        .replace('$', '-')
            }
            if (Map.isMapLoaded())
                Map.saveMap()
        }
    }
}