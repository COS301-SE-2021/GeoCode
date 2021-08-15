package tech.geocodeapp.geocode.geocode;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import tech.geocodeapp.geocode.geocode.model.Difficulty;
import tech.geocodeapp.geocode.geocode.model.GeoCode;
import tech.geocodeapp.geocode.geocode.model.GeoPoint;
import tech.geocodeapp.geocode.geocode.repository.GeoCodeRepository;

import java.util.*;

public class GeoCodeMockRepository implements GeoCodeRepository {

    private HashMap< UUID, GeoCode > map = new HashMap<>();

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

        Optional< GeoCode > hold = Optional.empty();

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

    /**
     * Finds all the GeoCode's that are not in an event
     *
     * @return all the GeoCode's without an eventID
     */
    @Override
    public Collection< GeoCode > findGeoCode() {

        Collection< GeoCode > found = new ArrayList<>();
        map.forEach( ( key, value ) -> {

            if ( value.getEventID() == null) {

                found.add( value );
            }
        } );

        return found;
    }

    /**
     * Finds all the GeoCode's with a certain level of difficulty
     *
     * @param difficulty the level of difficulty assigned to a GeoCode
     *
     * @return all the GeoCode's with the specified difficulty
     */
    @Override
    public Collection< GeoCode > findGeoCodeWithDifficulty( Difficulty difficulty ) {

        Collection< GeoCode > found = new ArrayList<>();
        map.forEach( ( key, value ) -> {

            if ( value.getDifficulty().equals( difficulty ) ) {

                found.add( value );
            }
        } );

        return found;
    }

    /**
     * Find the GeoCode with the given qrCode characters
     *
     * @param qrCode the unique characters allocated to a certain GeoCode
     *
     * @return the GeoCode with the specified qrCode
     */
    @Override
    public GeoCode findGeoCodeWithQRCode( String qrCode ) {

        //System.out.println( qrCode );
        for ( Map.Entry< UUID, GeoCode > entry : map.entrySet() ) {

            GeoCode found = entry.getValue();
            if ( ( found!= null ) &&  ( found.getQrCode()!= null ) && ( found.getQrCode().equals( qrCode ) ) ) {

                return found;
            }
        }

        return null;
    }

    /**
     * Find the GeoCode with the given location (longitude and latitude co-ordinates)
     *
     * @param location the longitude and Latitude to look for the GeoCode
     *
     * @return the GeoCode at the given location
     */
    @Override
    public GeoCode findGeoCodeAtLocation( GeoPoint location ) {

        for ( Map.Entry< UUID, GeoCode > entry : map.entrySet() ) {

            GeoCode found = entry.getValue();
            if ( found.getLocation().equals( location ) ) {

                return found;
            }
        }

        return null;
    }

}
