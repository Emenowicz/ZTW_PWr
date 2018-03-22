<template>
  <v-toolbar dark>
    <v-toolbar-title>Soccer.io</v-toolbar-title>
    <v-spacer></v-spacer>
    <v-toolbar-items>
      <v-btn flat v-for="route in routes" :key="route.id" :to="`${route.path}`">{{route.name}}</v-btn>
    </v-toolbar-items>
  </v-toolbar>

</template>

<script>
  export default {
    name: 'Navbar',
    data() {
      return {
        routes: [
          {
            id: 0,
            name: 'Tournaments',
            path: '/tournaments'
          },
          {
            id: 1,
            name: 'Statistics',
            path: '/stats'
          }
        ]
      }
    }
  }

</script>

<css scoped>


</css>
