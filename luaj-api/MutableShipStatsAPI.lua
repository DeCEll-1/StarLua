---@class MutableShipStatsAPI
local MutableShipStatsAPI = {}
--- * Only returns non-null during combat.
--- * @return entity (ShipAPI, MissileAPI, something else) if these mutable stats have one associated with them, null otherwise.
---@return CombatEntityAPI
function MutableShipStatsAPI:getEntity() return {} end

--- * Could be null, or a faked-up one for the stats of fighter wings deployed in combat.
---@return FleetMemberAPI
function MutableShipStatsAPI:getFleetMember() return {} end

---@return MutableStat
function MutableShipStatsAPI:getMaxSpeed() return {} end

---@return MutableStat
function MutableShipStatsAPI:getAcceleration() return {} end

---@return MutableStat
function MutableShipStatsAPI:getDeceleration() return {} end

---@return MutableStat
function MutableShipStatsAPI:getMaxTurnRate() return {} end

---@return MutableStat
function MutableShipStatsAPI:getTurnAcceleration() return {} end

---@return MutableStat
function MutableShipStatsAPI:getFluxCapacity() return {} end

---@return MutableStat
function MutableShipStatsAPI:getFluxDissipation() return {} end

--- * Check made once per second on average. Range is 0 (no chance) to 1 (100% chance).
---@return MutableStat
function MutableShipStatsAPI:getWeaponMalfunctionChance() return {} end

--- * Check made once per second on average. Range is 0 (no chance) to 1 (100% chance).
---@return MutableStat
function MutableShipStatsAPI:getEngineMalfunctionChance() return {} end

--- * Chance that a regular malfunction is critical (i.e. deals damage and permanently disables weapon or engine).
--- * More than half the engine nozzles can not suffer a critical malfunction.
--- * @return
---@return MutableStat
function MutableShipStatsAPI:getCriticalMalfunctionChance() return {} end

---@return MutableStat
function MutableShipStatsAPI:getShieldMalfunctionChance() return {} end

---@return MutableStat
function MutableShipStatsAPI:getShieldMalfunctionFluxLevel() return {} end

--- * Base value is 0, modified by crew etc. modifyPercent will do nothing since the base value is 0.
--- * Range is 0 to 1.
--- * @return
---@return MutableStat
function MutableShipStatsAPI:getMaxCombatReadiness() return {} end

--- * As percentage, i.e 0 to 100.
--- * @return
---@return StatBonus
function MutableShipStatsAPI:getCRPerDeploymentPercent() return {} end

--- * In seconds.
--- * @return
---@return StatBonus
function MutableShipStatsAPI:getPeakCRDuration() return {} end

--- * As percentage, i.e 0 to 100.
--- * @return
---@return StatBonus
function MutableShipStatsAPI:getCRLossPerSecondPercent() return {} end

--- * Use getEmpDamageTaken() instead.
--- * @return
---@return MutableStat
function MutableShipStatsAPI:getFluxDamageTakenMult() return {} end

---@return MutableStat
function MutableShipStatsAPI:getEmpDamageTakenMult() return {} end

---@return MutableStat
function MutableShipStatsAPI:getHullDamageTakenMult() return {} end

---@return MutableStat
function MutableShipStatsAPI:getArmorDamageTakenMult() return {} end

---@return MutableStat
function MutableShipStatsAPI:getShieldDamageTakenMult() return {} end

---@return MutableStat
function MutableShipStatsAPI:getEngineDamageTakenMult() return {} end

---@return MutableStat
function MutableShipStatsAPI:getWeaponDamageTakenMult() return {} end

--- * Applies to damage taken by hull and armor.
--- * @return
---@return MutableStat
function MutableShipStatsAPI:getBeamDamageTakenMult() return {} end

--- * Applies to damage taken by hull and armor.
--- * @return
---@return MutableStat
function MutableShipStatsAPI:getMissileDamageTakenMult() return {} end

--- * Applies to damage taken by hull and armor.
--- * @return
---@return MutableStat
function MutableShipStatsAPI:getProjectileDamageTakenMult() return {} end

--- * Applies to damage taken by hull and armor.
--- * @return
---@return MutableStat
function MutableShipStatsAPI:getEnergyDamageTakenMult() return {} end

--- * Applies to damage taken by hull and armor.
--- * @return
---@return MutableStat
function MutableShipStatsAPI:getKineticDamageTakenMult() return {} end

--- * Applies to damage taken by hull and armor.
--- * @return
---@return MutableStat
function MutableShipStatsAPI:getHighExplosiveDamageTakenMult() return {} end

--- * Applies to damage taken by hull and armor.
--- * @return
---@return MutableStat
function MutableShipStatsAPI:getFragmentationDamageTakenMult() return {} end

