package tech.geocodeapp.geocode.collectable;

import tech.geocodeapp.geocode.collectable.model.*;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import tech.geocodeapp.geocode.collectable.model.CollectableType;
import tech.geocodeapp.geocode.collectable.repository.CollectableRepository;

import java.util.*;

public class CollectableMockRepository implements CollectableRepository {
    private HashMap<java.util.UUID, Collectable> map = new HashMap<java.util.UUID, Collectable>();

    @Override
    public List<Collectable> findAll() {
        return new ArrayList<Collectable>(map.values());
    }

    @Override
    public List<Collectable> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Collectable> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Collectable> findAllById(Iterable<java.util.UUID> uuids) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(java.util.UUID uuid) {

    }

    @Override
    public void delete(Collectable entity) {

    }

    @Override
    public void deleteAll(Iterable<? extends Collectable> entities) {

    }

    @Override
    public void deleteAll() {
        map.clear();
    }

    @Override
    public <S extends Collectable> S save(S entity) {
        map.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public <S extends Collectable> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Collectable> findById(java.util.UUID uuid) {
        return Optional.ofNullable(map.get(uuid));
    }

    @Override
    public boolean existsById(java.util.UUID uuid) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Collectable> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<Collectable> entities) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Collectable getOne(java.util.UUID uuid) {
        return null;
    }

    @Override
    public <S extends Collectable> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Collectable> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Collectable> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Collectable> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Collectable> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Collectable> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public List<Collectable> findCollectablesByType(CollectableType type) {
        return null;
    }

    @Override
    public long deleteCollectablesByType(CollectableType type) {
        return 0;
    }
}
