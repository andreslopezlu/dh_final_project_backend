package com.dh.final_project_backend.repository;

import java.util.List;

public interface IDao<T> {

    public T guardar(T t);
    public T buscar (Integer id);
    public T actualizar (T t);
    public void eliminar (Integer id);
    public List<T> buscarTodos();

}
