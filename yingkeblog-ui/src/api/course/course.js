import request from "@/utils/request";

export function getCourseWordCountByUid(uid) {
	return request({
		method: 'GET',
		url: `/course/wordCount/${uid}`,
	})
}

export function getAllCourseByUid() {
	return request({
		method: 'GET',
		url: '/course',
	})
}

export function getCourseByUid(uid) {
	return request({
		method: 'GET',
		url: `course/user/${uid}`,
	})
}