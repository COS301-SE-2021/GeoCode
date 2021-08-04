package tech.geocodeapp.geocode.leaderboard;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import tech.geocodeapp.geocode.leaderboard.model.Leaderboard;
import tech.geocodeapp.geocode.leaderboard.model.MyLeaderboardDetails;
import tech.geocodeapp.geocode.leaderboard.model.Point;
import tech.geocodeapp.geocode.leaderboard.repository.PointRepository;

import java.util.*;
import java.util.stream.Collectors;

public class PointMockRepository implements PointRepository {
    private static final HashMap<UUID, Point> map = new HashMap<>();

    @Override
    public List<Point> findAllByLeaderboardID(UUID leaderboardID) {
       return map.values().stream().filter(point -> point.getLeaderBoard().getId().equals(leaderboardID)).sorted(Comparator.comparing(Point::getAmount)).collect(Collectors.toList());
    }

    @Override
    public List<MyLeaderboardDetails> getMyLeaderboards(UUID userID) {
        List<MyLeaderboardDetails> detailsList = new ArrayList<>();

        /* store Map(leaderboard_ID, list_of_unique_points) */
        HashMap<UUID, List<Integer>> leaderboardPoints = new HashMap<>();

        for(Point point : map.values()){
            /* check if found new Leaderboard */
            UUID leaderboardID = point.getLeaderBoard().getId();

            if(!leaderboardPoints.containsKey(leaderboardID)){
                /* initialize list for the current Leaderboard */
                leaderboardPoints.put(leaderboardID, new ArrayList<>(List.of(point.getAmount())));
            }else if(!leaderboardPoints.get(leaderboardID).contains(point.getAmount())){
                /* add only unique point amounts */
                leaderboardPoints.get(leaderboardID).add(point.getAmount());
            }
        }

        for(UUID leaderboardID : leaderboardPoints.keySet()){
            leaderboardPoints.get(leaderboardID).sort(Comparator.reverseOrder());
        }

        /* get the details for the User */
        for(Point point : map.values()){
            /* check if Point is not for given User */
            if(!point.getUser().getId().equals(userID)){
                continue;
            }

            Leaderboard leaderboard = point.getLeaderBoard();
            String leaderboardName = leaderboard.getName();
            int amount = point.getAmount();

            /* get the rank */
            int rank = leaderboardPoints.get(leaderboard.getId()).indexOf(amount)+1;

            /* add the details to the list */
            detailsList.add(new MyLeaderboardDetails() {
                @Override
                public String getName() {
                    return leaderboardName;
                }

                @Override
                public int getPoints() {
                    return amount;
                }

                @Override
                public int getRank() {
                    return rank;
                }
            });
        }

        return detailsList;
    }

    @Override
    public int getMyRank(UUID leaderboardID, int amount) {
        /* store list_of_unique_points for given Leaderboard */
        List<Integer> pointsOnLeaderboard = new ArrayList<>();

        for(Point point : map.values()){
            /* check if found not on wanted Leaderboard */
            UUID currentLeaderboardID = point.getLeaderBoard().getId();

            if(!currentLeaderboardID.equals(leaderboardID)){
                continue;
            }

            if(!pointsOnLeaderboard.contains(point.getAmount())){
                pointsOnLeaderboard.add(point.getAmount());
            }
        }

        /* return 0 rank if Leaderboard does not exist */
        if(pointsOnLeaderboard.isEmpty()){
            return 0;
        }

        /* return rank */
        pointsOnLeaderboard.sort(Comparator.reverseOrder());
        return pointsOnLeaderboard.indexOf(amount)+1;
    }

    @Override
    public int countByLeaderboard(Leaderboard leaderboard) {
        UUID leaderboardID = leaderboard.getId();
        return (int) map.values().stream().filter(point -> point.getLeaderBoard().getId().equals(leaderboardID)).count();
    }

    @Override
    public List<Point> findPointsByLeaderboardBetween(UUID leaderboardId, int offset, int next) {
        List<Point> pointsOnLeaderboard = findAllByLeaderboardID(leaderboardId);
        int index;
        if(offset + next > pointsOnLeaderboard.size()) {
            index = pointsOnLeaderboard.size();
        }else{
            index = offset + next;
        }
        List<Point> sorted = pointsOnLeaderboard;
        Collections.sort(sorted, new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return o2.getAmount().compareTo(o1.getAmount());
            }
        });
        return sorted.subList(offset, index);
    }

    @Override
    public Optional<Point> getPointForUser(UUID userID, UUID leaderboardID) {
        for(Point point : map.values()){
            if(point.getUser().getId().equals(userID) && point.getLeaderBoard().getId().equals(leaderboardID)){
                return Optional.of(point);
            }
        }

        return Optional.empty();
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
        map.remove(uuid);
    }

    @Override
    public void delete(Point point) {
        map.remove(point.getId());
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
