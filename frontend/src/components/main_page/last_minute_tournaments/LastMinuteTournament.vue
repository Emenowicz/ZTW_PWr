<template>
  <v-expansion-panel>
    <v-expansion-panel-content >
      <div slot="header">{{tournament.name}}</div>

      <v-card>
        <v-container grid-list-md>
          <v-layout row wrap>
            <v-flex sm12 md4 lg4>
              {{'Type: ' + tournament.type1}}
            </v-flex>
            <v-flex sm12 md4 lg4>
              {{'Mode: ' + tournament.type2}}
            </v-flex>
            <v-flex v-if="tournament.type1 === 'Local'" sm12 md4 lg4>
              {{'Location: ' + tournament.locationName}}
            </v-flex>
          </v-layout>
          <v-layout row wrap>
            <v-flex sm12 offset-md10 md2>
              <v-btn block outline color="indigo">Sing up</v-btn>
            </v-flex>
          </v-layout>
        </v-container>
      </v-card>
    </v-expansion-panel-content>
  </v-expansion-panel>

</template>

<script>

export default {
  name: 'LastMinuteTournament',
  props: {
    tournament: Object
  }
}
</script>
