package com.jnshu.dreamteam;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;


import java.util.ArrayList;
import java.util.List;


public class MybatisPlusConfig {

//    private static final String PACKAGENAME = "com.jnshu.dreamteam";
//
//
//    public static void main(String[] args) {
//        AutoGenerator mpg = new AutoGenerator();
//        // 选择 freemarker 引擎，默认 Veloctiy
//        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
//
//        //全局配置
//        GlobalConfig gc = new GlobalConfig();
//        gc.setAuthor("wzp");
//        String projectPath = System.getProperty("user.dir");
//        gc.setOutputDir(projectPath + "/src/main/java");
//        gc.setFileOverride(true);// 是否覆盖同名文件，默认是false
//        gc.setBaseResultMap(true);// XML ResultMap
//        // 自定义文件命名，注意 %s 会自动填充表实体属性！
//        gc.setMapperName("%sMapper");
//        gc.setServiceName("%sService");
//        gc.setServiceImplName("%sServiceImpl");
//        mpg.setGlobalConfig(gc);
//
//        // 数据源配置
//        DataSourceConfig dsc = new DataSourceConfig();
//        dsc.setDbType(DbType.MYSQL);
//        dsc.setUrl("jdbc:mysql://188.131.193.208:3306/replay_project?useUnicode=true&characterEncoding=utf-8&useSSL=false");
//        // dsc.setSchemaName("public");
//        dsc.setDriverName("com.mysql.jdbc.Driver");
//        dsc.setUsername("root");
//        dsc.setPassword("root");
//        mpg.setDataSource(dsc);
//
//        // 表策略配置
//        StrategyConfig strategy = new StrategyConfig();
//        // 表名生成策略
//        strategy.setNaming(NamingStrategy.underline_to_camel);   //下划线转变为驼峰
//        //类名生成策略
//        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//        // 需要生成的表
//        strategy.setInclude("lesson");
//        strategy.entityTableFieldAnnotationEnable(true);
//        mpg.setStrategy(strategy);
//
//        // 包配置
//        PackageConfig pc = new PackageConfig();
//        pc.setParent(PACKAGENAME);
//        pc.setEntity("pojo");
//        pc.setMapper("mapper");
//        mpg.setPackageInfo(pc);
//
//        // 注入自定义配置，可以在 VM 中使用 cfg.abc 【可无】
//        InjectionConfig cfg = new InjectionConfig() {
//            @Override
//            public void initMap() {
////                Map<String, Object> map = new HashMap<String, Object>();
////                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
////                this.setMap(map);
//            }
//        };
//        // 自定义 xxList.jsp 生成
//        List<FileOutConfig> focList = new ArrayList<FileOutConfig>();
////        focList.add(new FileOutConfig("/template/list.jsp.vm") {
////            @Override
////            public String outputFile(TableInfo tableInfo) {
////                // 自定义输入文件名称
////                return "D://my_" + tableInfo.getEntityName() + ".jsp";
////            }
////        });
////        cfg.setFileOutConfigList(focList);
////        mpg.setCfg(cfg);
//        // 调整 xml 生成目录演示
////        focList.add(new FileOutConfig("/templates/mapper.xml.vm") {
////            @Override
////            public String outputFile(TableInfo tableInfo) {
////                return "/develop/code/xml/" + tableInfo.getEntityName() + ".xml";
////            }
//        focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
//            @Override
//            public String outputFile(TableInfo tableInfo) {
//                // 自定义输入文件名称
//                return projectPath + "/src/main/resources/mapper/"
//                        + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
//            }
//        });
//        cfg.setFileOutConfigList(focList);
//        mpg.setCfg(cfg);
//
//        // 关闭默认 xml 生成，调整生成 至 根目录
//        TemplateConfig tc = new TemplateConfig();
//        tc.setXml(null);
//        mpg.setTemplate(tc);
//        // 执行生成
//        mpg.execute();
//
//
//
//    }


}
