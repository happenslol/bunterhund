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
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class TestDatenRunner(
    val testDatenService: TestDatenService
) : CommandLineRunner {

    override fun run(vararg args: String?) {
        if (args.getOrNull(0) != "beispiel-daten")
            return

        testDatenService.datenbankenLeeren()
        println("Datenbanken geleert.")

        testDatenService.beispielDatenLaden()
        println("Beispieldaten geladen.")
    }

}
