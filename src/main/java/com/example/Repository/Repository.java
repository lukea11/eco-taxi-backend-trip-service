package com.example.Repository;

public interface Repository<T> {
	T findById(int id);
    void update(T entity);
    void insert(T entity);
    void delete(T entity);
}
