--- * @author Alex Mosolov
--- * Copyright 2012 Fractal Softworks, LLC
---@class StatBonus
local StatBonus = {}
---@return boolean
function StatBonus:isUnmodified() return false end

---@param source string
---@return StatMod
function StatBonus:getFlatBonus(source) return {} end

---@param source string
---@return StatMod
function StatBonus:getPercentBonus(source) return {} end

---@param source string
---@return StatMod
function StatBonus:getMultBonus(source) return {} end

---@param source string
---@param value number
function StatBonus:modifyFlat(source, value) return {} end

---@param source string
---@param value number
---@param desc string
function StatBonus:modifyFlat(source, value, desc) return {} end

---@param source string
---@param value number
function StatBonus:modifyPercent(source, value) return {} end

---@param source string
---@param value number
---@param desc string
function StatBonus:modifyPercent(source, value, desc) return {} end

---@param source string
---@param value number
---@param desc string
function StatBonus:modifyPercentAlways(source, value, desc) return {} end

---@param source string
---@param value number
function StatBonus:modifyMult(source, value) return {} end

---@param source string
---@param value number
---@param desc string
function StatBonus:modifyMult(source, value, desc) return {} end

---@param source string
---@param value number
---@param desc string
function StatBonus:modifyMultAlways(source, value, desc) return {} end

---@param source string
---@param value number
---@param desc string
function StatBonus:modifyFlatAlways(source, value, desc) return {} end

function StatBonus:unmodify() return {} end

---@param source string
function StatBonus:unmodify(source) return {} end

---@param source string
function StatBonus:unmodifyFlat(source) return {} end

---@param source string
function StatBonus:unmodifyPercent(source) return {} end

---@param source string
function StatBonus:unmodifyMult(source) return {} end

---@param baseValue number
---@return number
function StatBonus:computeEffective(baseValue) return -1 end

---@return number
function StatBonus:getFlatBonus() return -1 end

--- * Returns combined percentage and multiplier modifiers.
--- * @return
---@return number
function StatBonus:getBonusMult() return -1 end

---@return number
function StatBonus:getMult() return -1 end

---@return number
function StatBonus:getPercentMod() return -1 end

---@param baseValue number
---@return boolean
function StatBonus:isPositive(baseValue) return false end

---@param baseValue number
---@return boolean
function StatBonus:isNegative(baseValue) return false end

---@param other MutableStat
function StatBonus:applyMods(other) return {} end

_G.StatBonus = StatBonus
