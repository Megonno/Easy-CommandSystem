val main = "de.megonno.easycommandsystem.MainKt"

plugins {
 kotlin("jvm") version "1.9.0"
 application
}

group = "de.megonno.easycommandsystem"
version = "1.0.0"

repositories {
 mavenCentral()
}

kotlin {
 jvmToolchain(17)
 
 sourceSets {
  val main by getting {
   dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.9.0")

    implementation("org.jline:jline:3.23.0")
    implementation("org.jline:jline-reader:3.23.0")
    implementation("org.jline:jline-terminal-jansi:3.23.0")
    implementation("org.fusesource.jansi:jansi:2.4.0")
   }
  }
 }
}

application {
 mainClass.set(main)
}

tasks.withType<Jar> {
 manifest {
  attributes["Main-Class"] = main
 }

 duplicatesStrategy = DuplicatesStrategy.EXCLUDE

 from(sourceSets.main.get().output)

 dependsOn(configurations.runtimeClasspath)
 from({
  configurations.runtimeClasspath.get().filter { it.name.endsWith("jar") }.map { zipTree(it) }
 })
}
