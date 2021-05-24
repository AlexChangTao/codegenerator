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

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.config.po.LikeTable;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import cn.hutool.core.util.StrUtil;
import cn.hutool.setting.dialect.Props;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

/**
 * <p>
 * 代码生成类
 * </p>
 *
 * @Author: Alex
 * @Email: changtao6605@gmail.com
 * @Date: 2021-05-11
 */
public class CodeGenerator {

    public static void main(String[] args) {
        AutoGenerator codeGenerator = new AutoGenerator();
//        String projectPath = System.getProperty("user.dir") + "/codeGenerator";
        String projectPath = System.getProperty("user.dir") + "/";
        String projectModuleName = scanner("项目模块名称");
        String[] dataTableNames = scanner("数据表名，可使用*通配符匹配多个数据表，也可输入多个数据表名，用逗号分割").split(",");
        // 配置生成器
        codeGenerator.setGlobalConfig(initGeneratorConfig(projectPath));
        codeGenerator.setDataSource(initDatabaseConfig());
        codeGenerator.setPackageInfo(initPackageConfig(projectModuleName));
        codeGenerator.setCfg(initInjectionConfig(projectPath, projectModuleName));
        codeGenerator.setTemplate(initTemplateConfig());
        codeGenerator.setStrategy(initStrategyConfig(dataTableNames));
        //使用流行的freemarker模板引擎
        codeGenerator.setTemplateEngine(new FreemarkerTemplateEngine());
        //执行代码生成
        codeGenerator.execute();
    }

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    private static String scanner(String msg) {
        Scanner scanner = new Scanner(System.in);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("请输入").append(msg).append(":");
        System.out.println(stringBuffer.toString());
        if (scanner.hasNext()) {
            String input = scanner.next();
            if (StrUtil.isNotEmpty(input)) {
                return input;
            }
        }
        throw new MybatisPlusException("请输入正确的" + msg + "！");
    }

    /**
     * 初始化模板配置
     */
    private static TemplateConfig initTemplateConfig() {
        TemplateConfig codeGeneratorTemplateConfig = new TemplateConfig();
        //可以对controller、service、entity模板进行配置,后缀不要加,如果注释模板就会直接调用maven包里的模板,下面的模板必须根据自己的需求来修改
//        codeGeneratorTemplateConfig.setEntity("templates/Entity");
        //禁用默认模板
        codeGeneratorTemplateConfig.disable(TemplateType.XML);
        codeGeneratorTemplateConfig.disable(TemplateType.CONTROLLER);
        codeGeneratorTemplateConfig.disable(TemplateType.ENTITY);
        codeGeneratorTemplateConfig.disable(TemplateType.MAPPER);
//        codeGeneratorTemplateConfig.disable(TemplateType.SERVICE);
        codeGeneratorTemplateConfig.setEntity("templates/entity.java");
        codeGeneratorTemplateConfig.setMapper("templates/mapper.xml");
        codeGeneratorTemplateConfig.setMapper("templates/mapper.java");
//        codeGeneratorTemplateConfig.setController("templates/Controller");
        codeGeneratorTemplateConfig.setController("templates/controller.java");
//        codeGeneratorTemplateConfig.setService("templates/Interface");
        codeGeneratorTemplateConfig.setServiceImpl("templates/service.java");
        codeGeneratorTemplateConfig.setServiceImpl("templates/serviceImpl.java");
        codeGeneratorTemplateConfig.setXml(null);
        return codeGeneratorTemplateConfig;
    }

