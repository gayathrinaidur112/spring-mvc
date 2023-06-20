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
	int c = Integer.parseInt(request.getParameter("t1"));
	int q= Integer.parseInt(request.getParameter("t2"));
	int tax=0;
	if(c<=5000)
	{
		tax=(c*5)/100;
		tax=tax*q;
	}
	else if(c>5000 && c<=50000)
	{

		tax=(c*10)/100;
		tax=tax*q;
		
	}
	else
	{
		tax=(c*1)/100;
		tax=tax*q;
	}
	mv.setViewName("results");
	mv.addObject("results",tax);
	return mv;
	
}
}