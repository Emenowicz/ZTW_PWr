<template>
  <v-container text-xs-center>
      <v-layout row wrap>
        <v-flex md4>
          <recent_tournaments_winners/>
        </v-flex>
        <v-flex md8>
          <last_minute_tournaments/>
        </v-flex>
      </v-layout>
   </v-container>
</template>

<script>
  import LastMinuteTournaments from './last_minute_tournaments/LastMinuteTournaments.vue'
  import RecentTournamentsWinners from './recent_tournaments/RecentTournamentsWinners.vue'

  export default {
    name: 'MainPage',
    components: {
      'last_minute_tournaments': LastMinuteTournaments,
      'recent_tournaments_winners': RecentTournamentsWinners
    }
  }
</script>
