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

public class PointMockRepository implements PointRepository {
    private static final HashMap<UUID, Point> map = new HashMap<>();

    @Override
    public List<Point> findAllByLeaderboard(Leaderboard leaderboard) {
        return null;
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
                System.out.println(leaderboardID+": first amount -> "+point.getAmount());
            }else if(!leaderboardPoints.get(leaderboardID).contains(point.getAmount())){
                /* add only unique point amounts */
                leaderboardPoints.get(leaderboardID).add(point.getAmount());
                System.out.println(leaderboardID+": adding -> "+point.getAmount());
            }
        }

        System.out.println("Printing the leaderboards and the unique points for the current user");
        for(UUID leaderboardID : leaderboardPoints.keySet()){
            leaderboardPoints.get(leaderboardID).sort(Comparator.reverseOrder());

            String pointString = "";
            for(Integer amount : leaderboardPoints.get(leaderboardID)){
                pointString += amount +" ";
            }
            System.out.println(leaderboardID.toString()+": "+pointString);


        }

        System.out.println("done");

        /*  */
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
            System.out.println(leaderboardName+" ("+leaderboard.getId()+"): rank = "+rank);

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
        return null;
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
