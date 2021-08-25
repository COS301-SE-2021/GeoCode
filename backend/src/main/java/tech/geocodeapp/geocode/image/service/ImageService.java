package tech.geocodeapp.geocode.image.service;

import tech.geocodeapp.geocode.image.exceptions.InvalidRequestException;
import tech.geocodeapp.geocode.image.exceptions.NotFoundException;
import tech.geocodeapp.geocode.image.request.CreateImageRequest;
import tech.geocodeapp.geocode.image.request.GetImageRequest;
import tech.geocodeapp.geocode.image.response.CreateImageResponse;
import tech.geocodeapp.geocode.image.response.GetImageResponse;

import java.io.IOException;

/**
 * This is the main interface for the Image subsystem,
 * it is used to store and retrieve collectable images.
 */
public interface ImageService {

    CreateImageResponse createImage(CreateImageRequest request) throws InvalidRequestException, IOException;

    GetImageResponse getImage(GetImageRequest request) throws InvalidRequestException, NotFoundException, IOException;

}
