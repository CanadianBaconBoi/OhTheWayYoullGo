package ca.endpath.ohthewayyoullgo.backend

import net.minecraft.util.math.BlockPos
import java.io.File
import kotlin.collections.HashSet

class WaypointManager {
    private val waypoints = HashSet<Waypoint>()

    constructor(WaypointsFile: String?) {
        if (WaypointsFile != null)
            for(i in StaticReferences.gson.fromJson(File(WaypointsFile).readText(), waypoints.javaClass))
                waypoints.add(i)
    }

    // Iterates over all waypoints
    // callback
    @Synchronized
    public fun iterate(callback: (wp: Waypoint) -> Unit) {
        for (wp in waypoints)
            callback(wp)
    }

    // Clears all waypoints
    @Synchronized
    public fun clear() {
        waypoints.clear()
    }

    // Saves the current set of waypoints back to its file
    @Synchronized
    public fun save(file: String?) {
        if (file == null) {
            //TODO("LOG THE SOFT RETURN ON INVALID ATTEMPT")
            return // Soft Return
        }
        File(file).writeText(StaticReferences.gson.toJson(waypoints, waypoints.javaClass))
    }

    @Synchronized
    public fun createWaypoint(pos: BlockPos, name: String) : Waypoint {
        val v = Waypoint(this, pos, name)
        waypoints.add(v)
        waypointAddedUpdate(v)
        return v
    }

    internal fun waypointPositionUpdate(waypoint: Waypoint, new: BlockPos) {

    }
    internal fun waypointNameUpdate(waypoint: Waypoint, new: String) {

    }
    internal fun waypointAddedUpdate(waypoint: Waypoint) {

    }
    internal fun waypointRemovedUpdate(waypoint: Waypoint) {
        waypoints.remove(waypoint) // adding code here because the event is fired from multiple places
    }
}