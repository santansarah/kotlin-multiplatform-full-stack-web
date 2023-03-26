import org.jetbrains.kotlin.fir.expressions.builder.buildArgumentList
import org.jetbrains.kotlin.gradle.plugin.KotlinJsCompilerType
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    kotlin("multiplatform") version "1.8.10"
    application
    kotlin("plugin.serialization") version "1.8.10"
}

val serializationVersion = "1.3.3"
val ktorVersion = "2.2.4"
val logbackVersion = "1.2.11"
val kotlinWrappersVersion = "1.0.0-pre.354"
val exposedVersion = "0.40.1"

group = "com.santansarah"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    mavenLocal()
}

kotlin {
    jvm {
        compilations.configureEach {
            kotlinOptions.jvmTarget = "17"
        }
        withJava()
        testRuns["test"].executionTask.configure {
            useJUnitPlatform()
        }
    }
    js {
        binaries.executable()

        browser {
            commonWebpackConfig {
                this.mode = KotlinWebpackConfig.Mode.DEVELOPMENT
                this.devServer = KotlinWebpackConfig.DevServer(
                    port = 8080,
                    proxy = mutableMapOf("*" to "http://127.0.0.1:9090")
                )
            }
        }


    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$serializationVersion")
                implementation("io.ktor:ktor-client-core:$ktorVersion")
            }

        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation("io.ktor:ktor-server-netty:$ktorVersion")
                implementation("io.ktor:ktor-server-html-builder-jvm:$ktorVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:0.8.0")

                implementation("io.ktor:ktor-serialization:$ktorVersion")
                implementation("io.ktor:ktor-server-content-negotiation:$ktorVersion")
                implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
                implementation("io.ktor:ktor-server-cors:$ktorVersion")
                implementation("io.ktor:ktor-server-compression:$ktorVersion")
                implementation("io.ktor:ktor-server-core-jvm:$ktorVersion")
                implementation("ch.qos.logback:logback-classic:$logbackVersion")

                implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
                implementation("org.jetbrains.exposed:exposed-dao:$exposedVersion")
                implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")

                // database
                implementation("org.jetbrains.exposed:exposed-java-time:$exposedVersion")
                implementation("org.xerial:sqlite-jdbc:3.36.0.3")

                // ktor client
                implementation("io.ktor:ktor-client-core:$ktorVersion")
                implementation("io.ktor:ktor-client-cio:$ktorVersion")
                implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
                implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
                implementation("io.ktor:ktor-client-logging:$ktorVersion")

                // Koin for Ktor
                implementation("io.insert-koin:koin-ktor:3.3.1")
                implementation("io.insert-koin:koin-logger-slf4j:3.3.1")
            }
        }
        val jvmTest by getting {

        }
        val jsMain by getting {
            dependencies {
                //implementation "org.jetbrains.kotlin-wrappers:kotlin-react:18.2.0-pre.346"
                //implementation "org.jetbrains.kotlin-wrappers:kotlin-react-dom:18.2.0-pre.346"
                //implementation "org.jetbrains.kotlin-wrappers:kotlin-emotion:11.9.3-pre.346"
                //implementation "org.jetbrains.kotlin-wrappers:kotlin-react-router-dom:6.3.0-pre.346"

                // React, React DOM + Wrappers
                implementation(project.dependencies.enforcedPlatform("org.jetbrains.kotlin-wrappers:kotlin-wrappers-bom:$kotlinWrappersVersion"))
                implementation("org.jetbrains.kotlin-wrappers:kotlin-react")
                implementation("org.jetbrains.kotlin-wrappers:kotlin-react-dom")
                implementation("org.jetbrains.kotlin-wrappers:kotlin-react-router-dom")

                // Kotlin React Emotion (CSS)
                implementation("org.jetbrains.kotlin-wrappers:kotlin-emotion")

                implementation("io.ktor:ktor-client-js:$ktorVersion")
                implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
                implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")

                // Coroutines & serialization
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.3")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.3")
            }
        }
        val jsTest by getting {

        }
    }
}

application {
    mainClass.set("ServerKt")
}

tasks.named<Copy>("jvmProcessResources") {
    val jsBrowserDistribution = tasks.named("jsBrowserDistribution")
    from(jsBrowserDistribution)
}

tasks.named<JavaExec>("run") {
    environment("IP_ADDR", "127.0.0.1")
    dependsOn(tasks.named<Jar>("jvmJar"))
    classpath(tasks.named<Jar>("jvmJar"))
}

tasks.named("jsProductionExecutableCompileSync") {
    dependsOn(tasks.named("jsBrowserDevelopmentWebpack"))
}

// include JS artifacts in any generated JAR
tasks.getByName<Jar>("jvmJar") {

    duplicatesStrategy = DuplicatesStrategy.EXCLUDE

    val taskName = if (project.hasProperty("isProduction")
        || project.gradle.startParameter.taskNames.contains("installDist")
    ) {
        "jsBrowserProductionWebpack"
    } else {
        "jsBrowserDevelopmentWebpack"
    }
    val webpackTask = tasks.getByName<org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpack>(taskName)
    dependsOn(webpackTask) // make sure JS gets compiled first
    from(File(webpackTask.destinationDirectory, webpackTask.outputFileName)) // bring output file along into the JAR
}

/*
tasks.named("jsBrowserDevelopmentRun").configure {
    group = "run hot"
    //args = listOf("--continuous")
    //dependsOn(tasks.named("run"))
}

tasks.named("run").configure {
    group = "run hot"
}
*/

