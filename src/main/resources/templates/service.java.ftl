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

package ${package.Service};

import ${package.Entity}.${entity};
import ${superServiceClassPackage};
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * <p>
 * ${table.comment!} 服务接口类
 * </p>
 *
 * @Author: ${author}
 * @Email: changtao6605@gmail.com
 * @Date: ${date}
 */
<#if kotlin>
interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>
public interface ${table.serviceName} {
 /**
  * 添加${table.entityPath}
  */
  int create(${entity} ${table.entityPath});

 /**
  * 修改${table.entityPath}信息
  */
  int update(Long id, ${entity} ${table.entityPath});

 /**
  * 指定id删除${table.entityPath}
  */
  int delete(Long id);

 /**
  * 批量删除${table.entityPath}
  */
  int delete(List<Long> ids);

 /**
  * 查询数量${table.entityPath}
  */
  Long count();

 /**
  * 获取所有${table.entityPath}列表
  */
  List<${entity}> list();

 /**
  * 分页获取${table.entityPath}列表
  */
  List<${entity}> list(String keyword, Integer pageSize, Integer pageNum);

 /**
  * 获取${table.entityPath}相关菜单
  */
  List<UmsMenu> listMenu(Long ${table.entityPath}Id);

 /**
  * 获取${table.entityPath}相关资源
  */
  ${entity} listResource(Integer key);

  /**
   * 获取${table.entityPath}的item
   */
   public ${entity} getItem(Long id);

}
</#if>
