export * from './collectable.service';
import { CollectableService } from './collectable.service';
export * from './event.service';
import { EventService } from './event.service';
export * from './geoCode.service';
import { GeoCodeService } from './geoCode.service';
export * from './leaderboard.service';
import { LeaderboardService } from './leaderboard.service';
export * from './mission.service';
import { MissionService } from './mission.service';
export * from './user.service';
import { UserService } from './user.service';
export const APIS = [CollectableService, EventService, GeoCodeService, LeaderboardService, MissionService, UserService];
