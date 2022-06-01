/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kilian.todo.mockservice;

import com.kilian.todo.dao.jpa.TodoDaoJpa;
import com.kilian.todo.model.Todo;
import com.kilian.todo.service.TodoService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Kilian
 */
public class MockTodoService extends TodoService{
    
    protected final TodoDaoJpa dao = null;
    private final List<Todo> store = new ArrayList<>();
    
    @Override
    public Todo find(Long id) {
        Todo t = store.stream()
                .filter(todo -> id.equals(todo.getId()))
                .findAny()
                .orElse(null);
        return t;
    }

    @Override
    public List<Todo> find(Long[] ids) {
        List<Todo> filteredList = store.stream()
                .filter(todo -> Arrays.asList(ids).stream()
                .anyMatch(id -> id.equals(todo.getId())))
                .collect(Collectors.toList());

        return filteredList;
    }

    @Override
    public List<Todo> findAll() {
        return store;
    }

    @Override
    public Long save(Todo t) {
        if(t.getId() != null)
        {
            Todo todo = find(t.getId());
            return todo.getId();
        }
        store.add(t);
        Long id = Long.valueOf(store.size());
        t.setId(id);
        return id;
    }

    @Override
    public void remove(Todo t) {
        store.remove(t);
    }

    @Override
    public void delete(Long k) {
        Todo t = store.stream()
                .filter(todo -> k.equals(todo.getId()))
                .findAny()
                .orElse(null);
        
        store.remove(t);
    }
    
}
