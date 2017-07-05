package com.uuzz.demo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.uuzz.config.redis.RedisHandler;
import com.uuzz.demo.bean.Demo;
import com.uuzz.demo.dao.IDemoDao;
import com.uuzz.demo.service.IDemoService;

@Service("demoService")
public class DemoServiceImp implements IDemoService{

	/*@Autowired
	private DemoMapper demoMapper;*/
	
	@Autowired
	private IDemoDao demoDao;
	
	@Resource
	private RedisHandler redisHandler;
			
	@Override	
	@Cacheable(value="demoCache",key="'demos'")
	//@Cacheable(value="demoCache",key="'demo-'+#demo.id" ,condition="#demo.no>5")
	public List<Demo> getDemos(Demo demo) {
		System.out.println("从数据库获取数据！");
		return demoDao.getDemos(demo);
	}

	@Override//SpEL
	@CacheEvict(value="demoCache",key="'demos'")	
	public void updateDemo(Demo demo) {
		
		demoDao.updateDemo(demo);		
	}

	@Override
	@CacheEvict(value="demoCache",key="'demo-'+#demo.id")
	public void deleteDemo(Demo demo) {
		
		demoDao.deleteDemo(demo);		
	}

	@Override	
	public int addDemo(Demo demo) {
		
		int updateSum = demoDao.addDemo(demo);
		if(updateSum>0){
			redisHandler.set("demo-"+demo.getId(), demo);
		}
		return updateSum;
	}

	
}
