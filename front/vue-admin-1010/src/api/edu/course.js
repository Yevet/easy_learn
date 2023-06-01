import request from '@/utils/request'
export default{
    addCourseInfo(courseInfo){
        return request({
            url: `/eduservice/course/addCourseInfo`,
            method: 'post',
            data:courseInfo
          })
    },
    getListTeacher(){
        return request({
            url: `/eduservice/teacher/findAll`,
            method: 'get'
        })
    },

    getCourseInfoId(id) {
        return request({
            url: '/eduservice/course/getCourseInfo/'+id,
            method: 'get'
          })
    },
    //修改课程信息
    updateCourseInfo(courseInfo) {
        return request({
            url: '/eduservice/course/updateCourseInfo',
            method: 'post',
            data: courseInfo
          })
    }
}
