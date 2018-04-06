<template>

  <v-toolbar dark>
    <router-link tag="v-toolbar-title" to="/">
      <v-btn flat class="headline">Soccer.io</v-btn>
    </router-link>
    <v-spacer/>
    <v-toolbar-items>
      <v-btn flat v-if="!route.authentication || isAuthenticated" v-for="route in routes" :key="route.id" :to="`${route.path}`">{{route.name}}</v-btn>
      <v-btn v-if="!isAuthenticated" flat @click="loginDialog=true">Log in</v-btn>
      <v-btn v-if="isAuthenticated" flat @click="logout">Logout</v-btn>
      <login :visible="loginDialog && !isAuthenticated" @close="hideLogin"/>
    </v-toolbar-items>
  </v-toolbar>

</template>

<script>
  import {mapActions, mapGetters} from 'vuex'
  import {AUTH_LOGOUT} from '@/store/actions/auth'
  import Login from './login/Login.vue'

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
            path: '/tournaments',
            authentication: false
          },
          {
            id: 1,
            name: 'Statistics',
            path: '/stats',
            authentication: false
          },
          {
            id: 2,
            name: 'Dashboard',
            path: '/dashboard',
            authentication: true
          }
        ]
      }
    },
    methods: {
      hideLogin() {
        this.loginDialog = false;
      },
      logout() {
        this.AUTH_LOGOUT().then(() => {
          this.$router.push('/')
        });

      },
      ...mapActions([
        AUTH_LOGOUT
      ])
    },
    computed: {
      ...mapGetters([
        'isAuthenticated'
      ])
    }
  }

</script>

<css scoped>

</css>
