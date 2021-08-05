package tech.geocodeapp.geocode.event;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import tech.geocodeapp.geocode.event.model.Event;
import tech.geocodeapp.geocode.event.repository.EventRepository;

import java.util.*;

public class EventMockRepository implements EventRepository {

    private HashMap< UUID, Event > map = new HashMap<>();

    @Override
    public List< Event > findAll() {

        return new ArrayList< Event >( map.values() );
    }

    @Override
    public List< Event > findAll( Sort sort ) {

        return null;
    }

    @Override
    public Page< Event > findAll( Pageable pageable ) {

        return null;
    }

    @Override
    public List< Event > findAllById( Iterable< UUID > uuids ) {

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
    public void delete( Event entity ) {

    }

    @Override
    public void deleteAll( Iterable< ? extends Event > entities ) {

    }

    @Override
    public void deleteAll() {

        map.clear();
    }

    @Override
    public < S extends Event > S save( S entity ) {

        map.put( entity.getId(), entity );
        return entity;
    }

    @Override
    public < S extends Event > List< S > saveAll( Iterable< S > entities ) {

        return null;
    }

    @Override
    public Optional< Event > findById( UUID uuid ) {

        Optional< Event > hold = Optional.empty();

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
    public < S extends Event > S saveAndFlush( S entity ) {

        return null;
    }

    @Override
    public void deleteInBatch( Iterable< Event > entities ) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Event getOne( UUID uuid ) {

        return null;
    }

    @Override
    public < S extends Event > Optional< S > findOne( Example< S > example ) {

        return Optional.empty();
    }

    @Override
    public < S extends Event > List< S > findAll( Example< S > example ) {

        return null;
    }

    @Override
    public < S extends Event > List< S > findAll( Example< S > example, Sort sort ) {

        return null;
    }

    @Override
    public < S extends Event > Page< S > findAll( Example< S > example, Pageable pageable ) {

        return null;
    }

    @Override
    public < S extends Event > long count( Example< S > example ) {

        return 0;
    }

    @Override
    public < S extends Event > boolean exists( Example< S > example ) {

        return false;
    }

}
