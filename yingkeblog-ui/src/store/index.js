import Vue from "vue"
import Vuex from 'vuex'
import user from "@/store/user";
import topic from "@/store/topic";

// 创建并暴露store
Vue.use(Vuex)
export default new Vuex.Store({
	modules: {
		user,
		topic
	},
})
