plugins {
    kotlin("jvm") version "1.6.20"
    id("org.jetbrains.dokka") version "1.6.20"
    `java-gradle-plugin`
    id("com.gradle.plugin-publish") version "0.20.0"
    id("net.kyori.indra") version "2.1.1"
    id("net.kyori.indra.license-header") version "2.1.1"
    id("net.kyori.indra.publishing.gradle-plugin") version "2.1.1"
}

group = "fr.xpdustry"
version = "2.0.0" + if (indraGit.headTag() == null) "-SNAPSHOT" else ""
description = "Gradle plugin for building and testing mindustry mods/plugins."

tasks.javadocJar {
    dependsOn(tasks.dokkaHtml.get())
    from(tasks.dokkaHtml.get())
}

repositories {
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    compileOnly(gradleApi())
    implementation("net.kyori:mammoth:1.1.0")
    implementation("org.hjson:hjson:3.0.0")
    implementation(kotlin("stdlib"))
}

license {
    header(rootProject.file("LICENSE_HEADER.md"))
}

signing {
    val signingKey: String? by project
    val signingPassword: String? by project
    useInMemoryPgpKeys(signingKey, signingPassword)
}

indra {
    javaVersions {
        target(8)
        minimumToolchain(17)
    }

    publishSnapshotsTo("xpdustry", "https://repo.xpdustry.fr/snapshots")
    publishReleasesTo("xpdustry", "https://repo.xpdustry.fr/releases")

    mitLicense()

    github("Xpdustry", "Toxopid") {
        ci(true)
        issues(true)
        scm(true)
    }

    configurePublications {
        pom {
            organization {
                name.set("Xpdustry")
                url.set("https://www.xpdustry.fr")
            }

            developers {
                developer {
                    id.set("Phinner")
                    timezone.set("Europe/Brussels")
                }
            }
        }
    }
}

indraPluginPublishing {
    website("https://github.com/Xpdustry/Toxopid")

    plugin(
        "toxopid",
        "fr.xpdustry.toxopid.ToxopidPlugin",
        "Toxopid",
        project.description,
        listOf("mindustry", "testing", "boilerplate")
    )
}
