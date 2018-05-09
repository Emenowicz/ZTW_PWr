
//TOURNAMENT
export const TOURNAMENT = '/tournaments'
export const TOURNAMENT_QUERY_PAGE = '/tournaments/find?q={0}?sd={1}?ed={2}?page={3}?size={4}' // {0} - tournament query, {1} - startDate, {2} - endDate, {3} - page number, {4} - page size
export const JOIN_TOURNAMENT = 'tournaments/{0}/join' // {0} - tournament_id

//USER
export const USER = '/users'
export const USER_TOURNAMENTS = '/users/{0}/ownedtournaments' // {0} - userId
