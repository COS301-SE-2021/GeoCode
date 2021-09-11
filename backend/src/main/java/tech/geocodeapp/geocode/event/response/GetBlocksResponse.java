package tech.geocodeapp.geocode.event.response;

import tech.geocodeapp.geocode.general.response.Response;

import java.util.*;

public class GetBlocksResponse extends Response {
    /**
     * The ids for the Blocks that the User has found
     */
    private List<String> blockNames;

    /**
     * Overload Constructor
     */
    public GetBlocksResponse(boolean success, String message){
        super(success, message);
    }

    /**
     * Overload Constructor
     */
    public GetBlocksResponse(boolean success, String message, List<String> blockNames){
        super(success, message);

        this.blockNames = blockNames;
    }

    public List<String> getBlockNames() {
        return blockNames;
    }

    public void setBlockNames(List<String> blockNames) {
        this.blockNames = blockNames;
    }
}
