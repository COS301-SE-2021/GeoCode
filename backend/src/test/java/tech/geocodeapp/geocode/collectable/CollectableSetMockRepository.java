package tech.geocodeapp.geocode.collectable;

import tech.geocodeapp.geocode.collectable.model.CollectableSet;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import tech.geocodeapp.geocode.collectable.repository.CollectableSetRepository;

import java.util.*;

public class CollectableSetMockRepository implements CollectableSetRepository {

    private HashMap<UUID, CollectableSet> map = new HashMap<UUID, CollectableSet>();

    @Override
    public List<CollectableSet> findAll() {
        return new ArrayList<CollectableSet>(map.values());
    }

    @Override
    public List<CollectableSet> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<CollectableSet> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<CollectableSet> findAllById(Iterable<UUID> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(UUID uuid) {

    }

    @Override
    public void delete(CollectableSet collectableSet) {

    }

    @Override
    public void deleteAll(Iterable<? extends CollectableSet> iterable) {

    }

    @Override
    public void deleteAll() {
        map.clear();
    }

    @Override
    public <S extends CollectableSet> S save(S s) {
        map.put(s.getId(), s);
        return s;
    }

    @Override
    public <S extends CollectableSet> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<CollectableSet> findById(UUID uuid) {
        CollectableSet collectableSet=map.get(uuid);
        return Optional.ofNullable(collectableSet);
    }

    @Override
    public boolean existsById(UUID uuid) {
        return map.containsKey(uuid);
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends CollectableSet> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<CollectableSet> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public CollectableSet getOne(UUID uuid) {
        return null;
    }

    @Override
    public <S extends CollectableSet> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends CollectableSet> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends CollectableSet> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends CollectableSet> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends CollectableSet> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends CollectableSet> boolean exists(Example<S> example) {
        return false;
    }
}
