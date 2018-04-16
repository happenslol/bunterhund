package com.happens.bunterhund.tiere

import com.happens.bunterhund.mitarbeiter.MitarbeiterRepository
import com.happens.bunterhund.orNull
import org.springframework.stereotype.Service

@Service
class TierService(
    val tierRepository: TierRepository,
    val tierArtRepository: TierArtRepository,
    val mitarbeiterRepository: MitarbeiterRepository
) {
    fun tierMitId(id: String): Tier? = tierRepository.findById(id).orNull()
    fun alle(): List<Tier> = tierRepository.findAll()
    fun speichern(tier: Tier): Tier = tierRepository.save(tier)
    fun loeschen(tier: Tier) = tierRepository.delete(tier)

    fun beschreibung(tier: Tier): String {
        val tierArt = tierArtRepository.findById(tier.art).orNull() ?: error("Tierart sollte existieren!")

        return """
            ${tier.name} ist ${tierArt.artikel()} ${tier.art}.
            Sie/er ist ${tier.alter} Jahre alt und isst gerne ${tierArt.futter}.
        """
    }

    fun tiereZumVerkauf(): List<Tier> {
        val mitarbeiter = mitarbeiterRepository.findAll()
        val mitarbeiterHaustierNamen = mitarbeiter
            .mapNotNull { it.haustierName }

        val tiere = tierRepository.findAll()
        val nichtHaustiere = tiere.filter {
            it.name !in mitarbeiterHaustierNamen
        }

        val tiereMitPreis = tiere
            .filter { it.preis != null }

        val tiereZumVerkauf = (tiereMitPreis + nichtHaustiere)
            .distinctBy { it.id }

        return tiereZumVerkauf
    }

    fun tiereNichtZumVerkauf(): List<Tier> {
        TODO("Nicht implementiert")
    }

    private fun TierArt.artikel(): String =
        when(this.art) {
            "Hund", "Kaninchen", "Goldfisch" -> "ein"
            "Katze", "Schildkroete" -> "eine"

            else -> "ein"
        }

    private fun Tier.istValide(): Boolean {
        if (this.alter <= 0) return false
        if (this.name.isEmpty()) return false

        return true
    }
}
