package main;

import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.Collection;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
@RunWith(Parameterized.class)
public class FindmoneyTest {

	private int input1;  
	private boolean expected; 
	private Findmoney test =  null;
	
  public FindmoneyTest(int input1,boolean expected)
  { 
	  this.input1 = input1;  
      this.expected = expected; 
  }
@Before 
  public void setUp()
  {  
	test = new Findmoney(); 
  } 
@Parameters 
  public static Collection<Object[]> getData()
  { 
	return Arrays.asList(new Object[][]{ 
		{-1,false},
		{1,true}, 
		{4,false},
		{52,true},
		{76,true}, 
		{83,true},
		{84,false} 
		}); 
  } 
@Test 
  public void testAdd() { 
	assertEquals(this.expected,test.findMoney(input1)); 
	}
	
}




