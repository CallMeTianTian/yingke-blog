import request from "@/utils/request";


export function isLogin() {
	return request({
		method: 'GET',
		url: '/user/is-login',
	})
}

export function loginOut() {
	return request({
		method: 'POST',
		url: '/user/logout',
	})
}

export function getUserDTO() {
	return request({
		method: 'GET',
		url: '/users/dto',
	})
}

export function registerSendCode(email) {
	return request({
		method: 'POST',
		url: `/register/simple/${email}`,
		timeout: 18000
	})
}

export function isRegisterCode(email, verifyCode) {
	return request({
		method: 'POST',
		url: `/register/verification/${email}/${verifyCode}`,
	})
}

export function register(form){
	return request.post('/register', form)
}


export function changePassword(password) {
	return request.put(`/users/update/pwd?password=${password}`)
}

export function getBaseUserInfoByUid(uid) {
	return request({
		method: 'GET',
		url: `/users/base/${uid}`,
	})
}

export function getUserByUid(uid) {
	return request({
		method: 'GET',
		url: `/users/${uid}`,
	})
}


export function UpdateUserInfoByUid(baseUserInfo) {
	return request.put('/users/base/update', baseUserInfo)
}


export function userChangePWDByEmail(email) {
	return request({
		method: 'POST',
		url: `/users/simple/${email}`,
		timeout: 18000
	})
}


export function isCode(verifyCode) {
	return request({
		method: 'POST',
		url: `/users/verification/${verifyCode}`,
	})
}

export function getMyBlogByLoginUser() {
	return request({
		method: 'GET',
		url: `/blog/me`,
	})
}