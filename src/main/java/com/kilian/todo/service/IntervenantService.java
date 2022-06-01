/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kilian.todo.service;

import com.kilian.todo.dao.jpa.IntervenantDaoJpa;
import com.kilian.todo.interfaces.DaoAble;
import com.kilian.todo.model.Intervenant;
import java.util.List;

/**
 *
 * @author Kilian
 */
public class IntervenantService implements DaoAble<Intervenant, Long> {

    @Override
    public Intervenant find(Long id) {
        return IntervenantDaoJpa.getInstance().find(id);
    }

    @Override
    public List<Intervenant> find(Long[] ids) {
        return IntervenantDaoJpa.getInstance().find(ids);
    }

    @Override
    public List<Intervenant> findAll() {
        return IntervenantDaoJpa.getInstance().findAll();
    }

    @Override
    public Long save(Intervenant t) {
        return IntervenantDaoJpa.getInstance().save(t);
    }

    @Override
    public void remove(Intervenant t) {
        IntervenantDaoJpa.getInstance().remove(t);
    }

    @Override
    public void delete(Long k) {
        IntervenantDaoJpa.getInstance().delete(k);
    }
    
    public void refreshEntity(Intervenant t)
    {
        IntervenantDaoJpa.getInstance().refreshEntity(t);
    }
}
