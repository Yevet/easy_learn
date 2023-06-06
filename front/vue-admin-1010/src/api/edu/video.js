import request from '@/utils/request'
export default{
    addVideo(video) {
        return request({
            url: '/eduservice/video/addVideo',
            method: 'post',
            data:video
          })
    },
    
    deleteVideo(id) {
        return request({
            url: '/eduservice/video/'+id,
            method: 'delete'
          })
    },
    deleteAliyunvod(id) {
        return request({
            url: '/eduvod/video/removeAlyVideo/'+id,
            method: 'delete'
          })
    }
}
