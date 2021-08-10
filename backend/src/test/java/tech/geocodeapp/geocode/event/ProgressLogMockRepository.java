package tech.geocodeapp.geocode.event;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import tech.geocodeapp.geocode.event.model.ProgressLog;
import tech.geocodeapp.geocode.event.repository.ProgressLogRepository;

import java.util.*;

public class ProgressLogMockRepository implements ProgressLogRepository {

    private HashMap< UUID, ProgressLog> map = new HashMap<>();

    @Override
    public List<ProgressLog> findAll() {

        return new ArrayList<>( map.values() );
    }

    @Override
    public List<ProgressLog> findAll(Sort sort ) {

        return null;
    }

    @Override
    public Page<ProgressLog> findAll(Pageable pageable ) {

        return null;
    }

    @Override
    public List<ProgressLog> findAllById(Iterable< UUID > uuids ) {

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
    public void delete( ProgressLog entity ) {

    }

    @Override
    public void deleteAll( Iterable< ? extends ProgressLog> entities ) {

    }

    @Override
    public void deleteAll() {

        map.clear();
    }

    @Override
    public < S extends ProgressLog> S save(S entity ) {

        map.put( entity.getId(), entity );
        return entity;
    }

    @Override
    public < S extends ProgressLog> List< S > saveAll(Iterable< S > entities ) {

        return null;
    }

    @Override
    public Optional<ProgressLog> findById(UUID uuid ) {

        Optional<ProgressLog> hold = Optional.empty();

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
    public < S extends ProgressLog> S saveAndFlush(S entity ) {

        return null;
    }

    @Override
    public void deleteInBatch( Iterable<ProgressLog> entities ) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public ProgressLog getOne(UUID uuid ) {

        return null;
    }

    @Override
    public < S extends ProgressLog> Optional< S > findOne(Example< S > example ) {

        return Optional.empty();
    }

    @Override
    public < S extends ProgressLog> List< S > findAll(Example< S > example ) {

        return null;
    }

    @Override
    public < S extends ProgressLog> List< S > findAll(Example< S > example, Sort sort ) {

        return null;
    }

    @Override
    public < S extends ProgressLog> Page< S > findAll(Example< S > example, Pageable pageable ) {

        return null;
    }

    @Override
    public < S extends ProgressLog> long count(Example< S > example ) {

        return 0;
    }

    @Override
    public < S extends ProgressLog> boolean exists(Example< S > example ) {

        return false;
    }

    @Override
    public ProgressLog findProgressLogByEventIDAndUserID(UUID eventID, UUID userID) {

        for (ProgressLog p: map.values()) {
            if ( (p.getEventID() == eventID) && (p.getUserID() == userID) ) {
                return p;
            }
        }
        return null;
    }

}
