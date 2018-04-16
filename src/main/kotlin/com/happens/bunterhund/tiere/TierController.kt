package com.happens.bunterhund.tiere

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("tiere")
class TierController(
    val tierService: TierService
) {

    @GetMapping
    fun alle(): List<Tier> = tierService.alle()

    @GetMapping("/{id}")
    fun eins(
        @PathVariable("id")
        id: String
    ): Tier = tierService.tierMitId(id)
        ?: throw TierFehler.TierNichtGefunden()

    @GetMapping("/{id}/preis")
    fun preis(
        @PathVariable("id")
        id: String
    ): Int = tierService.tierMitId(id)?.preis ?: throw TierFehler.KeinPreisVerfuegbar()

    @PostMapping
    fun erstellen(
        @RequestBody
        tier: Tier
    ): Tier = tierService.speichern(tier)

    @PutMapping("/{id}")
    fun bearbeiten(
        @PathVariable("id")
        id: String,

        @RequestBody
        tier: Tier
    ): Tier {
        val altesTier = tierService.tierMitId(id) ?:
            throw TierFehler.TierNichtGefunden()

        if (altesTier == tier) throw TierFehler.KeineVeraenderung()

        return tierService.speichern(tier)
    }

    @DeleteMapping("/{id}")
    fun loeschen(
        @PathVariable("id")
        id: String
    ): ResponseEntity.HeadersBuilder<*> {
        val tierZuLoeschen = tierService.tierMitId(id) ?:
            throw TierFehler.TierNichtGefunden()

        tierService.loeschen(tierZuLoeschen)

        return ResponseEntity.noContent()
    }


    data class TierBeschreibung(
        val beschreibung: String
    )

    @GetMapping("/{id}/beschreibung")
    fun beschreibung(
        @PathVariable("id")
        id: String
    ): TierBeschreibung {
        val tier = tierService.tierMitId(id) ?:
            throw TierFehler.TierNichtGefunden()

        val text = tierService.beschreibung(tier)
        return TierBeschreibung(
            beschreibung = text
        )
    }
}

sealed class TierFehler(message: String) : RuntimeException(message = message) {

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    class TierNichtGefunden : TierFehler(message = "Tier konnte nicht gefunden werden")

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    class KeineVeraenderung : TierFehler(message = "Tier wurde nicht veraendert")

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    class KeinPreisVerfuegbar : TierFehler(message = "Tier existiert nicht oder steht nicht zum Verkauf")
}
