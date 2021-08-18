package tech.geocodeapp.geocode.user;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import tech.geocodeapp.geocode.collectable.model.CollectableType;
import tech.geocodeapp.geocode.geocode.model.GeoCode;
import tech.geocodeapp.geocode.user.model.User;
import tech.geocodeapp.geocode.user.repository.UserRepository;

public class UserMockRepository implements UserRepository {
    private static final HashMap<UUID, User> map = new HashMap<>();

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public List<User> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<User> findAllById(Iterable<UUID> iterable) {
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
    public void delete(User user) {

    }

    @Override
    public void deleteAll(Iterable<? extends User> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends User> S save(S s) {
        map.put(s.getId(), s);
        return s;
    }

    @Override
    public <S extends User> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<User> findById(UUID uuid) {
        return Optional.ofNullable(map.get(uuid));
    }

    @Override
    public boolean existsById(UUID uuid) {
        return map.containsKey(uuid);
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends User> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<User> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public User getOne(UUID uuid) {
        return null;
    }

    @Override
    public <S extends User> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends User> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends User> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends User> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends User> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends User> boolean exists(Example<S> example) {
        return false;
    }

    /**
     * Add GeoCode to the User's foundGeoCodes
     *
     * @param userID    UserID
     * @param geocodeID GeoCode to add
     */
    @Override
    public void addFoundGeoCode(UUID userID, UUID geocodeID) {
        Optional<User> optionalUser = findById(userID);
        User user = optionalUser.get();

        //no duplicates
        if(user.getFoundGeocodes().stream().anyMatch(geocode -> geocode.getId().equals(geocodeID))){
            return;
        }

        GeoCode geoCode = new GeoCode();
        geoCode.setId(geocodeID);
        user.addFoundGeocodesItem(geoCode);

        map.put(userID, user);
    }

    /**
     * Add CollectableType to the User's foundCollectableTypes
     *
     * @param userID            UserID
     * @param collectableTypeID CollectableType to add
     */
    @Override
    public void addFoundCollectableType(UUID userID, UUID collectableTypeID) {
        Optional<User> optionalUser = findById(userID);
        User user = optionalUser.get();

        //no duplicates
        if(user.getFoundCollectableTypes().stream().anyMatch(type -> type.getId().equals(collectableTypeID))){
            return;
        }

        CollectableType collectableType = new CollectableType();
        collectableType.setId(collectableTypeID);
        collectableType.setName("test CollectableType");
        user.addFoundCollectableTypesItem(collectableType);

        System.out.println("number of found collectable types:"+user.getFoundCollectableTypes().size());

        map.put(userID, user);
    }

    /**
     * Add GeoCode to the User's ownedGeoCodes
     *
     * @param userID    UserID
     * @param geocodeID GeoCode to add
     */
    @Override
    public void addOwnedGeoCode(UUID userID, UUID geocodeID) {
        Optional<User> optionalUser = findById(userID);
        User user = optionalUser.get();

        //no duplicates
        if(user.getOwnedGeocodes().stream().anyMatch(geocode -> geocode.getId().equals(geocodeID))){
            return;
        }

        GeoCode geoCode = new GeoCode();
        geoCode.setId(geocodeID);
        user.addOwnedGeocodesItem(geoCode);

        map.put(userID, user);
    }
}
