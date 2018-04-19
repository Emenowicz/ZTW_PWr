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
              <v-flex offset-xs1 xs10 sm10 md10 lg10>

              </v-flex>
            </v-layout>
          </v-container>
        </v-card>
      </v-flex>
    </v-layout>

  </v-container>
</template>

<script>
  import {mapActions, mapGetters} from 'vuex';

  export default {
    name: 'Dashboard',
    data() {
      return {
        usersTournaments: []
      }
    },
    computed:{
    ...mapGetters([
      'userId'
    ])
    },
    methods: {
      ...mapActions([
        'GET_USERS_TOURNAMENTS'
      ])
    },
    mounted() {
      this.GET_USERS_TOURNAMENTS(userId)
        .then(function (response) {
          console.log(response);
        }, function (error) {
          console.log(error);
        })
    }
  }
</script>
