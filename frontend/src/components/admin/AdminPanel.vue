<template>
   <v-card flat>
    <div class="headline text-xs-center pa-5">
        <manage-users v-if="this.visiblePanel === 'users'"></manage-users>
        <manage-tournaments v-else-if="this.visiblePanel === 'tournaments'"></manage-tournaments>
    </div>
    <v-bottom-nav :value="true" :active.sync="visiblePanel" absolute color="transparent">
      <v-btn flat color="teal" value="users">
        <span>Users</span>
        <v-icon>face</v-icon>
      </v-btn>
      <v-btn flat color="teal" value="tournaments">
        <span>Tournaments</span>
        <v-icon>brightness_1</v-icon>
      </v-btn>
    </v-bottom-nav>
  </v-card>
</template>

<script>
import ManageUsers from './parts/ManageUsers.vue'
import ManageTournaments from './parts/ManageTournaments.vue'

  export default {
    name: 'AdminPanel',
    data () {
      return {
        visiblePanel: 'recent'
      }
    },
    components: {
        'manageUsers' : ManageUsers,
        'manageTournaments' : ManageTournaments
    }
  }
</script>
