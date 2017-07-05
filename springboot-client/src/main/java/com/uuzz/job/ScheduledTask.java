package com.uuzz.job;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScheduledTask implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private transient static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public void work(){  
        //这儿插入具体的调度任务  
		System.out.println("定时任务执行："+sdf.format(new Date()));
    }

}