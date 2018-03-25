<template>

  <v-toolbar dark>
    <router-link tag="v-toolbar-title" to="/">
      <div class="headline">Soccer.io</div>
    </router-link>
    <v-spacer/>
    <v-toolbar-items>
      <v-btn flat v-for="route in routes" :key="route.id" :to="`${route.path}`">{{route.name}}</v-btn>
      <v-btn flat @click="loginDialog=true">Log in</v-btn>
      <login :visible="loginDialog" @close="hideLogin"/>
    </v-toolbar-items>
  </v-toolbar>

</template>

<script>
import Login from '../Login.vue'

export default {
  name: 'Navbar',
  components: {
    'login': Login
  },
  data () {
    return {
      loginDialog: false,
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
  },
  methods: {
    hideLogin() {
      this.loginDialog = false;
    }
  }
}

</script>

<css scoped>

</css>
