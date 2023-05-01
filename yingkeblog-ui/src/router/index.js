//该文件专门用于创建整个应用的路由器
import Vue from "vue";
import VueRouter from "vue-router";
//引入组件
import NProgress from 'nprogress'
import 'nprogress/nprogress.css';

Vue.use(VueRouter)
NProgress.configure({showSpinner: false}) // 关闭进度环

//创建并暴露一个路由器
const router = new VueRouter({
	routes: [
		{
			path: '/',
			redirect: '/login'
		},
		{
			path: '/login',
			name: 'login',
			component: () => import('@/LoginAndRegister/Login.vue'),
			meta: {title: '应科博客 | 登录'}
		},
		{
			name: 'Error_404',
			path: '/error_404',
			component: () => import('@/components/Error/Error_404.vue')
		},
		{
			path: '/register',
			name: 'register',
			component: () => import('@/LoginAndRegister/Register.vue'),
			meta: {title: '应科博客 | 注册'}
		},
		{
			path: '/question-parse',
			name: 'question-parse',
			component: () => import('@/pages/heartfelt-content/training/QuestionParse.vue')
		},
		{
			path: '/study-list', // 跳转路径
			name: 'study-list',   // 名称
			component: () => import('@/pages/heartfelt-content/StudyList.vue'),
			meta: {title: '应科博客 | 学习列表', requireAuth: true},
		},
		{
			path: '/main',
			name: 'main',
			component: () => import('@/components/home/Main.vue'),
			meta: {title: '应科博客 | 首页', requireAuth: true}
		},
		{
			path: '/my-video',
			name: 'my-video',
			component: () => import('@/pages/heartfelt-content/video/BilibiliVideo.vue'),
			meta: {title: '应科博客 | 娱乐'}
		},
		{
			path: '/blog',
			name: 'blog',
			component: () => import('@/pages/blog/discuss.vue'),
			meta: {title: '应科博客 | 博客', requireAuth: true},
			children: [
				{
					path: '/blog',
					redirect: '/blog/user-article',
					meta: {title: '应科博客 | 博客列表', requireAuth: true},
				},
				{
					path: '/blog/user-article',
					name: 'user-article',
					component: () => import('@/pages/blog/UserArticles/UserArticle.vue'),
					meta: {title: '应科博客 | 博客列表', requireAuth: true},
				},
				{
					path: '/blog/write-article',
					name: 'write-article',
					component: () => import('@/pages/blog/WriteArticles/WriteArticle.vue'),
					meta: {title: '应科博客 | 博客书写', requireAuth: true},
				},
				{
					path: '/showcase-the-article',
					name: 'showcase-the-article',
					component: () => import("@/pages/blog/mavon-editor/ShowcaseTheArticle/ShowcaseTheArticle.vue"),
					meta: {title: '应科博客 | 博客详情', requireAuth: true},
				},
				{
					path: '/search-result',
					name: 'SearchResultListShow',
					component: () => import('@/pages/blog/SearchResultListShow/SearchResultListShow.vue'),
					meta: {title: '应科博客 | 博客列表', requireAuth: true},
				}
			]
		},
		{
			path: '/user-space',
			name: 'user-space',
			component: () => import('@/pages/person/space/UserSpace.vue'),
			redirect: '/user-space/space-info',
			meta: {title: '应科博客 | 个人中心', requireAuth: true}, //此时表示进入这个路由是需要登录的
			children: [
				{
					path: "/user-space/space-info",
					name: "space-info",
					component: () => import('@/pages/person/UserInfo/SpaceInfo.vue'),
					meta: {title: '应科博客 | 个人中心', requireAuth: true}
				},
				{
					path: '/user-space/changepwd',
					name: 'changepwd',
					component: () => import('@/pages/person/ChangePassword/ChangePwd.vue'),
					meta: {title: '应科博客 | 个人中心', requireAuth: true}
				},
				//
				{
					path: '/user-space/my-follow',
					name: 'my-follow',
					component: () => import('@/pages/person/UserInfo/MyFollow.vue'),
					meta: {title: '应科博客 | 我的关注', requireAuth: true}
				},
				{
					path: '/user-space/my-collect',
					name: 'my-collect',
					component: () => import('@/pages/person/UserInfo/MyCollect.vue'),
					meta: {title: '应科博客 | 我的收藏', requireAuth: true}
				},
				{
					path: "/user-space/blog-list",
					name: "blog-list",
					component: () => import("@/pages/person/UserInfo/BlogList.vue"),
					meta: {title: '应科博客 | 博客管理', requireAuth: true}
				},
				{
					path: '/user-space/study-chart',
					name: 'study-chart',
					component: () => import('@/pages/DataVisualizationCenter/StudyChart.vue'),
					meta: {title: '应科博客 | 学习数据可视化', requireAuth: true}
				},
				{
					path: '/user-space/blog-chart',
					name: 'blog-chart',
					component: () => import('@/pages/DataVisualizationCenter/BlogChart.vue'),
					meta: {title: '应科博客 | 博客数据可视化', requireAuth: true}
				}
			]
		},
		{
			name: 'data-center',
			path: '/data-center',
			component: () => import('@/pages/DataVisualizationCenter/DataCenter.vue'),
			meta: {title: '应科博客 | 可视化中心', requireAuth: true},
			children: [
				{
					username: 'study-chart',
					path: '/data-center/study-chart',
					component: () => import('@/pages/DataVisualizationCenter/StudyChart.vue')
				},
				{
					username: 'blog-chart',
					path: '/data-center/blog-chart',
					component: () => import('@/pages/DataVisualizationCenter/BlogChart.vue')
				}
			]
		},
		{
			path: "/user",
			name: "user",
			component: () => import('@/pages/person/UserInfo/FollowInfo.vue'),
			meta: {title: '应科博客', requireAuth: true}
		},
	]
})
import {Message} from 'element-ui';
// 暴露之前先添加一个路由守卫
// 全局前置路由守卫 => 在切换之前进行调用|初始化之前调用
// to来自哪里 from去哪里 next()放行
router.beforeEach((to, from, next) => {
	NProgress.start()
	// 判断页面是否为需要登录才能访问的页面
	if (to.meta.requireAuth) {
		//判断token是否存在
		if (localStorage.getItem("TTtoken") !== null) {
			// 存在直接放行
			next();
		} else {
			// 不存在跳转到登录页面
			Message.warning("无权操作 请先登录 !")
			next({path: '/login'});
		}
	} else {
		next();
	}
})

// 后置路由守卫 => 路由切换之后被调用
router.afterEach((to) => {
	NProgress.done()
	document.title = to.meta.title || "应科博客"
})


export default router