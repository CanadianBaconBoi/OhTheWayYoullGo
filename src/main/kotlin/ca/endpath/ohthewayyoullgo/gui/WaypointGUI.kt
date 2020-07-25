package ca.endpath.ohthewayyoullgo.gui

import ca.endpath.ohthewayyoullgo.OhTheWayYoullGo
import ca.endpath.ohthewayyoullgo.backend.Map
import ca.endpath.ohthewayyoullgo.backend.Waypoint
import de.erdbeerbaerlp.guilib.components.Button
import de.erdbeerbaerlp.guilib.components.Label
import de.erdbeerbaerlp.guilib.gui.ExtendedScreen
import net.minecraft.client.gui.screen.Screen

class WaypointGUI(parentGui: Screen?) : ExtendedScreen(parentGui) {

    private lateinit var guiTitle : Label;
    private lateinit var deleteButton : Button;
    private lateinit var addButton : Button;

    override fun doesEscCloseGui(): Boolean = true

    override fun buildGui() {

        guiTitle = Label("Waypoints", width/2, 15);
        addButton = Button(width/3, height-30, width/6, "Add Waypoint")
        deleteButton = Button(width/3*2, height-30, width/6,"Delete Waypoint")

        addButton.setClickListener(addWaypoint(new Waypoint(Map.getWaypointManager(), OhTheWayYoullGo.)))

        addAllComponents(guiTitle, addButton, deleteButton)
    }

    override fun doesGuiPauseGame(): Boolean = false

    override fun updateGui() {
        guiTitle.setPosition(width/2, 15)
        addButton.setPosition(width/3, height-30)
        deleteButton.setPosition(width/3*2, height-30)
        addButton.width = width/6
        deleteButton.width = width/6
    }

    inline fun addWaypoint(waypoint : Waypoint) : Thread {
        return Thread {TODO("NOT IMPLEMENTED YET")}
    }

    inline fun deleteWaypoint(waypoint : Waypoint) : Thread {
        return Thread {TODO("NOT IMPLEMENTED YET")}
    }

}