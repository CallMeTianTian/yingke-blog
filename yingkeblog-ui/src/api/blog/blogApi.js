import request from "@/utils/request";


export function getAllBlog(params) {
	return request({
		method: 'GET',
		params: params,
		url: `/blog/get-all`,
	})
}

export function getBlogByBlogId(blogId) {
	return request({
		method: 'GET',
		url: `/blog/article/${blogId}`
	})
}

export function likeBlogByBlogId(blogId) {
	return request({
		method: 'PUT',
		url: `/blog/like/${blogId}`
	})
}

export function isDidStar(blogId) {
	return request({
		method: 'GET',
		url: `/blog/is-like/${blogId}`
	})
}

export function addBlog(blog) {
	return request({
		method: 'POST',
		url: `/blog/add`,
		data: blog
	})
}

export function updateBlogByLoginUser(blog) {
	return request.put('/blog/update', blog)
}

export function deleteByBlogInLoginUser(blogId) {
	return request({
		method: 'delete',
		url: `/blog/delete/${blogId}`
	})
}

export function getBlogLabelCount() {
	return request({
		method: 'GET',
		url: '/count/label'
	})
}

export function GetBlogByLabel(label){
	return request({
		method: 'GET',
		url: `/blog/get-by-type/${label}`
	})
}

export function SearchByKeyWord(keyWord){
	return request({
		method: 'GET',
		url: `blog/search/${keyWord}`
	})
}