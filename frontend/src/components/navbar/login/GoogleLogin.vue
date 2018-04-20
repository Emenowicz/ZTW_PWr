<template>
  <div @click='signIn' class="google-signin-button">
    Continue with Google
  </div>
</template>

<script>
  import {mapActions, mapMutations, mapGetters} from 'vuex'

  export default {
    methods: {
      signIn() {
        this.GOOGLE_AUTH_REQUEST().then((response) => {
          this.LOAD_USERS_TOURNAMENTS(this.userId);
          console.log(response);
          this.$router.push('/')
        }, (error) => {
          console.log(error);
        })
      },
      ...mapActions([
        'GOOGLE_AUTH_REQUEST',
        'LOAD_USERS_TOURNAMENTS'
      ]),
      ...mapMutations([
        'SET_USERS_TOURNAMENTS'
      ])
    },
    computed: {
      ...mapGetters([
        'userId'
      ])
    }
  }
</script>

<style>
  .google-signin-button {
    display: inline-block;
    padding: 12px 18px;
    border-radius: 3px;
    background-color: #4267b2;
    color: #fff;
  }
</style>
