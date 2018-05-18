<template>
    <v-container>
        <v-layout row wrap>
            <v-flex xs12 sm12 md12 lg12>
                <info :tournament="detailsTournament"></info>
            </v-flex>
        </v-layout>
        <v-layout row wrap>
            <v-flex xs12 sm12 md8 lg8>
                <matches :tournamentId="detailsTournament.id"></matches>
            </v-flex>
            <v-flex xs12 sm12 md4 lg4>
                <members :tournament="detailsTournament"></members>
            </v-flex>
        </v-layout>
    </v-container>
    
</template>

<script>
 import {mapActions, mapGetters} from 'vuex';
 import TournamentInfo from './TournamentInfo.vue'
 import TournamentMatches from './TournamentMatches.vue'
 import TournamentMembers from './TournamentMembers.vue'
 import MatchDetails from './MatchDetails.vue'

 export default {
    name: 'TournamentDetails',
    components: {
        'info' : TournamentInfo,
        'members': TournamentMembers,
        'matches': TournamentMatches,
        "match": MatchDetails
    },
    computed: {
        ...mapGetters([
            'detailsTournament'
        ])
    }
 }

</script>

<style scoped>


</style>