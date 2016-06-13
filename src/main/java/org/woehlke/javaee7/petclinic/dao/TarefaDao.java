package org.woehlke.javaee7.petclinic.dao;

import org.woehlke.javaee7.petclinic.entities.Tarefa;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Fert
 * Date: 06.01.14
 * Time: 11:51
 * To change this template use File | Settings | File Templates.
 */
public interface TarefaDao {

    List<Tarefa> getAll();

    void delete(long id);

    void addNew(Tarefa tarefa);

    Tarefa findById(long id);

    void update(Tarefa tarefa);

}