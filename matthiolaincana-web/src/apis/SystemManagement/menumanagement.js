import request from 'utils/request'

const base = '/sysMenu'

const api = {
    inquireSysMenuList: '/inquireSysMenuList',
    inquireSysMenuHeadList: '/inquireSysUserHeadList',
    increaseSysMenu: '/increaseSysMenu',
    deleteSysMenu: '/deleteSysMenu',
    updataSysMenu: '/updataSysMenu'
}

export const inquireSysMenuList = (parameter) => {
    return request({
        url: base + api.inquireSysMenuList,
        method: 'post',
        data: parameter
    })
}

export const inquireSysMenuHeadList = (parameter) => {
    return request({
        url: base + api.inquireSysMenuHeadList,
        method: 'post',
        data: parameter
    })
}

export const increaseSysMenu = (parameter) => {
    return request({
        url: base + api.increaseSysMenu,
        method: 'post',
        data: parameter
    })
}

export const deleteSysMenu = (parameter) => {
    return request({
        url: base + api.deleteSysMenu,
        method: 'post',
        data: parameter
    })
}

export const updataSysMenu = (parameter) => {
    return request({
        url: base + api.updataSysMenu,
        method: 'post',
        data: parameter
    })
}
