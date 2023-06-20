package com.firstJava;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller

public class addController{
@RequestMapping("add")
public ModelAndView add(HttpServletRequest request,HttpServletResponse response)
{
	ModelAndView mv = new ModelAndView();
	int sum = 0;
	int a = Integer.parseInt(request.getParameter("t1"));
	int b = Integer.parseInt(request.getParameter("t2"));
    sum=a+b;
	mv.setViewName("results");
	mv.addObject("results",sum);
	return mv;	
}
}