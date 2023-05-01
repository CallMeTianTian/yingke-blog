import request from "@/utils/request";

export function getAllTopic() {
	return request({
		method: 'GET',
		url: `/question/get-all`,
	})
}

export function isAnswer() {
	return request({
		method: 'GET',
		url: `/question/is-answer`,
	})
}

export function addAnswerUser(answerForm) {
	return request.post('question/answer-user', answerForm)
}