/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kilian.todo.dao.jpa;

import com.kilian.todo.dao.IntervenantDao;
import com.kilian.todo.dao.AbstractDaoAble;
import com.kilian.todo.model.Intervenant;
import com.kilian.todo.model.Intervenant_;
import javax.ejb.Stateless;
import javax.persistence.metamodel.SingularAttribute;

@Stateless
public class IntervenantDaoJpa extends AbstractDaoAble<Intervenant, Long> implements IntervenantDao {

    private final static IntervenantDaoJpa INSTANCE = new IntervenantDaoJpa();
    
    @Override
    public Class<Intervenant> getEntityClass() {
        return Intervenant.class;
    }

    @Override
    protected SingularAttribute<? super Intervenant, Long> getSingularAttributePrimaryKey() {
        return Intervenant_.intervenantId;
    }
    
    public static IntervenantDaoJpa getInstance() {
        return INSTANCE;
    }

}
