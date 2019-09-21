/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilitarios;

import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

/**
 *
 * @author Leandro Guina
 */
public class DefaultCM<T> extends AbstractListModel implements ComboBoxModel {

    private List<T> lista;
    private T selecionado;
    private final static int PRIMEIRO = 0;

    public DefaultCM() {
        this.lista = new ArrayList<T>();
    }

    public DefaultCM(List<T> lista) {
        this();
        this.lista.addAll(lista);
        if (getSize() > 0) {
            setSelectedItem(this.lista.get(PRIMEIRO));
        }
    }

    @Override
    public int getSize() {
        return lista.size();
    }

    @Override
    public Object getElementAt(int index) {
        return lista.get(index);
    }

    @Override
    public void setSelectedItem(Object anItem) {
        selecionado = (T) anItem;
        
    }

    @Override
    public T getSelectedItem() {
        return selecionado;
    }

    public void add(T t) {
        lista.add(t);
        fireIntervalAdded(this, getSize() - 1, getSize() - 1);
        setSelectedItem(lista.get(getSize() - 1));
    }

    public void addAll(List<T> bairros) {
        int primeiraLinha = getSize();
        lista.addAll(bairros);
        fireIntervalAdded(this, primeiraLinha, primeiraLinha + bairros.size());
//        System.out.println(getSize());
//        if (!lista.isEmpty()) {
//            setSelectedItem(lista.get(0));
//        }
//        setSelectedItem(null);
    }

    public List<T> getAll() {
        return lista;
    }

    public void remove() {
        lista.remove(getSelectedItem());
        fireIntervalRemoved(this, PRIMEIRO, getSize() - 1);
        setSelectedItem(lista.get(PRIMEIRO));
    }

    public void clear() {
        lista.clear();
        fireContentsChanged(this, PRIMEIRO, getSize() - 1);
    }
    
    public boolean isEmpty(){
        return lista.isEmpty();
    }

}
