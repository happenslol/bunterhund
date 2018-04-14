package com.happens.bunterhund.futter

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.repository.MongoRepository

@Document
data class FutterVorrat(

    @Id
    val name: String,

    val anzahl: Int
)

interface FutterVorratRepository : MongoRepository<FutterVorrat, String>
