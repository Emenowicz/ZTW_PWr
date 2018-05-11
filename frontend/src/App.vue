<style>

#app {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    margin-top: 20px;
}

</style>

<template>

<v-app>
    <v-container grid-list-md text-xs-center>
        <v-layout row wrap>
            <v-flex xs12>
                <navbar/>
            </v-flex>
        </v-layout>
        <router-view/>
    </v-container>
</v-app>

</template>

<script>

import Navbar from './components/navbar/Navbar.vue'
import {
    API
}
from './api/api_config'
import {
    mapGetters, mapActions
}
from 'vuex'


export default {
    components: {
        'navbar': Navbar
    },
    computed: {
        ...mapGetters([
            'getToken',
            'getTokenType'
        ])
    },
    methods: {
        ...mapActions([
            'LOG_OUT_GOOGLE'
        ])
    },
    created: function() {
        let self = this;
        if (this.getToken) {
            API.defaults.headers.common['Authorization'] = this.getTokenType + ' ' + this.getToken
        }

        API.interceptors.response.use(function(response) {
            return response;
        }, function(err) {
            return new Promise(function(resolve, reject) {
                if (err.response.status === 401 && err.config && !err.config.__isRetryRequest) {
                    console.log("Logout due to 401 Unauthorized.");
                    self.LOG_OUT_GOOGLE();
                }
                throw err;
            });
        });
    }
}

</script>
