import request from "@/utils/request";

export function getMyCollects() {
	return request({
		method: 'GET',
		url: `/user/collect`,
	})
}