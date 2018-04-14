package com.happens.bunterhund

import org.springframework.boot.WebApplicationType
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.runApplication

@SpringBootApplication
class BunterhundApplication

fun main(args: Array<String>) {
    if (args.isNotEmpty() && args[0] == "beispiel-daten") {
        val app = SpringApplicationBuilder()
            .sources(BunterhundApplication::class.java)
            .web(WebApplicationType.NONE)
            .build()

        app.run(*args)
        return
    }

    runApplication<BunterhundApplication>(*args)
}
