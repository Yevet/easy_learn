import request from '@/utils/request'

export default {
    //查询前两条banner数据
  sendCode(phone) {
    return request({
      url: `/edumsm/msm/send/${phone}`,
      method: 'get'
    })
  },

  registerMember(fromItem) {
    return request({
      url: `/educenter/member/${phone}`,
      method: 'post',
      data:fromItem
    })
  }
}