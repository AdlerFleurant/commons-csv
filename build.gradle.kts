group = "org.apache.commons"
version = "1.7-SNAPSHOT"

plugins {
    `java-library`
//    checkstyle
    `maven-publish`
}

val jmhVersion = "1.21"
val junitVersion = "5.3.1"

java.sourceCompatibility = JavaVersion.VERSION_1_10
java.targetCompatibility = JavaVersion.VERSION_1_10

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.withType<Test> {
    useJUnit{
        exclude("**/*Benchmark*", "**/perf/PerformanceTest.java")
    }
}

//tasks.withType<Checkstyle>{
//    this.configFile = file("$rootDir/checkstyle.xml")
//    reports{
//
//    }
//}

repositories {
    jcenter()
    maven("https://repo.marketcetera.org/maven/")
}

dependencies {
    testCompile("junit:junit:4.12")
    testCompile("org.mockito", "mockito-all", "1.10.19")
    testCompile("commons-io", "commons-io", "2.6")
    testCompile("org.apache.commons", "commons-lang3", "3.8.1")
    testCompile("org.openjdk.jmh", "jmh-core", jmhVersion)
    testCompile("org.openjdk.jmh", "jmh-generator-annprocess", jmhVersion)
    testCompile("com.h2database", "h2", "1.4.196")

    testCompile("genjava", "gj-csv", "1.0")
    testCompile("net.sourceforge.javacsv", "javacsv", "2.0")
    testCompile("com.opencsv", "opencsv", "3.1")
    testCompile("net.sf.supercsv", "super-csv", "2.2.1")
    testCompile("org.skife.kasparov", "csv", "1.0")
//    <groupId>org.apache.commons</groupId>
//    <artifactId>commons-lang3</artifactId>
//    <version>3.6</version>
//    </dependency>
}

publishing {
    publications {
        register("mavenJava", MavenPublication::class) {
            from(components["java"])
        }
    }

    repositories {
        mavenLocal()
    }
}

tasks.register("wrapper", Wrapper::class) {
    gradleVersion = "4.10.2"
    distributionType = Wrapper.DistributionType.ALL
}