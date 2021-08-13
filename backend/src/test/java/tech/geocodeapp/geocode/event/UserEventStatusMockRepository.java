package tech.geocodeapp.geocode.event;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import tech.geocodeapp.geocode.event.model.UserEventStatus;
import tech.geocodeapp.geocode.event.repository.UserEventStatusRepository;

import java.util.*;

public class UserEventStatusMockRepository implements UserEventStatusRepository {

    private HashMap< UUID, UserEventStatus> map = new HashMap<>();

    @Override
    public List<UserEventStatus> findAll() {

        return new ArrayList<>( map.values() );
    }

    @Override
    public List<UserEventStatus> findAll(Sort sort ) {

        return null;
    }

    @Override
    public Page<UserEventStatus> findAll(Pageable pageable ) {

        return null;
    }

    @Override
    public List<UserEventStatus> findAllById(Iterable< UUID > uuids ) {

        return null;
    }

    @Override
    public long count() {

        return 0;
    }

    @Override
    public void deleteById( UUID uuid ) {

    }

    @Override
    public void delete( UserEventStatus entity ) {

    }

    @Override
    public void deleteAll( Iterable< ? extends UserEventStatus> entities ) {

    }

    @Override
    public void deleteAll() {

        map.clear();
    }

    @Override
    public < S extends UserEventStatus> S save(S entity ) {

        map.put( entity.getId(), entity );
        return entity;
    }

    @Override
    public < S extends UserEventStatus> List< S > saveAll(Iterable< S > entities ) {

        return null;
    }

    @Override
    public Optional<UserEventStatus> findById(UUID uuid ) {

        Optional<UserEventStatus> hold = Optional.empty();

        if ( map.containsKey( uuid ) ) {
            hold = Optional.ofNullable( map.get( uuid ) );
        }

        return hold;
    }

    @Override
    public boolean existsById( UUID uuid ) {

        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public < S extends UserEventStatus> S saveAndFlush(S entity ) {

        return null;
    }

    @Override
    public void deleteInBatch( Iterable<UserEventStatus> entities ) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public UserEventStatus getOne(UUID uuid ) {

        return null;
    }

    @Override
    public < S extends UserEventStatus> Optional< S > findOne(Example< S > example ) {

        return Optional.empty();
    }

    @Override
    public < S extends UserEventStatus> List< S > findAll(Example< S > example ) {

        return null;
    }

    @Override
    public < S extends UserEventStatus> List< S > findAll(Example< S > example, Sort sort ) {

        return null;
    }

    @Override
    public < S extends UserEventStatus> Page< S > findAll(Example< S > example, Pageable pageable ) {

        return null;
    }

    @Override
    public < S extends UserEventStatus> long count(Example< S > example ) {

        return 0;
    }

    @Override
    public < S extends UserEventStatus> boolean exists(Example< S > example ) {

        return false;
    }

    @Override
    public UserEventStatus findStatusByEventIDAndUserID(UUID eventID, UUID userID) {

        for (UserEventStatus p: map.values()) {
            if ( (p.getEventID() == eventID) && (p.getUserID() == userID) ) {
                return p;
            }
        }
        return null;
    }

}
