/*
 * Copyright 2010-2016 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jetbrains.kotlin.codegen

import org.jetbrains.kotlin.cli.jvm.config.addJvmClasspathRoot
import org.jetbrains.kotlin.config.CompilerConfiguration
import org.jetbrains.kotlin.test.ConfigurationKind
import java.io.File

abstract class AbstractBlackBoxAgainstJavaCodegenTest : AbstractBlackBoxCodegenTest() {
    override fun doMultiFileTest(wholeFile: File, files: MutableList<TestFile>, javaFilesDir: File?, coroutinesPackage: String) {
        javaClassesOutputDirectory = javaFilesDir!!.let { directory ->
            CodegenTestUtil.compileJava(CodegenTestUtil.findJavaSourcesInDirectory(directory), emptyList(), extractJavacOptions(files))
        }

        super.doMultiFileTest(wholeFile, files, null, coroutinesPackage)
    }

    override fun updateConfiguration(configuration: CompilerConfiguration) {
        configuration.addJvmClasspathRoot(javaClassesOutputDirectory)
    }

    override fun extractConfigurationKind(files: MutableList<TestFile>): ConfigurationKind {
        return ConfigurationKind.ALL
    }
}
