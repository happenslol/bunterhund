package com.happens.bunterhund

import java.util.*

fun <T> Optional<T>.orNull(): T? = this.orElse(null)