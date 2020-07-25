package ca.endpath.ohthewayyoullgo.gui

import ca.endpath.ohthewayyoullgo.backend.Map
import ca.endpath.ohthewayyoullgo.backend.Waypoint
import de.erdbeerbaerlp.guilib.components.*
import de.erdbeerbaerlp.guilib.gui.ExtendedScreen
import net.minecraft.client.Minecraft
import net.minecraft.entity.Entity
import net.minecraft.util.math.BlockPos
import kotlin.reflect.KFunction

class WaypointGUI : ExtendedScreen(Minecraft.getInstance().currentScreen) {

    private lateinit var guiTitle : Label
    private lateinit var addButton : Button
    private lateinit var deleteButton : Button
    private lateinit var nameBox : TextField
    private lateinit var waypointList : ScrollPanel
    private lateinit var waypoints : HashMap<ToggleButton, Waypoint>
    private var activeWaypoint: ToggleButton? = null

    override fun doesEscCloseGui(): Boolean = true

    override fun buildGui() {

        guiTitle = Label("Waypoints", width/2, 15)
        addButton = Button(width/4, height-30, width/8, "Add Waypoint")
        deleteButton = Button(width/4*3, height-30, width/8,"Delete Waypoint")
        nameBox = TextField(width/2, height-30, width/3)
        waypointList = ScrollPanel(width/2, (height/2)-height/8, (width*0.9).toInt(), height/4*3)
        waypoints = HashMap()

        reloadWaypointList()

        waypointList.addAllComponents(*waypoints.keys.toTypedArray())

        addButton.setClickListener(addWaypoint((Minecraft.getInstance().player as Entity)::getPosition, nameBox::getText))
        deleteButton.setClickListener(::activeWaypoint.get()?.let { deleteWaypoint(it) })

        addAllComponents(guiTitle, addButton, deleteButton)
    }

    override fun doesGuiPauseGame(): Boolean = false

    override fun updateGui() {
        guiTitle.setPosition(width/2, 15)
        addButton.setPosition(width/4, height-30)
        deleteButton.setPosition(width/4*3, height-30)
        nameBox.setPosition(width/2, height-30)
        waypointList.setPosition(width/2, (height/2)-height/8)

        addButton.width = width/6
        deleteButton.width = width/6
    }

    private fun reloadWaypointList() {
        waypoints.clear()
        Map.getWaypointManager().iterate { wp ->
            run {
                val button = ToggleButton(0, 0, (width * 0.9).toInt(), 50, "")
                button.drawString(Minecraft.getInstance().fontRenderer, if(button.value) "Enabled" else "Disabled", (width * 0.9).toInt()+5, 25, 0xFF)
                button.drawCenteredString(Minecraft.getInstance().fontRenderer, wp.name, width/2, 25, 0xFF)
                button.drawRightAlignedString(Minecraft.getInstance().fontRenderer, "${wp.position.x},${wp.position.y},${wp.position.z}", (width * 0.9).toInt()-5, 25, 0xFF)
                button.setClickListener(selectWaypoint(button))
                waypoints[button] = wp
            }
        }
    }

    private fun selectWaypoint(button: ToggleButton): Thread {
        return Thread {
            activeWaypoint?.value  = false
            button.value = true
            activeWaypoint = button
        }
    }

    private fun addWaypoint(pos: KFunction<BlockPos>, name: KFunction<String>) : Thread {
        return Thread {
            Map.getWaypointManager().createWaypoint(pos.call(), name.call())
            reloadWaypointList()
        }
    }

    private fun deleteWaypoint(waypoint : ToggleButton) : Thread {
        return Thread {
            waypoints[waypoint]?.removeWaypoint()
            reloadWaypointList()
            activeWaypoint = null
        }
    }

}