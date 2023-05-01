import axios from 'axios'
import NProgress from 'nprogress';
import 'nprogress/nprogress.css';
import {Message} from 'element-ui'

const instance = axios.create({
	baseURL: 'http://localhost',
	timeout: 4000,
})


instance.interceptors.request.use((config) => {
	NProgress.start()
	const token = localStorage.getItem("TTtoken")
	token ? config.headers["TTtoken"] = token : null;
	
	return config;
}, function (error) {
	Message.error("请求错误")
	return Promise.reject(error);
});


instance.interceptors.response.use((response) => {
	NProgress.done();
	
	return response;
}, function (error) {
	Message.error("响应错误")
	return Promise.reject(error);
});


export default instance
