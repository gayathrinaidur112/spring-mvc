package com.firstJava;
import static org.junit.Assert.*;

import org.junit.Test;

public class SampleTest {
 @Test
 public void addTest1()
 {
	Sample s=new Sample();
	int result=s.sum(100,200);
	 assertEquals(300,result);
 }

  @Test
 public void addTest()
 {
	Sample s=new Sample();
	int result=s.sum(1000,2000);
	 assertEquals(3000,result);
 }
}
