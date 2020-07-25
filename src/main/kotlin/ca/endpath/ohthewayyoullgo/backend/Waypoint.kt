package ca.endpath.ohthewayyoullgo.backend

import net.minecraft.util.math.BlockPos

class Waypoint {
    private var _position : BlockPos
    private var _name : String
    @Transient
    private val manager: WaypointManager

    public var position : BlockPos
    get() = _position
    set(value) {
        manager.waypointPositionUpdate(this, value)
        this._position = value
    }

    public var name : String
    get() = _name
    set(value) {
        manager.waypointNameUpdate(this, value)
        this._name = value
    }

    constructor(manager: WaypointManager, position: BlockPos, name: String)
    {
        this.manager = manager
        this._name = name
        this._position = position
    }

    public fun removeWaypoint() {
        manager.waypointRemovedUpdate(this)
    }
}