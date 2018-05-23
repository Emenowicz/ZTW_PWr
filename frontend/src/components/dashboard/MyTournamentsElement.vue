<template>
  <v-card color="blue lighten-5" class="black--text">
    <v-layout row wrap>
      <v-flex xs8 sm8 md8 lg8>
        <div class="heading pt-3"><b>{{tournament.name}}</b></div>
      </v-flex>

      <v-flex xs4 sm4 md4 lg4>
        <v-btn outline color="indigo" @click="onDetailsClick">Details</v-btn>
      </v-flex>
    </v-layout>
  </v-card>
</template>

<script>

  import {mapMutations} from 'vuex';

  export default {
    name: 'MyTournamentsElement',
    props: {
      tournament: Object
    },
    methods: {
      onEditClick: function () {
        console.log(this.tournament)
        this.SET_EDITED_TOURNAMENT(this.tournament);
        this.$router.push('/tournaments/edit')
      },
      onDetailsClick: function () {
        console.log(this.tournament)
        this.SET_DETAILS_TOURNAMENT(this.tournament);
        this.$router.push('/tournaments/details')
      },
      ...mapMutations([
        'SET_EDITED_TOURNAMENT',
        'SET_DETAILS_TOURNAMENT'
      ])
    }
  }
</script>

<style scoped>

</style>