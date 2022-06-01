/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kilian.todo;

import com.kilian.todo.model.Intervenant;
import com.kilian.todo.model.Todo;
import com.kilian.todo.service.IntervenantService;
import com.kilian.todo.service.TodoService;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Kilian
 */
@Path("/")
@RequestScoped
public class TodoResource {

    private IntervenantService intervenantService = new IntervenantService();
    private TodoService todoService = new TodoService();
    
    @Context
    private UriInfo context;

    protected TodoResource(IntervenantService mockIntervenantService, TodoService todoMockService) { //Constructor for injecting mock services
        this.intervenantService = mockIntervenantService;
        this.todoService = todoMockService;
    }
    
    protected TodoResource()
    {
        
    }
    
    @GET
    @Path("/Intervenant/{id}/Todos")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Todo> getTodosForIntervenant(@PathParam("id") String id) {
        Long longId = Long.parseLong(id);
        Intervenant intv = intervenantService.find(longId);
        return intv.getTodos();
    }
    
    @GET
    @Path("/Todo/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Todo getTodo(@PathParam("id") String id) {
        Long longId = Long.parseLong(id);
        Todo todo = todoService.find(longId);
        return todo;
    }
    
    @PUT
    @Path("/Todo/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Todo putTodo(Map<String,Object> params, @PathParam("id") String id) {
        Long longId = Long.parseLong(id);
        Todo todo = todoService.find(longId);
        
        final String description = (String) params.get("description");
        final BigDecimal dateModification = (BigDecimal) params.get("dateModification");
        final boolean done = (boolean) params.get("done");
        
        todo.setDateModification(dateModification.longValue());
        todo.setDescription(description);
        todo.setDone(done);
        
        todoService.save(todo);
        
        return todo;
    }

    @POST
    @Path("/Intervenant/{id}/Todo")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Todo postTodo(Map<String,Object> params, @PathParam("id") Long userId) {
        final String title = (String) params.get("title");
        final String description = (String) params.get("description");
        final BigDecimal dateCreation = (BigDecimal) params.get("dateCreation");
        final BigDecimal dateModification = (BigDecimal) params.get("dateModification");
        final boolean done = (boolean) params.get("done");
        
        Todo todo;
        todo = new Todo();
        Intervenant intervenant = intervenantService.find(userId);
        intervenant.addTodo(todo);
        todo.setDateCreation(dateCreation.longValue());
        todo.setDateModification(dateModification.longValue());
        todo.setTitle(title);
        todo.setDescription(description);
        todo.setDone(done);
        todoService.save(todo);
        return todo;
    }
    
    @POST
    @Path("/Intervenant")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Intervenant saveIntervenant( Map<String,Object> params  ) throws IOException {

        final Intervenant intv = new Intervenant();
        final String name = (String) params.get("name");
        final Date date = new Date();
        intv.setName(name);
        Todo todo = new Todo();
        todo.setDescription("You can change the description in the details view if you want");
        todo.setDone(false);
        todo.setTitle("This is a sample todo");
        todo.setDateCreation(date.getTime());
        todo.setDateModification(date.getTime());
        todo.setIntervenant(intv);
        intv.addTodo(todo);
        intervenantService.save(intv);
        todoService.save(todo);
        return intv;
    }
}
