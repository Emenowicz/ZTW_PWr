<template>
  <v-expansion-panel>
    <v-expansion-panel-content>
      <div slot="header" class="title">{{user.name}}</div>

      <v-card>
        <v-container grid-list-md>
          <v-layout row wrap>
            <v-spacer/>
            <v-flex md3 lg3 right>
              <v-btn block outline color="indigo" @click="onRemoveUser()">Remove</v-btn>
            </v-flex>
          </v-layout>
        </v-container>
      </v-card>

    </v-expansion-panel-content>
  </v-expansion-panel>

</template>

<script>
  import {mapGetters, mapActions} from 'vuex'

  export default {
    name: 'UserExpansionPanel',
    props: {
      user: Object
    },
    data() {
      return {
      }
    }
}

</script>
