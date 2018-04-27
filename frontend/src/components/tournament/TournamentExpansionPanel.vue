<template>
  <v-expansion-panel>
    <v-expansion-panel-content>
      <div slot="header" class="title">{{tournament.name}}</div>

      <v-card>
        <v-container grid-list-md>
          <v-layout row wrap>
            <v-flex sm12 md12 lg12>
              {{tournament.description}}
            </v-flex>
            <v-flex sm12 md4 lg4>
              {{'Type: ' + tournament.tournamentType}}
            </v-flex>
            <v-flex sm12 md4 lg4>
              <div v-if="tournament.tournamentType === 'LOCAL'">{{'Location: ' + tournament.location}}</div>
            </v-flex>
            <v-flex sm12 md4 lg4>
              {{'Free slots: ' + freeSlots}}
            </v-flex>
          </v-layout>
          <v-layout row wrap>
            <v-flex sm12 md4 lg4>
              {{'Start date: ' + tournament.startTime}}
            </v-flex>
            <v-flex sm12 md4 lg4>
              {{'End date: ' + tournament.endTime}}
            </v-flex>
          </v-layout>
          <v-layout row wrap>
            <v-flex sm12 offset-md10 md2>
              <v-btn block outline color="indigo">Sign up</v-btn>
            </v-flex>
          </v-layout>
        </v-container>
      </v-card>
    </v-expansion-panel-content>
  </v-expansion-panel>

</template>

<script>
  export default {
    name: 'LastMinuteTournament',
    props: {
      tournament: Object
    },
    computed: {
      freeSlots() {
        return this.tournament.maxTeams - this.tournament.teams.length
      }
    }
  }
</script>
