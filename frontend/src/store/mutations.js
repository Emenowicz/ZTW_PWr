

const mutations = {
  'SET_USER_INFO': (state, user_info) => {
    state.user_info.firstname = user_info.ofa;
    state.user_info.lastname = user_info.wea;
    state.user_info.email = user_info.U3;
    state.user_info.picture_url = user_info.Paa;
  },
  'CLEAR_USER_INFO': (state) => {
    Object.keys(state.user_info).forEach(k => state.user_info[k] = '');
  }
};

export default mutations
