package com.solvevolve.atp.annotationprocessing;

import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.type.DeclaredType;

public class BuilderModel {

  private DeclaredType classType;

  public void setClassName(String className) {
    this.className = className + "Builder";
  }

  public void setPackageName(String packageName) {
    this.packageName = packageName;
  }

  public void addSetFuncMetadata(SetFuncMetadata setFuncMetadata) {
    this.setFuncMetadataList.add(setFuncMetadata);
  }

  public String getClassName() {
    return className;
  }

  public String getPackageName() {
    return packageName;
  }

  public List<SetFuncMetadata> getSetFuncMetadataList() {
    return setFuncMetadataList;
  }

  private String className;
  private String packageName;

  private List<SetFuncMetadata> setFuncMetadataList = new ArrayList<SetFuncMetadata>();

  public DeclaredType getClassType() {
    return classType;
  }

  public void setClass(DeclaredType aClass) {
    this.classType = aClass;
  }


  public static class SetFuncMetadata {


    public ExecutableElement getExecutableElement() {
      return executableElement;
    }

    private final ExecutableElement executableElement;

    public SetFuncMetadata(String variableName, DeclaredType declaredType, ExecutableElement executableElement) {
      this.varName = variableName;
      this.dataType = declaredType;
      this.executableElement = executableElement;
    }

    public String getVarName() {
      return varName;
    }

    public void setVarName(String varName) {
      this.varName = varName;
    }


    private String varName;

    public DeclaredType getDataType() {
      return dataType;
    }

    private DeclaredType dataType;
  }
}
