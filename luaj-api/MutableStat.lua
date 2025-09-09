--- * @author Alex Mosolov
--- * Copyright 2012 Fractal Softworks, LLC
---@class MutableStat
local MutableStat = {}
---@param base number
---@return MutableStat
function MutableStat.new(base) return {} end

---@return MutableStat
function MutableStat:createCopy() return {} end

---@param other MutableStat
function MutableStat:applyMods(other) return {} end

---@return boolean
function MutableStat:isUnmodified() return false end

---@param source string
---@return StatMod
function MutableStat:getFlatStatMod(source) return {} end

---@param source string
---@return StatMod
function MutableStat:getFlatAfterMultStatMod(source) return {} end

---@param source string
---@return StatMod
function MutableStat:getPercentStatMod(source) return {} end

---@param source string
---@return StatMod
function MutableStat:getMultStatMod(source) return {} end

---@param source string
---@param value number
function MutableStat:modifyFlatAfterMult(source, value) return {} end

---@param source string
---@param value number
---@param desc string
function MutableStat:modifyFlatAfterMult(source, value, desc) return {} end

---@param source string
---@param value number
function MutableStat:modifyFlat(source, value) return {} end

---@param source string
---@param value number
---@param desc string
function MutableStat:modifyFlat(source, value, desc) return {} end

---@param source string
---@param value number
function MutableStat:modifyPercent(source, value) return {} end

---@param source string
---@param value number
---@param desc string
function MutableStat:modifyPercent(source, value, desc) return {} end

---@param source string
---@param value number
---@param desc string
function MutableStat:modifyPercentAlways(source, value, desc) return {} end

---@param source string
---@param value number
function MutableStat:modifyMult(source, value) return {} end

---@param source string
---@param value number
---@param desc string
function MutableStat:modifyMult(source, value, desc) return {} end

---@param source string
---@param value number
---@param desc string
function MutableStat:modifyMultAlways(source, value, desc) return {} end

---@param source string
---@param value number
---@param desc string
function MutableStat:modifyFlatAlways(source, value, desc) return {} end

function MutableStat:unmodify() return {} end

---@param source string
function MutableStat:unmodify(source) return {} end

---@param source string
function MutableStat:unmodifyFlat(source) return {} end

---@param source string
function MutableStat:unmodifyPercent(source) return {} end

---@param source string
function MutableStat:unmodifyMult(source) return {} end

---@return number
function MutableStat:getFlatMod() return -1 end

---@return number
function MutableStat:getPercentMod() return -1 end

---@return number
function MutableStat:getMult() return -1 end

---@return number
function MutableStat:computeMultMod() return -1 end

---@return number
function MutableStat:getModifiedValue() return -1 end

---@return integer
function MutableStat:getModifiedInt() return -1 end

---@return number
function MutableStat:getBaseValue() return -1 end

---@param base number
function MutableStat:setBaseValue(base) return {} end

---@return boolean
function MutableStat:isPositive() return false end

---@return boolean
function MutableStat:isNegative() return false end

_G.MutableStat = MutableStat
