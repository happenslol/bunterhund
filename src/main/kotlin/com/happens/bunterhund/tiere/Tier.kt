package com.happens.bunterhund.tiere

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

    val alter: Int
)

interface TierRepository : MongoRepository<Tier, String>

@Document
data class TierArt(

    @Id
    val art: String,

    val futter: String
)

interface TierArtRepository : MongoRepository<TierArt, String>