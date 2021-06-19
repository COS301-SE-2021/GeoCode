package tech.geocodeapp.geocode.GeoCode;

import tech.geocodeapp.geocode.GeoCode.Model.GeoCode;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import tech.geocodeapp.geocode.GeoCode.Repository.GeoCodeRepository;

import java.util.*;

public class GeoCodeMockRepository implements GeoCodeRepository {

    private HashMap< UUID, GeoCode > map = new HashMap< UUID, GeoCode >();

    @Override
    public List< GeoCode > findAll() {

        return new ArrayList< GeoCode >( map.values() );
    }

    @Override
    public List< GeoCode > findAll( Sort sort ) {

        return null;
    }

    @Override
    public Page< GeoCode > findAll( Pageable pageable ) {

        return null;
    }

    @Override
    public List< GeoCode > findAllById( Iterable< UUID > uuids ) {

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
    public void delete( GeoCode entity ) {

    }

    @Override
    public void deleteAll( Iterable< ? extends GeoCode > entities ) {

    }

    @Override
    public void deleteAll() {

        map.clear();
    }

    @Override
    public < S extends GeoCode > S save( S entity ) {

        map.put( entity.getId(), entity );
        return entity;
    }

    @Override
    public < S extends GeoCode > List< S > saveAll( Iterable< S > entities ) {

        return null;
    }

    @Override
    public Optional< GeoCode > findById( UUID uuid ) {

        return Optional.empty();
    }

    @Override
    public boolean existsById( UUID uuid ) {

        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public < S extends GeoCode > S saveAndFlush( S entity ) {

        return null;
    }

    @Override
    public void deleteInBatch( Iterable< GeoCode > entities ) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public GeoCode getOne( UUID uuid ) {

        return null;
    }

    @Override
    public < S extends GeoCode > Optional< S > findOne( Example< S > example ) {

        return Optional.empty();
    }

    @Override
    public < S extends GeoCode > List< S > findAll( Example< S > example ) {

        return null;
    }

    @Override
    public < S extends GeoCode > List< S > findAll( Example< S > example, Sort sort ) {

        return null;
    }

    @Override
    public < S extends GeoCode > Page< S > findAll( Example< S > example, Pageable pageable ) {

        return null;
    }

    @Override
    public < S extends GeoCode > long count( Example< S > example ) {

        return 0;
    }

    @Override
    public < S extends GeoCode > boolean exists( Example< S > example ) {

        return false;
    }

}
