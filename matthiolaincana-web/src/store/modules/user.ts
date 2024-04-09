import { defineStore } from 'pinia';
import { TOKEN_NAME } from '@/config/global';
import { store } from '@/store';
import { request } from '@/utils/request';

const InitUserInfo = {
  roles: [],
};

export const useUserStore = defineStore('user', {
  state: () => ({
    token: localStorage.getItem(TOKEN_NAME), // 默认token不走权限
    userInfo: InitUserInfo,
  }),
  getters: {
    roles: (state) => {
      return state.userInfo?.roles;
    },
  },
  actions: {
    async login(userInfo: Record<string, unknown>) {
      const header = { 'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8' };
      const res = await request.post({ headers: header, url: '/login', data: userInfo });
      if (res.code === 200) {
        this.token = res.data;
        localStorage.setItem(TOKEN_NAME, this.token);
      } else {
        throw res;
      }
    },
    async getUserInfo() {
      const res = await request.get({ url: '/sysUser/userInfo' });
      res.roles = ['all'];
      this.userInfo = res;
    },
    async logout() {
      await request.post({ url: '/logout' });
      localStorage.removeItem(TOKEN_NAME);
      this.token = '';
      this.userInfo = InitUserInfo;
    },
    async removeToken() {
      this.token = '';
    },
  },
});

export function getUserStore() {
  return useUserStore(store);
}
