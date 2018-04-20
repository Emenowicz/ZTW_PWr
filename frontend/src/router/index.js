import Vue from 'vue'
import Router from 'vue-router'
import MainPage from '@/components/main_page/MainPage'
import Statistics from '@/components/statistics/Statistics'
import Dashboard from '@/components/dashboard/Dashboard'
import TournamentSearch from '@/components/tournament/TournamentSearch'
import CreateTournament from '@/components/tournament/CreateTournament'
import EditTournament from '@/components/tournament/EditTournament'
import store from '@/store/store'

Vue.use(Router)

const ifNotAuthenticated = (to, from, next) => {
  if (!store.getters.isAuthenticated) {
    next()
    return
  }
  next(false);
}

const ifAuthenticated = (to, from, next) => {
  if (store.getters.isAuthenticated) {
    next()
    return
  }
  next(false)
};

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
      component: TournamentSearch,
    },
    {
      path: '/stats',
      name: 'Statistics',
      component: Statistics,
    },
    {
      path: '/dashboard',
      name: 'Dashboard',
      component: Dashboard,
      beforeEnter: ifAuthenticated
    },
    {
      path: '/tournaments/new',
      name: 'Create new tournament',
      component: CreateTournament,
      beforeEnter: ifAuthenticated
    },
    {
      path: '/tournaments/edit',
      name: 'Edit tournament',
      component: EditTournament,
      beforeEnter: ifAuthenticated
    }
  ]
})