--- * Applies to damage taken by shields.
--- * @return
---@return MutableStat
function MutableShipStatsAPI:getBeamShieldDamageTakenMult() return {} end

--- * Applies to damage taken by shields.
--- * @return
---@return MutableStat
function MutableShipStatsAPI:getMissileShieldDamageTakenMult() return {} end

--- * Applies to damage taken by shields.
--- * @return
---@return MutableStat
function MutableShipStatsAPI:getProjectileShieldDamageTakenMult() return {} end

--- * Applies to damage taken by shields.
--- * @return
---@return MutableStat
function MutableShipStatsAPI:getEnergyShieldDamageTakenMult() return {} end

--- * Applies to damage taken by shields.
--- * @return
---@return MutableStat
function MutableShipStatsAPI:getKineticShieldDamageTakenMult() return {} end

--- * Applies to damage taken by shields.
--- * @return
---@return MutableStat
function MutableShipStatsAPI:getHighExplosiveShieldDamageTakenMult() return {} end

--- * Applies to damage taken by shields.
--- * @return
---@return MutableStat
function MutableShipStatsAPI:getFragmentationShieldDamageTakenMult() return {} end

---@return MutableStat
function MutableShipStatsAPI:getBeamWeaponDamageMult() return {} end

---@return MutableStat
function MutableShipStatsAPI:getEnergyWeaponDamageMult() return {} end

---@return MutableStat
function MutableShipStatsAPI:getBallisticWeaponDamageMult() return {} end

---@return MutableStat
function MutableShipStatsAPI:getMissileWeaponDamageMult() return {} end

---@return StatBonus
function MutableShipStatsAPI:getEnergyWeaponFluxCostMod() return {} end

---@return StatBonus
function MutableShipStatsAPI:getBallisticWeaponFluxCostMod() return {} end

---@return StatBonus
function MutableShipStatsAPI:getMissileWeaponFluxCostMod() return {} end

---@return MutableStat
function MutableShipStatsAPI:getBeamWeaponFluxCostMult() return {} end

---@return MutableStat
function MutableShipStatsAPI:getShieldUpkeepMult() return {} end

---@return MutableStat
function MutableShipStatsAPI:getShieldAbsorptionMult() return {} end

---@return MutableStat
function MutableShipStatsAPI:getShieldTurnRateMult() return {} end

---@return MutableStat
function MutableShipStatsAPI:getShieldUnfoldRateMult() return {} end

---@return MutableStat
function MutableShipStatsAPI:getMissileRoFMult() return {} end

---@return MutableStat
function MutableShipStatsAPI:getBallisticRoFMult() return {} end

---@return MutableStat
function MutableShipStatsAPI:getEnergyRoFMult() return {} end

---@return StatBonus
function MutableShipStatsAPI:getPhaseCloakActivationCostBonus() return {} end

---@return StatBonus
function MutableShipStatsAPI:getPhaseCloakUpkeepCostBonus() return {} end

---@return StatBonus
function MutableShipStatsAPI:getEnergyWeaponRangeBonus() return {} end

---@return StatBonus
function MutableShipStatsAPI:getBallisticWeaponRangeBonus() return {} end

---@return StatBonus
function MutableShipStatsAPI:getMissileWeaponRangeBonus() return {} end

---@return StatBonus
function MutableShipStatsAPI:getBeamWeaponRangeBonus() return {} end

--- * Does not include beam weapons, which have a separate bonus.
--- * @return
---@return StatBonus
function MutableShipStatsAPI:getWeaponTurnRateBonus() return {} end

---@return StatBonus
function MutableShipStatsAPI:getBeamWeaponTurnRateBonus() return {} end

---@return StatBonus
function MutableShipStatsAPI:getRepairTimeBonus() return {} end

---@return MutableStat
function MutableShipStatsAPI:getCombatEngineRepairTimeMult() return {} end

---@return MutableStat
function MutableShipStatsAPI:getCombatWeaponRepairTimeMult() return {} end

---@return StatBonus
function MutableShipStatsAPI:getWeaponHealthBonus() return {} end

---@return StatBonus
function MutableShipStatsAPI:getEngineHealthBonus() return {} end

---@return StatBonus
function MutableShipStatsAPI:getArmorBonus() return {} end

---@return StatBonus
function MutableShipStatsAPI:getHullBonus() return {} end

---@return StatBonus
function MutableShipStatsAPI:getShieldArcBonus() return {} end

---@return StatBonus
function MutableShipStatsAPI:getBallisticAmmoBonus() return {} end

---@return StatBonus
function MutableShipStatsAPI:getEnergyAmmoBonus() return {} end

