package tech.geocodeapp.geocode.leaderboard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tech.geocodeapp.geocode.general.exception.NullRequestParameterException;
import tech.geocodeapp.geocode.geocode.model.GeoPoint;
import tech.geocodeapp.geocode.leaderboard.model.Point;
import tech.geocodeapp.geocode.leaderboard.repository.LeaderboardRepository;
import tech.geocodeapp.geocode.leaderboard.repository.PointRepository;
import tech.geocodeapp.geocode.leaderboard.request.*;
import tech.geocodeapp.geocode.leaderboard.response.PointResponse;
import tech.geocodeapp.geocode.leaderboard.service.LeaderboardService;
import tech.geocodeapp.geocode.leaderboard.service.LeaderboardServiceImpl;
import tech.geocodeapp.geocode.user.request.GetUserByIdRequest;
import tech.geocodeapp.geocode.user.request.HandleLoginRequest;
import tech.geocodeapp.geocode.user.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
public class LeaderboardServiceImplIT {
    @Autowired
    private UserService userService;

    @Autowired
    private PointRepository pointRepository;

    @Autowired
    private LeaderboardRepository leaderboardRepository;

    private LeaderboardService leaderboardService;

    @BeforeEach
    void setUp(){
        leaderboardService = new LeaderboardServiceImpl(leaderboardRepository, pointRepository, userService);
    }

