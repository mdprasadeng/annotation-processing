package com.solvevolve.atp;

import com.google.auto.service.AutoService;
import com.google.common.collect.Sets;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

@AutoService(value = Processor.class)
public class CopyConstructorAnnotationProcessor extends AbstractProcessor {
  Messager messager;

  public CopyConstructorAnnotationProcessor() {
    System.out.println("test");
  }

  @Override
  public synchronized void init(ProcessingEnvironment processingEnv) {
    super.init(processingEnv);
    this.messager = processingEnv.getMessager();
    System.out.println("testing annotation processing");
    messager.printMessage(Diagnostic.Kind.ERROR, "Its not going to work");
  }

  @Override
  public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
    messager.printMessage(Diagnostic.Kind.ERROR, "Its not going to work");
    return false;
  }

  @Override
  public SourceVersion getSupportedSourceVersion() {
    return SourceVersion.latestSupported();
  }

  @Override
  public Set<String> getSupportedAnnotationTypes() {
    return Sets.newHashSet(CopyConstructor.class.getName());
  }
}