---@return StatBonus
function MutableShipStatsAPI:getMissileAmmoBonus() return {} end

---@return MutableStat
function MutableShipStatsAPI:getEccmChance() return {} end

---@return MutableStat
function MutableShipStatsAPI:getMissileGuidance() return {} end

---@return StatBonus
function MutableShipStatsAPI:getSightRadiusMod() return {} end

---@return MutableStat
function MutableShipStatsAPI:getHullCombatRepairRatePercentPerSecond() return {} end

---@return MutableStat
function MutableShipStatsAPI:getMaxCombatHullRepairFraction() return {} end

---@return MutableStat
function MutableShipStatsAPI:getHullRepairRatePercentPerSecond() return {} end

---@return MutableStat
function MutableShipStatsAPI:getMaxHullRepairFraction() return {} end

--- * For hit strength only.
--- * @return
---@return StatBonus
function MutableShipStatsAPI:getEffectiveArmorBonus() return {} end

--- * Affects damage reduction by target's armor.
--- * @return
---@return StatBonus
function MutableShipStatsAPI:getHitStrengthBonus() return {} end

---@return MutableStat
function MutableShipStatsAPI:getDamageToTargetEnginesMult() return {} end

---@return MutableStat
function MutableShipStatsAPI:getDamageToTargetWeaponsMult() return {} end

---@return MutableStat
function MutableShipStatsAPI:getDamageToTargetShieldsMult() return {} end

---@return MutableStat
function MutableShipStatsAPI:getDamageToTargetHullMult() return {} end

--- * Clamped to a maximum of 1. Green crew at 0.
--- * @return
---@return MutableStat
function MutableShipStatsAPI:getAutofireAimAccuracy() return {} end

---@return MutableStat
function MutableShipStatsAPI:getMaxRecoilMult() return {} end

---@return MutableStat
function MutableShipStatsAPI:getRecoilPerShotMult() return {} end

---@return MutableStat
function MutableShipStatsAPI:getRecoilDecayMult() return {} end

---@return StatBonus
function MutableShipStatsAPI:getOverloadTimeMod() return {} end

---@return MutableStat
function MutableShipStatsAPI:getZeroFluxSpeedBoost() return {} end

---@return MutableStat
function MutableShipStatsAPI:getZeroFluxMinimumFluxLevel() return {} end

---@return MutableStat
function MutableShipStatsAPI:getCrewLossMult() return {} end

---@return MutableStat
function MutableShipStatsAPI:getPhaseCloakUpkeepMult() return {} end

---@return MutableStat
function MutableShipStatsAPI:getHardFluxDissipationFraction() return {} end

---@return StatBonus
function MutableShipStatsAPI:getFuelMod() return {} end

---@return StatBonus
function MutableShipStatsAPI:getFuelUseMod() return {} end

---@return StatBonus
function MutableShipStatsAPI:getMinCrewMod() return {} end

---@return StatBonus
function MutableShipStatsAPI:getMaxCrewMod() return {} end

---@return StatBonus
function MutableShipStatsAPI:getCargoMod() return {} end

---@return StatBonus
function MutableShipStatsAPI:getHangarSpaceMod() return {} end

---@return StatBonus
function MutableShipStatsAPI:getMissileMaxSpeedBonus() return {} end

---@return StatBonus
function MutableShipStatsAPI:getMissileAccelerationBonus() return {} end

---@return StatBonus
function MutableShipStatsAPI:getMissileMaxTurnRateBonus() return {} end

---@return StatBonus
function MutableShipStatsAPI:getMissileTurnAccelerationBonus() return {} end

---@return MutableStat
function MutableShipStatsAPI:getProjectileSpeedMult() return {} end

---@return MutableStat
function MutableShipStatsAPI:getVentRateMult() return {} end

---@return MutableStat
function MutableShipStatsAPI:getBaseSupplyUsePerDay() return {} end

---@return MutableStat
function MutableShipStatsAPI:getBaseRepairRatePercentPerDay() return {} end

---@return MutableStat
function MutableShipStatsAPI:getBaseCRRecoveryRatePercentPerDay() return {} end

---@return MutableStat
function MutableShipStatsAPI:getMaxBurnLevel() return {} end

--- * Only applicable for ships with flight decks. Modifies the amount of time it
--- * takes a flight deck to spawn a replacement fighter.
--- * @return
---@return MutableStat
function MutableShipStatsAPI:getFighterRefitTimeMult() return {} end

---@return MutableStat
function MutableShipStatsAPI:getRepairRatePercentPerDay() return {} end

---@return MutableStat
function MutableShipStatsAPI:getSensorProfile() return {} end

---@return MutableStat
function MutableShipStatsAPI:getSensorStrength() return {} end

