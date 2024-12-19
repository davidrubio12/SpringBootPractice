package es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.repositories.base;

import es.iesclaradelrey.da2d1e2425.shopmartadavidrubio.entities.Entity;

import java.util.*;

public class RepositoryImpl<T extends Entity<ID>, ID>  implements Repository<T, ID> {

   private Map<ID, T> map = new HashMap<ID,T>();

    @Override
    public long count() {
        return map.keySet().size();
    }

    @Override
    public void save(T t) {
        map.put(t.getId(), t);
    }

    @Override
    public Optional<T> findById(ID id) {
        if(map.containsKey(id)) {
            return Optional.of(map.get(id));
        }

        return Optional.empty();
    }

    @Override
    public Collection<T> findAll() {
        return map.values();
    }

    @Override
    public void deleteById(ID id) {
        map.remove(id);

    }
}
