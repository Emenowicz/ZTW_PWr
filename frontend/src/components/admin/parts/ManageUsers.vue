<template>
    <v-card  flat>
      <v-container text-xs-center>
          <v-layout row wrap>
              <v-flex v-for="user in this.users" :key="user.id" xs12 sm12 offset-md2 md8 offset-lg2 lg8>
                  <user :user="user" />
              </v-flex>
          </v-layout>
          <v-layout row wrap>
              <v-flex>
                  <v-pagination v-if="this.users.length !== 0" :length="pagesNumber" v-model="page" @input="onQueryChanged(0)"></v-pagination>
              </v-flex>
          </v-layout>
      </v-container>
    </v-card>
</template>

<script>
import { mapActions } from "vuex";
import User from "./User.vue";

export default {
  name: "ManageUsers",
  data() {
    return {
      users: [],
      page: 1,
      pagesNumber: 1
    };
  },
  mounted() {
    this.GET_USERS().then(response => {
      this.users = response;
    });
  },
  methods: {
    ...mapActions(["GET_USERS"])
  },
  components: {
    User
  }
};
</script>
