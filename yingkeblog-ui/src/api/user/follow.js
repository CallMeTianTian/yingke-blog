import request from "@/utils/request";

export function isFollow(blogUserId) {
	return request({
		method: 'GET',
		url: `/follow/or/not/${blogUserId}`,
	})
}

export function follow(blogUserId, isFollow) {
	return request({
		method: 'PUT',
		url: `/follow/${blogUserId}/${isFollow}`,
	})
}

export function getMyLove() {
	return request({
		method: 'GET',
		url: `/follow`,
	})
}

export function getFollowInfoByUid(uid) {
	return request({
		method: 'GET',
		url: `course/follow-user/${uid}`
	})
}