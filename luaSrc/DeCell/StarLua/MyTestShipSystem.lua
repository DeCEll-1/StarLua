-- TYPE SHIP_SYSTEM
-- PACKAGE data.scripts.DeCell.StarLua
-- NAME MyTestShipSystem
JITTER_COLOR = Color.new(90, 200, 255, 50)
return {

    -- apply(stats: MutableShipStatsAPI, id: String, state: String, effectLevel: float): void
    apply = function(stats, id, state, effectLevel)
        local ship = nil
        local player = false

        local entity = stats:getEntity()

        if instanceof(entity, ShipAPI) then
            ship = cast(entity, ShipAPI)
            player = ship == Global:getCombatEngine():getPlayerShip()
            id = id .. "_" .. ship:getId()
        else
            return
        end

    end,

    -- unapply(stats: MutableShipStatsAPI, id: String): void
    unapply = function(stats, id)
    end,

    -- getStatusData(index: int, state: String, effectLevel: float): { text: String, active: boolean } or nil
    getStatusData = function(index, state, effectLevel)
        -- Example: return { text = "Status text", active = true }
        return nil
    end,

    -- the text next to the cooldown bar for the ship system
    -- getInfoText(system: ShipSystemAPI, ship: ShipAPI): String or nil
    getInfoText = function(system, ship)
        return nil
    end,

    -- isUsable(system: ShipSystemAPI, ship: ShipAPI): boolean
    isUsable = function(system, ship)
        return true
    end,

    -- getActiveOverride(ship: ShipAPI): float
    getActiveOverride = function(ship)
        return -1
    end,

    -- getInOverride(ship: ShipAPI): float
    getInOverride = function(ship)
        return -1
    end,

    -- getOutOverride(ship: ShipAPI): float
    getOutOverride = function(ship)
        return -1
    end,

    -- getRegenOverride(ship: ShipAPI): float
    getRegenOverride = function(ship)
        return -1
    end,

    -- getUsesOverride(ship: ShipAPI): int
    getUsesOverride = function(ship)
        return -1
    end,

    -- the text at the left of the cooldown bar of the ship system
    -- getDisplayNameOverride(state: String, effectLevel: float): String or nil
    getDisplayNameOverride = function(state, effectLevel)
        -- return state

        return nil
        -- return JITTER_COLOR:getBlue()
    end
}
