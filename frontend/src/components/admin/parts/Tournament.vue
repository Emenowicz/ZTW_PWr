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
            <v-spacer/>
            <v-flex md3 lg3 right>
              <v-btn block outline color="indigo" @click="onEditTournament()">Edit</v-btn>
            </v-flex>
            <v-flex md3 lg3 right>
              <v-btn block outline color="indigo" @click="onRemoveTournament()">Remove</v-btn>
            </v-flex>
          </v-layout>
        </v-container>
      </v-card>

    </v-expansion-panel-content>
  </v-expansion-panel>

</template>

<script>
  import {mapGetters, mapActions, mapMutations} from 'vuex'

  export default {
    name: 'UserExpansionPanel',
    props: {
      tournament: Object
    },
    data() {
      return {
      }
    },
    methods: {
      onEditTournament() {
        console.log(this.tournament)
        this.SET_EDITED_TOURNAMENT(this.tournament);
        this.$router.push('/tournaments/edit')
      },
      onRemoveTournament() {
        console.log("Not implemented yet")
      },
      ...mapMutations([
        'SET_EDITED_TOURNAMENT'
      ])
    }
}

</script>
