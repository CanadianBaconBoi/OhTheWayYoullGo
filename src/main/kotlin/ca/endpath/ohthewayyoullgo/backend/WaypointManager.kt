package ca.endpath.ohthewayyoullgo.backend

import net.minecraft.util.math.BlockPos
import kotlin.collections.HashSet

class WaypointManager {
    private val waypoints = HashSet<Waypoint>()
    private val file: String?

    constructor(WaypointsFile: String?) {
        file = WaypointsFile

        Iterate { wp -> wp.name = "kek" }
    }

    @Synchronized
    public fun Iterate(callback: (wp: Waypoint) -> Unit) {
        for (wp in waypoints)
            callback(wp)
    }

    @Synchronized
    public fun Clear() {
        waypoints.clear()
    }

    @Synchronized
    public fun Save() {
        if (file == null)
            TODO("LOG THE SOFT RETURN ON INVALID ATTEMPT")
            return // Soft Return

    }

    @Synchronized
    internal fun waypointUpdated_position(waypoint: Waypoint, new: BlockPos) {

    }
    @Synchronized
    internal fun waypointUpdated_name(waypoint: Waypoint, new: String) {

    }
}