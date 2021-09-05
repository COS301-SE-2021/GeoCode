package tech.geocodeapp.geocode.event.response;

import tech.geocodeapp.geocode.general.response.Response;

import java.util.List;

public class SubmitBlocklyCodeResponse extends Response {
    /**
     * A list for whether each test case passed
     */
    private List<Boolean> passedCases;

    public SubmitBlocklyCodeResponse(boolean success, String message){
        super(success, message);
    }

    public SubmitBlocklyCodeResponse(boolean success, String message, List<Boolean> passedCases){
        super(success, message);
        this.passedCases = passedCases;
    }

    public List<Boolean> getPassedCases() {
        return passedCases;
    }

    public void setPassedCases(List<Boolean> passedCases) {
        this.passedCases = passedCases;
    }
}
