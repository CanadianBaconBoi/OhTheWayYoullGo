package ca.endpath.ohthewayyoullgo.backend

object Map {
    private var waypointManager = WaypointManager(null)
    private var worldId: String = ""

    public final fun isMapLoaded() : Boolean {
        return worldId != ""
    }

    fun getWaypointManager() : WaypointManager = waypointManager

    // loads a waypoint and map
    // WorldID : Unique id of the server hash
    @Synchronized
    public final fun loadMap(WorldID: String) {
        this.worldId = WorldID
        TODO("Make Proper File Lookup")
        val mapFolder = "./$WorldID"
        waypointManager = WaypointManager("$mapFolder/waypoints.bin")

    }

    @Synchronized
    public final fun saveMap() {
        val mapFolder = "./$worldId"
        waypointManager.save("$mapFolder/waypoints.bin")
    }


    private final fun onHandleConnection()
    {

    }
}