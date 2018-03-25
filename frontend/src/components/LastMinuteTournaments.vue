<template>
  <v-card color="blue-grey darken-2" class="white--text">
    <v-card-title primary-title>
      <div class="title">Last minute tournaments</div>
    </v-card-title>
    <v-container grid-list-md text-xs-center>
      <v-layout row wrap v-for="tournament in tournaments" :key="tournament.name">
        <v-flex md12>
          <last-minute-tournament :tournament="tournament"/>
        </v-flex>
        <v-divider/>
      </v-layout>
    </v-container>

  </v-card>
</template>

<script>
import { mapGetters } from 'vuex'
import LastMinuteTournament from './LastMinuteTournament'

export default {
  name: 'LastMinuteTournaments',
  computed: mapGetters({
    tournaments: 'lastMinuteTournaments'
  }),
  components: {
    'last-minute-tournament': LastMinuteTournament
  }
}
</script>
