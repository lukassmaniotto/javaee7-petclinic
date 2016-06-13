package org.woehlke.javaee7.petclinic.dao;

import org.woehlke.javaee7.petclinic.entities.Tarefa;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: Fert
 * Date: 06.01.14
 * Time: 11:51
 * To change this template use File | Settings | File Templates.
 */
@Stateless
public class TarefaDaoImpl implements TarefaDao {

    private static Logger log = Logger.getLogger(TarefaDaoImpl.class.getName());

    @PersistenceContext(unitName="javaee7petclinic")
    private EntityManager entityManager;


    @Override
    public List<Tarefa> getAll() {
        TypedQuery<Tarefa> q = entityManager.createQuery("select t from Tarefa t",Tarefa.class);
        List<Tarefa> list =  q.getResultList();
        return list;
    }

    @Override
    public void delete(long id) {
        Tarefa tarefa = entityManager.find(Tarefa.class, id);
        entityManager.remove(tarefa);
    }

    @Override
    public void addNew(Tarefa tarefa) {
        log.info("addNewTarefa: "+tarefa.toString());
        entityManager.persist(tarefa);
    }

    @Override
    public Tarefa findById(long id) {
        Tarefa tarefa = entityManager.find(Tarefa.class, id);
        return tarefa;
    }

    @Override
    public void update(Tarefa tarefa) {
        entityManager.merge(tarefa);
    }
}
