package tech.geocodeapp.geocode.leaderboard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.geocodeapp.geocode.general.exception.NullRequestParameterException;
import tech.geocodeapp.geocode.leaderboard.model.Leaderboard;
import tech.geocodeapp.geocode.leaderboard.request.CreateLeaderboardRequest;
import tech.geocodeapp.geocode.leaderboard.response.CreateLeaderboardResponse;
import tech.geocodeapp.geocode.leaderboard.service.LeaderboardService;
import tech.geocodeapp.geocode.leaderboard.service.LeaderboardServiceImpl;
import tech.geocodeapp.geocode.user.request.UpdateLocationRequest;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith( MockitoExtension.class )
public class LeaderboardServiceImplTest {
    private LeaderboardService leaderboardService;

    private final String hatfieldEaster = "Hatfield Easter Hunt 2021";
    private final String menloParkChristmas = "Christmas 2021 market";

    @BeforeEach
    void setup() {
        LeaderboardMockRepository leaderboardMockRepo = new LeaderboardMockRepository();

        leaderboardService = new LeaderboardServiceImpl(leaderboardMockRepo, new PointMockRepository(), null, null);

        /* create a Leaderboard so that can test for uniqueness of names */
        Leaderboard leaderboard1 = new Leaderboard(hatfieldEaster);
        leaderboardMockRepo.save(leaderboard1);
    }

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
    public void createLeaderboardTestExistingName(){
        CreateLeaderboardRequest request = new CreateLeaderboardRequest(hatfieldEaster);

        try {
            CreateLeaderboardResponse response = leaderboardService.createLeaderboard(request);

            Assertions.assertFalse(response.isSuccess());
            Assertions.assertEquals("A Leaderboard already exists with that name", response.getMessage());
            Assertions.assertNull(response.getLeaderboard());
        } catch (NullRequestParameterException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createLeaderboardTestNewName(){
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
}
