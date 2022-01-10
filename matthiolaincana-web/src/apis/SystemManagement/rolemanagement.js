import request from 'utils/request'

const base = '/sysRole'

const api = {
    inquireSysRoleHeadList: '/inquireSysRoleHeadList',
    inquireSysRoleList: '/inquireSysRoleList',
    increaseSysRole: '/increaseSysRole',
    deleteSysRole: '/deleteSysRole',
    updataSysRole: '/updataSysRole',
    inquireSysRoleByMenu: '/inquireSysRoleByMenu'
}

export const inquireSysRoleHeadList = (parameter) => {
    return request({
        url: base + api.inquireSysRoleHeadList,
        method: 'post',
        data: parameter
    })
}

export const inquireSysRoleList = (parameter) => {
    return request({
        url: base + api.inquireSysRoleList,
        method: 'post',
        data: parameter
    })
}

export const increaseSysRole = (parameter) => {
    return request({
        url: base + api.increaseSysRole,
        method: 'post',
        data: parameter
    })
}

export const deleteSysRole = (parameter) => {
    return request({
        url: base + api.deleteSysRole,
        method: 'post',
        data: parameter
    })
}

export const updataSysRole = (parameter) => {
    return request({
        url: base + api.updataSysRole,
        method: 'post',
        data: parameter
    })
}

export const inquireSysRoleByMenu = (parameter) => {
    return request({
        url: base + api.inquireSysRoleByMenu,
        method: 'post',
        data: parameter
    })
}

