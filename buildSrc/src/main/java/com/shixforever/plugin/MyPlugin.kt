package com.shixforever.plugin

import com.android.build.api.instrumentation.FramesComputationMode
import com.android.build.api.instrumentation.InstrumentationScope
import com.android.build.api.variant.AndroidComponentsExtension
import com.vivo.warnsdkplugin.AopTransform
import org.gradle.api.Plugin
import org.gradle.api.Project

abstract class MyPlugin :Plugin<Project>{

    override fun apply(project: Project) {
        println("===进入插件===")
        val androidComponents = project.extensions.getByType(AndroidComponentsExtension::class.java)
        androidComponents.onVariants { variant ->
            variant.instrumentation.transformClassesWith(
                AopTransform::class.java,
                InstrumentationScope.ALL) {}
            variant.instrumentation.setAsmFramesComputationMode(FramesComputationMode.COPY_FRAMES)
        }
    }
}