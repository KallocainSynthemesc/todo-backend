/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kilian.todo.dao.jpa;

import com.kilian.todo.dao.AbstractDaoAble;
import com.kilian.todo.dao.TodoDao;
import com.kilian.todo.model.Todo;
import com.kilian.todo.model.Todo_;
import javax.ejb.Stateless;
import javax.persistence.metamodel.SingularAttribute;

@Stateless
public class TodoDaoJpa extends AbstractDaoAble<Todo, Long> implements TodoDao {

    private final static TodoDaoJpa INSTANCE = new TodoDaoJpa();

    protected TodoDaoJpa() {
    }

    public static TodoDaoJpa getInstance() {
        return INSTANCE;
    }

    @Override
    public Class<Todo> getEntityClass() {
        return Todo.class;
    }

    @Override
    protected SingularAttribute<? super Todo, Long> getSingularAttributePrimaryKey() {
        return Todo_.todoId;
    }
}
