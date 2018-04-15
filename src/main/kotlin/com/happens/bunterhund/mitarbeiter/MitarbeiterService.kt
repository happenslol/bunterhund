package com.happens.bunterhund.mitarbeiter

import com.happens.bunterhund.orNull
import com.happens.bunterhund.tiere.Tier
import com.happens.bunterhund.tiere.TierArtRepository
import com.happens.bunterhund.tiere.TierRepository
import org.springframework.stereotype.Service

@Service
class MitarbeiterService(
    val mitarbeiterRepository: MitarbeiterRepository,
    val tierRepository: TierRepository,
    val tierArtRepository: TierArtRepository
) {
    fun alle(): List<Mitarbeiter> = mitarbeiterRepository.findAll()

    fun mitarbeiterHaustiere(): List<Tier> {
        val mitarbeiter = mitarbeiterRepository.findAll()

        val haustiere = mitarbeiter
            .mapNotNull { it.haustierName }
            .mapNotNull { tierRepository.findByName(it) }

        return haustiere
    }

    fun haustiereHinzufuegen(zumHinzufuegen: Map<Mitarbeiter, Tier>): List<Mitarbeiter> {
        val zuSpeichern = zumHinzufuegen
            .filterKeys { mitarbeiterRepository.findById(it.id).orNull() != null }
            .filterValues { tierRepository.findByName(it.name) != null }
            .map { (mitarbeiter, tier) ->
                mitarbeiter.copy(
                    haustierName = tier.name
                )
            }

        return mitarbeiterRepository.saveAll(zuSpeichern)
    }

    fun futterAnzahlFuerMitarbeiterHaustiere(): Map<String, Int> {
        val mitarbeiter = mitarbeiterRepository.findAll()

        val tierArtAnzahl = mitarbeiter
            .mapNotNull { it.haustierName }
            .mapNotNull { tierRepository.findByName(it) }
            .groupBy { it.art }
            .map { it.key to it.value.size }
            .toMap()

        val futterFuerTierart = tierArtAnzahl
            .mapNotNull { (artName, anzahl) ->
                val art = tierArtRepository.findById(artName).orNull()
                    ?: return@mapNotNull null

                art.futter to anzahl
            }
            .toMap()

        return futterFuerTierart
    }

    fun futterFuerMitarbeiter(id: String): String =
        mitarbeiterRepository.findById(id).orNull()
            ?.haustierName
            ?.let { tierName ->
                tierRepository.findByName(tierName)
            }
            ?.let { tier ->
                tierArtRepository.findById(tier.art).orNull()
            }
            ?.futter ?: "Kein Haustier"

    fun genugFutterFuerMitarbeiter(): Boolean {
        TODO("Nicht implementiert")
    }
}
