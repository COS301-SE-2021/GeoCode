package tech.geocodeapp.geocode.leaderboard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tech.geocodeapp.geocode.general.exception.NullRequestParameterException;
import tech.geocodeapp.geocode.leaderboard.request.CreateLeaderboardRequest;
import tech.geocodeapp.geocode.leaderboard.response.CreateLeaderboardResponse;
import tech.geocodeapp.geocode.leaderboard.service.LeaderboardService;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@TestMethodOrder( MethodOrderer.OrderAnnotation.class )
public class LeaderboardServiceImplIT {
    @Autowired
    private LeaderboardService leaderboardService;


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
}
