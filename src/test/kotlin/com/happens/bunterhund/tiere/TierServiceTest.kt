package com.happens.bunterhund.tiere

import com.happens.bunterhund.daten.TestDatenService
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class TierServiceTest {

    @Autowired
    lateinit var tierService: TierService

    @Autowired
    lateinit var testDatenService: TestDatenService

    @Before
    fun beispieldatenLaden() = testDatenService.beispielDatenLaden()

    @After
    fun datenLoeschen() = testDatenService.datenbankenLeeren()

    // TODO
    @Test
    fun `Soll korrekt Liste der Tiere zum Verkauf ausgeben`() {}

    // TODO
    @Test
    fun `Soll korrekt Tierbeschreibungen ausgeben`() {}

    // TODO
    @Test
    fun `Soll korrekt Tiere nicht zum Verkauf ausgeben`() {}
}
