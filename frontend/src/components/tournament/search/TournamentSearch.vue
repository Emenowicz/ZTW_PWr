<style scoped>

.fab-container {
    position: fixed;
    bottom: 0;
    right: 0;
}

</style>

<template>

<v-container>
    <v-layout row wrap>
        <v-flex v-for="tournament in this.tournaments" :key="tournament.id" xs12 sm12 offset-md2 md8 offset-lg2 lg8>
            <tournament :tournament="tournament" />
        </v-flex>
    </v-layout>
    <v-layout row wrap>
        <v-flex>
            <v-pagination :length="pagesNumber" v-model="page" @input="onQueryChanged(0)"></v-pagination>
        </v-flex>
    </v-layout>

    <v-layout row class="fab-container">
        <v-btn fab dark color="blue" @click="onFilterButtonClicked">
            <v-icon dark>filter</v-icon>
        </v-btn>
        <v-btn v-if="isAuthenticated" :to="'/tournaments/new'" fab dark color="indigo">
            <v-icon dark>add</v-icon>
        </v-btn>
    </v-layout>

    <v-bottom-sheet inset :hide-overlay='true' :value='this.showFilters' :persistent="true">
        <v-layout row wrap>
            <v-flex xs12 sm12 offset-md1 md10 offset-lg1 lg10>
                <v-text-field label="Keywords.. (i.e. 'XX Championship Virtual', or 'Warsaw money prize award')" v-model="tournamentQuery" @keyup="onQueryChanged(1000)" type="text" />
            </v-flex>
        </v-layout>

        <v-layout row wrap>
            <v-flex xs12 sm12 offset-md2 md2 offset-lg2 lg2>
                <date-picker :valid='true' :startDate="''" :endDate="(endDate === '') ? '' : endDate" :label="'Starts after..'" @selectedDate="onStartDateSelected" :date="this.startDate" />
            </v-flex>
            <v-flex xs12 sm12 md2 lg2>
                <date-picker :valid='true' :startDate="(startDate === '') ? '' : startDate" :endDate="''" :label="'Starts before..'" @selectedDate="onEndDateSelected" :date="this.endDate" />
            </v-flex>
            <v-spacer></v-spacer>
            <v-flex xs12 sm12 md1 lg1>
                <v-select label="On page" v-model="itemsOnPageInput" @change="onQueryChanged(0)" :items="itemsOnPageOptions"></v-select>
            </v-flex>
            <v-flex md2 lg2></v-flex>
        </v-layout row wrap>
    </v-bottom-sheet>

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
            refreshTimer: '',
            itemsOnPageInput: 10,
            itemsOnPageOptions: [
                5, 10, 15, 20
            ],
            showFilters: false
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
                    page: this.page - 1,
                    size: this.itemsOnPageInput
                }).then((page) => {
                    this.tournaments = page.content;
                    this.page = page.number + 1;
                    this.pagesNumber = page.totalPages;
                });
            },
            onQueryChanged(timeout) {
                clearTimeout(this.refreshTimer);
                this.refreshTimer = setTimeout(this.fetchTournamentsPage, timeout);
            },
            ...mapActions([
                'QUERY_TOURNAMENTS_PAGE'
            ]),
            onStartDateSelected(date) {
                this.startDate = date;
                this.onQueryChanged(0);
            },
            onEndDateSelected(date) {
                this.endDate = date;
                this.onQueryChanged(0);
            },
            onFilterButtonClicked() {
                this.showFilters = !this.showFilters;
            }
    },
    components: {
        'tournament': TournamentExpansionPanel,
        'date-picker': DatePicker
    }
}

</script>