    /**
     * 初始化代码生成器配置
     */
    private static GlobalConfig initGeneratorConfig(String projectPath) {
        //创建代码生成通用配置实例
        GlobalConfig codeGeneratorGlobalConfig = new GlobalConfig();
        codeGeneratorGlobalConfig.setOutputDir(projectPath + "/src/main/java");
        codeGeneratorGlobalConfig.setOpen(false);
        codeGeneratorGlobalConfig.setSwagger2(true);
        codeGeneratorGlobalConfig.setFileOverride(true);
        codeGeneratorGlobalConfig.setBaseResultMap(true);
        codeGeneratorGlobalConfig.setAuthor("Alex");
        codeGeneratorGlobalConfig.setDateType(DateType.ONLY_DATE);
        codeGeneratorGlobalConfig.setEntityName("%s");
        codeGeneratorGlobalConfig.setControllerName("%sController");
        codeGeneratorGlobalConfig.setXmlName("%sMapper");
        codeGeneratorGlobalConfig.setMapperName("%sMapper");
        codeGeneratorGlobalConfig.setServiceName("%sService");
        codeGeneratorGlobalConfig.setServiceImplName("%sServiceImpl");
        return codeGeneratorGlobalConfig;
    }

    /**
     * 数据库配置
     */
    private static DataSourceConfig initDatabaseConfig() {
        DataSourceConfig codeGeneratorDataSourceConfig = new DataSourceConfig();
        Props props = new Props("generator.properties");
        codeGeneratorDataSourceConfig.setDriverName(props.getStr("dataSource.driverName"));
        codeGeneratorDataSourceConfig.setUrl(props.getStr("dataSource.url"));
        codeGeneratorDataSourceConfig.setUsername(props.getStr("dataSource.username"));
        codeGeneratorDataSourceConfig.setPassword(props.getStr("dataSource.password"));
        return codeGeneratorDataSourceConfig;
    }

    /**
     * 代码生成包配置
     */
    private static PackageConfig initPackageConfig(String moduleName) {
        PackageConfig codeGeneratorPackageConfig = new PackageConfig();
        Props props = new Props("generator.properties");
        codeGeneratorPackageConfig.setModuleName(moduleName);
        codeGeneratorPackageConfig.setParent(props.getStr("package.base"));
        codeGeneratorPackageConfig.setEntity("model");
        return codeGeneratorPackageConfig;
    }

    /**
     * 代码生成策略配置
     */
    private static StrategyConfig initStrategyConfig(String[] databaseTableNames) {
        StrategyConfig codeGeneratorStrategyConfig = new StrategyConfig();
        codeGeneratorStrategyConfig.setNaming(NamingStrategy.underline_to_camel);
        codeGeneratorStrategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
//        codeGeneratorStrategyConfig.setSuperEntityClass("com.baomidou.mybatisplus.samples.generator.common.BaseEntity");
        codeGeneratorStrategyConfig.setSuperControllerClass("com.baomidou.mybatisplus.samples.generator.common.BaseController");
        codeGeneratorStrategyConfig.setEntityLombokModel(true);
        codeGeneratorStrategyConfig.setRestControllerStyle(true);
        //当表名中带*号时可以启用通配符模式
        if (1 == databaseTableNames.length  && databaseTableNames[0].contains("*")) {
            String[] tableNameStr = databaseTableNames[0].split("_");
            String likeTableNameStr = tableNameStr[0] + "_";
            codeGeneratorStrategyConfig.setLikeTable(new LikeTable(likeTableNameStr));
        } else {
            codeGeneratorStrategyConfig.setInclude(databaseTableNames);
        }
        return codeGeneratorStrategyConfig;
    }

    /**
     * 代码生成自定义配置
     */
    private static InjectionConfig initInjectionConfig(String projectPath, String projectModuleName) {
        // 创建自定义配置实例
        InjectionConfig codeGeneratorInjectionConfig = new InjectionConfig() {
            @Override
            public void initMap() {
            }
        };
//      获取mapper.xml的模板
        String templatePath = "templates/mapper.xml.ftl";
        // 模板配置列表
        List<FileOutConfig> fileOutConfigList = new ArrayList<>();
        // 添加一个匿名类并自定义mapper的输出路径
        fileOutConfigList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return projectPath + "/src/main/resources/mapper/" + projectModuleName
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        codeGeneratorInjectionConfig.setFileOutConfigList(fileOutConfigList);
        return codeGeneratorInjectionConfig;
    }

}