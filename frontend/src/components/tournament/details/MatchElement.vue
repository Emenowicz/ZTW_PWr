<template>
    <v-card color="blue lighten-5" class="black--text">
    <v-layout row wrap>
      <v-flex xs8 sm8 md8 lg8>
        <div class="heading pt-3"><b>{{match.teamBlue.playerOne.name}} vs. {{match.teamRed.playerOne.name}}</b></div>
      </v-flex>

      <v-flex xs4 sm4 md4 lg4>
        <v-btn outline color="indigo" @click="onSetScoreClick">Set score</v-btn>
      </v-flex>
    </v-layout>
    <match-modal :visible="modal" :match="match"></match-modal>
  </v-card>
</template>

<script>
 import MatchDetails from './MatchDetails.vue'

export default {
    props: ['match'],
    methods: {
        onSetScoreClick: function() {
            this.modal = true;
        }
    },
    data() {
        return {
            modal: false
        }
    },
    components: {
        "match-modal": MatchDetails
    }
}
</script>