    private void registerNewUser(String username){
        try {
            userService.handleLogin(new HandleLoginRequest(new GeoPoint(0.0, 0.0)));
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createLeaderboardTestNullRequest(){
        try{
            var response = leaderboardService.createLeaderboard(null);

            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals("The CreateLeaderboardRequest object passed was NULL", response.getMessage());
            Assertions.assertNull(response.getLeaderboard());
        }catch (NullRequestParameterException e){
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    public void createLeaderboardTestNullName(){
        var request = new CreateLeaderboardRequest(null);

        assertThatThrownBy(() -> leaderboardService.createLeaderboard(request))
                .isInstanceOf(NullRequestParameterException.class);
    }

    @Test
    public void createLeaderboardTestNew(){
        var menloParkChristmas = "Christmas 2021 market";
        var request = new CreateLeaderboardRequest(menloParkChristmas);

        try {
            var response = leaderboardService.createLeaderboard(request);

            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("The Leaderboard was successfully created", response.getMessage());

            var christmasLeaderboard = response.getLeaderboard();

            Assertions.assertNotNull(christmasLeaderboard);
            Assertions.assertEquals(menloParkChristmas, christmasLeaderboard.getName());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createPointTestNullRequest() {
        try {
            var response = leaderboardService.createPoint(null);
            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals("The CreatePointRequest passed was NULL", response.getMessage());
            Assertions.assertNull(response.getPoint());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createPointTestNullAllParameters() {
        var request = new CreatePointRequest(null, null, null);

        assertThatThrownBy(() -> leaderboardService.createPoint(request))
                .isInstanceOf(NullRequestParameterException.class);
    }

    @Test
    public void createPointTestInvalidLeaderboardId() {
        var request = new CreatePointRequest(1, UUID.randomUUID(), UUID.randomUUID());
        try {
            var response = leaderboardService.createPoint(request);
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
        var leaderboardRequest = new CreateLeaderboardRequest("test");

        try {
            var leaderboardResponse = leaderboardService.createLeaderboard(leaderboardRequest);
            var request = new CreatePointRequest(1, UUID.randomUUID(), leaderboardResponse.getLeaderboard().getId());
            var response = leaderboardService.createPoint(request);

            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals("Invalid user Id provided", response.getMessage());
            Assertions.assertNull(response.getPoint());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createPointTestValidRequestParameters() {
        var leaderboardRequest = new CreateLeaderboardRequest("testValid");

        try {
            var leaderboardResponse = leaderboardService.createLeaderboard(leaderboardRequest);

            var userId = registerNewUser("Test user");

            var request = new CreatePointRequest(1, userId, leaderboardResponse.getLeaderboard().getId());
            var response = leaderboardService.createPoint(request);

            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("The Point was successfully created.", response.getMessage());
            Assertions.assertNotNull(response.getPoint());
            Assertions.assertEquals(1, response.getPoint().getAmount());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deletePointTestNullRequest() {
        try {
            var response = leaderboardService.deletePoint(null);

            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals("The DeletePointRequest passed was NULL", response.getMessage());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deletePointTestNullPointId() {
        var request = new DeletePointRequest(null);

        assertThatThrownBy(() -> leaderboardService.deletePoint(request))
                .isInstanceOf(NullRequestParameterException.class);
    }

    @Test
    public void deletePointTestInvalidPointId() {
        var request = new DeletePointRequest(UUID.randomUUID());

        try {
            var response = leaderboardService.deletePoint(request);

            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals("No Point with the given Id exists", response.getMessage());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deletePointTestValidPointId() {
        var leaderboardRequest = new CreateLeaderboardRequest("test delete");

        try {
            var leaderboardResponse = leaderboardService.createLeaderboard(leaderboardRequest);

            var userId = registerNewUser("Delete point");

            var createPointRequest = new CreatePointRequest(1, userId, leaderboardResponse.getLeaderboard().getId());
            var createPointResponse = leaderboardService.createPoint(createPointRequest);

            var request = new DeletePointRequest(createPointResponse.getPoint().getId());
            var response = leaderboardService.deletePoint(request);

            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("Successfully deleted the provided point", response.getMessage());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updatePointTestNullRequest() {
        try {
            var response = leaderboardService.updatePoint(null);

            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals("The UpdatePointRequest passed was NULL", response.getMessage());
            Assertions.assertNull(response.getPoint());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updatePointTestNullPointId() {
        var request = new UpdatePointRequest(null, null, null, null);

        assertThatThrownBy(() -> leaderboardService.updatePoint(request))
                .isInstanceOf(NullRequestParameterException.class);
    }

    @Test
    public void updatePointTestInvalidPointId() {
        var request  = new UpdatePointRequest(UUID.randomUUID(), 1, null, null);

        try {
            var response = leaderboardService.updatePoint(request);

            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals("No point with the provided Id exists", response.getMessage());
            Assertions.assertNull(response.getPoint());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updatePointTestOnlyAmountUpdated() {
        var leaderboardRequest = new CreateLeaderboardRequest("update amount");

        try {
            var leaderboardResponse = leaderboardService.createLeaderboard(leaderboardRequest);

            var userId = registerNewUser("Test user");

            var createPointRequest = new CreatePointRequest(1, userId, leaderboardResponse.getLeaderboard().getId());
            var createPointResponse = leaderboardService.createPoint(createPointRequest);

            var request = new UpdatePointRequest(createPointResponse.getPoint().getId(), 2, null, null);
            var response = leaderboardService.updatePoint(request);

            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("Updated point successfully", response.getMessage());
            Assertions.assertNotNull(response.getPoint());
            Assertions.assertEquals(2, response.getPoint().getAmount());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updatePointTestInvalidLeaderboardId() {
        var leaderboardRequest = new CreateLeaderboardRequest("update leaderboard invalid");

        try {
            var leaderboardResponse = leaderboardService.createLeaderboard(leaderboardRequest);

            var userId = registerNewUser("Test user");

            var createPointRequest = new CreatePointRequest(1, userId, leaderboardResponse.getLeaderboard().getId());
            var createPointResponse = leaderboardService.createPoint(createPointRequest);

            var request = new UpdatePointRequest(createPointResponse.getPoint().getId(), null, null, UUID.randomUUID());
            var response = leaderboardService.updatePoint(request);

            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals("Invalid LeaderboardId to update to was provided", response.getMessage());
            Assertions.assertNull(response.getPoint());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updatePointTestOnlyLeaderboardId() {
        var leaderboardRequest = new CreateLeaderboardRequest("test");

        try {
            var leaderboardResponse = leaderboardService.createLeaderboard(leaderboardRequest);

            var userId = registerNewUser("Test user");

            var createPointRequest = new CreatePointRequest(1, userId, leaderboardResponse.getLeaderboard().getId());
            var createPointResponse = leaderboardService.createPoint(createPointRequest);

            var updatedLeaderboardRequest = new CreateLeaderboardRequest("updated leaderboard");
            var updatedLeaderboardResponse = leaderboardService.createLeaderboard(updatedLeaderboardRequest);

            var request = new UpdatePointRequest(createPointResponse.getPoint().getId(), null, null, updatedLeaderboardResponse.getLeaderboard().getId());
            var response = leaderboardService.updatePoint(request);

            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("Updated point successfully", response.getMessage());
            Assertions.assertNotNull(response.getPoint());
            Assertions.assertEquals(updatedLeaderboardResponse.getLeaderboard().getId(), response.getPoint().getLeaderBoard().getId());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updatePointTestInvalidUserId() {
        var leaderboardRequest = new CreateLeaderboardRequest("test");

        try {
            var leaderboardResponse = leaderboardService.createLeaderboard(leaderboardRequest);

            var userId = registerNewUser("Test user");

            var createPointRequest = new CreatePointRequest(1, userId, leaderboardResponse.getLeaderboard().getId());
            var pointResponse = leaderboardService.createPoint(createPointRequest);

            var request = new UpdatePointRequest(pointResponse.getPoint().getId(), null, UUID.randomUUID(), null);
            var response = leaderboardService.updatePoint(request);

            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals("Invalid UserId to update to was provided", response.getMessage());
            Assertions.assertNull(response.getPoint());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updatePointTestOnlyUserId() {
        var leaderboardRequest = new CreateLeaderboardRequest("test");

        try {
            var leaderboardResponse = leaderboardService.createLeaderboard(leaderboardRequest);

            var userId = registerNewUser("Test user");

            var createPointRequest = new CreatePointRequest(1, userId, leaderboardResponse.getLeaderboard().getId());
            var pointResponse = leaderboardService.createPoint(createPointRequest);

            var updatedUserId = registerNewUser("Updated User");

            var request = new UpdatePointRequest(pointResponse.getPoint().getId(), null, updatedUserId, null);
            var response = leaderboardService.updatePoint(request);

            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("Updated point successfully", response.getMessage());
            Assertions.assertNotNull(response.getPoint());
            Assertions.assertEquals(updatedUserId, response.getPoint().getUser().getId());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updatePointTestValidAmountAndUserId() {
        var leaderboardRequest = new CreateLeaderboardRequest("test");

        try {
            var leaderboardResponse = leaderboardService.createLeaderboard(leaderboardRequest);

            var userId = registerNewUser("Test user");

            var createPointRequest = new CreatePointRequest(1, userId, leaderboardResponse.getLeaderboard().getId());
            var pointResponse = leaderboardService.createPoint(createPointRequest);

            var updatedUserId = registerNewUser("Updated User");

            var request = new UpdatePointRequest(pointResponse.getPoint().getId(), 3, updatedUserId, null);
            var response = leaderboardService.updatePoint(request);

            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("Updated point successfully", response.getMessage());
            Assertions.assertNotNull(response.getPoint());
            Assertions.assertEquals(updatedUserId, response.getPoint().getUser().getId());
            Assertions.assertEquals(3, response.getPoint().getAmount());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updatePointTestValidAmountAndLeaderboardId() {
        var leaderboardRequest = new CreateLeaderboardRequest("test");

        try {
            var leaderboardResponse = leaderboardService.createLeaderboard(leaderboardRequest);

            var userId = registerNewUser("Test user");

            var createPointRequest = new CreatePointRequest(1, userId, leaderboardResponse.getLeaderboard().getId());
            var createPointResponse = leaderboardService.createPoint(createPointRequest);

            var updatedLeaderboardRequest = new CreateLeaderboardRequest("updated leaderboard");
            var updatedLeaderboardResponse = leaderboardService.createLeaderboard(updatedLeaderboardRequest);

            var request = new UpdatePointRequest(createPointResponse.getPoint().getId(), 4, null, updatedLeaderboardResponse.getLeaderboard().getId());
            var response = leaderboardService.updatePoint(request);

            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("Updated point successfully", response.getMessage());
            Assertions.assertNotNull(response.getPoint());
            Assertions.assertEquals(updatedLeaderboardResponse.getLeaderboard().getId(), response.getPoint().getLeaderBoard().getId());
            Assertions.assertEquals(4, response.getPoint().getAmount());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updatePointTestValidUserIdAndLeaderboardId() {
        var leaderboardRequest = new CreateLeaderboardRequest("test");

        try {
            var leaderboardResponse = leaderboardService.createLeaderboard(leaderboardRequest);

            var userId = registerNewUser("Test user");

            var createPointRequest = new CreatePointRequest(1, userId, leaderboardResponse.getLeaderboard().getId());
            var pointResponse = leaderboardService.createPoint(createPointRequest);

            var updatedUserId = registerNewUser("Updated User");

            var updatedLeaderboardRequest = new CreateLeaderboardRequest("updated leaderboard");
            var updatedLeaderboardResponse = leaderboardService.createLeaderboard(updatedLeaderboardRequest);

            var request = new UpdatePointRequest(pointResponse.getPoint().getId(), null, updatedUserId, updatedLeaderboardResponse.getLeaderboard().getId());
            var response = leaderboardService.updatePoint(request);

            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("Updated point successfully", response.getMessage());
            Assertions.assertNotNull(response.getPoint());
            Assertions.assertEquals(updatedUserId, response.getPoint().getUser().getId());
            Assertions.assertEquals(updatedLeaderboardResponse.getLeaderboard().getId(), response.getPoint().getLeaderBoard().getId());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updatePointTestAllOptionalFieldsValid() {
        var leaderboardRequest = new CreateLeaderboardRequest("test");

        try {
            var leaderboardResponse = leaderboardService.createLeaderboard(leaderboardRequest);

            var userId = registerNewUser("Test user");

            var createPointRequest = new CreatePointRequest(1, userId, leaderboardResponse.getLeaderboard().getId());
            var pointResponse = leaderboardService.createPoint(createPointRequest);

            var updatedUserId = registerNewUser("Updated User");

            var updatedLeaderboardRequest = new CreateLeaderboardRequest("updated leaderboard");
            var updatedLeaderboardResponse = leaderboardService.createLeaderboard(updatedLeaderboardRequest);

            var request = new UpdatePointRequest(pointResponse.getPoint().getId(), 2, updatedUserId, updatedLeaderboardResponse.getLeaderboard().getId());
            var response = leaderboardService.updatePoint(request);

            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("Updated point successfully", response.getMessage());
            Assertions.assertNotNull(response.getPoint());
            Assertions.assertEquals(updatedUserId, response.getPoint().getUser().getId());
            Assertions.assertEquals(updatedLeaderboardResponse.getLeaderboard().getId(), response.getPoint().getLeaderBoard().getId());
            Assertions.assertEquals(2, response.getPoint().getAmount());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getEventLeaderboardTestNullRequest() {
        try {
            var response = leaderboardService.getEventLeaderboard(null);

            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals("The GetEventLeaderboardRequest object passed was NULL", response.getMessage());
            Assertions.assertNull(response.getLeaderboard());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getEventLeaderboardTestNullRequestParameters() {
        var request = new GetEventLeaderboardRequest(null, null, null);

        assertThatThrownBy(() -> leaderboardService.getEventLeaderboard(request))
                .isInstanceOf(NullRequestParameterException.class);
    }

    @Test
    public void getEventLeaderboardTestInvalidLeaderboardId() {
        var request = new GetEventLeaderboardRequest(UUID.randomUUID(), 1, 1);

        try {
            var response = leaderboardService.getEventLeaderboard(request);

            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals("No leaderboard exists for the provided leaderboardId", response.getMessage());
            Assertions.assertTrue(response.getLeaderboard().isEmpty());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getEventLeaderboardTestStartingGreaterThanPointsInLeaderboard() {
        var leaderboardRequest = new CreateLeaderboardRequest("test");

        try {
            var leaderboardResponse = leaderboardService.createLeaderboard(leaderboardRequest);

            var userId = registerNewUser("Test user");

            var pointRequest = new CreatePointRequest(1, userId, leaderboardResponse.getLeaderboard().getId());
            leaderboardService.createPoint(pointRequest);

            var request = new GetEventLeaderboardRequest(leaderboardResponse.getLeaderboard().getId(), 2, 1);
            var response = leaderboardService.getEventLeaderboard(request);

            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals("Starting is greater than the number of points in the leaderboard", response.getMessage());
            Assertions.assertTrue(response.getLeaderboard().isEmpty());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getEventLeaderboardTestResponseWithAllPointsInLeaderboard() {
        var leaderboardRequest = new CreateLeaderboardRequest("test");

        try {
            var leaderboardResponse = leaderboardService.createLeaderboard(leaderboardRequest);

            //Create three users to use
            List<UUID> userIds = new ArrayList<>();
            for (var i = 0; i < 3; i++) {
                userIds.add(UUID.randomUUID());
            }
            for (var i = 0; i < 3; i++) {
                userIds.add(registerNewUser("test user"));
            }

            //create 3 points to rank
            List<PointResponse> pointResponses = new ArrayList<>();
            var pointRequest = new CreatePointRequest(5, userIds.get(0), leaderboardResponse.getLeaderboard().getId());
            pointResponses.add(leaderboardService.createPoint(pointRequest));
            pointRequest.setAmount(10);
            pointRequest.setUserId(userIds.get(1));
            pointResponses.add(leaderboardService.createPoint(pointRequest));
            pointRequest.setAmount(6);
            pointRequest.setUserId(userIds.get(2));
            pointResponses.add(leaderboardService.createPoint(pointRequest));

            var request = new GetEventLeaderboardRequest(leaderboardResponse.getLeaderboard().getId(), 1, 3);
            var response = leaderboardService.getEventLeaderboard(request);

            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("Successfully found points for event", response.getMessage());
            Assertions.assertFalse(response.getLeaderboard().isEmpty());

            //test that each value in the list is in the correct place with the correct value of points and rank
            Assertions.assertEquals(10, response.getLeaderboard().get(0).getPoints());
            Assertions.assertEquals(1, response.getLeaderboard().get(0).getRank());
            Assertions.assertEquals(6, response.getLeaderboard().get(1).getPoints());
            Assertions.assertEquals(2, response.getLeaderboard().get(1).getRank());
            Assertions.assertEquals(5, response.getLeaderboard().get(2).getPoints());
            Assertions.assertEquals(3, response.getLeaderboard().get(2).getRank());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getEventLeaderboardTestCountGreaterThanNumberOfPointsLeft() {
        var leaderboardRequest = new CreateLeaderboardRequest("test");

        try {
            var leaderboardResponse = leaderboardService.createLeaderboard(leaderboardRequest);

            //Create three users to use
            List<UUID> userIds = new ArrayList<>();

            for (var i = 0; i < 3; i++) {
                userIds.add(registerNewUser("test user"));
            }

            //create 3 points to rank
            List<PointResponse> pointResponses = new ArrayList<>();
            var pointRequest = new CreatePointRequest(5, userIds.get(0), leaderboardResponse.getLeaderboard().getId());
            pointResponses.add(leaderboardService.createPoint(pointRequest));
            pointRequest.setAmount(10);
            pointRequest.setUserId(userIds.get(1));
            pointResponses.add(leaderboardService.createPoint(pointRequest));
            pointRequest.setAmount(6);
            pointRequest.setUserId(userIds.get(2));
            pointResponses.add(leaderboardService.createPoint(pointRequest));

            var request = new GetEventLeaderboardRequest(leaderboardResponse.getLeaderboard().getId(), 2, 3);
            var response = leaderboardService.getEventLeaderboard(request);

            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("Successfully found points for event", response.getMessage());
            Assertions.assertFalse(response.getLeaderboard().isEmpty());
            Assertions.assertEquals(2, response.getLeaderboard().size());

            //test that each value in the list is in the correct place with the correct value of points and rank
            Assertions.assertEquals(6, response.getLeaderboard().get(0).getPoints());
            Assertions.assertEquals(2, response.getLeaderboard().get(0).getRank());
            Assertions.assertEquals(5, response.getLeaderboard().get(1).getPoints());
            Assertions.assertEquals(3, response.getLeaderboard().get(1).getRank());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getEventLeaderboardTestCountLowerThanNumberOfPointsInLeaderboard() {
        var leaderboardRequest = new CreateLeaderboardRequest("test");

        try {
            var leaderboardResponse = leaderboardService.createLeaderboard(leaderboardRequest);

            //Create three users to use
            List<UUID> userIds = new ArrayList<>();
            for (var i = 0; i < 3; i++) {
                userIds.add(UUID.randomUUID());
            }
            for (var i = 0; i < 3; i++) {
                userIds.add(registerNewUser("test user"));
            }

            //create 3 points to rank
            List<PointResponse> pointResponses = new ArrayList<>();
            var pointRequest = new CreatePointRequest(5, userIds.get(0), leaderboardResponse.getLeaderboard().getId());
            pointResponses.add(leaderboardService.createPoint(pointRequest));
            pointRequest.setAmount(10);
            pointRequest.setUserId(userIds.get(1));
            pointResponses.add(leaderboardService.createPoint(pointRequest));
            pointRequest.setAmount(6);
            pointRequest.setUserId(userIds.get(2));
            pointResponses.add(leaderboardService.createPoint(pointRequest));

            var request = new GetEventLeaderboardRequest(leaderboardResponse.getLeaderboard().getId(), 1, 2);
            var response = leaderboardService.getEventLeaderboard(request);

            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("Successfully found points for event", response.getMessage());
            Assertions.assertFalse(response.getLeaderboard().isEmpty());
            Assertions.assertEquals(2, response.getLeaderboard().size());

            //test that each value in the list is in the correct place with the correct value of points and rank
            Assertions.assertEquals(10, response.getLeaderboard().get(0).getPoints());
            Assertions.assertEquals(1, response.getLeaderboard().get(0).getRank());
            Assertions.assertEquals(6, response.getLeaderboard().get(1).getPoints());
            Assertions.assertEquals(2, response.getLeaderboard().get(1).getRank());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void savePointNullTest() {
        try {
            var response = leaderboardService.savePoint(null);

            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals("Point provided is null", response.getMessage());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void savePointNullValuesTest() {
        var point = new Point(null, null, null);

        assertThatThrownBy(() -> leaderboardService.savePoint(point))
                .isInstanceOf(NullRequestParameterException.class);
    }

    @Test
    public void savePointValidTest() {
        var leaderboardRequest = new CreateLeaderboardRequest("testValid");

        try {
            var leaderboardResponse = leaderboardService.createLeaderboard(leaderboardRequest);

            var userId = registerNewUser("Test user");

            var user = new GetUserByIdRequest(userId);
            var point = new Point(1, userService.getUserById(user).getUser(), leaderboardResponse.getLeaderboard());
            var response = leaderboardService.savePoint(point);

            Assertions.assertTrue(response.isSuccess());
            Assertions.assertEquals("Saved point successfully", response.getMessage());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }
}
