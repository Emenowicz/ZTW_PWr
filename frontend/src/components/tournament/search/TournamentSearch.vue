

<template>

<v-container>
    <v-layout row wrap>
        <v-flex xs12 sm12 offset-md2 md8 offset-lg2 lg8>
            <v-text-field label="Keywords.. (i.e. 'XX Championship Virtual', or 'Warsaw money prize award')" v-model="tournamentQuery" @keyup="onQueryChanged" type="text" />
        </v-flex>
        <v-flex xs12 sm12 offset-md2 md4 offset-lg2 lg4>
            <date-picker :valid='true' :startDate="''" :endDate="(endDate === '') ? '' : endDate" :label="'Starts after..'" @selectedDate="onStartDateSelected" :date="this.startDate" />
        </v-flex>
        <v-flex xs12 sm12 md4 lg4>
            <date-picker :valid='true' :startDate="(startDate === '') ? '' : startDate" :endDate="''" :label="'Starts before..'" @selectedDate="onEndDateSelected" :date="this.endDate" />
        </v-flex>
        </v-flex>
        <v-flex v-for="tournament in this.tournaments" :key="tournament.id" xs12 sm12 offset-md2 md8 offset-lg2 lg8>
            <tournament :tournament="tournament" />
        </v-flex>
        <v-flex md12>
            <v-btn v-if="isAuthenticated" :to="'/tournaments/new'" fab fixed bottom right dark color="indigo">
                <v-icon dark>add</v-icon>
            </v-btn>
        </v-flex>
    </v-layout>
    <v-layout row wrap>
        <v-flex>
            <v-pagination :length="pagesNumber" v-model="page"></v-pagination>
        </v-flex>
    </v-layout row wrap>
</v-container>

</template>

<script>

import {
    mapGetters, mapActions
}
from 'vuex'
import TournamentExpansionPanel from '@/components/tournament/TournamentExpansionPanel.vue'
import DatePicker from '@/components/tools/DatePicker'

export default {
    name: 'TournamentSearch',
    computed: {
        ...mapGetters({
            isAuthenticated: 'isAuthenticated',
        })
    },
    data() {
        return {
            tournaments: [],
            tournamentQuery: '',
            startDate: '',
            endDate: '',
            page: 1,
            pagesNumber: 1,
            refreshTimer: ''
        }
    },
    mounted() {
        this.fetchTournamentsPage();
    },
    methods: {
        fetchTournamentsPage() {
                this.QUERY_TOURNAMENTS_PAGE({
                    query: this.tournamentQuery,
                    startDate: this.startDate,
                    endDate: this.endDate,
                    page: this.page,
                    size: this.size
                }).then((page) => {
                  console.log('fetchTournamentsPage');
                  this.tournaments = page.content;
                  this.page = page.number;
                  this.pagesNumber = page.totalPages;
                });
            },
        onQueryChanged() {
                clearTimeout(this.refreshTimer);
                this.refreshTimer = setTimeout(this.fetchTournamentsPage, 1000);
            },
        ...mapActions([
                'QUERY_TOURNAMENTS_PAGE'
            ]),
        onStartDateSelected(date) {
              this.startDate = date;
              this.onQueryChanged();
            },
        onEndDateSelected(date) {
              this.endDate = date;
              this.onQueryChanged();
            },
    },
    components: {
        'tournament': TournamentExpansionPanel,
        'date-picker': DatePicker
    }
}

</script>
