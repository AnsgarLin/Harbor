package com.ansgar.harbor

import com.android.build.gradle.AppExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

class HarborPlugin implements Plugin<Project> {
    void apply(Project project) {
        def android = project.extensions.findByType(AppExtension)
        println("Applying plugin")
        android.registerTransform(new HarborTransform(android))
    }
}