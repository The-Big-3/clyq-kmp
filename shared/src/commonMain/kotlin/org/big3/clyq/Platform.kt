package org.big3.clyq

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform