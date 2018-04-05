<template>
  <div @click='signIn' class="google-signin-button">
    Continue with Google
  </div>
</template>

<script>
import Vue from 'vue'

export default {

  methods: {
    signIn() {
      Vue.googleAuth().signIn(this.onSignInSuccess, this.onSignInError)
    },

    onSignInSuccess(authorizationCode) {
      this.$http.post('http://localhost:8088/auth/google',
        {
          authorizationCode
        }
      ).then(function (response) {
        if (response.data) {
          var data = response.data;
          console.log(data);
          this.$router.push({ name: '' })
        }
      }.bind(this), function (error) {
        console.log('BACKEND SERVER REQUEST ERROR', error)
      })
    },
    onSignInError(error) {
      console.log('GOOGLE SERVER REQUEST ERROR', error)
    }
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
