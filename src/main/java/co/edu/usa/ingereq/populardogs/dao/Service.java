/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.usa.ingereq.populardogs.dao;

import java.util.List;

public interface Service<T> 
{
    T get(Object id);
    void save(T object);
    T update(T object);
    void delete(T object);
    List<T> findAll();
    List<T> findByProperty(String prop, Object val);
}
