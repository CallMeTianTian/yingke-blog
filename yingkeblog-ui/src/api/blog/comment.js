import request from "@/utils/request";

export function getCommentsByBlogId(blogId) {
	return request({
		method: 'GET',
		url: `/blog/comments/${blogId}`
	})
}

export function addComment(data) {
	return request.post('/blog/comments', data)
}

export function isComment(blogId) {
	return request({
		method: 'GET',
		url: `/user/collect/or/not/${blogId}`
	})
}
