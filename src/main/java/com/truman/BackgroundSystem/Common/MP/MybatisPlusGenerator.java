package com.truman.BackgroundSystem.Common.MP;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

public class MybatisPlusGenerator {
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://192.168.126.129:3306/graduation",
                "root",
                "Fyh.mysql20000718")
                .globalConfig(builder -> {
                    builder.author("冯有恒") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("C:\\Users\\84583\\Desktop\\宿舍管理系统\\BackgroundSystem\\src\\main\\java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.truman") // 设置父包名
                            .moduleName("BackgroundSystem") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "C:\\Users\\84583\\Desktop\\宿舍管理系统\\BackgroundSystem\\src\\main\\resources\\Mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("application_announce",
                            "application_checkout",
                            "application_late_return",
                            "application_repair",
                            "application_term_finish_checkout",
                            "application_term_start_checkin",
                            "application_water",
                            "dorm_detail",
                            "student_detail",
                            "user_login",
                            "user_role",
                            "worker_detail") // 设置需要生成的表名
                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();

    }
}
