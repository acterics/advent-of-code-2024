plugins {
    kotlin("jvm")
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