package tech.geocodeapp.geocode.leaderboard.model;

import java.util.UUID;

/**
 * MyLeaderboardDetails
 */
public interface MyLeaderboardDetails  {
  UUID getEventID();
  String getName();
  int getPoints();
  int getRank();
}
