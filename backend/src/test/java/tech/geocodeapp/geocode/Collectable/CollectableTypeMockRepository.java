package tech.geocodeapp.geocode.Collectable;

import io.swagger.model.CollectableSet;
import io.swagger.model.CollectableType;
import io.swagger.model.Rarity;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import tech.geocodeapp.geocode.Collectable.Repository.CollectableTypeRepository;

import java.util.*;

public class CollectableTypeMockRepository implements CollectableTypeRepository {

    private static HashMap<UUID, CollectableType> map = new HashMap<UUID, CollectableType>();

    @Override
    public List<CollectableType> getCollectableTypesByRarity(Rarity rarity) {
        return null;
    }

    @Override
    public List<CollectableType> getCollectableTypesBySet(CollectableSet set) {
        return null;
    }

    @Override
    public long deleteCollectableTypesBySet(CollectableSet set) {
        return 0;
    }

    @Override
    public List<CollectableType> findAll() {
        return new ArrayList<CollectableType>(map.values());
    }

    @Override
    public List<CollectableType> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<CollectableType> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<CollectableType> findAllById(Iterable<UUID> iterable) {
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
    public void delete(CollectableType collectableType) {

    }

    @Override
    public void deleteAll(Iterable<? extends CollectableType> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends CollectableType> S save(S s) {
        map.put(s.getId(), s);
        return s;
    }

    @Override
    public <S extends CollectableType> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<CollectableType> findById(UUID uuid) {
        CollectableType collectableType=map.get(uuid);
        return Optional.ofNullable(collectableType);
    }

    @Override
    public boolean existsById(UUID uuid) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends CollectableType> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public <S extends CollectableType> List<S> saveAllAndFlush(Iterable<S> iterable) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<CollectableType> iterable) {

    }

    @Override
    public void deleteAllInBatch(Iterable<CollectableType> iterable) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<UUID> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public CollectableType getOne(UUID uuid) {
        return null;
    }

    @Override
    public CollectableType getById(UUID uuid) {
        return null;
    }

    @Override
    public <S extends CollectableType> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends CollectableType> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends CollectableType> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends CollectableType> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends CollectableType> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends CollectableType> boolean exists(Example<S> example) {
        return false;
    }
}
