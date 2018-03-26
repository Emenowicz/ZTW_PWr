import Vue from 'vue'
import Router from 'vue-router'
import MainPage from '@/components/main_page/MainPage'
import Statistics from '@/components/statistics/Statistics'
import TournamentSearch from '@/components/tournament_search/TournamentSearch'

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
