// 引入Vue
import Vue from 'vue'
// 引入App组件 它是所有组件的父组件
import App from './App.vue'
import router from "@/router";
import VueRouter from 'vue-router'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import VueCoreVideoPlayer from 'vue-core-video-player'
import VueCookies from "vue-cookies";
import './plugins/hljs'
import store from "@/store/index";
import * as echarts from 'echarts'


Vue.use(VueCoreVideoPlayer)
Vue.use(VueRouter)
Vue.use(ElementUI)
Vue.use(VueCookies)
// 关闭Vue的生产提示
Vue.config.productionTip = false
//需要挂载到Vue原型上
Vue.prototype.$echarts = echarts


// 创建Vue实例对象 vm
new Vue({
	// 引入路由
	router,
	store,
	render: h => h(App),
	beforeCreate(){
		Vue.prototype.$bus = this	//安装全局事件总线
	}
}).$mount('#app')
