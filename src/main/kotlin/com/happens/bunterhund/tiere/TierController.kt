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
    ): Tier = tierService.tierMitId(id).orNull()
        ?: throw TierFehler.TierNichtGefunden()

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

    fun loeschen(
        @PathVariable("id")
        id: String
    ): ResponseEntity.HeadersBuilder<*> {
        val tierZuLoeschen = tierService.tierMitId(id) ?:
            throw TierFehler.TierNichtGefunden()

        tierService.loeschen(tierZuLoeschen)

        return ResponseEntity.noContent()
    }
}

sealed class TierFehler : RuntimeException() {

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    class TierNichtGefunden : TierFehler()

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    class KeineVeraenderung : TierFehler()
}
