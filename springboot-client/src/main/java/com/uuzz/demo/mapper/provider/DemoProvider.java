package com.uuzz.demo.mapper.provider;

import java.text.SimpleDateFormat;
import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import com.uuzz.demo.bean.Demo;

public class DemoProvider {

	private static final String TBL_DEMO = "tbl_demo";
	
	public String getDemos(Demo demo){
		
		SQL sql = new SQL().SELECT("*").FROM(TBL_DEMO);
		
		if(demo == null){
			return sql.toString();
		}
		if(demo.getId()!=null){
			sql.WHERE("id=#{id}");
		}
		if(demo.getNo()!=null){
			sql.WHERE("no=#{no}");
		}
		if(demo.getName()!=null){
			sql.WHERE("name=#{name}");
		}
		return sql.toString();
	}
	
	public String updateDemo(Demo demo){
		
		if(demo == null){
			return null;
		}
		SQL sql = new SQL().UPDATE(TBL_DEMO);
				
		if(demo.getNo()!=null){
			sql.SET("no=#{no}");
		}
		if(demo.getName()!=null){
			sql.SET("name=#{name}");
		}
		sql.WHERE("id=#{id}");
		
		return sql.toString();
	}
	
	public String deleteDemo(Demo demo){
		
		if(demo == null){
			return null;
		}		
		SQL sql = new SQL().DELETE_FROM(TBL_DEMO);
		
		if(demo.getId()!=null){
			sql.WHERE("id=#{id}");
		}
		if(demo.getNo()!=null){
			sql.WHERE("no=#{no}");
		}
		if(demo.getName()!=null){
			sql.WHERE("name=#{name}");
		}
		return sql.toString();
	}
	
	public String addDemo(Demo demo){
		
		if(demo == null){
			return null;
		}
		SQL sql = new SQL().INSERT_INTO(TBL_DEMO);
		
        if(demo.getNo()!=null){
        	sql.VALUES("no", "#{no}");
		}
		if(demo.getName()!=null){
			sql.VALUES("name", "#{name}");
		}
		if(demo.getCreateTime()!=null){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");		
			String createTime = sdf.format(demo.getCreateTime());
			sql.VALUES("createTime", "str_to_date('"+createTime+"','%Y-%m-%d %T')");
		}
		return sql.toString();
	}
	
	public String getDemosByParamMap(Map<String,Object> paramMap){
		
        SQL sql = new SQL().SELECT("*").FROM(TBL_DEMO);
		
		if(paramMap == null){
			return sql.toString();
		}
		if(paramMap.get("id")!=null){
			sql.WHERE("id=#{id}");
		}
		if(paramMap.get("no")!=null){
			sql.WHERE("no=#{no}");
		}
		if(paramMap.get("name")!=null){
			sql.WHERE("name=#{name}");
		}
		return sql.toString();
	}
	
	public String getDemoById(Integer id){
		
		return new SQL().SELECT("*")
				.FROM("tbl_demo")
				.WHERE("id=#{id}").toString();
	}
}
