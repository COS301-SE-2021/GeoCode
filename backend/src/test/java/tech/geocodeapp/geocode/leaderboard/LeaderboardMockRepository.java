package tech.geocodeapp.geocode.leaderboard;

import lombok.NonNull;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import tech.geocodeapp.geocode.leaderboard.model.Leaderboard;
import tech.geocodeapp.geocode.leaderboard.repository.LeaderboardRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class LeaderboardMockRepository implements LeaderboardRepository {
    private static final HashMap<UUID, Leaderboard> map = new HashMap<>();

    @Override
    public Optional<Leaderboard> findByName(String name) {
        for(Leaderboard leaderboard : map.values()){
            if(leaderboard.getName().equals(name)){
                return Optional.of(leaderboard);
            }
        }

        return Optional.empty();
    }

    @Override
    @NonNull
    public List<Leaderboard> findAll() {
        return (List<Leaderboard>) map.values();
    }

    @Override
    public List<Leaderboard> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Leaderboard> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Leaderboard> findAllById(Iterable<UUID> iterable) {
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
    public void delete(Leaderboard leaderboard) {

    }

    @Override
    public void deleteAll(Iterable<? extends Leaderboard> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Leaderboard> S save(S s) {
        return null;
    }

    @Override
    public <S extends Leaderboard> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<Leaderboard> findById(UUID uuid) {
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
    public <S extends Leaderboard> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<Leaderboard> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Leaderboard getOne(UUID uuid) {
        return null;
    }

    @Override
    public <S extends Leaderboard> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Leaderboard> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Leaderboard> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Leaderboard> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Leaderboard> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Leaderboard> boolean exists(Example<S> example) {
        return false;
    }
}
