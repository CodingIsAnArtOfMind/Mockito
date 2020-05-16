package com.in28minutes.powermock;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ UtilityClass.class})
public class MockingStaticMethodTest {

	@Mock
	Dependency dependency;
	
	@InjectMocks
	SystemUnderTest systemUnderTest;
	@Test
	public void powerMockito_MockingAStaticMethodCall() {
		
		  List<Integer> stats = Arrays.asList(1,2,3);
		  Mockito.when(dependency.retrieveAllStats()).thenReturn(stats);
		  
		  PowerMockito.mockStatic(UtilityClass.class);
		  Mockito.when(UtilityClass.staticMethod(6)).thenReturn(150); 
		  int result = systemUnderTest.methodCallingAStaticMethod(); 
		  assertEquals(150, result);
		  PowerMockito.verifyStatic(); 
		  UtilityClass.staticMethod(6);
		 
		
		}
}
