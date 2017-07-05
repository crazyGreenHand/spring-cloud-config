package com.uuzz.demo.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.uuzz.config.interceptor.Token;
import com.uuzz.demo.bean.Demo;
import com.uuzz.demo.service.IDemoService;


/**
 * 
* @ClassName: DemoController  
* @Description: demo控制器  
* @author zj 
* @date 2017年6月21日 上午10:35:42  
 */
@Controller
@RequestMapping("/demo")
@Scope("prototype")
public class DemoController {

	@Resource
	private IDemoService demoService;
	
	@Value("${application.auth}")//读取配置文件中的属性
	private String globalAuthName;
	
	/*@RequestMapping注解中method 属性声明该方法的请求方式*/
	@RequestMapping(value="/getDemos",method=RequestMethod.GET)	
	public ModelAndView getDemos(Demo demo){
		
		ModelAndView mv = new ModelAndView();
		
		System.out.println("authName:"+globalAuthName);
		//分页
		PageHelper.startPage(1,3);
		List<Demo> demos = demoService.getDemos(demo);
		
		mv.addObject("demos", demos);
		mv.setViewName("/demo/demo_list");
		
		return mv;
	}
	

	@Token(saveToken=true)
	@RequestMapping(value="/toAddDemo",method=RequestMethod.GET)
	public String toAddDemo(){
		return "/demo/demo_add";
	}
	
	/*重定向 redirect:+资源名称,这里用不到重定向只是用来演示怎么使用*/
	@Token(removeToken=true)
	@RequestMapping(value="/addDemo",method=RequestMethod.POST)
	public String addDemo(Demo demo){
		
		try {
			demo.setCreateTime(new Date());
			demoService.addDemo(demo);
			System.out.println("新增demo的id为："+demo.getId()+"");
			return "redirect:/demo/toSuccess";
		} catch (Exception e) {
			e.printStackTrace();
			return "/demo/demo_list";
		}		
	}

	@RequestMapping(value="/toSuccess")
    public String toSuccess(){
    	
		return "/success";
    }
    
	@Token(saveToken=true)
	@RequestMapping("/toUpdateDemo/{id}")
	public ModelAndView toUpdateDemo(Demo demo){
		
		List<Demo> demos = demoService.getDemos(demo);
		ModelAndView mv = new ModelAndView();
		
		if(demos == null||demos.isEmpty()){
			mv.setViewName("/demo/demo_list");
		}		
		mv.setViewName("/demo/demo_edit");
		mv.addObject("demo", demos.get(0));
		
		return mv;
	}
	
	/*不声明请求方式则那种方式都可以访问该方法*/
	@Token(removeToken = true)
	@RequestMapping("/updateDemo")
	public ModelAndView updateDemo(Demo demo){
		
		demo.setUpdateTime(new Date());
		demoService.updateDemo(demo);
		
		//跳转到List页面
		return getDemos(null);
	}
	
	@RequestMapping("/deleteDemo")
	public String deleteDemo(Demo demo){
		
		demoService.deleteDemo(demo);
		
		return "/demo/demo_list";
	}
	
	/*ajax调用示例/也可以在JS中封装JSON字符串传输过来*/
	@RequestMapping("/ajax")
	public void testAjax(HttpServletRequest req,HttpServletResponse rep) throws IOException{
		
		rep.setCharacterEncoding("utf-8");
		rep.getWriter().print("你好"+globalAuthName+"!");
	}
	
	/*使用@RequestBody可以将页面的json字符串绑定到指定的对象上*/
	@RequestMapping("/ajaxJson")
    public void testAjaxJson(@RequestBody Demo demo,ServletOutputStream out) throws IOException{
		
		if(demo!=null){		
			demo.setCreateTime(new Date());
			demo.setUpdateTime(new Date());
			out.print(demo.toString());
		}else{
			out.print("nothing");
		}
	}
	
	/*@PathVariable注解*/
	@RequestMapping("/demo/{id}")
	public void testPathVariable(@PathVariable String id,@PathVariable(value="id") String id1){
		
	}
	
	/*@RequestBody注解*/
	public void testRequestBody(@RequestBody() String somthing){
		
	}
}
