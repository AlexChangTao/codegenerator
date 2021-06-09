/*
 *  Copyright 2020-2021 ChangTao
 *  Email changtao6605@gmail.com
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package pers.changtao.codegenerator.generator;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.config.MergeConstants;
import org.mybatis.generator.config.PropertyRegistry;
import org.mybatis.generator.internal.DefaultCommentGenerator;
import org.mybatis.generator.internal.util.StringUtility;
import static org.mybatis.generator.internal.util.StringUtility.isTrue;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Set;


/**
 * Mybatis代码注释生成器
 */
public class MybatisCommentGenerator extends DefaultCommentGenerator implements CommentGenerator {
    private boolean isAddRemarkComments = false;
    private Properties myproperties;
    private Properties sysProperties;
    private boolean suppressDate;
    private boolean suppressAllComments;
    private String currentDateFormat;
    private static final String MODEL_CLASS_SUFFIX ="Example";
    private static final String SWAGGER_MODEL_PROPERTY ="io.swagger.annotations.ApiModelProperty";

    public MybatisCommentGenerator() {
        super();
        myproperties = new Properties();
        sysProperties = System.getProperties();
        suppressDate = false;
        suppressAllComments = false;
        currentDateFormat = (new SimpleDateFormat("yyyy-MM-dd")).format(new Date());
    }

    public void addConfigurationProperties(Properties properties) {
//        super.addConfigurationProperties(myproperties);
        this.isAddRemarkComments = StringUtility.isTrue(properties.getProperty("isAddRemarkComments"));
        this.myproperties.putAll(properties);
        suppressDate = isTrue(properties.getProperty(PropertyRegistry.COMMENT_GENERATOR_SUPPRESS_DATE));
        suppressAllComments = isTrue(properties.getProperty(PropertyRegistry.COMMENT_GENERATOR_SUPPRESS_ALL_COMMENTS));
    }

    /**
     * 模型类字段添加注释
     * @param javaElement the java element
     */
    protected void addJavadocTag(JavaElement javaElement, boolean markNotDelete) {
//        javaElement.addJavaDocLine(" *");
        StringBuilder sb = new StringBuilder();
        sb.append(" * ");
        sb.append(MergeConstants.NEW_ELEMENT_TAG);
        if (markNotDelete) {
            sb.append(" do not delete during merge");
        }
        String s = getDateString();
        if (s != null) {
            sb.append(' ');
            sb.append(s);
        }
        javaElement.addJavaDocLine(sb.toString());
    }

    @Override
    public void addFieldAnnotation(Field field, IntrospectedTable introspectedTable,
                                   IntrospectedColumn introspectedColumn, Set<FullyQualifiedJavaType> imports) {
        imports.add(new FullyQualifiedJavaType("javax.annotation.Generated"));
        String comment = "Source field: "
                + introspectedTable.getFullyQualifiedTable().toString()
                + "."
                + introspectedColumn.getActualColumnName();
        field.addAnnotation((comment));
        if (!suppressAllComments && isAddRemarkComments) {
            String remarks = introspectedColumn.getRemarks();
            if (isAddRemarkComments && StringUtility.stringHasValue(remarks)) {
                field.addJavaDocLine("/**");
                field.addJavaDocLine(" * Database Column Remarks:");
                String[] remarkLines = remarks.split(System.getProperty("line.separator"));
                for (String remarkLine : remarkLines) {
                    field.addJavaDocLine(" *   " + remarkLine);
                }
                field.addJavaDocLine(" */");
            }
        }
    }

    @Override
    public void addClassAnnotation(InnerClass innerClass, IntrospectedTable introspectedTable, Set<FullyQualifiedJavaType> set) {
    }

    public void addClassComment(InnerClass innerClass, IntrospectedTable table) {
        if (suppressAllComments) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        innerClass.addJavaDocLine("/**");
        sb.append(" * ");
        sb.append(table.getFullyQualifiedTable());
        sb.append(" ");
        sb.append(getDateString());
        innerClass.addJavaDocLine(sb.toString());
        innerClass.addJavaDocLine(" */");
    }

    /**
     * 获取日期字符串
     * @return a string representing the current timestamp, or null
     */
    protected String getDateString() {
        String result = null;
        if (!suppressDate) {
            result = currentDateFormat;
        }
        return result;
    }


    public void addEnumComment(InnerEnum innerEnum, IntrospectedTable table) {
        if (suppressAllComments) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        innerEnum.addJavaDocLine("/**");
        sb.append(" * ");
        sb.append(table.getFullyQualifiedTable());
        innerEnum.addJavaDocLine(sb.toString());
        innerEnum.addJavaDocLine(" */");
    }

