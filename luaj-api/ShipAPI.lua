---@class ShipAPI
local ShipAPI = {}

---@return string
function ShipAPI:getId() return "" end

---@return ShipSystemAPI
function ShipAPI:getSystem() return {} end

---@return ShipEngineControllerAPI
function ShipAPI:getEngineController() return {} end

---@param source any
---@param color Color
---@param level number
---@param copies integer
---@param delay number
---@param range number
function ShipAPI:setJitter(source, color, level, copies, delay, range) end

---@param source any
---@param color Color
---@param level number
---@param intensity number
---@param delay number
---@param range number
function ShipAPI:setJitterUnder(source, color, level, intensity, delay, range) end
