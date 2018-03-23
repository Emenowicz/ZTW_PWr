import Vue from 'vue'
import Router from 'vue-router'
import MainPage from '@/components/main_components/MainPage'
import Statistics from '@/components/main_components/Statistics'
import TournamentSearch from '@/components/main_components/TournamentSearch'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'MainPage',
      component: MainPage
    },
    {
      path: '/tournaments',
      name: 'TournamentSearch',
      component: TournamentSearch
    },
    {
      path: '/stats',
      name: 'Statistics',
      component: Statistics
    }
  ]
})
