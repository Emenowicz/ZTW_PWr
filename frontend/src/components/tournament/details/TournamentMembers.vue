<template>
    <v-card dark color="blue darken-3" class="white--text">
            <v-container>
                <v-layout row wrap>
                   <div class="headline">Members</div>  
                    <v-flex v-for="member in tournament.players" :key="member.id" xs12 sm12 md12 lg12>
                        <h3>{{member.name | upperCase}}</h3>
                    </v-flex>     
                </v-layout>
            </v-container>
          </v-card>
</template>

<script>
 
 export default {
    name: 'TournamentMembers',
    props: ['tournament'],
    filters: {
    upperCase: function (value) {
        if (!value) return ''
        value = value.toString()
        return value.toUpperCase()
        } 
    }
    
 }

</script>

<style scoped>


</style>