---@class StatMod
local StatMod = {}
---@param source string
---@param type StatModType
---@param value number
---@return StatMod
function StatMod.new(source, type, value) return {} end

---@param source string
---@param type StatModType
---@param value number
---@param desc string
---@return StatMod
function StatMod.new(source, type, value, desc) return {} end

---@return string
function StatMod:getSource() return "" end

---@return StatModType
function StatMod:getType() return {} end

---@return number
function StatMod:getValue() return -1 end

---@return string
function StatMod:getDesc() return "" end

_G.StatMod = StatMod
