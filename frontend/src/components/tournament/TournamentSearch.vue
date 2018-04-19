<template>
  <v-container>
    <v-layout row wrap>
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

  export default {
    name: 'TournamentSearch',
    computed: {
      ...mapGetters([
        'isAuthenticated'
      ])
    }
  }
</script>

<style scoped>


</style>
