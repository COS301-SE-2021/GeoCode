/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.26).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

import io.swagger.model.Collectable;
import io.swagger.model.CollectableSet;
import io.swagger.model.CollectableType;
import io.swagger.model.CreateCollectableRequest;
import io.swagger.model.CreateCollectableSetRequest;
import io.swagger.model.CreateCollectableTypeRequest;
import io.swagger.model.GetCollectableByTypeRequest;
import io.swagger.model.GetCollectableSetsResponse;
import io.swagger.model.GetCollectableTypeByRarityRequest;
import io.swagger.model.GetCollectableTypeBySetRequest;
import io.swagger.model.GetCollectableTypesResponse;
import io.swagger.model.GetCollectablesResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.CookieValue;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-06-01T17:39:45.783Z[GMT]")
@Validated
public interface CollectableApi {

    @Operation(summary = "Creates a new Collectable", description = "Create Collectable", security = {
        @SecurityRequirement(name = "bearerAuth")    }, tags={ "Collectable" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Collectable created successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Collectable.class))),
        
        @ApiResponse(responseCode = "401", description = "Invalid JWT token") })
    @RequestMapping(value = "/Collectable/createCollectable",
        produces = { "application/json", "application/xml" }, 
        consumes = { "application/json", "application/xml" }, 
        method = RequestMethod.POST)
    ResponseEntity<Collectable> createCollectable(@Parameter(in = ParameterIn.DEFAULT, description = "Request to create a new Collectable", required=true, schema=@Schema()) @Valid @RequestBody CreateCollectableRequest body);


    @Operation(summary = "Create a new Collectable Set", description = "Create Collectable Set", security = {
        @SecurityRequirement(name = "bearerAuth")    }, tags={ "Collectable" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Successfully created Collectable Set", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CollectableSet.class))),
        
        @ApiResponse(responseCode = "401", description = "Invalid JWT token") })
    @RequestMapping(value = "/Collectable/createCollectableSet",
        produces = { "application/json", "application/xml" }, 
        consumes = { "application/json", "application/xml" }, 
        method = RequestMethod.POST)
    ResponseEntity<CollectableSet> createCollectableSet(@Parameter(in = ParameterIn.DEFAULT, description = "Request to create a new Collectable Set", required=true, schema=@Schema()) @Valid @RequestBody CreateCollectableSetRequest body);


    @Operation(summary = "Creates a new Collectable Type", description = "Create Collectable Type", security = {
        @SecurityRequirement(name = "bearerAuth")    }, tags={ "Collectable" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Collectable Type created successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CollectableType.class))),
        
        @ApiResponse(responseCode = "401", description = "Invalid JWT token") })
    @RequestMapping(value = "/Collectable/createCollectableType",
        produces = { "application/json", "application/xml" }, 
        consumes = { "application/json", "application/xml" }, 
        method = RequestMethod.POST)
    ResponseEntity<CollectableType> createCollectableType(@Parameter(in = ParameterIn.DEFAULT, description = "Request to create a new Collectable Type", required=true, schema=@Schema()) @Valid @RequestBody CreateCollectableTypeRequest body);


    @Operation(summary = "", description = "Returns all Collectables of the specified type", security = {
        @SecurityRequirement(name = "bearerAuth")    }, tags={ "Collectable" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Successfully returned Collectables of given type", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GetCollectablesResponse.class))),
        
        @ApiResponse(responseCode = "401", description = "Invalid JWT token") })
    @RequestMapping(value = "/Collectable/getCollectableByType",
        produces = { "application/json", "application/xml" }, 
        consumes = { "application/json", "application/xml" }, 
        method = RequestMethod.POST)
    ResponseEntity<GetCollectablesResponse> getCollectableByType(@Parameter(in = ParameterIn.DEFAULT, description = "Request Collectables by type", required=true, schema=@Schema()) @Valid @RequestBody GetCollectableByTypeRequest body);


    @Operation(summary = "", description = "Returns all Collectable Sets", security = {
        @SecurityRequirement(name = "bearerAuth")    }, tags={ "Collectable" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Successfully returned Collectable Sets", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GetCollectableSetsResponse.class))),
        
        @ApiResponse(responseCode = "401", description = "Invalid JWT token") })
    @RequestMapping(value = "/Collectable/getCollectableSets",
        produces = { "application/json", "application/xml" }, 
        method = RequestMethod.GET)
    ResponseEntity<GetCollectableSetsResponse> getCollectableSets();


    @Operation(summary = "", description = "Returns all Collectable Types of the specified rarity", security = {
        @SecurityRequirement(name = "bearerAuth")    }, tags={ "Collectable" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Successfully returned Collectable Types of given rarity", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GetCollectableTypesResponse.class))),
        
        @ApiResponse(responseCode = "401", description = "Invalid JWT token") })
    @RequestMapping(value = "/Collectable/getCollectableTypeByRarity",
        produces = { "application/json", "application/xml" }, 
        consumes = { "application/json", "application/xml" }, 
        method = RequestMethod.POST)
    ResponseEntity<GetCollectableTypesResponse> getCollectableTypeByRarity(@Parameter(in = ParameterIn.DEFAULT, description = "Request Collectable Types by rarity", required=true, schema=@Schema()) @Valid @RequestBody GetCollectableTypeByRarityRequest body);


    @Operation(summary = "", description = "Returns all Collectable Types of the specified set", security = {
        @SecurityRequirement(name = "bearerAuth")    }, tags={ "Collectable" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Successfully returned Collectable Types of given set", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GetCollectableTypesResponse.class))),
        
        @ApiResponse(responseCode = "401", description = "Invalid JWT token") })
    @RequestMapping(value = "/Collectable/getCollectableTypeBySet",
        produces = { "application/json", "application/xml" }, 
        consumes = { "application/json", "application/xml" }, 
        method = RequestMethod.POST)
    ResponseEntity<GetCollectableTypesResponse> getCollectableTypeBySet(@Parameter(in = ParameterIn.DEFAULT, description = "Request Collectable Types by set", required=true, schema=@Schema()) @Valid @RequestBody GetCollectableTypeBySetRequest body);


    @Operation(summary = "", description = "Returns all Collectable Types", security = {
        @SecurityRequirement(name = "bearerAuth")    }, tags={ "Collectable" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Successfully returned Collectable Types", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GetCollectableTypesResponse.class))),
        
        @ApiResponse(responseCode = "401", description = "Invalid JWT token") })
    @RequestMapping(value = "/Collectable/getCollectableTypes",
        produces = { "application/json", "application/xml" }, 
        method = RequestMethod.GET)
    ResponseEntity<GetCollectableTypesResponse> getCollectableTypes();


    @Operation(summary = "", description = "Returns all Collectables", security = {
        @SecurityRequirement(name = "bearerAuth")    }, tags={ "Collectable" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Successfully returned Collectables", content = @Content(mediaType = "application/json", schema = @Schema(implementation = GetCollectablesResponse.class))),
        
        @ApiResponse(responseCode = "401", description = "Invalid JWT token") })
    @RequestMapping(value = "/Collectable/getCollectables",
        produces = { "application/json", "application/xml" }, 
        method = RequestMethod.GET)
    ResponseEntity<GetCollectablesResponse> getCollectables();

}

