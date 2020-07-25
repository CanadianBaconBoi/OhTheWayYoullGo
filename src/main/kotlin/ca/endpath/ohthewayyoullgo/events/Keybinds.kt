package ca.endpath.ohthewayyoullgo.events

import net.minecraft.client.settings.KeyBinding
import net.minecraftforge.fml.client.registry.ClientRegistry
import java.awt.event.KeyEvent

object Keybinds {
    lateinit var waypointKey: KeyBinding

    public fun register() {
        waypointKey = KeyBinding("Open Waypoint GUI", KeyEvent.VK_V, "Oh The Way You'll Go")
        ClientRegistry.registerKeyBinding(waypointKey)
    }
}