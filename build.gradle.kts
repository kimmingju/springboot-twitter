plugins {
	java
	id("org.springframework.boot") version "4.0.3"
	id("io.spring.dependency-management") version "1.1.7"
	id("io.freefair.lombok") version "8.13.1"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-webmvc")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-h2console")
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-webmvc-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	implementation("com.mysql:mysql-connector-j")
//    runtimeOnly("com.h2database:h2") // 런타임에만 필요한 , 인메모리 구현 잘 해놓은 라이브러리
}

tasks.withType<Test> {
	useJUnitPlatform()
}
tasks.jar {
    enabled = false
}