package tech.geocodeapp.geocode.image.service;

import tech.geocodeapp.geocode.image.exceptions.InvalidRequestException;
import tech.geocodeapp.geocode.image.exceptions.NotFoundException;
import tech.geocodeapp.geocode.image.request.CreateImageRequest;
import tech.geocodeapp.geocode.image.request.GetImageBytesRequest;
import tech.geocodeapp.geocode.image.response.CreateImageResponse;
import tech.geocodeapp.geocode.image.response.GetImageBytesResponse;

import java.io.IOException;

/**
 * This is the main interface for the Image subsystem,
 * it is used to store and retrieve collectable images.
 */
public interface ImageService {

    CreateImageResponse createImage(CreateImageRequest request) throws InvalidRequestException, IOException;

    GetImageBytesResponse getImageBytes(GetImageBytesRequest request) throws InvalidRequestException, NotFoundException, IOException;

}
