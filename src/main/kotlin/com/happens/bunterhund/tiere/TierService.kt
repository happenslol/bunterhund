package com.happens.bunterhund.tiere

import com.happens.bunterhund.orNull
import org.springframework.stereotype.Service

@Service
class TierService(
    val tierRepository: TierRepository
) {
    fun tierMitId(id: String): Tier? = tierRepository.findById(id).orNull()
    fun alle(): List<Tier> = tierRepository.findAll()
    fun speichern(tier: Tier): Tier = tierRepository.save(tier)
    fun loeschen(tier: Tier) = tierRepository.delete(tier)
}