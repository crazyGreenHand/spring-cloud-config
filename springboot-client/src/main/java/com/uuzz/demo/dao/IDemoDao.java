package com.uuzz.demo.dao;

import java.util.List;

import com.uuzz.demo.bean.Demo;

/*使用xml方式*/
public interface IDemoDao {

	public List<Demo> getDemos(Demo demo);
	
	public void updateDemo(Demo demo);
	
	public void deleteDemo(Demo demo);
	
	public int addDemo(Demo demo);
	
	
}
