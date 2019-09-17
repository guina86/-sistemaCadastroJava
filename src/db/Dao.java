/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.util.List;

/**
 *
 * @author Leandro Guina
 */
public interface Dao<T> {
    T get(Integer id);
    List<T> getAll();
    int save(T t);
    void update(T t);
    void delete(T t);
    
}
