import request from 'utils/request'
import qs from 'qs'

const userApi = {
  Login: '/login/userLogin',
  Logout: '/login/userLogout',
  UserInfo: '/sysUser/userInfo',
  UserMenu: '/sysMenu/maun'
}

export function login (parameter) {
  return request({
    url: userApi.Login,
    method: 'post',
    data: qs.stringify(parameter)
  })
}

export function getInfo () {
  return request({
    url: userApi.UserInfo,
    method: 'get'
  })
}

export function getUserMenu (parameter) {
  return request({
    url: userApi.UserMenu,
    method: 'post',
    data: parameter
  })
}

export function logout () {
  return request({
    url: userApi.Logout,
    method: 'post'
  })
}

