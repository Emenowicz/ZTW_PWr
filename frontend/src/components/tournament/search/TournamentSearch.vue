<template>
  <v-container>
    <v-layout row wrap>
      <v-flex xs12 sm12 offset-md2 md8 offset-lg2 lg8>
        <v-text-field
          label="Tournament name.."
          v-model="tournamentNameFilter"
          type="text"/>
      </v-flex>
      <v-flex v-for="tournament in filteredTournaments" :key="tournament.id" xs12 sm12 offset-md2 md8 offset-lg2 lg8>
        <tournament :tournament="tournament"/>
      </v-flex>
      <v-flex md12>
        <v-btn v-if="isAuthenticated" :to="'/tournaments/new'" fab fixed bottom right dark color="indigo">
          <v-icon dark>add</v-icon>
        </v-btn>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
  import {mapGetters} from 'vuex'
  import TournamentExpansionPanel from '@/components/tournament/TournamentExpansionPanel.vue'

  export default {
    name: 'TournamentSearch',
    computed: {
      filteredTournaments() {
        return this.tournaments.filter((t) => {
          return t.name.toLowerCase().match(this.tournamentNameFilter.toLowerCase())
        })
      },
      ...mapGetters({
        isAuthenticated : 'isAuthenticated',
        tournaments : 'allTournaments'
    })
    },
    data() {
      return {
        tournamentNameFilter: ''
      }
    },
    components: {
      'tournament' : TournamentExpansionPanel
    }
  }
</script>

<style scoped>


</style>
