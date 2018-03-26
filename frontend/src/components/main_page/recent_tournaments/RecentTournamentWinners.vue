<template>
  <v-card color="blue-grey lighten-5" class="black--text">

    <div class="heading pt-3"><b>{{tournament.name}}</b></div>
    <v-card-text>
        <div v-for="(player, i) in tournament.winners" :key="player.name">
          {{i+1 + '. ' + player.name }}
        </div>
    </v-card-text>
  </v-card>
</template>

<script>

export default {
  name: 'RecentTournamentsWinners',
  props: {
    tournament: Object
  }
}
</script>
