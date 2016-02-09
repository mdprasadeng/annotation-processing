package com.solvevolve.test;

import com.google.testing.compile.CompileTester;
import com.google.testing.compile.JavaFileObjects;

import com.solvevolve.atp.annotationprocessing.BuilderAnnotationProcessor;

import org.junit.Test;

import java.util.Collections;

import javax.tools.JavaFileObject;

import static com.google.common.truth.Truth.assertAbout;
import static com.google.testing.compile.JavaSourceSubjectFactory.javaSource;

public class BuilderAnnotationTest {

  @Test
  public void processorTest() {

    JavaFileObject beanFile = JavaFileObjects.forResource("Employee.java");
    JavaFileObject builderFile = JavaFileObjects.forResource("EmployeeBuilder.java");

    CompileTester.SuccessfulCompilationClause
        successfulCompilationClause =
        assertAbout(javaSource())
            .that(beanFile)
            .processedWith(Collections.singleton(new BuilderAnnotationProcessor()))
            .compilesWithoutError()
            .and()
            .generatesSources(builderFile);
  }
}
