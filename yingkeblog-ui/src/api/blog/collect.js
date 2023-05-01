import request from "@/utils/request";

export function addCollect(blogId) {
	return request.post(`/user/collect`, {blogId})
}