package tech.geocodeapp.geocode.event.response;

import tech.geocodeapp.geocode.general.response.Response;

import java.util.*;

public class GetBlocksResponse extends Response {
    /**
     * The ids for the Blocks that the User has found
     */
    private List<UUID> blockIDs;

    /**
     * Overload Constructor
     */
    public GetBlocksResponse(boolean success, String message){
        super(success, message);
    }

    /**
     * Overload Constructor
     */
    public GetBlocksResponse(boolean success, String message, List<UUID> blockIDs){
        super(success, message);

        this.blockIDs = blockIDs;
    }

    public List<UUID> getBlockIDs() {
        return blockIDs;
    }

    public void setBlockIDs(List<UUID> blockIDs) {
        this.blockIDs = blockIDs;
    }
}
