# Techolutionassignment
Deploying to Cloud Foundry from STS

This guide walks you through the process of deploying a "HasshMaapServiceBroker" Spring application to Cloud Foundry from Spring Tool Suite (STS).

You’ll build an application which will accept HTTP GET requests at:

https://hashmaapservicebroker.cfapps.io

The myname portion of the URL is what you will change when you deploy your application to Cloud Foundry through STS, to avoid host-taken errors during deployment

Required tools: Spring Tool Suite (STS) JDK 8 or later Pivotal Web Services (PWS) account Spring Boot Dashboard

Download and unzip the source repository for this guide, or clone it using Git: git clone https://github.com/spring-guides/gs-serving-web-content.git

Build with Gradle.

Once the code downloaded from repository ,import project to the STS as Gradle project

Create a Gradle build file

Below is the initial Gradle build file.

build.gradle /***************Start******************/ apply plugin: 'java' apply plugin: 'spring-boot' apply plugin: 'cloudfoundry' apply plugin: 'jacoco'

buildscript { ext { springBootVersion = '1.3.5.RELEASE' } repositories { jcenter() mavenCentral() maven {url "http://plugins.gradle.org/m2/"} } dependencies { classpath("org.springframework.boot:spring-boot-gradle-plugin:${springBootVersion}") classpath("org.cloudfoundry:cf-gradle-plugin:1.1.2")

}
}

//Application jar name and version

jar { baseName = 'HashMapServiceBroker' version = '0.0.1-SNAPSHOT' }

//JDK version sourceCompatibility = 1.8 targetCompatibility = 1.8

repositories { mavenCentral() jcenter() }

configurations { providedRuntime }

//unit and functional scripts sourceSets {

test { java.srcDirs = ['src/test/unit/java'] } functionalTest { java { srcDir 'src/test/functional/java'

    }
    resources {
        srcDir 'src/test/functional/resources'
    }
}
}

configurations { functionalTestCompile.extendsFrom testCompile functionalTestRuntime.extendsFrom testRuntime }

//Jars required to compile and run the applications dependencies { compile('org.springframework.boot:spring-boot-starter-web')

//cloud connector jar
compile ('org.springframework.cloud:spring-cloud-spring-service-connector:1.2.2.RELEASE')
compile ('org.springframework.cloud:spring-cloud-cloudfoundry-connector:1.2.2.RELEASE')
compile('org.apache.commons:commons-lang3:3.4')
compile('commons-collections:commons-collections:3.2.2')







providedRuntime('org.springframework.boot:spring-boot-starter-tomcat')

testCompile('org.springframework.boot:spring-boot-starter-test')

//required to run functional test
functionalTestCompile sourceSets.main.output
functionalTestCompile configurations.testCompile
functionalTestCompile sourceSets.test.output
functionalTestRuntime configurations.testRuntime
}

//Defined Functional task

task functionalTest(type: Test) { testClassesDir = sourceSets.functionalTest.output.classesDir classpath += sourceSets.functionalTest.runtimeClasspath }

tasks.withType(Test) { reports.html.destination = file("${reporting.baseDir}/${name}") }

cloudfoundry { application = project.getProperty("application") target = project.getProperty("target") space = project.getProperty("space")
file = file("build/libs/HashMapServiceBroker-0.0.1-SNAPSHOT.jar") memory = 512 instances = 1 env = [ "key": "value" ] services {
"cares-db" { bind= true }
} }

//code for generating logs for HashMapServiceBroker /* import org.gradle.internal.logging.* def taskName = project.getGradle().startParameter.taskNames.get(0); def logDir = "${rootDir}/HashMapServiceBroker_logs"
if(taskName.equals("build")) { logDir = "${rootDir}/HashMapServiceBroker_logs" } else if(taskName.equals("test")) { logDir = "${rootDir}/HashMapServiceBroker_logs" } else if(taskName.equals("deploy")) { logDir = "${rootDir}/HashMapServiceBroker_logs" } mkdir("${logDir}") def tstamp = new Date().format('yyyy-MM-dd_HH-mm-ss') def logFile = new File("${logDir}/${tstamp}_${taskName}.log") System.setProperty('org.gradle.color.error', 'RED') gradle.services.get(LoggingOutputInternal).addStandardOutputListener (new StandardOutputListener () { void onOutput(CharSequence output) { logFile << output } }) gradle.services.get(LoggingOutputInternal).addStandardErrorListener (new StandardOutputListener () { void onOutput(CharSequence output) { logFile << output } })

*/

task unitTest << { println "Running unit tests" } unitTest.dependsOn test

/***************End******************/


