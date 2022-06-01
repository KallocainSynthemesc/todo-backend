/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kilian.todo;

import com.kilian.todo.mockservice.MockIntervenantService;
import com.kilian.todo.mockservice.MockTodoService;
import com.kilian.todo.model.Intervenant;
import com.kilian.todo.model.Todo;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Kilian
 */
public class TodoResourceTest {
    
    public TodoResourceTest() {
    }

    /**
     * Test of getTodosForIntervenant method, of class TodoResource.
     */
    @Test
    public void testGetTodosForIntervenant() {
        System.out.println("getTodosForIntervenant");
        final MockIntervenantService intervenantService = new MockIntervenantService();
        final MockTodoService todoService = new MockTodoService();
        Intervenant inter = new Intervenant();
        inter.setName("Kilian");
        
        Date date = new Date();
        Todo todo = new Todo();
        todo.setTitle("testGetTodosForIntervenant");
        todo.setDescription("creating a todo so I can search it through the intervenant");
        todo.setDateCreation(date.getTime());
        todo.setDateModification(date.getTime());
        Long todoId = todoService.save(todo);
        todo.setId(todoId); //Hibernate would do this for us.
        
        inter.addTodo(todo);
        Long interId = intervenantService.save(inter);
        
        String id = interId.toString();
        TodoResource instance = new TodoResource(intervenantService, todoService);
        List<Todo> expResult = inter.getTodos();
        List<Todo> result = instance.getTodosForIntervenant(id);
        assertEquals(expResult, result);
    }

    /**
     * Test of getTodo method, of class TodoResource.
     */
    @Test
    public void testGetTodo() {
        final MockIntervenantService intervenantService = new MockIntervenantService();
        final MockTodoService todoService = new MockTodoService();
        System.out.println("getTodo");
        
        Date date = new Date();
        Todo todo = new Todo();
        todo.setTitle("testGetTodo");
        todo.setDescription("creating a todo so I can search it through the intervenant");
        todo.setDateCreation(date.getTime());
        todo.setDateModification(date.getTime());
        Long todoId = todoService.save(todo);
        todo.setId(todoId); //Hibernate would do this for us.
        
        String id = todoId.toString();
        TodoResource instance = new TodoResource(intervenantService, todoService);
        Todo result = instance.getTodo(id);
        assertEquals(todo, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of putTodo method, of class TodoResource.
     */
    @Test
    public void testPutTodo() {
        final MockIntervenantService intervenantService = new MockIntervenantService();
        final MockTodoService todoService = new MockTodoService();
        System.out.println("putTodo");
        
        Date date = new Date();
        Todo todo = new Todo();
        todo.setTitle("testGetTodo");
        todo.setDescription("creating a todo so I can search it through the intervenant");
        todo.setDateCreation(date.getTime());
        todo.setDateModification(date.getTime());
        Long todoId = todoService.save(todo);
        todo.setId(todoId); //Hibernate would do this for us.
        
        //It makes little sense to update the todo in putTodo and the check for eqauls since we work on the same reference
        //Therefore I create an identical copy so we actually check an old state vs the new state.
        Todo todoCopy = new Todo();
        todoCopy.setTitle("testGetTodo");
        todoCopy.setDescription("creating a todo so I can search it through the intervenant");
        todoCopy.setDateCreation(date.getTime());
        todoCopy.setDateModification(date.getTime());
        todoCopy.setId(todoId);
        
        Date newDate = new Date();
        Map<String, Object> params = new HashMap();
        params.put("description", "New Description");
        params.put("dateModification", new BigDecimal(newDate.getTime()));
        params.put("done", true);
        
        String id = todoId.toString();
        TodoResource instance = new TodoResource(intervenantService, todoService);
        Todo result = instance.putTodo(params, id);
        
        assertNotEquals(todoCopy, result);
        
        todoCopy.setDateModification(newDate.getTime());
        todoCopy.setDescription("New Description");
        todoCopy.setDone(true);
        
        assertEquals(todoCopy, result);
    }

    /**
     * Test of postTodo method, of class TodoResource.
     */
    @Test
    public void testPostTodo() {
        final MockIntervenantService intervenantService = new MockIntervenantService();
        final MockTodoService todoService = new MockTodoService();
        System.out.println("postTodo");
        
        Intervenant inter = new Intervenant();
        inter.setName("Kilian");
        Long intId = intervenantService.save(inter);
        inter.setId(intId);
        
        Date date = new Date();
        Map<String, Object> params = new HashMap<>();
        params.put("description", "testPostTodo description");
        params.put("dateModification", new BigDecimal(date.getTime()));
        params.put("dateCreation", new BigDecimal(date.getTime()));
        params.put("title", "testPostTodo");
        params.put("done", true);
        
        TodoResource instance = new TodoResource(intervenantService, todoService);
        Todo result = instance.postTodo(params, intId);
        
        Todo expectedTodo = new Todo();
        expectedTodo.setDateCreation(date.getTime());
        expectedTodo.setDateModification(date.getTime());
        expectedTodo.setDone(true);
        expectedTodo.setIntervenant(inter);
        expectedTodo.setTitle("testPostTodo");
        expectedTodo.setDescription("testPostTodo description");
        expectedTodo.setId(result.getId());
        
        assertEquals(expectedTodo, result);
    }

    /**
     * Test of saveIntervenant method, of class TodoResource.
     * @throws java.lang.Exception
     */
    @Test
    public void testSaveIntervenant() throws Exception {
        final MockIntervenantService intervenantService = new MockIntervenantService();
        final MockTodoService todoService = new MockTodoService();
        System.out.println("saveIntervenant");
        
        Intervenant inter = new Intervenant();
        inter.setName("Kilian");
        
        Map<String, Object> params = new HashMap<>();
        params.put("name", "Kilian");
        
        TodoResource instance = new TodoResource(intervenantService, todoService);
        Intervenant result = instance.saveIntervenant(params);
        
        inter.setId(result.getId());
        assertEquals(inter, result);
    }
    
}
