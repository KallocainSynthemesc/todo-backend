/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kilian.todo.interfaces;

/**
 * Allow to get the primary key as a String with getId() and Create the primary
 * key object as a K type with the specified String. The sort of the primary
 * key should be the same.<br>
 * @author Kilian
 * @param <K> the type of the key (for instance, Long or @EmbeddedId)
 */
public interface Identifiable<K> {
	public K getId();
}
