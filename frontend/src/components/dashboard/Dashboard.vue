<template>
  <v-container >
    <v-layout row wrap>
      <v-flex xs12 sm12 md6 lg6>
          <v-card dark color="blue darken-3" class="white--text">
            <v-card-title primary-title>
              <div class="title">ORGANIZED BY YOU</div></div>
            </v-card-title>
            <v-container grid-list-md text-xs-center>
              <v-layout row wrap>
                <v-flex class="my-tournament" v-for="tournament in ownedTournaments" :key="tournament.id" offset-xs1 xs10 sm10 md10 lg10>
                  <my-tournament :tournament="tournament"/>
                </v-flex>
              </v-layout>
            </v-container>
          </v-card>
      </v-flex>
      <v-flex xs12 sm12 md6 lg6>
        <v-card dark color="blue darken-3" class="white--text">
          <v-card-title primary-title>
            <div class="title">YOU TAKE PART IN</div>
          </v-card-title>
          <v-container grid-list-md text-xs-center>
            <v-layout row wrap>
              <v-flex class="my-tournament" v-for="tournament in playingTournaments" :key="tournament.id" offset-xs1 xs10 sm10 md10 lg10>
                <my-tournament :tournament="tournament"/>
              </v-flex>
            </v-layout>
          </v-container>
        </v-card>
      </v-flex>
    </v-layout>

  </v-container>
</template>

<script>
  import {mapGetters, mapActions} from 'vuex';
  import MyTournamentsElement from './MyTournamentsElement'

  export default {
    name: 'Dashboard',
    data() {
      return {
        playingTournaments: [],
        ownedTournaments: []
      }
    },
    components: {
      'my-tournament': MyTournamentsElement
    },
    methods: {
    ...mapActions([
      'GET_USERS_PLAYING_TOURNAMENTS',
      'GET_USERS_OWNED_TOURNAMENTS'
    ])
    },
    mounted() {
      this.GET_USERS_PLAYING_TOURNAMENTS()
      .then((response) => {
        this.playingTournaments = response;
      })

      this.GET_USERS_OWNED_TOURNAMENTS()
      .then((response) => {
        this.ownedTournaments = response;
      })
    }
  }
</script>

<style scoped>
.my-tournament {
  margin-bottom: 5px;
}

</style>
