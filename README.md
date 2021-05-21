# 自动代码生成

## 项目介绍
CodeGenerator项目基于mybatis-plus进行大量优化和改进，解决了mybatis-plus默认只能生成类头和极少数代码的问题，通过精心编辑调试的
freemarker模板能一次性生成多个表的所有常用类，包括实体类，控制器，接口类，服务类，服务实现类，mapper类等，减少了工作量，提高了
程序员的开发效率！
使用简单，只需要下载此项目，配置好application.yml文件，generator.properies文件和数据库，随便新建几表表，就能开始生成数据表对应的
代码。

CodeGenerator生成器默认不覆盖源文件，如果要重新生成并覆盖，需要配置GlobalConfig.setFileOverride(true)

自定义模板在codegenerator\src\main\resources\templates，可根据自己需要修改。