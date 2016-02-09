package com.solvevolve.atp.annotationprocessing;

import com.google.auto.service.AutoService;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;

@SupportedAnnotationTypes(
    "com.solvevolve.atp.annotationprocessing.Builder"
)
@SupportedSourceVersion(SourceVersion.RELEASE_7)
@AutoService(Processor.class)
public class BuilderAnnotationProcessor extends AbstractProcessor {

  private Filer filer;
  private Messager messager;
  private Types typeUtils;
  private Elements elementUtils;

  @Override
  public synchronized void init(ProcessingEnvironment processingEnv) {
    this.filer = processingEnv.getFiler();
    this.messager = processingEnv.getMessager();
    this.typeUtils = processingEnv.getTypeUtils();
    this.elementUtils = processingEnv.getElementUtils();
  }

  @Override
  public boolean process(Set<? extends TypeElement> annotations,
                         RoundEnvironment roundEnv) {
    if (annotations.size() == 0) {
      return false;
    } else {
      TypeElement builderAnnotation = annotations.iterator().next();
      Set<? extends Element>
          elementsAnnotated =
          roundEnv.getElementsAnnotatedWith(builderAnnotation);
      for (Element element : elementsAnnotated) {
        BuilderModel model = getModel(element);
        processModel(model);
      }
    }
    return false;
  }

  private void processModel(BuilderModel model) {
    System.out.println("creating file");

    TypeSpec.Builder builderClassBuilder = TypeSpec.classBuilder(model.getClassName())
        .addModifiers(Modifier.PUBLIC)
        .addField(ClassName.get(model.getClassType()), "holder", Modifier.PRIVATE)
        .addMethod(
            MethodSpec.methodBuilder("build")
                .addModifiers(Modifier.PUBLIC)
                .returns(ClassName.get(model.getClassType()))
                .addStatement("return holder")
                .build()
        );



    ClassName builderClassName = ClassName.get(model.getPackageName(), model.getClassName());

    for (BuilderModel.SetFuncMetadata setFuncMetadata : model.getSetFuncMetadataList()) {

      DeclaredType dataType = setFuncMetadata.getDataType();

      MethodSpec setter = MethodSpec.methodBuilder(setFuncMetadata.getVarName())
          .addModifiers(Modifier.PUBLIC)
          .returns(builderClassName)
          .addParameter(ClassName.get(dataType), setFuncMetadata.getVarName())
          .addStatement("this.holder.$L($L)",
                        setFuncMetadata.getExecutableElement().getSimpleName(),
                        setFuncMetadata.getVarName())
          .addStatement("return this")
          .build();

      builderClassBuilder.addMethod(setter);


    }

    TypeSpec buildClass = builderClassBuilder.build();

    JavaFile javaFile = JavaFile.builder(model.getPackageName(), buildClass)
        .build();



    try {
//      javaFile.writeTo(System.out);
      javaFile.writeTo(filer);

    } catch (IOException e) {
      e.printStackTrace();
    }


  }

  private BuilderModel getModel(Element rootElement) {
    if (rootElement.getKind() == ElementKind.CLASS
        || rootElement.getEnclosingElement().getKind() == ElementKind.PACKAGE) {
      BuilderModel builderModel = new BuilderModel();

      TypeElement typeElement = (TypeElement) rootElement;
      PackageElement packageElement = (PackageElement) typeElement.getEnclosingElement();

      builderModel.setClass((DeclaredType) rootElement.asType());
      builderModel.setClassName(rootElement.getSimpleName().toString());
      builderModel.setPackageName(packageElement.getQualifiedName().toString());

      for (Element element : rootElement.getEnclosedElements()) {
        if (element.getKind() == ElementKind.METHOD && element.getSimpleName().toString()
            .startsWith("set")) {
          ExecutableElement executableElement = (ExecutableElement) element;
          executableElement.getEnclosedElements();
          if (executableElement.getReturnType().getKind() == TypeKind.VOID
              && executableElement.getParameters().size() == 1) {
            VariableElement variableElement = executableElement.getParameters().get(0);
            String variableName = variableElement.getSimpleName().toString();
            DeclaredType declaredType = (DeclaredType) variableElement.asType();
            builderModel.addSetFuncMetadata(
                new BuilderModel.SetFuncMetadata(variableName, declaredType, executableElement));

          }
        }
      }
      return builderModel;
    } else {
      this.messager.printMessage(Diagnostic.Kind.ERROR, "Applicable only to top level Classes");
    }
    return null;
  }
}
