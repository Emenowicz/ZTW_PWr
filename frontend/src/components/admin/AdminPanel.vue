<template>
   <v-card flat>
    <div class="headline text-xs-center pa-5">
        <manage-users v-if="this.visiblePanel === 'users'"></manage-users>
    </div>
    <v-bottom-nav :value="true" :active.sync="visiblePanel" absolute color="transparent">
      <v-btn flat color="teal" value="users">
        <span>Users</span>
        <v-icon>face</v-icon>
      </v-btn>
      <v-btn flat color="teal" value="favorites">
        <span>Tournaments</span>
        <v-icon>brightness_1</v-icon>
      </v-btn>
      <v-btn flat color="teal" value="nearby">
        <span>Nearby</span>
        <v-icon>place</v-icon>
      </v-btn>
    </v-bottom-nav>
  </v-card>
</template>

<script>
import ManageUsers from './parts/ManageUsers.vue'

  export default {
    name: 'AdminPanel',
    data () {
      return {
        visiblePanel: 'recent'
      }
    },
    components: {
        'manageUsers' : ManageUsers
    }
  }
</script>
