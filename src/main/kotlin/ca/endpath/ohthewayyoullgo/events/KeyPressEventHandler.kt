package ca.endpath.ohthewayyoullgo.events

import ca.endpath.ohthewayyoullgo.gui.WaypointGUI
import net.minecraft.client.Minecraft
import net.minecraftforge.client.event.InputEvent.KeyInputEvent
import net.minecraftforge.eventbus.api.SubscribeEvent

class KeyPressEventHandler {
    @SubscribeEvent
    public fun keyPress(event: KeyInputEvent) {
        if(Keybinds.waypointKey.isPressed) {
            Minecraft.getInstance().displayGuiScreen(WaypointGUI())
        }
    }
}