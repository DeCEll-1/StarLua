---@class ShipSystemAPI
local ShipSystemAPI = {}
---@return string
function ShipSystemAPI:getId() return "" end

---@return number
function ShipSystemAPI:getCooldownRemaining() return -1 end

---@return boolean
function ShipSystemAPI:isOutOfAmmo() return false end

--- * @return true if the system is charging up, down, or is on.
---@return boolean
function ShipSystemAPI:isActive() return false end

---@return boolean
function ShipSystemAPI:isCoolingDown() return false end

---@return integer
function ShipSystemAPI:getAmmo() return -1 end

---@return number
function ShipSystemAPI:getFluxPerUse() return -1 end

---@return number
function ShipSystemAPI:getFluxPerSecond() return -1 end

---@return string
function ShipSystemAPI:getDisplayName() return "" end

--- * @return true if the system is charging up or is on.
---@return boolean
function ShipSystemAPI:isOn() return false end

---@return boolean
function ShipSystemAPI:isChargeup() return false end

---@return boolean
function ShipSystemAPI:isChargedown() return false end

---@return boolean
function ShipSystemAPI:isStateActive() return false end

---@return integer
function ShipSystemAPI:getMaxAmmo() return -1 end

---@param ammo integer
function ShipSystemAPI:setAmmo(ammo) return {} end

---@return number
function ShipSystemAPI:getEffectLevel() return -1 end

---@return number
function ShipSystemAPI:getCooldown() return -1 end

---@param fluxPerUse number
function ShipSystemAPI:setFluxPerUse(fluxPerUse) return {} end

---@param fluxPerSecond number
function ShipSystemAPI:setFluxPerSecond(fluxPerSecond) return {} end

---@return SystemState
function ShipSystemAPI:getState() return {} end

---@return number
function ShipSystemAPI:getChargeUpDur() return -1 end

---@return number
function ShipSystemAPI:getChargeDownDur() return -1 end

---@return number
function ShipSystemAPI:getChargeActiveDur() return -1 end

---@return ShipSystemSpecAPI
function ShipSystemAPI:getSpecAPI() return {} end

function ShipSystemAPI:deactivate() return {} end

---@param remaining number
function ShipSystemAPI:setCooldownRemaining(remaining) return {} end

---@param cooldown number
function ShipSystemAPI:setCooldown(cooldown) return {} end

---@return Vector2f
function ShipSystemAPI:getTargetLoc() return {} end

---@param state SystemState
---@param progress number
function ShipSystemAPI:forceState(state, progress) return {} end

---@return number
function ShipSystemAPI:getAmmoPerSecond() return -1 end

---@return number
function ShipSystemAPI:getAmmoReloadProgress() return -1 end

---@param progress number
function ShipSystemAPI:setAmmoReloadProgress(progress) return {} end

---@return boolean
function ShipSystemAPI:canBeActivated() return false end

---@return ShipSystemStatsScript
function ShipSystemAPI:getScript() return {} end

_G.ShipSystemAPI = ShipSystemAPI
