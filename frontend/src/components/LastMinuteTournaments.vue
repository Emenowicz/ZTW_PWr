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
import LastMinuteTournament from './LastMinuteTournament'

export default {
  name: 'LastMinuteTournaments',
  data () {
    return {
      tournaments: [
        {
          name: 'Wroclaw XX Table Soccer Cups',
          remainingTime: 6456546,
          type1: 'Local',
          type2: '2 vs. 2',
          locationName: 'Wroclaw',
          freeSlots: 4
        },
        {
          name: 'Poland IV Table Soccer Cups',
          remainingTime: 6456546,
          type1: 'Virtual',
          type2: '1 vs. 1',
          locationName: '',
          freeSlots: 16
        },
        {
          name: 'Kraków V Table Soccer Cups',
          remainingTime: 6456546,
          type1: 'Local',
          type2: '1 vs. 1',
          locationName: 'Kraków',
          freeSlots: 4
        }
      ]
    }
  },
  components: {
    'last-minute-tournament': LastMinuteTournament
  }
}
</script>
