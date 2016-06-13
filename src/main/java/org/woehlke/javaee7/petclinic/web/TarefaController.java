package org.woehlke.javaee7.petclinic.web;


import org.richfaces.component.SortOrder;
import org.woehlke.javaee7.petclinic.dao.TarefaDao;
import org.woehlke.javaee7.petclinic.entities.Tarefa;

import javax.ejb.EJB;
import javax.ejb.EJBTransactionRolledbackException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Fert
 * Date: 06.01.14
 * Time: 11:49
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean
@SessionScoped
public class TarefaController implements Serializable {

    @EJB
    private TarefaDao tarefaDao;

    private Tarefa tarefa;

    private SortOrder tarefaSortOrder = SortOrder.ascending;
    private int scrollerPage;

    public SortOrder getTarefaSortOrder() {
        return tarefaSortOrder;
    }

    public void setTarefaSortOrder(SortOrder tarefaSortOrder) {
        this.tarefaSortOrder = tarefaSortOrder;
    }

    public void switchSortOrder(){
        if(tarefaSortOrder == SortOrder.ascending){
            tarefaSortOrder = SortOrder.descending;
        } else {
            tarefaSortOrder = SortOrder.ascending;
        }
    }

    public Tarefa getTarefa() {
        return tarefa;
    }

    public void setTarefa(Tarefa tarefa) {
        this.tarefa = tarefa;
    }

    public List<Tarefa> getTarefas(){
        return tarefaDao.getAll();
    }

    public String getNewTarefaForm(){
        tarefa = new Tarefa();
        return "newTarefa.jsf";
    }

    public String saveNewTarefa(){
        tarefaDao.addNew(this.tarefa);
        return "tarefas.jsf";
    }

    public String getEditForm(long id){
        this.tarefa = tarefaDao.findById(id);
        return "editTarefa.jsf";
    }

    public String saveEditedTarefa(){
        tarefaDao.update(this.tarefa);
        return "tarefas.jsf";
    }

    public String delete(long id){
        try {
            tarefaDao.delete(id);
        } catch (EJBTransactionRolledbackException e) {
            FacesContext ctx = FacesContext.getCurrentInstance();
            ctx.addMessage(null, new FacesMessage("cannot delete, object still in use"));
        }
        return "tarefas.jsf";
    }

    public void setScrollerPage(int scrollerPage) {
        this.scrollerPage = scrollerPage;
    }

    public int getScrollerPage() {
        return scrollerPage;
    }
}
