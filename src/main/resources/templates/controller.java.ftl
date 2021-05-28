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

package ${package.Controller};


import org.springframework.web.bind.annotation.RequestMapping;

import pers.changtao.codegenerator.common.MyPage;
import pers.changtao.codegenerator.common.DataReturn;
import pers.changtao.codegenerator.model.*;
import ${package.Service}.${table.entityName}Service;
import ${package.Entity}.${table.entityName};
import ${package.ServiceImpl}.${table.entityName}ServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>

/**
 * <p>
 * ${table.comment!} 前端控制器
 * </p>
 *
 * @Author: ${author}
 * @Email: changtao6605@gmail.com
 * @Date: ${date}
 */
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@Api(tags = "${table.entityName}Controller", description = "${table.comment!}前端控制器")
@RequestMapping("<#if package.ModuleName??>/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class ${table.controllerName} {
</#if>
  @Autowired
  private ${table.entityName}Service ${table.entityPath}Service;

  private static final Logger logger = LoggerFactory.getLogger(${table.entityName}Controller.class);

    @ApiOperation("添加角色")
    @RequestMapping(value = "/create${table.entityName}", method = RequestMethod.POST)
    @ResponseBody
    public DataReturn create${table.entityName}(@RequestBody ${table.entityName} ${table.entityPath}) {
    int count = ${table.entityPath}Service.create(${table.entityPath});
        if (count > 0) {
            return DataReturn.success(count);
        }
        return DataReturn.failed();
    }

    @ApiOperation("修改角色")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public DataReturn update(@PathVariable Long id, @RequestBody ${table.entityName} ${table.entityPath}) {
        int count = ${table.entityPath}Service.update(id, ${table.entityPath});
        if (count > 0) {
            return DataReturn.success(count);
        }
        return DataReturn.failed();
    }

    @ApiOperation("批量删除角色")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public DataReturn delete(@PathVariable Long id) {
    int count = ${table.entityPath}Service.delete(id);
        if (count > 0) {
            return DataReturn.success(count);
        }
        return DataReturn.failed();
    }

    @ApiOperation("获取所有角色")
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    @ResponseBody
    public DataReturn<List<${table.entityName}>> listAll() {
        List<${table.entityName}> ${table.entityPath}List = ${table.entityPath}Service.list();
        return DataReturn.success(${table.entityPath}List);
     }

    @ApiOperation("修改状态")
    @RequestMapping(value = "/updateStatus/{id}", method = RequestMethod.POST)
    @ResponseBody
    public DataReturn updateStatus(@PathVariable Long id, @RequestBody ${table.entityName} ${table.entityPath}) {
        int count = ${table.entityPath}Service.updateStatus(id, ${table.entityPath});
        if (count > 0) {
            return DataReturn.success(count);
         }
        return DataReturn.failed();
    }

    @ApiOperation("根据角色名称分页获取角色列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public DataReturn<MyPage<${table.entityName}>> list(@RequestParam(value = "keyword", required = false) String keyword,
        @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
        @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<${table.entityName}> ${table.entityPath}List = ${table.entityPath}Service.list(keyword, pageSize, pageNum);
        return DataReturn.success(MyPage.pageResult(${table.entityPath}List));
    }

 }
</#if>
