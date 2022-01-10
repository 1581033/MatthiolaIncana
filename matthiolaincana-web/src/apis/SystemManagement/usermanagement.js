import request from 'utils/request'

const base = '/sysUser'

const api = {
    inquireSysUserList: '/inquireSysUserList',
    inquireSysUserHeadList: '/inquireSysUserHeadList',
    increaseSysUser: '/increaseSysUser',
    deleteSysUser: '/deleteSysUser',
    updataSysUser: '/updataSysUser'
}

export function inquireSysUserList (parameter) {
    return request({
        url: base + api.inquireSysUserList,
        method: 'post',
        data: parameter
    })
}

export function inquireSysUserHeadList (parameter) {
    return request({
        url: base + api.inquireSysUserHeadList,
        method: 'post',
        data: parameter
    })
}

export function increaseSysUser (parameter) {
    return request({
        url: base + api.increaseSysUser,
        method: 'post',
        data: parameter
    })
}

export function deleteSysUser (parameter) {
    return request({
        url: base + api.deleteSysUser,
        method: 'post',
        data: parameter
    })
}

export function updataSysUser (parameter) {
    return request({
        url: base + api.updataSysUser,
        method: 'post',
        data: parameter
    })
}
