<template>
    <v-card dark color="blue darken-3" class="white--text">
            <v-container>
                <v-layout row wrap>
                    <div class="headline">Matches</div>  
                    <v-flex v-for="match in matches" :key="match.id" xs12 sm12 md12 lg12>
                        {{match.name}}
                    </v-flex>     
                </v-layout>
            </v-container>
    </v-card>
</template>

<script>
 import {mapActions} from 'vuex';

 export default {
    name: 'TournamentMatches',
    props: ['tournamentId'],
    data() {
        return {
            matches: []
        }
    },
    mounted() {
        this.GET_TOURNAMENTS_MATCHES(this.tournamentId)
        .then((response) => {
            console.log(response)
        this.matches = response;
      })
    },
    methods: {
        ...mapActions([
            'GET_TOURNAMENTS_MATCHES'
        ])
    }
 }

</script>

<style scoped>


</style>
