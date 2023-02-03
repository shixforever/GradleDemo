package com.shixforever.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

abstract class MyPlugin :Plugin<Project>{

    override fun apply(p0: Project) {
        println("===进入插件===")
    }
}