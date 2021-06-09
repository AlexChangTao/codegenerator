package ${package_name}.service;

import ${package_name}.request.${table_name}Request;
import ${package_name}.response.BaseResponse;
import ${package_name}.entity.${table_name};
import com.baomidou.mybatisplus.service.IService;

/**
* 描述：${table_annotation}服务实现层接口
* @author ${author}
* @date ${date}
*/
public interface ${table_name}Service extends IService<${table_name}>{

    public BaseResponse page(${table_name}Request ${table_name_small}Request);

}