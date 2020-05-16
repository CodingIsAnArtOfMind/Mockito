package com.in28minutes.powermock;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
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
@PrepareForTest({ SystemUnderTest.class })
public class PowerMockitoMockingConstructorTest {

	@Mock
	Dependency dependencyMock;

	@InjectMocks
	SystemUnderTest systemUnderTest;
	@Mock
	ArrayList mocklist;

	@Test
	public void powerMockito_MockingAConstructor() throws Exception {

		List<Integer> stats = Arrays.asList(1, 2, 3);
		Mockito.when(mocklist.size()).thenReturn(10);
		PowerMockito.whenNew(ArrayList.class).withAnyArguments().thenReturn(mocklist);
		int size = systemUnderTest.methodUsingAnArrayListConstructor();
		assertEquals(10, size);

	}

}
