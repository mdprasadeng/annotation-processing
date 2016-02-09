package com.solvevolve.atp.annotationprocessing;

import java.util.ArrayList;
import java.util.List;

import javax.lang.model.type.DeclaredType;

public class BuilderModel {

  public void setClassName(String className) {
    this.className = className;
  }

  public void setPackageName(String packageName) {
    this.packageName = packageName;
  }

  public void addSetFuncMetadata(SetFuncMetadata setFuncMetadata) {
    this.setFuncMetadataList.add(setFuncMetadata);
  }

  private String className;
  private String packageName;

  private List<SetFuncMetadata> setFuncMetadataList = new ArrayList<SetFuncMetadata>();


  public static class SetFuncMetadata {

    public SetFuncMetadata(String variableName, DeclaredType declaredType) {
      this.varName = variableName;
      this.dataType = declaredType;
    }

    public String getVarName() {
      return varName;
    }

    public void setVarName(String varName) {
      this.varName = varName;
    }



    private String varName;
    private DeclaredType dataType;
  }
}
