package com.happens.bunterhund.tiere

import com.happens.bunterhund.orNull
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.*

@Document
data class Tier(

    @Id
    val id: String = UUID.randomUUID().toString(),

    val art: String,

    val name: String,

    val alter: Int,

    val preis: Int? = null
)

interface TierRepository : MongoRepository<Tier, String> {
    fun findByName(name: String): Tier?
}

@Document
data class TierArt(

    @Id
    val art: String,

    val futter: String
)

interface TierArtRepository : MongoRepository<TierArt, String>

@DslMarker
annotation class TierDsl

@TierDsl
class TierBuilder {
    var tier = Tier(
        art = "",
        name = "",
        alter = 0
    )

    infix fun Tier.istArt(art: String) {
        tier = this.copy(art = art)
    }

    infix fun Tier.heisst(name: String) {
        tier = this.copy(name = name)
    }

    infix fun Tier.kostet(preis: Int) {
        tier = this.copy(preis = preis)
    }

    infix fun Tier.hatAlter(alter: Int) {
        tier = this.copy(alter = alter)
    }

    fun build(): Tier = tier
}

fun NeuesTier(setup: TierBuilder.() -> Unit): Tier {
    val tierBuilder = TierBuilder()
    tierBuilder.setup()
    return tierBuilder.build()
}

val wuffiDerHund = NeuesTier {
    tier istArt "Hund"
    tier heisst "Wuffi"
    tier kostet 50
    tier hatAlter 15
}
