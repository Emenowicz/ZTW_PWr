<template>
    <v-card dark color="blue darken-3" class="white--text">
            <v-card-title primary-title>
                <div class="headline">Members</div>  
            </v-card-title>

            <v-container>
                <v-layout row wrap>
                    <v-flex v-for="member in tournament.players" xs12 sm12 md12 lg12>
                        {{member.name}}
                    </v-flex>     
                </v-layout>
            </v-container>
          </v-card>
</template>

<script>
 
 export default {
    name: 'TournamentMembers',
    props: ['tournament']
    
 }

</script>

<style scoped>


</style>