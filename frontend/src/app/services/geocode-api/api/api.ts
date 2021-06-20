export * from './collectable.service';
import { CollectableService } from './collectable.service';
export * from './geoCode.service';
import { GeoCodeService } from './geoCode.service';
export * from './user.service';
import { UserService } from './user.service';
export const APIS = [CollectableService, GeoCodeService, UserService];
