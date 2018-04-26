<template>
  <v-container>
    <v-layout row wrap>
      <v-flex xs12 sm12 md8 lg8>

      </v-flex>
      <v-flex xs12 sm12 md4 lg4>
        <v-card dark color="blue-grey darken-2" class="white--text">
          <v-card-title primary-title>
            <div class="title">MY TOURNAMENTS</div>
          </v-card-title>
          <v-container grid-list-md text-xs-center>
            <v-layout row wrap>
              <v-flex v-for="tournament in usersTournaments" :key="tournament.id" offset-xs1 xs10 sm10 md10 lg10>
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
    computed: {
      ...mapGetters([
        'usersTournaments'
      ])
    },
    components: {
      'my-tournament': MyTournamentsElement
    },
    methods: {
    ...mapActions([
      'LOAD_USERS_TOURNAMENTS'
    ])
    },
    mounted() {
      this.LOAD_USERS_TOURNAMENTS();
    }
  }
</script>
