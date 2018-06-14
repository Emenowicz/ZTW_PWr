<template>
    <v-card  flat>
      <v-container text-xs-center>
          <v-layout row wrap>
              <v-flex v-for="tournament in this.tournaments" :key="tournament.id" xs12 sm12 offset-md2 md8 offset-lg2 lg8>
                  <tournament :tournament="tournament" />
              </v-flex>
          </v-layout>
          <v-layout row wrap>
              <v-flex>
                  <v-pagination v-if="this.tournaments.length !== 0" :length="pagesNumber" v-model="page" @input="onQueryChanged(0)"></v-pagination>
              </v-flex>
          </v-layout>
      </v-container>
    </v-card>
</template>

<script>
import { mapActions } from "vuex";
import Tournament from "./Tournament.vue";

export default {
  name: "ManageTournaments",
  data() {
    return {
      tournaments: [],
      page: 1,
      pagesNumber: 1
    };
  },
  methods: {
    fetchTournamentsPage() {
      this.QUERY_TOURNAMENTS_PAGE({
        query: "",
        startDate: "",
        endDate: "",
        page: this.page - 1,
        size: 10
      })
        .then(page => {
          this.tournaments = page.content;
          this.page = page.number + 1;
          this.pagesNumber = page.totalPages;
        })
        .catch(error => {
          console.log(
            "Problem with fetching tournaments page occured.\n" + error
          );
        });
    },
    onQueryChanged(timeout) {
      this.fetchTournamentsPage()
    },
    ...mapActions(["QUERY_TOURNAMENTS_PAGE"])
  },
  mounted() {
    this.fetchTournamentsPage();
  },
  components: {
    Tournament
  }
};
</script>
