package com.in28minutes.business.api;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import com.in28minutes.business.ap.TodoBusinessImpl;
import com.in28minutes.data.api.TodoService;

public class TodoBusinessImplMockTest {

	@Test
	public void usingAMock() {
		TodoService todoService = mock(TodoService.class);
		List<String> allTodos = Arrays.asList("Learn Spring MVC",
				"Learn Spring", "Learn to Dance");
		when(todoService.retrieveTodos("Raza")).thenReturn(allTodos);
		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoService);
		List<String> todos = todoBusinessImpl
				.retrieveTodosRelatedToSpring("Raza");
		System.out.println(todos.size());
		assertEquals(2, todos.size());
		
	}

	
	@Test
	public void usingAMockBDD() {
		// Given
		TodoService todoService = mock(TodoService.class);
		List<String> allTodos = Arrays.asList("Learn Spring MVC",
				"Learn Spring", "Learn to Dance");
		given(todoService.retrieveTodos("Raza")).willReturn(allTodos);
		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoService);
		
		// When
		List<String> todos = todoBusinessImpl
				.retrieveTodosRelatedToSpring("Raza");
		System.out.println(todos.size());
		
		// Then
		assertEquals(2, todos.size());
		
	}

	@Test
	public void letsTestDeleteNow() {

		TodoService todoService = mock(TodoService.class);

		List<String> allTodos = Arrays.asList("Learn Spring MVC",
				"Learn Spring", "Learn to Dance");

		when(todoService.retrieveTodos("Ranga")).thenReturn(allTodos);

		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoService);

		todoBusinessImpl.deleteTodosNotRelatedToSpring("Ranga");

		verify(todoService).deleteTodo("Learn to Dance");
		
		// Using Then instead of verify
		then(todoService).should().deleteTodo("Learn to Dance");

		verify(todoService, Mockito.never()).deleteTodo("Learn Spring MVC");

		verify(todoService, Mockito.never()).deleteTodo("Learn Spring");

		verify(todoService, Mockito.times(1)).deleteTodo("Learn to Dance");
		// atLeastOnce, atLeast

	}

	@Test
	public void captureArgument() {
		ArgumentCaptor<String> argumentCaptor = ArgumentCaptor
				.forClass(String.class);

		TodoService todoService = mock(TodoService.class);

		List<String> allTodos = Arrays.asList("Learn Spring MVC",
				"Learn Spring", "Learn to Dance");
		Mockito.when(todoService.retrieveTodos("Ranga")).thenReturn(allTodos);

		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoService);
		todoBusinessImpl.deleteTodosNotRelatedToSpring("Ranga");
		then(todoService).should().deleteTodo(argumentCaptor.capture());
		// or
		Mockito.verify(todoService).deleteTodo(argumentCaptor.capture());

		assertEquals("Learn to Dance", argumentCaptor.getValue());
		//    or
		assertThat(argumentCaptor.getValue(), is("Learn to Dance"));
	}
	
	
	@Test
	public void captureArgumentMultipleArgument() {
		ArgumentCaptor<String> argumentCaptor = ArgumentCaptor
				.forClass(String.class);

		TodoService todoService = mock(TodoService.class);

		List<String> allTodos = Arrays.asList("Learn to rock and roll",
				"Learn Spring", "Learn to Dance");
		given(todoService.retrieveTodos("Ranga")).willReturn(allTodos);

		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoService);
		todoBusinessImpl.deleteTodosNotRelatedToSpring("Ranga");
		// times for calling multiple times
		then(todoService).should(times(2)).deleteTodo(argumentCaptor.capture());
		//    or
		assertThat(argumentCaptor.getAllValues().size(), is(2));
	}
	

}
