<template>
    <v-card color="blue lighten-5" class="black--text">
    <v-layout text-center row wrap>

      <v-flex>
        <div class="heading pt-3">
            <b>
                <v-icon v-if="this.match.acceptedBlue" color="green">done</v-icon>
                <v-icon v-else color="red">clear</v-icon>
                <span v-if="this.isPlayerBlueWinner"  class="winner">{{this.match.teamBlue.playerOne.name}} </span>
                <span v-else-if="this.isPlayerRedWinner"  class="loser">{{this.match.teamBlue.playerOne.name}} </span>
                <span v-else>{{this.match.teamBlue.playerOne.name}} </span>
                
                vs. 
                 <span v-if="this.isPlayerRedWinner" class="winner">{{this.match.teamRed.playerOne.name}}</span>
                <span v-else-if="this.isPlayerBlueWinner" class="loser">{{this.match.teamRed.playerOne.name}}</span>
                <span v-else>{{this.match.teamRed.playerOne.name}}</span>
                <v-icon v-if="this.match.acceptedRed" color="green">done</v-icon>
                <v-icon v-else color="red">clear</v-icon>
            </b>
        </div>
      </v-flex>

    <v-spacer></v-spacer>
      <v-flex xs4 sm4 md4 lg4>
        <v-btn v-if="this.isOwner || this.isPlayer" outline color="indigo" @click="onSetScoreClick">Set score</v-btn>
      </v-flex>
    </v-layout>
    <match-modal :visible="modal" :match="match" @close="closeModal"></match-modal>
  </v-card>
</template>

<script>
import MatchDetails from "./MatchDetails.vue";
import { mapGetters, mapActions } from "vuex";

export default {
  props: ["matchProp"],
  methods: {
    onSetScoreClick: function() {
      this.modal = true;
    },
    closeModal: function() {
      console.log("close modal");
      this.modal = false;
      this.refreshMatch()
    },
    refreshMatch: function() {
        this.GET_MATCH(this.match.id).then((response) => {
            this.match = response;
        })
    },
    ...mapActions([
        'GET_MATCH'
    ])
  },
  mounted() {
    console.log("created match view");
    this.$on("closeMatchModal", () => {
      console.log("on");
      this.closeModal();
    });
  },
  data() {
    return {
      match: this.matchProp,
      modal: false
    };
  },
  computed: {
    isOwner() {
      console.log(this.match);
      return this.match.tournament.owner.id === this.userId;
    },
    isPlayer() {
      return (
        this.match.teamBlue.playerOne.id === this.userId ||
        this.match.teamRed.playerOne.id === this.userId
      );
    },
    isPlayerBlueWinner() {
      return (
        this.isFullyAccepted && this.match.scoreBlue >= this.match.scoreRed
      );
    },
    isPlayerRedWinner() {
      return (
        this.isFullyAccepted && this.match.scoreBlue <= this.match.scoreRed
      );
    },
    isFullyAccepted() {
      return this.match.acceptedBlue && this.match.acceptedRed;
    },
    ...mapGetters(["userId"])
  },
  components: {
    "match-modal": MatchDetails
  }
};
</script>

<style scoped>
.winner {
  color: green;
}
.loser {
  color: grey;
}
</style>
