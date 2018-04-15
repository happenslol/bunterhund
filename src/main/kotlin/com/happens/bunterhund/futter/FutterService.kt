package com.happens.bunterhund.futter

import com.happens.bunterhund.mitarbeiter.Mitarbeiter
import com.happens.bunterhund.tiere.Tier

class FutterService(
    val futterVorratRepository: FutterVorratRepository
) {
    fun alle(): List<FutterVorrat> {
        TODO("Nicht implementiert")
    }

    fun futterFuerTierVorhanden(tier: Tier): Boolean {
        TODO("Nicht implementiert")
    }

    fun futterFuerMitarbeiterHaustierVorhanden(mitarbeiter: Mitarbeiter): Boolean {
        TODO("Nicht implementiert")
    }

    fun futterVorratHinzufuegen(vorrat: FutterVorrat): FutterVorrat {
        TODO("Nicht implementiert")
    }

    fun futterAnzahlBearbeiten(vorrat: FutterVorrat, neueAnzahl: Int): FutterVorrat {
        TODO("Nicht implementiert")
    }
}
