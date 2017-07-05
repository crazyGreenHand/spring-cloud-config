package com.uuzz.demo.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import com.uuzz.demo.bean.Demo;
import com.uuzz.demo.mapper.provider.DemoProvider;

/*使用注解方式*/
public interface DemoMapper {

	@SelectProvider(method = "getDemos", type = DemoProvider.class)
	public List<Demo> getDemos(Demo demo);
	

	@UpdateProvider(method = "updateDemo", type = DemoProvider.class)
	public void updateDemo(Demo demo);
	

	@DeleteProvider(method = "deleteDemo", type = DemoProvider.class)
	public void deleteDemo(Demo demo);
	

	@InsertProvider(method = "addDemo", type = DemoProvider.class)
	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id") 	
	public int addDemo(Demo demo);

	//参数可以是Map
	@SelectProvider(method = "getDemosByParamMap", type = DemoProvider.class)
	public List<Map<String,Object>> getDemosByParamMap(Map<String,Object> paramMap);
	
	@Select(value = { "select * from tbl_demo where id=#{id}" })
	public Demo getDemoById(@Param(value = "id") Integer id);
}
