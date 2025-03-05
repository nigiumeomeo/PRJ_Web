package dao.intefaces;

import java.util.List;

public interface GenericDao<T> {

    List<T> getAll();

    T getById(int id);

    boolean insert(T object);

    boolean update(T object);

    boolean delete(int id);
}
