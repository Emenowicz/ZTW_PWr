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
            <v-flex sm12 offset-md8 md4>
              <v-btn v-if="!alreadyRegistered" block outline color="indigo" @click="onSignUpClicked()">Sign up</v-btn>
              <div v-else><v-icon large color="green darken-2">done</v-icon> Already registered</div>
            </v-flex>
          </v-layout>
          <v-snackbar
            :timeout="10000"
            :bottom="true"
            v-model="snackbar">
            {{ snackMessage }}
            <v-btn flat color="pink" @click.native="snackbar = false">Close</v-btn>
          </v-snackbar>
        </v-container>
      </v-card>
    </v-expansion-panel-content>
  </v-expansion-panel>

</template>

<script>
  import {mapGetters, mapActions} from 'vuex'

  export default {
    name: 'LastMinuteTournament',
    props: {
      tournamentId: Object
    },
    data() {
      return {
        snackbar: false,
        snackMessage: ''
      }
    },
    computed: {
      tournament() {
        return this.getTournament(this.tournamentId)
      },
      freeSlots() {
        return this.tournament.maxTeams - this.tournament.teams.length
      },
      alreadyRegistered() {
        return this.tournament.players.map((p) => p.id).includes(this.userId);
      },
      ...mapGetters([
        'userId',
        'getTournament'
      ])
    },
    methods: {
      onSignUpClicked() {
        if (!this.alreadyRegistered) {
          this.JOIN_TOURNAMENT(this.tournament.id)
            .then((response) => {
              this.showSnackMessage('You\'ve successfuly joined tournament')
              this.LOAD_ALL_TOURNAMENTS();
          }).catch((error) => {
            this.showSnackMessage('Something went wrong :(')
          })
        } else {
          this.showSnackMessage('You already take part in this tournament')
        }
      },
      showSnackMessage(message) {
        this.snackMessage = message
        this.snackbar = true;
      },
      ...mapActions([
        'JOIN_TOURNAMENT',
        'LOAD_ALL_TOURNAMENTS'
      ])
    }
  }
</script>
