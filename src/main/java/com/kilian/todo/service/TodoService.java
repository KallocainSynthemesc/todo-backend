/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kilian.todo.service;

import com.kilian.todo.dao.jpa.TodoDaoJpa;
import com.kilian.todo.interfaces.DaoAble;
import com.kilian.todo.model.Todo;
import java.util.List;

/**
 *
 * @author Kilian
 */
public class TodoService implements DaoAble<Todo, Long>{
    
    @Override
    public Todo find(Long id) {
        return TodoDaoJpa.getInstance().find(id);
    }

    @Override
    public List<Todo> find(Long[] ids) {
        return TodoDaoJpa.getInstance().find(ids);
    }

    @Override
    public List<Todo> findAll() {
        return TodoDaoJpa.getInstance().findAll();
    }

    @Override
    public Long save(Todo t) {
        return TodoDaoJpa.getInstance().save(t);
    }

    @Override
    public void remove(Todo t) {
        TodoDaoJpa.getInstance().remove(t);
    }

    @Override
    public void delete(Long k) {
        TodoDaoJpa.getInstance().delete(k);
    }
    
}