    public void addFieldComment(Field field, IntrospectedTable table,
                                IntrospectedColumn column) {
        if (suppressAllComments) {
            return;
        }
        field.addJavaDocLine("/**");
        String remarks = column.getRemarks();
        if (isAddRemarkComments && StringUtility.stringHasValue(remarks)) {
//            field.addJavaDocLine(" * Database Table Column Remarks:");
            String[] remarkStrs = remarks.split(System.getProperty("line.separator"));
            for (String remarkStr : remarkStrs) {
                field.addJavaDocLine(" * " + remarkStr);
            }
        }
//        field.addJavaDocLine(" * This field was generated by MyBatis Generator.");
        StringBuilder sb = new StringBuilder();
//        sb.append(" * This field corresponds to the database column ");
        sb.append(" * ");
        sb.append(table.getFullyQualifiedTable());
        sb.append('.');
        sb.append(column.getActualColumnName());
        field.addJavaDocLine(sb.toString());
        addJavadocTag(field, false);
        field.addJavaDocLine(" */");
        //为模型类字段添加swagger注解
        field.addJavaDocLine("@ApiModelProperty(value = \""+remarks+"\")");
    }

    public void addFieldComment(Field field, IntrospectedTable table) {
        if (suppressAllComments) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        field.addJavaDocLine("/**");
        sb.append(" * ");
        sb.append(table.getFullyQualifiedTable());
        field.addJavaDocLine(sb.toString());
        field.addJavaDocLine(" */");
    }

    public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {

    }

    public void addGeneralMethodComment(Method method, IntrospectedTable introspectedTable) {
        if (suppressAllComments) {
            return;
        }

    }

    public void addGetterComment(Method method, IntrospectedTable table,
                                 IntrospectedColumn column) {
        if (suppressAllComments) {
            return;
        }
        method.addJavaDocLine("/**");
        StringBuilder sb = new StringBuilder();
        sb.append(" * ");
        sb.append(column.getRemarks());
        method.addJavaDocLine(sb.toString());
        sb.setLength(0);
        sb.append(" * @return ");
        sb.append(column.getActualColumnName());
        sb.append(" ");
        sb.append(column.getRemarks());
        method.addJavaDocLine(sb.toString());
        //      addJavadocTag(method, false);
        method.addJavaDocLine(" */");
    }

    public void addSetterComment(Method method, IntrospectedTable table,
                                 IntrospectedColumn column) {
        if (suppressAllComments) {
            return;
        }
        method.addJavaDocLine("/**");
        StringBuilder sb = new StringBuilder();
        sb.append(" * ");
        sb.append(column.getRemarks());
        method.addJavaDocLine(sb.toString());
        Parameter parm = method.getParameters().get(0);
        sb.setLength(0);
        sb.append(" * @param ");
        sb.append(parm.getName());
        sb.append(" ");
        sb.append(column.getRemarks());
        method.addJavaDocLine(sb.toString());
        // addJavadocTag(method, false);
        method.addJavaDocLine(" */");
    }

    public void addClassComment(InnerClass innerClass, IntrospectedTable table, boolean markNotDelete) {
        if (suppressAllComments) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        innerClass.addJavaDocLine("/**");
        sb.append(" * ");
        sb.append(table.getFullyQualifiedTable());
        innerClass.addJavaDocLine(sb.toString());
        sb.setLength(0);
        sb.append(" * @author ");
        sb.append(sysProperties.getProperty("user.name"));
        sb.append(" ");
        sb.append(currentDateFormat);
        innerClass.addJavaDocLine(" */");
    }

    @Override
    public void addJavaFileComment(CompilationUnit swaggerCompilationUnit) {
        super.addJavaFileComment(swaggerCompilationUnit);
        //只在model中添加swagger注解类的导入
        if(!swaggerCompilationUnit.isJavaInterface() && !swaggerCompilationUnit.getType().getFullyQualifiedName().contains(MODEL_CLASS_SUFFIX)){
            swaggerCompilationUnit.addImportedType(new FullyQualifiedJavaType(SWAGGER_MODEL_PROPERTY));
        }
    }

    /**
     * 模型类字段添加注释
     */
    private void addFieldJavaDoc(Field field, String remarks) {
        field.addJavaDocLine("/**");
        String[] remarkStrs = remarks.split(System.getProperty("line.separator"));
        for(String remarkStr:remarkStrs){
            field.addJavaDocLine(" * "+remarkStr);
        }
        addJavadocTag(field, false);
        field.addJavaDocLine(" */");
    }

    public void addComment(XmlElement xmlElement) {
        return;
    }

    public void addRootComment(XmlElement rootElement) {
        // add no document level comments by default
        return;
    }

    @Override
    public void addGeneralMethodAnnotation(Method method, IntrospectedTable introspectedTable, Set<FullyQualifiedJavaType> set) {

    }

    @Override
    public void addGeneralMethodAnnotation(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn, Set<FullyQualifiedJavaType> set) {

    }

    @Override
    public void addFieldAnnotation(Field field, IntrospectedTable introspectedTable, Set<FullyQualifiedJavaType> set) {

    }

}