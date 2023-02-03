import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.0"
    id("java-gradle-plugin")
}

repositories {
    google()
    jcenter()
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions.apiVersion = "1.3"
}

dependencies {
    implementation("com.android.tools.build:gradle-api:7.4.0")
    implementation(kotlin("stdlib"))
    gradleApi()
}

// 也可以使用resources路径创建文件的方式注册插件
gradlePlugin{
    plugins {
        create("MyPlugin") {
            id = "MyPlugin"
            implementationClass = "com.shixforever.plugin.MyPlugin"
        }
    }
}