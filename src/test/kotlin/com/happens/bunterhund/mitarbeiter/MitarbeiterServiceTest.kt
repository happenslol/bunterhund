package com.happens.bunterhund.mitarbeiter

import com.happens.bunterhund.daten.TestDatenService
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class MitarbeiterServiceTest {

    @Autowired
    lateinit var mitarbeiterService: MitarbeiterService

    @Autowired
    lateinit var testDatenService: TestDatenService

    @Before
    fun beispieldatenLaden() = testDatenService.beispielDatenLaden()

    @After
    fun datenLoeschen() = testDatenService.datenbankenLeeren()

    @Test
    fun `Soll korrekt eine Liste aller Haustiere von Mitarbeitern ausgeben`() {
        val beispielHaustiere = testDatenService.mitarbeiterBeispiele()
            .mapNotNull { it.haustierName }.toSet()

        val resultat = mitarbeiterService.mitarbeiterHaustiere()
            .map { it.name }.toSet()

        assertEquals(beispielHaustiere, resultat)
    }

    @Test
    fun `Soll korrekt Futteranzahl fuer Mitarbeiterhaustiere ausgeben`() {
        val resultat = mitarbeiterService.futterAnzahlFuerMitarbeiterHaustiere()

        assertEquals(3, resultat.get("Fischfutter"))
        assertEquals(1, resultat.get("Karotten"))
        assertEquals(1, resultat.get("Koerner"))
    }

    @Test
    fun `Soll korrekt ausgeben welches Futter ein Mitarbeiter braucht`() {
        val alleMitarbeiter = mitarbeiterService.alle()

        val mitarbeiterA = alleMitarbeiter.getOrNull(0)
            ?: error("Sollte mindestens 2 beispielmitarbeiter enthalten!")

        val mitarbeiterB = alleMitarbeiter.getOrNull(1)
            ?: error("Sollte mindestens 2 beispielmitarbeiter enthalten!")

        val resultatA = mitarbeiterService.futterFuerMitarbeiter(mitarbeiterA.id)
        val resultatB = mitarbeiterService.futterFuerMitarbeiter(mitarbeiterB.id)

        assertEquals("Kein Haustier", resultatA)
        assertEquals("Fischfutter", resultatB)
    }
}