---@return DynamicStatsAPI
function MutableShipStatsAPI:getDynamic() return {} end

---@return MutableStat
function MutableShipStatsAPI:getSuppliesToRecover() return {} end

---@return MutableStat
function MutableShipStatsAPI:getSuppliesPerMonth() return {} end

---@return MutableStat
function MutableShipStatsAPI:getWeaponRangeThreshold() return {} end

---@return MutableStat
function MutableShipStatsAPI:getWeaponRangeMultPastThreshold() return {} end

---@return MutableStat
function MutableShipStatsAPI:getTimeMult() return {} end

---@return StatBonus
function MutableShipStatsAPI:getBeamPDWeaponRangeBonus() return {} end

---@return StatBonus
function MutableShipStatsAPI:getNonBeamPDWeaponRangeBonus() return {} end

---@return MutableStat
function MutableShipStatsAPI:getMinArmorFraction() return {} end

---@return MutableStat
function MutableShipStatsAPI:getMaxArmorDamageReduction() return {} end

---@return MutableStat
function MutableShipStatsAPI:getNumFighterBays() return {} end

---@return StatBonus
function MutableShipStatsAPI:getMissileHealthBonus() return {} end

---@return StatBonus
function MutableShipStatsAPI:getPhaseCloakCooldownBonus() return {} end

---@return StatBonus
function MutableShipStatsAPI:getSystemCooldownBonus() return {} end

---@return StatBonus
function MutableShipStatsAPI:getSystemFluxCostBonus() return {} end

---@return StatBonus
function MutableShipStatsAPI:getSystemRegenBonus() return {} end

---@return StatBonus
function MutableShipStatsAPI:getSystemUsesBonus() return {} end

---@return StatBonus
function MutableShipStatsAPI:getSystemRangeBonus() return {} end

---@return MutableStat
function MutableShipStatsAPI:getKineticArmorDamageTakenMult() return {} end

---@return MutableStat
function MutableShipStatsAPI:getDamageToFighters() return {} end

---@return MutableStat
function MutableShipStatsAPI:getDamageToMissiles() return {} end

---@return MutableStat
function MutableShipStatsAPI:getDamageToFrigates() return {} end

---@return MutableStat
function MutableShipStatsAPI:getDamageToDestroyers() return {} end

---@return MutableStat
function MutableShipStatsAPI:getDamageToCruisers() return {} end

---@return MutableStat
function MutableShipStatsAPI:getDamageToCapital() return {} end

---@return StatBonus
function MutableShipStatsAPI:getCriticalMalfunctionDamageMod() return {} end

---@return MutableStat
function MutableShipStatsAPI:getBreakProb() return {} end

---@return StatBonus
function MutableShipStatsAPI:getFighterWingRange() return {} end

---@return ShipVariantAPI
function MutableShipStatsAPI:getVariant() return {} end

---@return MutableStat
function MutableShipStatsAPI:getRecoilPerShotMultSmallWeaponsOnly() return {} end

---@return MutableStat
function MutableShipStatsAPI:getEnergyWeaponFluxBasedBonusDamageMagnitude() return {} end

---@return MutableStat
function MutableShipStatsAPI:getEnergyWeaponFluxBasedBonusDamageMinLevel() return {} end

---@return MutableStat
function MutableShipStatsAPI:getAllowZeroFluxAtAnyLevel() return {} end

---@return CombatListenerManagerAPI
function MutableShipStatsAPI:getListenerManager() return {} end

---@param listener Object
function MutableShipStatsAPI:addListener(listener) return {} end

---@param listener Object
function MutableShipStatsAPI:removeListener(listener) return {} end

function MutableShipStatsAPI:removeListenerOfClass() return {} end

---@param listener Object
---@return boolean
function MutableShipStatsAPI:hasListener(listener) return {} end

---@return boolean
function MutableShipStatsAPI:hasListenerOfClass() return {} end

---@return MutableStat
function MutableShipStatsAPI:getBallisticProjectileSpeedMult() return {} end

---@return MutableStat
function MutableShipStatsAPI:getEnergyProjectileSpeedMult() return {} end

---@return MutableStat
function MutableShipStatsAPI:getMissileAmmoRegenMult() return {} end

---@return MutableStat
function MutableShipStatsAPI:getEnergyAmmoRegenMult() return {} end

---@return MutableStat
function MutableShipStatsAPI:getBallisticAmmoRegenMult() return {} end

---@return MutableStat
function MutableShipStatsAPI:getShieldSoftFluxConversion() return {} end

---@return StatBonus
function MutableShipStatsAPI:getBeamSpeedMod() return {} end

_G.MutableShipStatsAPI = MutableShipStatsAPI
