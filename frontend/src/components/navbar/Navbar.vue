<template>

  <v-toolbar dark>
    <router-link tag="v-toolbar-title" to="/">
      <v-avatar size="36px" v-if="!(userInfo.picture_url==='')">
        <img :src="userInfo.picture_url" :alt="userInfo.firstname + ' ' + userInfo.lastname"/>
      </v-avatar>
      <v-btn flat class="headline">Soccer.io</v-btn>
    </router-link>
    <v-spacer/>
    <v-toolbar-items>
      <v-btn flat v-if="!route.authentication || isAuthenticated" v-for="route in routes" :key="route.id"
             :to="`${route.path}`">{{route.name}}
      </v-btn>
      <v-btn v-if="!isAuthenticated" flat @click="loginDialog=true">Log in</v-btn>
      <v-btn v-if="isAuthenticated" flat @click="logout">Logout</v-btn>
      <login :visible="loginDialog && !isAuthenticated" @close="hideLogin"/>
    </v-toolbar-items>
  </v-toolbar>

</template>

<script>
  import {mapActions, mapGetters, mapState} from 'vuex'
  import Login from './login/Login.vue'

  export default {
    name: 'Navbar',
    components: {
      'login': Login
    },
    data() {
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
        this.LOG_OUT_GOOGLE().then(() => {
          this.$router.push('/')
        });

      },
      ...mapActions([
        'LOG_OUT_GOOGLE'
      ])
    },
    computed: {
      ...mapGetters([
        'isAuthenticated',
        'userInfo'
      ])
    }
  }

</script>

<css scoped>

</css>
