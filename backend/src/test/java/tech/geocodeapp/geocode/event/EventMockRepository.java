package tech.geocodeapp.geocode.event;

import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import tech.geocodeapp.geocode.event.model.Event;
import tech.geocodeapp.geocode.event.model.UserEventStatus;
import tech.geocodeapp.geocode.event.repository.EventRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import java.util.*;

public class EventMockRepository implements EventRepository {

    private final UserEventStatusMockRepository statusRepo;
    private HashMap< UUID, Event > map = new HashMap<>();

    public EventMockRepository(UserEventStatusMockRepository statusRepo) {
        this.statusRepo = statusRepo;
    }

    @Override
    public List< Event > findAll() {

        return new ArrayList< Event >( map.values() );
    }

    @Override
    public List< Event > findAll( Sort sort ) {

        return null;
    }

    /**
     * Returns a {@link Page} of entities meeting the paging restriction provided in the {@code Pageable} object.
     *
     * @param pageable
     *
     * @return a page of entities
     */
    @Override
    public Page< Event > findAll( Pageable pageable ) {

        return null;
    }

    @Override
    public List< Event > findAllById( Iterable< UUID > uuids ) {

        return null;
    }

    /**
     * Returns the number of entities available.
     *
     * @return the number of entities.
     */
    @Override
    public long count() {

        return 0;
    }

    /**
     * Deletes the entity with the given id.
     *
     * @param uuid must not be {@literal null}.
     *
     * @throws IllegalArgumentException in case the given {@literal id} is {@literal null}
     */
    @Override
    public void deleteById( UUID uuid ) {

    }

    /**
     * Deletes a given entity.
     *
     * @param entity must not be {@literal null}.
     *
     * @throws IllegalArgumentException in case the given entity is {@literal null}.
     */
    @Override
    public void delete( Event entity ) {

    }

    /**
     * Deletes the given entities.
     *
     * @param entities must not be {@literal null}. Must not contain {@literal null} elements.
     *
     * @throws IllegalArgumentException in case the given {@literal entities} or one of its entities is {@literal null}.
     */
    @Override
    public void deleteAll( Iterable< ? extends Event > entities ) {

    }

    /**
     * Deletes all entities managed by the repository.
     */
    @Override
    public void deleteAll() {

        map.clear();
    }

    /**
     * Saves a given entity. Use the returned instance for further operations as the save operation might have changed the
     * entity instance completely.
     *
     * @param entity must not be {@literal null}.
     *
     * @return the saved entity; will never be {@literal null}.
     *
     * @throws IllegalArgumentException in case the given {@literal entity} is {@literal null}.
     */
    @Override
    public < S extends Event > S save( S entity ) {

        map.put( entity.getId(), entity );
        return entity;
    }

    @Override
    public < S extends Event > List< S > saveAll( Iterable< S > entities ) {

        return null;
    }

    /**
     * Retrieves an entity by its id.
     *
     * @param uuid must not be {@literal null}.
     *
     * @return the entity with the given id or {@literal Optional#empty()} if none found.
     *
     * @throws IllegalArgumentException if {@literal id} is {@literal null}.
     */
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

    /**
     * Returns whether an entity with the given id exists.
     *
     * @param uuid must not be {@literal null}.
     *
     * @return {@literal true} if an entity with the given id exists, {@literal false} otherwise.
     *
     * @throws IllegalArgumentException if {@literal id} is {@literal null}.
     */
    @Override
    public boolean existsById( UUID uuid ) {

        return map.containsKey(uuid);
    }

    /**
     * Flushes all pending changes to the database.
     */
    @Override
    public void flush() {

    }

    /**
     * Saves an entity and flushes changes instantly.
     *
     * @param entity
     *
     * @return the saved entity
     */
    @Override
    public < S extends Event > S saveAndFlush( S entity ) {

        return null;
    }

    /**
     * Deletes the given entities in a batch which means it will create a single. Assume that we will clear
     * the {@link EntityManager} after the call.
     *
     * @param entities
     */
    @Override
    public void deleteInBatch( Iterable< Event > entities ) {

    }

    /**
     * Deletes all entities in a batch call.
     */
    @Override
    public void deleteAllInBatch() {

    }

    /**
     * Returns a reference to the entity with the given identifier. Depending on how the JPA persistence provider is
     * implemented this is very likely to always return an instance and throw an
     * {@link EntityNotFoundException} on first access. Some of them will reject invalid identifiers
     * immediately.
     *
     * @param uuid must not be {@literal null}.
     *
     * @return a reference to the entity with the given identifier.
     *
     * @see EntityManager#getReference(Class, Object) for details on when an exception is thrown.
     */
    @Override
    public Event getOne( UUID uuid ) {

        return null;
    }

    /**
     * Returns a single entity matching the given {@link Example} or {@literal null} if none was found.
     *
     * @param example must not be {@literal null}.
     *
     * @return a single entity matching the given {@link Example} or {@link Optional#empty()} if none was found.
     *
     * @throws IncorrectResultSizeDataAccessException if the Example yields more than one result.
     */
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

    /**
     * Returns a {@link Page} of entities matching the given {@link Example}. In case no match could be found, an empty
     * {@link Page} is returned.
     *
     * @param example  must not be {@literal null}.
     * @param pageable can be {@literal null}.
     *
     * @return a {@link Page} of entities matching the given {@link Example}.
     */
    @Override
    public < S extends Event > Page< S > findAll( Example< S > example, Pageable pageable ) {

        return null;
    }

    /**
     * Returns the number of instances matching the given {@link Example}.
     *
     * @param example the {@link Example} to count instances for. Must not be {@literal null}.
     *
     * @return the number of instances matching the {@link Example}.
     */
    @Override
    public < S extends Event > long count( Example< S > example ) {

        return 0;
    }

    /**
     * Checks whether the data store contains elements that match the given {@link Example}.
     *
     * @param example the {@link Example} to use for the existence check. Must not be {@literal null}.
     *
     * @return {@literal true} if the data store contains elements that match the given {@link Example}.
     */
    @Override
    public < S extends Event > boolean exists( Example< S > example ) {

        return false;
    }

    @Override
    public List< Object[] > findEnteredEvents( UUID userID ) {
        List< Object[] > output = new ArrayList<>();
        for ( UserEventStatus status: statusRepo.findAll() ) {
            if ( userID.equals( status.getUserID() ) ) {
                Event event = map.get( status.getEventID() );
                output.add( new Object[] { event, status } );
            }
        }
        return output;
    }
}
