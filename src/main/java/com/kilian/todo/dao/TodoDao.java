/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kilian.todo.dao;

import com.kilian.todo.interfaces.DaoAble;
import com.kilian.todo.model.Todo;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Cedric Chappert
 */
@Local
public interface TodoDao extends DaoAble<Todo, Long>{
    
    @Override
    public Todo find(Long id);
    
    @Override
    public List<Todo> findAll();
    
    @Override
    public Long save(Todo t);

    @Override    
    public void remove(Todo t);
        
    @Override
    public void delete(Long v);
    
    @Override
    public List<Todo> find(Long[] ids);
    
}
