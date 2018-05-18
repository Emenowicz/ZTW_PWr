<template>
     <v-card dark color="blue darken-3" class="white--text">
            <v-card-title primary-title>
                <v-layout row wrap>
                    <v-flex xs8 sm4 md4 lg4>
                        <div class="headline">{{tournament.name}}</div>
                    </v-flex>
                    <v-spacer></v-spacer>
                    <v-flex v-if="isOwner" xs4 sm2 md2 lg2>
                        <v-btn outline color="white" @click="onEditClick">Edit</v-btn>
                    </v-flex>
                    <v-flex v-if="(isOwner && !isStarted)" xs4 sm2 md2 lg2>
                        <v-btn outline color="white" @click="onStartClick">Start</v-btn>
                    </v-flex>
                </v-layout>
            </v-card-title>
            
            <v-card-text>
              {{tournament.description}}              
            </v-card-text>

            <v-container>
                <v-layout row wrap>
                    <v-flex xs12 sm3 md3 lg3>
                        Mode: {{tournament.playMode}}
                    </v-flex>
                    <v-flex xs12 sm3 md3 lg3>
                        Type: {{tournament.tournamentType}}
                    </v-flex>
                    <v-flex v-if="tournament.tournamentType==='LOCAL'" xs12 sm3 md3 lg3>
                        Location: {{tournament.location}}
                    </v-flex>
              </v-layout>
                <v-layout row wrap>
                    <v-flex xs12 sm3 md3 lg3>
                        Start date: {{tournament.startTime}}
                    </v-flex>
                    <v-flex xs12 sm3 md3 lg3>
                        End date: {{tournament.endTime}}
                    </v-flex>
                    <v-flex xs12 sm3 md3 lg3>
                        Teams: {{teamsNumber}}
                    </v-flex>
              </v-layout>
            </v-container>
          </v-card>
</template>

<script>
 import {mapActions, mapGetters, mapMutations} from 'vuex';

 export default {
    name: 'TournamentInfo',
    props: ['tournament'],
    methods: {
      onEditClick: function () {
        this.SET_EDITED_TOURNAMENT(this.tournament);
        this.$router.push('/tournaments/edit')
      },
      onStartClick: function() {
          console.log(this.tournament.id)
          this.START_TOURNAMENT(this.tournament.id).then((response) => {
              console.log(response)
              this.SET_DETAILS_TOURNAMENT(response)
          });
      },
      ...mapMutations([
        'SET_EDITED_TOURNAMENT',
        'SET_DETAILS_TOURNAMENT'
      ]),
      ...mapActions([
          "START_TOURNAMENT"
      ])
      },
      computed: {
          isOwner: function() {
              return this.tournament.owner.id === this.userId
          },
          isStarted: function() {
              return this.tournament.started
          },
          ...mapGetters([
              'userId'
          ]),
          teamsNumber: function() {
              return this.tournament.players.length
          }
      }
      
 }

</script>

<style scoped>


</style>