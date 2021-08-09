package tech.geocodeapp.geocode.event;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import tech.geocodeapp.geocode.event.model.Level;
import tech.geocodeapp.geocode.event.repository.LevelRepository;

import java.util.*;

public class LevelMockRepository implements LevelRepository {

    private HashMap< UUID, Level > map = new HashMap<>();

    @Override
    public List<Level> findAll() {
        return null;
    }

    @Override
    public List<Level> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Level> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Level> findAllById(Iterable<UUID> uuids) {
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
    public void delete(Level entity) {

    }

    @Override
    public void deleteAll(Iterable<? extends Level> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Level> S save(S entity) {
        map.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public <S extends Level> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Level> findById(UUID uuid) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(UUID uuid) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Level> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<Level> entities) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Level getOne(UUID uuid) {
        return null;
    }

    @Override
    public <S extends Level> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Level> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Level> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Level> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Level> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Level> boolean exists(Example<S> example) {
        return false;
    }
}
