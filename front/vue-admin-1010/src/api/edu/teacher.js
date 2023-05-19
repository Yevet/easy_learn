import request from '@/utils/request'
export default{
    getTeacherListPage(current,limit,teacherQuery){
        return request({
            url: `/eduservice/teacher/pageTeacherCondition/${current}/${limit}`,
            method: 'post',
            data:teacherQuery
          })
    },
    deleteTeacherId(id){
        return request({
            url: `/eduservice/teacher/${id}`,
            method: 'delete',
          })
    },
    addTeacher(teacher){
        return request({
            url: `/eduservice/teacher/${id}`,
            method: 'delete',
        })
    }
}
