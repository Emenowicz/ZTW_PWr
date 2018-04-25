<template>
  <v-app>
    <v-container grid-list-md text-xs-center>
      <v-layout row wrap>
        <v-flex xs12>
          <navbar/>
        </v-flex>
      </v-layout>
      <router-view/>
    </v-container>
  </v-app>

</template>

<script>
  import Navbar from './components/navbar/Navbar.vue'
  import {API} from './api/api_config'

  export default {
    components: {
      'navbar': Navbar
    },
    created: function () {

      API.interceptors.response.use(undefined, function (err) {
        return new Promise(function (resolve, reject) {
          if (err.status === 401 && err.config && !err.config.__isRetryRequest) {
            this.$store.dispatch('AUTH_LOGOUT')
          }
          throw err;
        });
      });
    }
  }
</script>

<style>
  #app {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    margin-top: 20px;
  }
</style>
