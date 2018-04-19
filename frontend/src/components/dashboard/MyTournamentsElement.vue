<template>
  <v-card color="blue-grey lighten-5" class="black--text">
    <v-container row wrap>
      <v-flex xs8 sm8 md8 lg8>
        <div class="heading pt-3"><b>{{tournament.name}}</b></div>
      </v-flex>
      <v-spacer/>
      <v-flex xs4 sm4 md4 lg4>
        <v-btn outline color="indigo">Edit</v-btn>
      </v-flex>
    </v-container>
  </v-card>
</template>

<script>

  export default {
    name: 'MyTournamentsElement',
    props: {
      tournament: Object
    }
  }
</script>
