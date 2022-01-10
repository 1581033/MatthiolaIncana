import request from '@/utils/request'
import qs from 'qs'

//测试
export function getInfo(params) {
    return request({
        url: '/test/userLogin',
        method: 'post',
        data: qs.stringify(params)
    });
}

export function getOther(params) {
    return request({
        url: '/test/testOne',
        method: 'get',
        params: params
    });
}
