package tech.geocodeapp.geocode.mission;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import tech.geocodeapp.geocode.mission.model.Mission;
import tech.geocodeapp.geocode.mission.repository.MissionRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class MissionMockRepository implements MissionRepository {
    private final HashMap<UUID, Mission> map = new HashMap<>();

    @Override
    public List<Mission> findAll() {
        return null;
    }

    @Override
    public List<Mission> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Mission> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Mission> findAllById(Iterable<UUID> iterable) {
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
    public void delete(Mission mission) {

    }

    @Override
    public void deleteAll(Iterable<? extends Mission> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Mission> S save(S s) {
        map.put(s.getId(), s);
        return s;
    }

    @Override
    public <S extends Mission> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<Mission> findById(UUID uuid) {
        return Optional.ofNullable(map.get(uuid));
    }

    @Override
    public boolean existsById(UUID uuid) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Mission> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<Mission> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Mission getOne(UUID uuid) {
        return null;
    }

    @Override
    public <S extends Mission> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Mission> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Mission> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Mission> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Mission> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Mission> boolean exists(Example<S> example) {
        return false;
    }
}
