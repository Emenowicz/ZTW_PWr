<template>
  <v-app>
    <Navbar></Navbar>
    <br/>
    <router-view></router-view>
  </v-app>

</template>

<script>
  import Navbar from "./components/main_components/Navbar.vue"

  export default {
    components: {
      'Navbar': Navbar
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
