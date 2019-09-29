package com.ansgar.harbor


import com.android.build.api.transform.QualifiedContent
import com.android.build.api.transform.Transform
import com.android.build.gradle.AppExtension
import com.android.build.gradle.internal.pipeline.TransformManager
import org.gradle.api.Plugin
import org.gradle.api.Project

class MyPlugin implements Plugin<Project> {

    void apply(Project project) {
        def android = project.extensions.findByType(AppExtension)
        println("Applying plugin")
        android.registerTransform(new MyTransform())
    }

    private static class MyTransform extends Transform {
        @Override
        String getName() {
            return "MyTransform"
        }

        @Override
        Set<QualifiedContent.ContentType> getInputTypes() {
            return TransformManager.CONTENT_CLASS
        }

        @Override
        Set<? super QualifiedContent.Scope> getScopes() {
            return TransformManager.SCOPE_FULL_PROJECT
        }

        @Override
        boolean isIncremental() {
            return false
        }
    }
}