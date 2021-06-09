package pers.changtao.codegenerator.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * MyBatis配置类
 * </p>
 *
 * @Author: Alex
 * @Email: changtao6605@gmail.com
 * @Date: 2021-05-11
 */
@Configuration
@MapperScan("pers.changtao.codegenerator.modules.*.mapper")
public class MyBatisConfig {
}
