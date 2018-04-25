<template>
  <v-dialog flat v-model="visible" persistent max-width="500px">
    <v-card>
      <v-card-title>
        <v-container grid-list-md>
          <v-layout wrap>
            <v-flex xs12 md12 lg12>
              <span class="headline">Log in to Soccer.io</span>
            </v-flex>
          </v-layout>
        </v-container>
      </v-card-title>
      <v-card-text>
        <v-container grid-list-md>
          <v-layout>
            <v-flex mb-2 xs12 md12 lg12>
              <google-login/>
            </v-flex>
          </v-layout>
        </v-container>
      </v-card-text>
      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn color="blue darken-1" flat @click="closeDialog()">Close</v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>
  import GoogleLogin from './GoogleLogin.vue'

  export default {
    props: {
      visible: Boolean
    },
    methods: {
      closeDialog() {
        this.$emit('close', false)
      }
    },
    components: {
      'google-login': GoogleLogin
    }
  }
</script>
