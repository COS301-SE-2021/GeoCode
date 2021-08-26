package tech.geocodeapp.geocode.image.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tech.geocodeapp.geocode.image.response.CreateImageResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Validated
public interface ImageApi {

    @Operation(summary = "Retrieves an image", description = "Retrieves an image with the specified ID", tags={ "Image" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Image retrieved", content = @Content(mediaType = "image/gif", schema = @Schema(implementation = Resource.class))),
            @ApiResponse(responseCode = "200", description = "Image retrieved", content = @Content(mediaType = "image/png", schema = @Schema(implementation = Resource.class))),
            @ApiResponse(responseCode = "400", description = "Request does not include a valid UUID"),
            @ApiResponse(responseCode = "401", description = "Invalid JWT token"),
            @ApiResponse(responseCode = "404", description = "An image with the specified ID was not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error") })
    @RequestMapping(value = "/Image/getImage/{imageID}",
            produces = { "image/gif", "image/png" },
            method = RequestMethod.GET)
    ResponseEntity<byte[]> getImage(@Parameter(in = ParameterIn.PATH, description = "ID of the image to retrieve", required=true, schema=@Schema()) @PathVariable("imageID") UUID imageID);


    @Operation(summary = "Uploads an image", description = "Uploads an image to the server and returns its ID", security = {
            @SecurityRequirement(name = "bearerAuth")    }, tags={ "Image" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Image uploaded", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CreateImageResponse.class))),
            @ApiResponse(responseCode = "400", description = "Request does not include a file", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CreateImageResponse.class))),
            @ApiResponse(responseCode = "401", description = "Invalid JWT token"),
            @ApiResponse(responseCode = "413", description = "File is too large", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CreateImageResponse.class))),
            @ApiResponse(responseCode = "415", description = "File is not an image", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CreateImageResponse.class))),
            @ApiResponse(responseCode = "422", description = "Image could not be processed", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CreateImageResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CreateImageResponse.class))) })
    @RequestMapping(value = "/Image/uploadImage",
            produces = { "application/json", "application/xml" },
            consumes = { "image/bmp", "image/gif", "image/jpeg", "image/png" },
            method = RequestMethod.POST)
    ResponseEntity<CreateImageResponse> uploadImage(HttpServletRequest request);

}