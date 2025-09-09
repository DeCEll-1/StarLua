-- TYPE SHIP_SYSTEM
-- PACKAGE PACKAGE_PATH
-- NAME CLASS_NAME
return {

    --- Called every frame the ship system is active.
    ---@param stats MutableShipStatsAPI  The stats object representing the ship's mutable stats.
    ---@param id string                  A unique ID for modifying stats, often based on the ship.
    ---@param state string               The current state of the system: "IN", "ACTIVE", or "OUT".
    ---@param effectLevel number         A float from 0 to 1 representing how active the system is.
    apply = function(stats, id, state, effectLevel)

    end,

    --- Called when the system is deactivated to clean up stat modifications.
    ---@param stats MutableShipStatsAPI  The stats object for the ship.
    ---@param id string                  The ID used to identify modifications to be removed.
    unapply = function(stats, id)

    end,

    --- Returns status information shown next to the system icon.
    ---@param index integer             The status line index (0 = first line).
    ---@param state string              The current system state.
    ---@param effectLevel number        A float from 0 to 1 representing the current effect intensity.
    ---@return table|nil                A table with `text` and `active` fields or nil to hide the line.
    getStatusData = function(index, state, effectLevel)
        return nil
    end,

    --- The text next to the cooldown bar for the ship system.
    ---@param system ShipSystemAPI      The ship system instance.
    ---@param ship ShipAPI              The ship that owns this system.
    ---@return string|nil               Text to display next to the system cooldown bar.
    getInfoText = function(system, ship)
        return nil
    end,

    --- Determines if the system is usable under custom conditions.
    ---@param system ShipSystemAPI      The system in question.
    ---@param ship ShipAPI              The ship attempting to use the system.
    ---@return boolean                  True if the system can be activated.
    isUsable = function(system, ship)
        return true
    end,

    --- Overrides the system's active duration.
    ---@param ship ShipAPI              The ship using the system.
    ---@return number                   Duration in seconds, or -1 to use default.
    getActiveOverride = function(ship)
        return -1
    end,

    --- Overrides the system's charge-up (IN) duration.
    ---@param ship ShipAPI              The ship using the system.
    ---@return number                   Duration in seconds, or -1 to use default.
    getInOverride = function(ship)
        return -1
    end,

    --- Overrides the system's deactivation (OUT) duration.
    ---@param ship ShipAPI              The ship using the system.
    ---@return number                   Duration in seconds, or -1 to use default.
    getOutOverride = function(ship)
        return -1
    end,

    --- Overrides the system's regeneration rate.
    ---@param ship ShipAPI              The ship using the system.
    ---@return number                   Rate in charges/sec, or -1 to use default.
    getRegenOverride = function(ship)
        return -1
    end,

    --- Overrides how many times the system can be used.
    ---@param ship ShipAPI              The ship using the system.
    ---@return integer                  Number of uses, or -1 to use default.
    getUsesOverride = function(ship)
        return -1
    end,

    --- Overrides the text at the left of the cooldown bar.
    ---@param state string              Current system state.
    ---@param effectLevel number        A float from 0 to 1 representing effect intensity.
    ---@return string|nil               Text to display, or nil to hide.
    getDisplayNameOverride = function(state, effectLevel)
        return nil
    end
}
