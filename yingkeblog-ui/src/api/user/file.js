import request from '@/utils/request'

export function uploadImg(data) {
	return request.post('/common/upload', data)
}

export function deleteImg(fileName) {
	return request.delete(`/common/delete/${fileName}`)
}