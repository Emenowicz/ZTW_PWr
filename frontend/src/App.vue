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

export default {
  components: {
    'navbar': Navbar
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
