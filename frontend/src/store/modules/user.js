const state = {
    user_info: {
          id: "",
          firstname: "",
          lastname: "",
          email: "",
          picture_url: ""
        }
};

const actions = {

}

const mutations = {
  'SET_USER_INFO': (state, user_info) => {
    state.user_info.id = user_info.Eea;
    state.user_info.firstname = user_info.ofa;
    state.user_info.lastname = user_info.wea;
    state.user_info.email = user_info.U3;
    state.user_info.picture_url = user_info.Paa;
  },
  'CLEAR_USER_INFO': (state) => {
    Object.keys(state.user_info).forEach(k => state.user_info[k] = '');
  }
};

const getters = {
  userId: state => state.user_info.id,
  userInfo: state => state.user_info
}

export default {
    state,
    getters,
    actions,
    mutations
}
