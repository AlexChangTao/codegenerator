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

package ${package.ServiceImpl};

import ${package.Entity}.${entity};
import ${package.Entity}.${entity}Example;
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import ${superServiceImplClassPackage};
import com.github.pagehelper.PageHelper;
import com.alibaba.fastjson.JSONArray;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * ${table.comment!} 服务实现类
 * </p>
 *
 * @Author: ${author}
 * @Email: changtao6605@gmail.com
 * @Date: ${date}
 */
@Service
<#if kotlin>
open class ${table.serviceImplName} : ${superServiceImplClass}<${table.mapperName}, ${entity}>(), ${table.serviceName} {

}
<#else>
public class ${table.serviceImplName} implements ${table.serviceName} {
    @Autowired
    private ${entity}Mapper ${table.entityPath}Mapper;
    @Autowired
    private ${entity} ${table.entityPath};
    @Autowired
    private UmsAdminCacheService adminCacheService;
    @Override
    public int create(${entity} ${table.entityPath}) {
        ${table.entityPath}.setCreatetime(new Date());
        ${table.entityPath}.setLasttime(new Date());
        return ${table.entityPath}Mapper.insert(${table.entityPath});
    }
    
    @Override
    public int update(Long id, ${entity} ${table.entityPath}) {
        ${table.entityPath}.set${entity}Id(Integer.parseInt(String.valueOf(id)));
        return ${table.entityPath}Mapper.updateByPrimaryKeySelective(${table.entityPath});
    }

    @Override
    public int updateStatus(byte id, ${entity} ${table.entityPath}) {
        ${entity}Example example = new ${entity}Example();
        example.createCriteria().and${entity}IdEqualTo(Integer.parseInt(String.valueOf(id)));
        return ${table.entityPath}Mapper.updateByExampleSelective(${table.entityPath}, example);
    }

    @Override
    public int delete(Long id) {
        ${entity}Example example = new ${entity}Example();
        example.createCriteria().and${entity}IdEqualTo(Integer.parseInt(String.valueOf(id)));
        int count = ${table.entityPath}Mapper.deleteByExample(example);
        return count;
    }

    @Override
    public int delete(List<Long> ids) {
        ${entity}Example example = new ${entity}Example();
        List<Integer> listInt = JSONArray.parseArray(ids.toString(),Integer.class);
        example.createCriteria().and${entity}IdIn(ids);
        int count = ${table.entityPath}Mapper.deleteByExample(example);
        return count;
    }

    @Override
    public Long count() {
        return ${table.entityPath}Mapper.countByExample(new ${entity}Example());
    }
    
    @Override
    public List<${entity}> list() {
        return ${table.entityPath}Mapper.selectByExample(new ${entity}Example());
    }
    
    @Override
    public List<${entity}> list(String keyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        ${entity}Example example = new ${entity}Example();
        if (!StringUtils.isEmpty(keyword)) {
            example.createCriteria().and${entity}NameLike("%" + keyword + "%");
        }
        return ${table.entityPath}Mapper.selectByExample(example);
    }
    
    @Override
    public List<UmsMenu> listMenu(Long ${table.entityPath}Id) {
        return ${table.entityPath}Dao.getMenuListBy${entity}Id(${table.entityPath}Id);
    }

    @Override
    public ${entity} getItem(Long id) {
        return ${table.entityPath}Mapper.selectByPrimaryKey(Integer.parseInt(String.valueOf(id)));
    }

    @Override
    public ${entity} listResource(Integer key) {
        return  ${table.entityPath}Mapper.selectByPrimaryKey(key);
    }

}
</#if>
