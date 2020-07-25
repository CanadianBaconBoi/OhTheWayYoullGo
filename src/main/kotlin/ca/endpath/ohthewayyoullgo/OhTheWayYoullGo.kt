package ca.endpath.ohthewayyoullgo

import ca.endpath.ohthewayyoullgo.gui.events.KeyPressEventHandler
import ca.endpath.ohthewayyoullgo.gui.events.Keybinds
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.ExtensionPoint
import net.minecraftforge.fml.ModLoadingContext
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.server.FMLServerAboutToStartEvent
import net.minecraftforge.fml.network.FMLNetworkConstants
import org.apache.logging.log4j.Level
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import thedarkcolour.kotlinforforge.forge.FORGE_BUS
import java.util.function.BiPredicate
import java.util.function.Supplier
import org.apache.commons.lang3.tuple.Pair as Pair

@Mod(OhTheWayYoullGo.ID)
object OhTheWayYoullGo {
    const val ID: String = "ohthewayyoullgo"
    val logger : Logger = LogManager.getLogger()

    init {
        logger.log(Level.INFO, "Oh The Way You'll Go Initializing")
        FORGE_BUS.addListener(::onServerAboutToStart)

        //This ugly line of code makes it so the server doesn't need the mod, only the client
        ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.DISPLAYTEST) { Pair.of(Supplier { FMLNetworkConstants.IGNORESERVERONLY }, BiPredicate { _, _ -> true }) }

        Keybinds.register()
        MinecraftForge.EVENT_BUS.register(KeyPressEventHandler())
    }

    private fun onServerAboutToStart(event: FMLServerAboutToStartEvent) {
        logger.log(Level.INFO, "Server starting...")
    }
}