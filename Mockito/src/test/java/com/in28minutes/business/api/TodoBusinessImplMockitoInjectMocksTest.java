package com.in28minutes.business.api;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.in28minutes.business.ap.TodoBusinessImpl;
import com.in28minutes.data.api.TodoService;


@RunWith(MockitoJUnitRunner.class)
public class TodoBusinessImplMockitoInjectMocksTest {
	
@Mock
TodoService todoService;

@InjectMocks
TodoBusinessImpl todoBusinessImpl;
@Captor
ArgumentCaptor<String> stringArgumentCaptor;

@Test
	public void usingAMock() {
		// TodoService todoService = mock(TodoService.class);  @Mock
		List<String> allTodos = Arrays.asList("Learn Spring MVC",
				"Learn Spring", "Learn to Dance");
		when(todoService.retrieveTodos("Raza")).thenReturn(allTodos);
		
	//	TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoService); @InjectMock
		List<String> todos = todoBusinessImpl
				.retrieveTodosRelatedToSpring("Raza");
		System.out.println(todos.size());
		assertEquals(2, todos.size());
		
	}

@Test
public void letsTestDeleteNow() {

	List<String> allTodos = Arrays.asList("Learn Spring MVC",
			"Learn Spring", "Learn to Dance");

	when(todoService.retrieveTodos("Ranga")).thenReturn(allTodos);

	todoBusinessImpl.deleteTodosNotRelatedToSpring("Ranga");

	Mockito.verify(todoService).deleteTodo("Learn to Dance");

	Mockito.verify(todoService, Mockito.never()).deleteTodo("Learn Spring MVC");

	Mockito.verify(todoService, Mockito.never()).deleteTodo("Learn Spring");

	Mockito.verify(todoService, Mockito.times(1)).deleteTodo("Learn to Dance");
	// atLeastOnce, atLeast

}

@Test
public void captureArgument() {
	List<String> allTodos = Arrays.asList("Learn Spring MVC",
			"Learn Spring", "Learn to Dance");
	when(todoService.retrieveTodos("Ranga")).thenReturn(allTodos);

	todoBusinessImpl.deleteTodosNotRelatedToSpring("Ranga");
	Mockito.verify(todoService).deleteTodo(stringArgumentCaptor.capture());

	assertEquals("Learn to Dance", stringArgumentCaptor.getValue());
}
	
	
}
