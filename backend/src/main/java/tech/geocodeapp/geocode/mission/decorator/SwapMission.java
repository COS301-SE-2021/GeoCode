package tech.geocodeapp.geocode.mission.decorator;

public class SwapMission extends MissionDecorator{

    public SwapMission(MissionComponent decoratedMission) {
        super(decoratedMission);
    }

    /**
     * Compares the amount and completion to see if the correct number of swaps have been made
     * @return the value returned from comparing if the completion and amount of a
     * decorated mission are equal
     */
    @Override
    public boolean checkIfFinished() {
        finished = decoratedMission.getAmount().equals(decoratedMission.getCompletion());
        return finished;
    }

}
