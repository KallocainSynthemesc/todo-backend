package com.kilian.todo.model;

import com.kilian.todo.interfaces.Identifiable;
import java.io.Serializable;
import java.util.Objects;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;


@Entity
public class Todo implements Serializable, Identifiable<Long>{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long todoId;
    
    @Column
    @NotNull
    private boolean done;
    
    @Column(length = 128)
    @NotNull
    private String title;
    
    @Column(length = 1024)
    @NotNull
    private String description;

    @JsonbTransient
    @ManyToOne
    @JoinColumn(name = "intervenant_intervenantId")
    private Intervenant intervenant;
    
    @Column
    @NotNull
    private Long dateCreation;
    
    @Column
    @NotNull
    private Long dateModification;
        
    @Override
    public Long getId() {
        return todoId;
    }

    public void setId(final Long todoId) {
        this.todoId = todoId;
    }
    
    public void setDone(final boolean done)
    {
        this.done = done;
    }
    
    public boolean getDone(){
        return this.done;
    }
    
    public void setTitle(final String title)
    {
        this.title = title;
    }
    
    public String getTitle(){
        return this.title;
    }
    
    public void setDescription(final String description)
    {
        this.description = description;
    }
    
    public String getDescription()
    {
        return this.description;
    }
    
    public void setIntervenant(final Intervenant intervenant)
    {
        this.intervenant = intervenant;
    }
    
    public Intervenant getIntervenant()
    {
        return this.intervenant;
    }
    
    public void setDateCreation(final Long dateCreation)
    {
        this.dateCreation = dateCreation;
    }
    
    public Long getDateCreation()
    {
        return this.dateCreation;
    }
    
    public void setDateModification(final Long dateModification)
    {
        this.dateModification = dateModification;
    }
    
    public Long getDateModification()
    {
        return this.dateModification;
    }
    
    @Override
    public int hashCode() {
        int result = Long.hashCode(todoId);
        result = 31 * result + title.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + dateCreation.hashCode();
        result = 31 * result + dateModification.hashCode();
        result = 31 * result + todoId.hashCode();
        result = 31 * result + intervenant.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Todo)) {
            return false;
        }
        Todo todo = (Todo) o;
        return Objects.equals(todo.getDone(), this.done)
                && Objects.equals(todo.getTitle(), this.title)
                && Objects.equals(todo.getDescription(), this.description)
                && Objects.equals(todo.getDateCreation(), this.dateCreation)
                && Objects.equals(todo.getDateModification(), this.dateModification)
                && Objects.equals(todo.getIntervenant(), this.intervenant)
                && Objects.equals(todo.getId(), this.todoId);
    }
}
