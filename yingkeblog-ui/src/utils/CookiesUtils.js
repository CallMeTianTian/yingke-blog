export function getCookies(key) {
	// 获取cookie中的信息
	this.userInfo.name = this.$cookies.get(key)
}