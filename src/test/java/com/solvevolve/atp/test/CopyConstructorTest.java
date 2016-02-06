package com.solvevolve.atp.test;

import com.google.testing.compile.JavaFileObjects;

import com.solvevolve.atp.CopyConstructorAnnotationProcessor;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;

import javax.tools.JavaFileObject;

import static com.google.common.truth.Truth.assertAbout;
import static com.google.testing.compile.JavaSourceSubjectFactory.javaSource;

public class BuilderAnnotationTest {

  @Test
  public void processorTest() {
    String code = "package test;\n"
                  + "import com.solvevolve.atp.CopyConstructor;\n"
                  + "@CopyConstructor\n"
                  + "public class User {\n"
                  + "  private String name;\n"
                  + "  private String emailId;\n"
                  + "\n"
                  + "  public static void main(String[] args) {\n"
                  + "    System.out.println(\"Hello\");\n"
                  + "    User user = new User();\n"
                  + "  }\n"
                  + "}\n";

    JavaFileObject inputFile = JavaFileObjects.forSourceString("User", code);

    Collection<CopyConstructorAnnotationProcessor>
        processors =
        Arrays.asList(new CopyConstructorAnnotationProcessor());


    assertAbout(javaSource()).that(inputFile).processedWith(processors).compilesWithoutError();
  }
}
