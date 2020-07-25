package ca.endpath.ohthewayyoullgo.backend

object Map {
    private var waypointManager: WaypointManager = WaypointManager(null)

    // loads a waypoint and map
    // WorldID : Unique id of the server hash
    public final fun Load(WorldID: String) {
        TODO("Make Proper File Lookup")
        val mapFolder = "./$WorldID"

        waypointManager = WaypointManager("$mapFolder/waypoints.bin")


    }
}