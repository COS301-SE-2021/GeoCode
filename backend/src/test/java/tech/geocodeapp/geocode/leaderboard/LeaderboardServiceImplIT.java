package tech.geocodeapp.geocode.leaderboard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tech.geocodeapp.geocode.general.exception.NullRequestParameterException;
import tech.geocodeapp.geocode.leaderboard.model.Leaderboard;
import tech.geocodeapp.geocode.leaderboard.request.CreateLeaderboardRequest;
import tech.geocodeapp.geocode.leaderboard.request.CreatePointRequest;
import tech.geocodeapp.geocode.leaderboard.response.CreateLeaderboardResponse;
import tech.geocodeapp.geocode.leaderboard.response.PointResponse;
import tech.geocodeapp.geocode.leaderboard.service.LeaderboardService;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@TestMethodOrder( MethodOrderer.OrderAnnotation.class )
public class LeaderboardServiceImplIT {
    @Autowired
    private LeaderboardService leaderboardService;
    private final String menloParkChristmas = "Christmas 2021 market";

    @Test
    public void createLeaderboardTestNullRequest(){
        try{
            CreateLeaderboardResponse response = leaderboardService.createLeaderboard(null);

            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals("The CreateLeaderboardRequest object passed was NULL", response.getMessage());
            Assertions.assertNull(response.getLeaderboard());
        }catch (NullRequestParameterException e){
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    public void createLeaderboardTestNullName(){
        CreateLeaderboardRequest request = new CreateLeaderboardRequest(null);

        assertThatThrownBy(() -> leaderboardService.createLeaderboard(request))
                .isInstanceOf(NullRequestParameterException.class);
    }

    @Test
    public void createLeaderboardTestNew(){
        CreateLeaderboardRequest request = new CreateLeaderboardRequest(menloParkChristmas);

        try {
            CreateLeaderboardResponse response = leaderboardService.createLeaderboard(request);

            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("The Leaderboard was successfully created", response.getMessage());

            Leaderboard christmasLeaderboard = response.getLeaderboard();

            Assertions.assertNotNull(christmasLeaderboard);
            Assertions.assertEquals(menloParkChristmas, christmasLeaderboard.getName());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createPointTestNullRequest() {
        try {
            PointResponse response = leaderboardService.createPoint(null);
            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals("The CreatePointRequest passed was NULL", response.getMessage());
            Assertions.assertNull(response.getPoint());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createPointTestNullAllParameters() {
        CreatePointRequest request = new CreatePointRequest(null, null, null);

        assertThatThrownBy(() -> leaderboardService.createPoint(request))
                .isInstanceOf(NullRequestParameterException.class);
    }

    @Test
    public void createPointTestInvalidLeaderboardId() {
        CreatePointRequest request = new CreatePointRequest(1, UUID.randomUUID(), UUID.randomUUID());
        try {
            PointResponse response = leaderboardService.createPoint(request);
            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals("Invalid leaderboard Id provided", response.getMessage());
            Assertions.assertNull(response.getPoint());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createPointTestInvalidUserId() {
        //create a leaderboard to ensure a valid id for leaderboardId parameter
        CreateLeaderboardRequest leaderboardRequest = new CreateLeaderboardRequest("test");
        try {
            CreateLeaderboardResponse leaderboardResponse = leaderboardService.createLeaderboard(leaderboardRequest);
            CreatePointRequest request = new CreatePointRequest(1, UUID.randomUUID(), leaderboardResponse.getLeaderboard().getId());
            PointResponse response = leaderboardService.createPoint(request);
            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals("Invalid user Id provided", response.getMessage());
            Assertions.assertNull(response.getPoint());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }
}
