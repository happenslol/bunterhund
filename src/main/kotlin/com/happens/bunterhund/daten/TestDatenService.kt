package com.happens.bunterhund.daten

import com.happens.bunterhund.futter.FutterVorrat
import com.happens.bunterhund.futter.FutterVorratRepository
import com.happens.bunterhund.mitarbeiter.Mitarbeiter
import com.happens.bunterhund.mitarbeiter.MitarbeiterRepository
import com.happens.bunterhund.mitarbeiter.Name
import com.happens.bunterhund.tiere.Tier
import com.happens.bunterhund.tiere.TierArt
import com.happens.bunterhund.tiere.TierArtRepository
import com.happens.bunterhund.tiere.TierRepository
import org.springframework.stereotype.Service

@Service
class TestDatenService(
    val futterVorratRepository: FutterVorratRepository,
    val tierRepository: TierRepository,
    val tierArtRepository: TierArtRepository,
    val mitarbeiterRepository: MitarbeiterRepository
) {
    fun datenbankenLeeren() {
        futterVorratRepository.deleteAll()
        tierRepository.deleteAll()
        tierArtRepository.deleteAll()
        mitarbeiterRepository.deleteAll()
    }

    fun beispielDatenLaden() {
        futterVorratRepository.saveAll(futterVorratBeispiele())
        tierArtRepository.saveAll(tierArtBeispiele())
        tierRepository.saveAll(tierBeispiele())
        mitarbeiterRepository.saveAll(mitarbeiterBeispiele())
    }

    fun futterVorratBeispiele(): List<FutterVorrat> = listOf(
        FutterVorrat("Nassfutter", 10),
        FutterVorrat("Trockenfutter", 20),
        FutterVorrat("Fischfutter", 25),
        FutterVorrat("Knochen", 5),
        FutterVorrat("Karotten", 100),
        FutterVorrat("Koerner", 200)
    )

    fun tierArtBeispiele(): List<TierArt> = listOf(
        TierArt("Hund", "Knochen"),
        TierArt("Katze", "Nassfutter"),
        TierArt("Goldfisch", "Fischfutter"),
        TierArt("Karpfen", "Fischfutter"),
        TierArt("Regenbogenfisch", "Fischfutter"),
        TierArt("Kaninchen", "Karotten"),
        TierArt("Maus", "Karotten"),
        TierArt("Schildkroete", "Trockenfutter"),
        TierArt("Wellensittich", "Koerner"),
        TierArt("Papagei", "Koerner")
    )

    fun tierBeispiele(): List<Tier> = listOf(
        Tier(art = "Hund", name = "Fido", alter = 5),
        Tier(art = "Hund", name = "Brutus", alter = 8),
        Tier(art = "Hund", name = "Pluto", alter = 3),
        Tier(art = "Hund", name = "Balu", alter = 5),
        Tier(art = "Hund", name = "Benny", alter = 8),
        Tier(art = "Hund", name = "Sami", alter = 3),
        Tier(art = "Hund", name = "Diana", alter = 5),
        Tier(art = "Hund", name = "Diva", alter = 8),
        Tier(art = "Hund", name = "Bambi", alter = 3),

        Tier(art = "Katze", name = "Katarina", alter = 4),
        Tier(art = "Katze", name = "Drops", alter = 11),
        Tier(art = "Katze", name = "Roofie", alter = 9),
        Tier(art = "Katze", name = "Blackie", alter = 9),

        Tier(art = "Goldfisch", name = "Karl", alter = 2),
        Tier(art = "Goldfisch", name = "Qwerty", alter = 22),
        Tier(art = "Goldfisch", name = "Romeo", alter = 18),
        Tier(art = "Goldfisch", name = "Nemo", alter = 5),
        Tier(art = "Goldfisch", name = "Penelope", alter = 10),

        Tier(art = "Regenbogenfisch", name = "Tilli", alter = 15),
        Tier(art = "Regenbogenfisch", name = "Verne", alter = 10),
        Tier(art = "Regenbogenfisch", name = "Jake", alter = 22),
        Tier(art = "Regenbogenfisch", name = "Beans", alter = 8),
        Tier(art = "Regenbogenfisch", name = "Rango", alter = 3),

        Tier(art = "Maus", name = "Fips", alter = 1),
        Tier(art = "Maus", name = "Knibbel", alter = 2),
        Tier(art = "Maus", name = "Blitz", alter = 1),

        Tier(art = "Karpfen", name = "Elmo", alter = 3),
        Tier(art = "Karpfen", name = "Chong", alter = 5),
        Tier(art = "Karpfen", name = "Cleo", alter = 19),
        Tier(art = "Karpfen", name = "Goldie", alter = 3),

        Tier(art = "Kaninchen", name = "Judy", alter = 3),
        Tier(art = "Kaninchen", name = "Pumpkin", alter = 5),
        Tier(art = "Kaninchen", name = "Pippa", alter = 3),
        Tier(art = "Kaninchen", name = "Graf von Krolock", alter = 4),

        Tier(art = "Schildkroete", name = "Walter", alter = 30),
        Tier(art = "Schildkroete", name = "Rosalinda", alter = 27),

        Tier(art = "Wellensittich", name = "Peter", alter = 10),
        Tier(art = "Wellensittich", name = "Zazou", alter = 4),

        Tier(art = "Papagei", name = "Polly", alter = 7),
        Tier(art = "Papagei", name = "Carla", alter = 15),
        Tier(art = "Papagei", name = "Bonnie", alter = 11),
        Tier(art = "Papagei", name = "Clyde", alter = 10)
    )

    fun mitarbeiterBeispiele(): List<Mitarbeiter> = listOf(
        Mitarbeiter(name = Name("Hans", "Mueller")),
        Mitarbeiter(name = Name("Leon", "Strauss"), haustierName = "Karl"),
        Mitarbeiter(name = Name("Hannes", "Guedelhoefer")),
        Mitarbeiter(name = Name("Mischa", "Holz")),
        Mitarbeiter(name = Name("Julia", "Wirth"), haustierName = "Pippa"),
        Mitarbeiter(name = Name("Daniel", "Meier"), haustierName = "Romeo"),
        Mitarbeiter(name = Name("David", "Mulder"), haustierName = "Penelope"),
        Mitarbeiter(name = Name("Fabian", "Wolf"), haustierName = "Bonnie"),
        Mitarbeiter(name = Name("Hendrik", "Neumann")),
        Mitarbeiter(name = Name("Hilmar", "Wiegand"), haustierName = "Peter"),
        Mitarbeiter(name = Name("Julian", "Klein"), haustierName = "Polly")
    )
}
