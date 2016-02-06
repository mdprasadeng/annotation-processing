package com.solvevolve.atp.annotationprocessing;

import com.google.auto.service.AutoService;
import com.google.common.collect.Sets;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;

@SupportedAnnotationTypes(
    "com.solvevolve.atp.annotationprocessing.Builder"
)
@AutoService(Processor.class)
public class BuilderAnnotationProcessor extends AbstractProcessor {

  @Override
  public synchronized void init(ProcessingEnvironment processingEnv) {

  }

  @Override
  public boolean process(Set<? extends TypeElement> annotations,
                         RoundEnvironment roundEnv) {
    return false;
  }

  @Override
  public Set<String> getSupportedAnnotationTypes() {
    return Sets.newHashSet(
        "com.solvevolve.atp.annotationprocessing.Builder");
  }

  @Override
  public SourceVersion getSupportedSourceVersion() {
    return SourceVersion.latestSupported();
  }
}
