
// TOURNAMENT
export const TOURNAMENT = '/tournaments/{0}'
export const TOURNAMENTS = '/tournaments'
export const TOURNAMENT_START = '/tournaments/{0}/start'
export const TOURNAMENTS_MATCHES = '/tournaments/{0}/matches'
export const TOURNAMENT_QUERY_PAGE = '/tournaments/find?' // q={0}?sd={1}?ed={2}?page={3}?size={4} {0} - tournament query, {1} - startDate, {2} - endDate, {3} - page number, {4} - page size
export const JOIN_TOURNAMENT = 'tournaments/{0}/join' // {0} - tournament_id

// MATCH
export const MATCH_SET_SCORE = '/matches/{0}/score'
export const MATCH = '/matches/{0}'


// USER
export const USER = '/users'
export const USER_OWNED_TOURNAMENTS = '/tournaments?owner={0}' // {0} - userId
export const USER_PLAYING_TOURNAMENTS = '/tournaments?player={0}' // {0} - userId
