package com.ansgar.harbor

import com.android.build.api.transform.Format
import com.android.build.api.transform.QualifiedContent
import com.android.build.api.transform.Transform
import com.android.build.api.transform.TransformInvocation
import com.android.build.gradle.AppExtension
import com.android.build.gradle.internal.pipeline.TransformManager
import com.android.utils.FileUtils

class HarborTransform(private val android: AppExtension) : Transform() {
    override fun getName(): String = "com.ansgar.harbor.HarborTransform"

    @Override
    override fun getInputTypes(): MutableSet<QualifiedContent.ContentType> = TransformManager.CONTENT_CLASS

    override fun getScopes(): MutableSet<in QualifiedContent.Scope> = TransformManager.SCOPE_FULL_PROJECT

    override fun isIncremental(): Boolean = false

    override fun transform(transformInvocation: TransformInvocation?) {

        transformInvocation?.inputs?.forEach {
            it.directoryInputs.forEach { directoryInput ->
                with(directoryInput) {
                    FileUtils.copyDirectory(file, transformInvocation.outputProvider.getContentLocation(
                        directoryInput.name,
                        directoryInput.contentTypes,
                        directoryInput.scopes,
                        Format.DIRECTORY
                    ))
                }
            }

            it.jarInputs.forEach { jarInput ->
                with(jarInput) {
                    FileUtils.copyFile(file, transformInvocation.outputProvider.getContentLocation(
                        jarInput.name,
                        jarInput.contentTypes,
                        jarInput.scopes,
                        Format.JAR
                    ))
                }
            }
        }
    }
}