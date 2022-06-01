/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kilian.todo.mockservice;

import com.kilian.todo.dao.jpa.IntervenantDaoJpa;
import com.kilian.todo.model.Intervenant;
import com.kilian.todo.service.IntervenantService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Kilian
 */
public class MockIntervenantService extends IntervenantService{

    protected final IntervenantDaoJpa dao = null;
    
    private final List<Intervenant> store = new ArrayList<>();
    
    @Override
    public Intervenant find(Long id) {
        Intervenant t = store.stream()
                .filter(inter -> id.equals(inter.getId()))
                .findAny()
                .orElse(null);
        return t;
    }

    @Override
    public List<Intervenant> find(Long[] ids) {
        List<Intervenant> filteredList = store.stream()
                .filter(inter -> Arrays.asList(ids).stream()
                .anyMatch(id -> id.equals(inter.getId())))
                .collect(Collectors.toList());

        return filteredList;
    }

    @Override
    public List<Intervenant> findAll() {
        return store;
    }

    @Override
    public Long save(Intervenant t) {
        Long id = Long.valueOf(store.size());
        t.setId(id);
        store.add(t);
        return id;
    }

    @Override
    public void remove(Intervenant t) {
        store.remove(t);
    }

    @Override
    public void delete(Long k) {
        Intervenant t = store.stream()
                .filter(inter -> k.equals(inter.getId()))
                .findAny()
                .orElse(null);
        
        store.remove(t);
    }

}
