---@class ShipEngineControllerAPI
local ShipEngineControllerAPI = {}
---@return boolean
function ShipEngineControllerAPI:isAccelerating() return false end

---@return boolean
function ShipEngineControllerAPI:isAcceleratingBackwards() return false end

---@return boolean
function ShipEngineControllerAPI:isDecelerating() return false end

---@return boolean
function ShipEngineControllerAPI:isTurningLeft() return false end

---@return boolean
function ShipEngineControllerAPI:isTurningRight() return false end

---@return boolean
function ShipEngineControllerAPI:isStrafingLeft() return false end

---@return boolean
function ShipEngineControllerAPI:isStrafingRight() return false end

---@return boolean
function ShipEngineControllerAPI:isIdle() return false end

---@return List<ShipEngineAPI>
function ShipEngineControllerAPI:getShipEngines() return {} end

---@param key Object
---@param other Color
---@param contrailColor Color
---@param effectLevel number
---@param maxBlend number
function ShipEngineControllerAPI:fadeToOtherColor(key, other, contrailColor, effectLevel, maxBlend) return {} end

---@param key Object
---@param extendLengthFraction number
---@param extendWidthFraction number
---@param extendGlowFraction number
function ShipEngineControllerAPI:extendFlame(key, extendLengthFraction, extendWidthFraction, extendGlowFraction) return {} end

function ShipEngineControllerAPI:forceFlameout() return {} end

---@param suppressFloaty boolean
function ShipEngineControllerAPI:forceFlameout(suppressFloaty) return {} end

---@return number
function ShipEngineControllerAPI:getMaxSpeedWithoutBoost() return -1 end

---@return number
function ShipEngineControllerAPI:computeDisabledFraction() return -1 end

---@return number
function ShipEngineControllerAPI:getFlameoutFraction() return -1 end

---@param forceShowFloaty boolean
function ShipEngineControllerAPI:computeEffectiveStats(forceShowFloaty) return {} end

---@return boolean
function ShipEngineControllerAPI:isFlamedOut() return false end

---@return boolean
function ShipEngineControllerAPI:isDisabled() return false end

---@return boolean
function ShipEngineControllerAPI:isFlamingOut() return false end

--- * How extended the engine flame is. 1.0 = maximum, 0 = not at all, 0.4 = default idle level.
--- * @param slot
--- * @param level
---@param slot EngineSlotAPI
---@param level number
function ShipEngineControllerAPI:setFlameLevel(slot, level) return {} end

---@return ValueShifterAPI
function ShipEngineControllerAPI:getExtendLengthFraction() return {} end

---@return ValueShifterAPI
function ShipEngineControllerAPI:getExtendWidthFraction() return {} end

---@return ValueShifterAPI
function ShipEngineControllerAPI:getExtendGlowFraction() return {} end

function ShipEngineControllerAPI:forceShowAccelerating() return {} end

---@return ColorShifterAPI
function ShipEngineControllerAPI:getFlameColorShifter() return {} end

---@return number
function ShipEngineControllerAPI:getTurnDeceleration() return -1 end

-- ShipEngiineAPI

--- * @return location, in absolute coordinates.
---@return Vector2f
function ShipEngineControllerAPI:getLocation() return {} end

--- * @return whether this engine is currently engaged (some engines are only "active" when a ship system is in use, for example.)
---@return boolean
function ShipEngineControllerAPI:isActive() return false end

--- * @return whether this engine is only shown when the ship system is active.
---@return boolean
function ShipEngineControllerAPI:isSystemActivated() return false end

---@return string
function ShipEngineControllerAPI:getStyleId() return "" end

---@return boolean
function ShipEngineControllerAPI:isDisabled() return false end

function ShipEngineControllerAPI:disable() return {} end

---@param permanent boolean
function ShipEngineControllerAPI:disable(permanent) return {} end

--- * Fraction of total engine power this engine provides.
--- * @return
---@return number
function ShipEngineControllerAPI:getContribution() return -1 end

---@return boolean
function ShipEngineControllerAPI:isPermanentlyDisabled() return false end

---@param damAmount number
---@param source Object
function ShipEngineControllerAPI:applyDamage(damAmount, source) return {} end

---@return number
function ShipEngineControllerAPI:getMaxHitpoints() return -1 end

---@return number
function ShipEngineControllerAPI:getHitpoints() return -1 end

---@return EngineSlotAPI
function ShipEngineControllerAPI:getEngineSlot() return {} end

---@param hp number
function ShipEngineControllerAPI:setHitpoints(hp) return {} end

---@return Color
function ShipEngineControllerAPI:getEngineColor() return {} end

---@return Color
function ShipEngineControllerAPI:getContrailColor() return {} end

function ShipEngineControllerAPI:repair() return {} end

--


_G.ShipEngineControllerAPI = ShipEngineControllerAPI
