package com.happens.bunterhund.daten

import com.happens.bunterhund.futter.FutterVorrat
import com.happens.bunterhund.futter.FutterVorratRepository
import com.happens.bunterhund.tiere.Tier
import com.happens.bunterhund.tiere.TierArt
import com.happens.bunterhund.tiere.TierArtRepository
import com.happens.bunterhund.tiere.TierRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class TestDatenService(
    val futterVorratRepository: FutterVorratRepository,
    val tierRepository: TierRepository,
    val tierArtRepository: TierArtRepository
) : CommandLineRunner {

    override fun run(vararg args: String?) {
        if (args.getOrNull(0) != "beispiel-daten")
            return

        datenbankenLeeren()
        println("Datenbanken geleert.")

        beispielDatenLaden()
        println("Beispieldaten geladen.")
    }

    fun datenbankenLeeren() {
        futterVorratRepository.deleteAll()
        tierRepository.deleteAll()
        tierArtRepository.deleteAll()
    }

    fun beispielDatenLaden() {
        futterVorratRepository.saveAll(FutterVorratBeispiele())
        tierArtRepository.saveAll(TierArtBeispiele())
        tierRepository.saveAll(TierBeispiele())
    }

    private fun FutterVorratBeispiele(): List<FutterVorrat> = listOf(
        FutterVorrat("Nassfutter", 10),
        FutterVorrat("Trockenfutter", 20),
        FutterVorrat("Fischfutter", 25),
        FutterVorrat("Knochen", 5),
        FutterVorrat("Karotten", 100)
    )

    private fun TierArtBeispiele(): List<TierArt> = listOf(
        TierArt("Hund", "Knochen"),
        TierArt("Katze", "Nassfutter"),
        TierArt("Goldfisch", "Fischfutter"),
        TierArt("Kaninchen", "Karotten"),
        TierArt("Schildkroete", "Trockenfutter")
    )

    private fun TierBeispiele(): List<Tier> = listOf(
        Tier(art = "Hund", name = "Fido", alter = 5),
        Tier(art = "Hund", name = "Brutus", alter = 8),
        Tier(art = "Hund", name = "Pluto", alter = 3),

        Tier(art = "Katze", name = "Charlie", alter = 4),
        Tier(art = "Katze", name = "Drops", alter = 11),
        Tier(art = "Katze", name = "Roofie", alter = 9),

        Tier(art = "Goldfisch", name = "Karl", alter = 2),
        Tier(art = "Goldfisch", name = "Qwerty", alter = 22),
        Tier(art = "Goldfisch", name = "Romeo", alter = 18),
        Tier(art = "Goldfisch", name = "Nemo", alter = 5),
        Tier(art = "Goldfisch", name = "Penelope", alter = 10),

        Tier(art = "Kaninchen", name = "Judy", alter = 3),
        Tier(art = "Kaninchen", name = "Pumpkin", alter = 5),
        Tier(art = "Kaninchen", name = "Pipper", alter = 3),
        Tier(art = "Kaninchen", name = "Graf von Krolock", alter = 4),

        Tier(art = "Schildkroete", name = "Walter", alter = 30),
        Tier(art = "Schildkroete", name = "Rosalinda", alter = 27)
    )
}
