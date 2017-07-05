package com.uuzz.demo.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.uuzz.demo.bean.Demo;

public interface IDemoService {

	/**
	 * 
	* @Title: getDemos  
	* @Description: 获取所有的demo 
	* @param @return
	* @return List<Demo>
	* @throws
	 */
	public List<Demo> getDemos(Demo demo);
	
	/**
	 * 
	* @Title: updateDemo  
	* @Description: 更新demo 
	* @param @param demo
	* @return void
	* @throws
	 */
	@Transactional
	public void updateDemo(Demo demo);
	
	/**
	 * 
	* @Title: deleteDemo  
	* @Description: 删除Demo  
	* @param @param demo
	* @return void
	* @throws
	 */
	public void deleteDemo(Demo demo);
	
	/**
	 * 
	* @Title: addDemo  
	* @Description: 添加demo 
	* @param @param demo
	* @param @return
	* @return boolean
	* @throws
	 */
	public int addDemo(Demo demo);
}
