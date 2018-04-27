<template>
  <div @click='signIn' class="google-signin-button">
    Continue with Google
  </div>
</template>

<script>
  import {mapActions, mapGetters} from 'vuex'

  export default {
    methods: {
      signIn() {
        this.LOG_IN_GOOGLE().then((response) => {
          this.LOAD_USERS_TOURNAMENTS(this.userId);
          this.LOAD_ALL_TOURNAMENTS();
          this.$router.push('/')
        }, (error) => {
          console.log(error);
        })
      },
      ...mapActions([
        'LOG_IN_GOOGLE',
        'LOAD_USERS_TOURNAMENTS',
        'LOAD_ALL_TOURNAMENTS'
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
