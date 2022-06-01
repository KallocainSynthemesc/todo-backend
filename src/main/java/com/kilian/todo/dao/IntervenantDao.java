/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kilian.todo.dao;

import com.kilian.todo.interfaces.DaoAble;
import com.kilian.todo.model.Intervenant;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Cedric Chappert
 */
@Local
public interface IntervenantDao extends DaoAble<Intervenant, Long>{
    
    @Override
    public Intervenant find(Long id);
    
    @Override
    public List<Intervenant> findAll();
    
    @Override
    public Long save(Intervenant t);

    @Override    
    public void remove(Intervenant t);
        
    @Override
    public void delete(Long v);
    
    @Override
    public List<Intervenant> find(Long[] ids);
    
}
