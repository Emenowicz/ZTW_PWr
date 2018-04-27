<template>
  <v-card color="blue-grey lighten-5" class="black--text">
    <v-layout row wrap>
      <v-flex xs8 sm8 md8 lg8>
        <div class="heading pt-3"><b>{{tournament.name}}</b></div>
      </v-flex>

      <v-flex xs4 sm4 md4 lg4>
        <v-btn outline color="indigo" @click="onEditClick">Edit</v-btn>
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
        this.SET_EDITED_TOURNAMENT(this.tournament);
        this.$router.push('/tournaments/edit')
      },
      ...mapMutations([
        'SET_EDITED_TOURNAMENT'
      ])
    }
  }
</script>
