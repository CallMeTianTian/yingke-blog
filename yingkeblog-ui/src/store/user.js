export default {
	namespaced: true,
	state: {
		// 存 用户id 用户名称 用户邮箱 用户头像地址
		userInfo: {},
	},
	mutations: {
		STORAGE_USERINFO(state, userData) {
			state.userInfo = userData
		}
	},
	actions: {},
	getters: {}
}