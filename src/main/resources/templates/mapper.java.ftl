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

package ${package.Mapper};

import ${package.Entity}.${entity};
import ${superMapperClassPackage};
import org.apache.ibatis.annotations.Param;
import ${package.Entity}.${entity}Example;

import java.util.List;

/**
 * <p>
 * ${table.comment!} mapper接口
 * </p>
 *
 * @Author: ${author}
 * @Email: changtao6605@gmail.com
 * @Date: ${date}
 */
<#if kotlin>
interface ${table.mapperName} : ${superMapperClass}<${entity}>
<#else>
public interface ${table.mapperName} {
   long countByExample(${entity}Example example);

   int deleteByExample(${entity}Example example);

   int deleteByPrimaryKey(Long id);

   int insert(${entity} record);

   int insertSelective(${entity} record);

   List<${entity}> selectByExample(${entity}Example example);

   ${entity} selectByPrimaryKey(Long id);

   int updateByExampleSelective(@Param("record") ${entity} record, @Param("example") ${entity}Example example);

   int updateByExample(@Param("record") ${entity} record, @Param("example") ${entity}Example example);

   int updateByPrimaryKeySelective(${entity} record);

   int updateByPrimaryKey(${entity} record);
}
</#if>
