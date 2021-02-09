package com.simoes.mario.brsservice.Classes;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by MARIO on 09/03/2017.
 */

public class ListSeriazeble implements List, Serializable {

    List lista;

    public ListSeriazeble() {
        lista = new ArrayList<>();

    }

    public ListSeriazeble(ArrayList lista) {
        addAll(lista);
    }


    @Override
    public void add(int location, Object object) {
        lista.add(location, object);
    }

    @Override
    public boolean add(Object object) {
        lista.add(object);
        return false;
    }

    @Override
    public boolean addAll(int location, @NonNull Collection collection) {
        return false;
    }

    @Override
    public boolean addAll(@NonNull Collection collection) {
        return lista.addAll(collection);
    }

    @Override
    public void clear() {
        lista.clear();
    }

    @Override
    public boolean contains(Object object) {
        if (lista.contains(object)) {
            return true;
        }
        return false;
    }

    @Override
    public Object get(int location) {
        return lista.get(location);
    }

    @Override
    public int indexOf(Object object) {
        return lista.indexOf(object);
    }

    @Override
    public boolean isEmpty() {
        return lista.isEmpty();
    }

    @NonNull
    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public int lastIndexOf(Object object) {
        return 0;
    }

    @Override
    public ListIterator listIterator() {
        return null;
    }

    @NonNull
    @Override
    public ListIterator listIterator(int location) {
        return null;
    }

    @Override
    public Object remove(int location) {
        return null;
    }

    @Override
    public boolean remove(Object object) {
        return false;
    }

    @Override
    public Object set(int location, Object object) {
        return null;
    }

    @Override
    public int size() {
        return lista.size();
    }

    @NonNull
    @Override
    public List subList(int start, int end) {
        return null;
    }

    @NonNull
    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @NonNull
    @Override
    public Object[] toArray(@NonNull Object[] array) {
        return new Object[0];
    }

    @Override
    public boolean retainAll(@NonNull Collection collection) {
        return false;
    }

    @Override
    public boolean removeAll(@NonNull Collection collection) {
        return false;
    }

    @Override
    public boolean containsAll(@NonNull Collection collection) {
        return false;
    }
}
