package tech.geocodeapp.geocode.event;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import tech.geocodeapp.geocode.event.model.TimeLog;
import tech.geocodeapp.geocode.event.repository.TimeLogRepository;

import java.util.*;

public class TimeLogMockRepository implements TimeLogRepository {

    private HashMap< UUID, TimeLog > map = new HashMap<>();

    @Override
    public List< TimeLog > findAll() {

        return new ArrayList<>( map.values() );
    }

    @Override
    public List< TimeLog > findAll( Sort sort ) {

        return null;
    }

    @Override
    public Page< TimeLog > findAll( Pageable pageable ) {

        return null;
    }

    @Override
    public List< TimeLog > findAllById( Iterable< UUID > uuids ) {

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
    public void delete( TimeLog entity ) {

    }

    @Override
    public void deleteAll( Iterable< ? extends TimeLog > entities ) {

    }

    @Override
    public void deleteAll() {

        map.clear();
    }

    @Override
    public < S extends TimeLog > S save( S entity ) {

        map.put( entity.getId(), entity );
        return entity;
    }

    @Override
    public < S extends TimeLog > List< S > saveAll( Iterable< S > entities ) {

        return null;
    }

    @Override
    public Optional< TimeLog > findById( UUID uuid ) {

        Optional< TimeLog > hold = Optional.empty();

        for ( int x = 0; x < map.size(); x++ ) {

            if ( map.containsKey( uuid ) ) {

                hold = Optional.ofNullable( map.get( uuid ) );
            }
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
    public < S extends TimeLog > S saveAndFlush( S entity ) {

        return null;
    }

    @Override
    public void deleteInBatch( Iterable< TimeLog > entities ) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public TimeLog getOne( UUID uuid ) {

        return null;
    }

    @Override
    public < S extends TimeLog > Optional< S > findOne( Example< S > example ) {

        return Optional.empty();
    }

    @Override
    public < S extends TimeLog > List< S > findAll( Example< S > example ) {

        return null;
    }

    @Override
    public < S extends TimeLog > List< S > findAll( Example< S > example, Sort sort ) {

        return null;
    }

    @Override
    public < S extends TimeLog > Page< S > findAll( Example< S > example, Pageable pageable ) {

        return null;
    }

    @Override
    public < S extends TimeLog > long count( Example< S > example ) {

        return 0;
    }

    @Override
    public < S extends TimeLog > boolean exists( Example< S > example ) {

        return false;
    }

}
