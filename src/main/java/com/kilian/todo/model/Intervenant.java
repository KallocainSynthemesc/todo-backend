package com.kilian.todo.model;

import com.kilian.todo.interfaces.Identifiable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Intervenant implements Serializable,Identifiable<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long intervenantId;

    @Column(length = 128)
    @NotNull
    private String name;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = com.kilian.todo.model.Todo.class)
    @JoinColumn(name = "intervenant_intervenantId", referencedColumnName = "intervenantId", updatable = false, insertable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private List<Todo> todos = new ArrayList<>();
        
    @Override
    public Long getId() {
        return intervenantId;
    }

    public void setId(Long intervenantId) {
        this.intervenantId = intervenantId;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
    
    public List<Todo> getTodos()
    {
        return this.todos;
    }
    
    public void setTodos(final List<Todo> todos){
        this.todos = todos;
    }
    
    public void addTodo(Todo todo)
    {
        this.todos.add(todo);
        todo.setIntervenant(this);
    }
    
    public void removeTodo(Todo todo){
        this.todos.remove(todo);
        todo.setIntervenant(null);
    }
    
    @Override
    public int hashCode() {
        int result = Long.hashCode(intervenantId);
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Intervenant)) {
            return false;
        }
        Intervenant intervenant = (Intervenant) o;
        return Objects.equals(intervenant.getId(), this.intervenantId)
                && Objects.equals(intervenant.getName(), this.name);
    }
}
