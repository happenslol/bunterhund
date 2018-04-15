package com.happens.bunterhund.mitarbeiter

import com.happens.bunterhund.orNull
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.*

@Document
data class Mitarbeiter(

    @Id
    val id: String = UUID.randomUUID().toString(),

    val name: Name,

    val haustierName: String? = null
) {
    val hatHaustier: Boolean
        get() = this.haustierName != null
}

data class Name(
    val firstName: String,
    val lastName: String
)

interface MitarbeiterRepository : MongoRepository<Mitarbeiter, String>
