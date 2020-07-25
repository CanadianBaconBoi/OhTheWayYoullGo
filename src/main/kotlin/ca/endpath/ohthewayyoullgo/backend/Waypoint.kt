package ca.endpath.ohthewayyoullgo.backend

import net.minecraft.util.math.BlockPos

class Waypoint {
    private var _position : BlockPos
    private var _name : String
    private val manager: WaypointManager

    public var position : BlockPos
    get() = _position
    set(value){

        manager.waypointUpdated_position(this, value)
        this._position = value
    }
    public var name : String
    get() = _name
    set(value) {
        manager.waypointUpdated_name(this, value)
        this._name = value
    }

    constructor(manager: WaypointManager, position: BlockPos, name: String)
    {
        this.manager = manager
        this._name = name
        this._position = position
    }

}