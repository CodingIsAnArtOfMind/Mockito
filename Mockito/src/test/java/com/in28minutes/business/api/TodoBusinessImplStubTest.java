package com.in28minutes.business.api;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.in28minutes.business.ap.TodoBusinessImpl;
import com.in28minutes.data.api.TodoService;
import com.in28minutes.data.api.TodoServiceStub;

public class TodoBusinessImplStubTest {

	@Test
	public void usingAStub() {
		TodoService todoService = new 	TodoServiceStub();
		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoService);
		assertEquals(2, todoBusinessImpl.retrieveTodosRelatedToSpring("Raza").size());
		
	}

	@Test
	public void usingBStub() {
		TodoService todoService = new 	TodoServiceStub();
		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoService);
		List<String> s= new ArrayList<String>();
		s.add("Learn Spring MVC");
		s.add("Learn Spring");
		//System.out.println(todoBusinessImpl.retrieveTodosRelatedToSpring("Raza"));
		assertEquals(s, todoBusinessImpl.retrieveTodosRelatedToSpring("Raza"));
		
	}

	


}
