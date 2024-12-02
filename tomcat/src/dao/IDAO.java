package dao;

import java.util.List;
import java.util.Optional;

public interface IDAO <T, K>{
    public List<K> findAll();
    public Optional<K> findById(T id);
    public K save(K entity);
    public boolean delete(T id);
    public void update(K entity);

}
