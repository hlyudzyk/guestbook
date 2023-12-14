package models.dataaccesslayer;

import java.io.IOException;
import java.util.Optional;

public interface DataMapper<T> {
    Optional< T > find(String id);

    void insert(T itemToInsert) throws RuntimeException, IOException;

    void update(T itemToUpdate) throws RuntimeException, IOException;

    void delete(T itemToDelete) throws RuntimeException;


}


