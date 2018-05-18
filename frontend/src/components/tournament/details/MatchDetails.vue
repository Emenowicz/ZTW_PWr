<template>

  <v-dialog v-model="visible" persistent max-width="290">
    <v-card>
      <v-card-title class="headline">SET SCORE</v-card-title>
      <v-card-text>
          <v-container>
              <v-layout>
                  <v-flex>
                      {{match.teamBlue.playerOne.name}} vs. {{match.teamRed.playerOne.name}}
                  </v-flex>
              </v-layout>
                  <v-layout>
                  <v-flex>
                      <v-text-field
                      :label="match.teamBlue.playerOne.name"
                      v-model="playerOneScore"/>
                      <v-text-field
                      :label="match.teamRed.playerOne.name"
                      v-model="playerTwoScore"/>
                  </v-flex>
              </v-layout>
          </v-container>

      </v-card-text>
      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn color="green darken-1" flat="flat" @click.native="dialog = false">Approve</v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>
<script>
  export default {
      props: ['visible', 'match'],
      data() {
          return {
              playerTwoScore: 0,
              playerOneScore: 0
          }
      }
  }

</script>
