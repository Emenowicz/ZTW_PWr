<template>
    <v-card dark color="blue darken-3" class="white--text">
            <v-card-title primary-title>
                <div class="headline">Matches</div>  
            </v-card-title>

            <v-container>
                <v-layout row wrap>
                    <v-flex v-for="match in tournament.matches" xs12 sm12 md12 lg12>
                        {{match.name}}
                    </v-flex>     
                </v-layout>
            </v-container>
    </v-card>
</template>

<script>
 
 export default {
    name: 'TournamentMatches',
    props: ['tournament']
 }

</script>

<style scoped>


</style>
