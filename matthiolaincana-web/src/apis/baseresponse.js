import request from 'utils/request'

const apiRequest = (parameter,api,method) => {
    let Method = 'post'
    if (!method){ method = Method }
    if (method === Method){
        return request({url: api, method: Method, data: parameter})
    }else {
        Method = method
        return request({url: api, method: Method, params: parameter})
    }
}

const baseResponse = {
    apiRequest: (parameter,api,method) => {
        return apiRequest(parameter,api,method)
    },
    inquireHeadList: (parameter,api,method) => {
        const Api = `/${api}/inquire${api.charAt(0).toUpperCase() + api.slice(1)}HeadList`
        return apiRequest(parameter,Api,method)
    },
    inquireList: (parameter,api,method) => {
        const Api = `/${api}/inquire${api.charAt(0).toUpperCase() + api.slice(1)}List`
        return apiRequest(parameter,Api,method)
    },
    increase: (parameter,api,method) => {
        const Api = `/${api}/increase${api.charAt(0).toUpperCase() + api.slice(1)}`
        return apiRequest(parameter,Api,method)
    },
    delete: (parameter,api,method) => {
        const Api = `/${api}/delete${api.charAt(0).toUpperCase() + api.slice(1)}`
        return apiRequest(parameter,Api,method)
    },
    updata: (parameter,api,method) => {
        const Api = `/${api}/updata${api.charAt(0).toUpperCase() + api.slice(1)}`
        return apiRequest(parameter,Api,method)
    },
    dictData: (parameter) => {
        const Api = '/sysDictType/getDictData'
        return apiRequest({ code: parameter },Api,'get')
    }
}

export default baseResponse
