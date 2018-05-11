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
          this.$router.push('/')
        }, (error) => {
          console.log("Problem with login.\n" + error);
        })
      },
      ...mapActions([
        'LOG_IN_GOOGLE',
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
