plugins {
    kotlin("jvm")
    application
}

application {
    mainClass.set("ua.olehlypskyi.adventofcode2024.day4.MainKt")
}

kotlin {
    sourceSets.main {
        kotlin.srcDirs("src")
    }
    sourceSets.test {
        kotlin.srcDirs("test")
    }
}
dependencies {
    testImplementation(kotlin("test"))
}