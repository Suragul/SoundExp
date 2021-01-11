import com.soywiz.korge.gradle.*
import com.soywiz.korge.gradle.util.get

buildscript {
	val korgePluginVersion: String by project

	repositories {
		mavenLocal()
		maven { url = uri("https://dl.bintray.com/korlibs/korlibs") }
		maven { url = uri("https://plugins.gradle.org/m2/") }
		mavenCentral()
		google()
	}
	dependencies {
		classpath("com.soywiz.korlibs.korge.plugins:korge-gradle-plugin:$korgePluginVersion")
	}
}

apply<KorgeGradlePlugin>()

korge {
	id = "com.example.example"
	version = "1.0.0"
	icon = projectDir["src/commonMain/resources/logo.png"]
	orientation = Orientation.PORTRAIT
	fullscreen = true
	targetJvm()
	targetAndroidIndirect() // targetAndroidDirect()
}