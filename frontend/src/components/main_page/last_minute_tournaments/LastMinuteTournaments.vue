<template>
  <v-card color="blue-grey darken-2" class="white--text">
    <v-card-title primary-title>
      <div class="title">Last minute tournaments</div>
    </v-card-title>
    <v-container grid-list-md text-xs-center>
      <v-layout row wrap v-for="tournament in tournaments" :key="tournament.name">
        <v-flex xs12 md12 lg12>
          <tournament :tournamentId="tournament.id" :showRemainingTime="true"/>
        </v-flex>
        <v-divider/>
      </v-layout>
    </v-container>

  </v-card>
</template>

<script>
  import {mapGetters} from 'vuex'
  import TournamentExpansionPanel from '@/components/tournament/TournamentExpansionPanel.vue'

  export default {
    name: 'LastMinuteTournaments',
    computed: {
      tournaments() {
        return this.allTournaments.filter(t => {
          var difference = new Date(t.startTime) - Date.now();
          return ((difference < (3 * 24 * 60 * 60 * 1000)) && (difference >= 0) && !(t.players.map((p) => p.id).includes(this.userId)))
        })
      },
      ...mapGetters([
        'allTournaments',
        'userId'
      ])
    },
    components: {
      'tournament': TournamentExpansionPanel
    }
  }
</script>
