<template>

  <v-dialog v-model="visible" persistent max-width="500">
    <v-card>
      <v-card-title class="headline">{{match.teamBlue.playerOne.name}} vs. {{match.teamRed.playerOne.name}} - SCORE</v-card-title>
      <v-card-text>
          <v-container v-if="match.acceptedBlue && !match.acceptedRed">
              <v-layout>
                  <v-flex>
                      {{match.teamBlue.playerOne.name}} set score to:
                  </v-flex>
              </v-layout>
                  <v-layout>
                  <v-flex>
                      <v-layout>
                          <v-flex xs12 md6 lg6>
                              <v-text-field
                              :rules="[rules.score]"
                                :label="match.teamBlue.playerOne.name"
                                v-model="playerBlue"/>
                          </v-flex>
                          <v-flex xs12 md6 lg6>
                            <v-text-field
                            :rules="[rules.score]"
                                :label="match.teamRed.playerOne.name"
                                v-model="playerRed"/>
                          </v-flex>
                      </v-layout>
                  </v-flex>
              </v-layout>
          </v-container>
          <v-container v-if="!match.acceptedBlue && match.acceptedRed">
            <v-layout>
                  <v-flex>
                      {{match.teamRed.playerOne.name}} set score to:
                  </v-flex>
              </v-layout>
                  <v-layout>
                  <v-flex>
                      <v-layout>
                          <v-flex xs12 md6 lg6>
                              <v-text-field
                              :rules="[rules.score]"
                                :label="match.teamBlue.playerOne.name"
                                v-model="playerBlue"/>
                          </v-flex>
                          <v-flex xs12 md6 lg6>
                            <v-text-field
                            :rules="[rules.score]"
                                :label="match.teamRed.playerOne.name"
                                v-model="playerRed"/>
                          </v-flex>
                      </v-layout>
                  </v-flex>
              </v-layout>
          </v-container>
          <v-container v-if="!match.acceptedBlue && !match.acceptedRed">
              <v-layout>
                  <v-flex>
                      Score is not set yet.
                  </v-flex>
              </v-layout>
                  <v-layout>
                  <v-flex>
                      <v-layout>
                          <v-flex xs12 md6 lg6>
                              <v-text-field
                                :rules="[rules.score]"
                                :label="match.teamBlue.playerOne.name"
                                v-model="playerBlue"/>
                          </v-flex>
                          <v-flex xs12 md6 lg6>
                            <v-text-field
                                :rules="[rules.score]"
                                :label="match.teamRed.playerOne.name"
                                v-model="playerRed"/>
                          </v-flex>
                      </v-layout>
                  </v-flex>
              </v-layout>
          </v-container>
          <v-container v-if="match.acceptedBlue && match.acceptedRed">
              <v-layout>
                  <v-flex>
                      Score is accepted by both players.
                  </v-flex>
              </v-layout>
                  <v-layout>
                  <v-flex>
                      {{playerBlue}} : {{playerRed}}
                  </v-flex>
              </v-layout>
          </v-container>

      </v-card-text>
      <v-card-actions>
        <v-spacer></v-spacer>
         <v-btn color="green darken-1" flat="flat" @click="close">Close</v-btn>
        <v-btn color="green darken-1" flat="flat" @click="setScore">Approve</v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>
<script>
import { mapActions } from "vuex";
export default {
  props: ["visible", "match"],
  data() {
    return {
      playerBlue: this.match.scoreBlue,
      playerRed: this.match.scoreRed,
      rules: {
        score: value => (value > 0 && value < 4) || "Valid value: 0 - 3"
      }
    };
  },
  methods: {
    setScore: function() {
      if (
        !(this.match.acceptedBlue && this.match.acceptedRed) &&
        this.rules.score(this.playerBlue) &&
        this.rules.score(this.playerRed)
      ) {
        this.SET_MATCH_SCORE({
          matchId: this.match.id,
          scores: { red: this.playerRed, blue: this.playerBlue }
        }).then(response => {
          this.$emit("close");
        });
      }
    },
    close: function() {
      this.$emit("close");
    },
    ...mapActions(["SET_MATCH_SCORE"])
  }
};
</script>
