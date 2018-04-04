<template>
  <v-card color="blue-grey darken-2" class="white--text">
    <v-card-title primary-title>
      <div class="title">Recent Tournaments</div>
    </v-card-title>

    <v-container grid-list-md>
      <v-layout row wrap v-for="recent in recentTournaments" :key="recent.name">
        <v-flex xs12 md12 lg12>
          <recent-tournament-winners :tournament="recent"/>
        </v-flex>
        <v-divider/>
      </v-layout>
    </v-container>
  </v-card>
</template>

<script>

import RecentTournamentWinners from './RecentTournamentWinners'

export default {
  name: 'RecentTournamentsWinners',
  data () {
    return {
      recentTournaments: [
        {
          name: 'Wroclaw XX Table Soccer Cups',
          winners: [
            {name: 'Jarek12'},
            {name: 'Grigori96'},
            {name: 'DobrzeGram_PL'}
          ]
        },
        {
          name: 'Virtual Table Soccer Cups',
          winners: [
            {name: 'Dropek'},
            {name: 'Vuevue'},
            {name: 'PL_KAKA'}
          ]
        },
        {
          name: 'Random Cups',
          winners: [
            {name: 'Ketchup'},
            {name: 'XiaomiLepsze'},
            {name: 'DawacDolany'}
          ]
        }
      ]
    }
  },
  components: {
    RecentTournamentWinners,
    'recent-tournament-winners': RecentTournamentWinners
  }
}
</script>
