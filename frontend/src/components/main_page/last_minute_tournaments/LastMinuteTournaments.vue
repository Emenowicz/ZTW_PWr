<template>
  <v-card color="blue darken-3" class="white--text">
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
        return [];
      },
      ...mapGetters([
        'userId'
      ])
    },
    components: {
      'tournament': TournamentExpansionPanel
    }
  }
</script>
