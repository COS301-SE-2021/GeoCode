package tech.geocodeapp.geocode.leaderboard;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import tech.geocodeapp.geocode.leaderboard.model.Leaderboard;
import tech.geocodeapp.geocode.leaderboard.model.MyLeaderboardDetails;
import tech.geocodeapp.geocode.leaderboard.model.Point;
import tech.geocodeapp.geocode.leaderboard.repository.PointRepository;
import tech.geocodeapp.geocode.user.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class PointMockRepository implements PointRepository {
    private static HashMap<UUID, Point> map = new HashMap<UUID, Point>();

    @Override
    public List<Point> findAllByLeaderboard(Leaderboard leaderboard) {

    }

    @Override
    public List<MyLeaderboardDetails> getMyLeaderboards(UUID userID) {
        return null;
    }

    @Override
    public int getMyRank(UUID leaderboardID, int amount) {
        return 0;
    }

    @Override
    public int countByLeaderboard(Leaderboard leaderboard) {
        UUID leaderboardID = leaderboard.getId();

        long count = 0;/*

        for(Point point : map.values()){
            if(point.getLeaderBoard().getId() == leaderboardID){
                ++count;
            }
        }*/

        count = map.values().stream().filter(point -> point.getLeaderBoard().getId() == leaderboardID).count();

        return (int) count;
    }

    @Override
    public List<Point> findPointsByLeaderboardBetween(UUID leaderboardId, int offset, int next) {

    }

    @Override
    public List<Point> findAll() {
        return null;
    }

    @Override
    public List<Point> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Point> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Point> findAllById(Iterable<UUID> iterable) {
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
    public void delete(Point point) {

    }

    @Override
    public void deleteAll(Iterable<? extends Point> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Point> S save(S s) {
        map.put(s.getId(), s);
        return s;
    }

    @Override
    public <S extends Point> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<Point> findById(UUID uuid) {
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
    public <S extends Point> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<Point> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Point getOne(UUID uuid) {
        return null;
    }

    @Override
    public <S extends Point> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Point> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Point> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Point> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Point> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Point> boolean exists(Example<S> example) {
        return false;
    }
}
