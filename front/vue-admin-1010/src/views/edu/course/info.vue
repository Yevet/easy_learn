<template>
    <div class="app-container">
    <h2 style="text-align: center;">Submit New Course</h2>
    <el-steps :active="1" process-status="wait" align-center style="margin-bottom: 40px;">
        <el-step title="Course Information"/>
        <el-step title="Course Menu"/>
        <el-step title="Final Submit"/>
    </el-steps>
    <el-form label-width="120px">
        <el-form-item label="Title">
            <el-input v-model="courseInfo.title" placeholder=" 示例：机器学习项目课：从基础到搭建项目视频课程。专业名称注意大小写"/>
        </el-form-item>
        <!-- 所属分类 TODO -->

        <!-- 课程讲师 TODO -->
        
        <!-- 课程讲师 -->
        <el-form-item label="Teacher">
        <el-select
            v-model="courseInfo.teacherId"
            placeholder="Please Select">
            <el-option
            v-for="teacher in teacherList"
            :key="teacher.id"
            :label="teacher.name"
            :value="teacher.id"/>
        </el-select>
        </el-form-item>
        <!-- 课程讲师 -->
        <el-form-item label="Subject">
        <el-select
            v-model="courseInfo.subjectParentId"
            placeholder="Please Select" @change="subjectLevelOneChanged">
            <el-option
            v-for="subject in subjectOneList"
            :key="subject.id"
            :label="subject.title"
            :value="subject.id"/>
        </el-select>
        
        <el-select v-model="courseInfo.subjectId" placeholder="Please Select">
        <el-option
            v-for="subject in subjectTwoList"
            :key="subject.id"
            :label="subject.title"
            :value="subject.id"/>
        </el-select>
        </el-form-item>
        <el-form-item label="Lessons">
            <el-input-number :min="0" v-model="courseInfo.lessonNum" controls-position="right" placeholder="请填写课程的总课时数"/>
        </el-form-item>
        <!-- 课程简介 TODO -->
        <el-form-item label="Description">
            <tinymce :height="300" v-model="courseInfo.description"/>
        </el-form-item>
        <!-- 课程封面-->
        <el-form-item label="Cover">
        <el-upload
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
            :before-upload="beforeAvatarUpload"
            :action="BASE_API+'/eduoss/fileoss'"
            class="avatar-uploader">
            <img :src="courseInfo.cover">
        </el-upload>
        </el-form-item>

        <el-form-item label="Price">
            <el-input-number :min="0" v-model="courseInfo.price" controls-position="right" placeholder="免费课程请设置为0元"/> 元
        </el-form-item>
        <el-form-item>
            <el-button :disabled="saveBtnDisabled" type="primary" @click="saveOrUpdate">Next Step</el-button>
        </el-form-item>
</el-form>
    </div>
</template>
<script>
import course from '@/api/edu/course'
import subject from '@/api/edu/subject'
import Tinymce from '@/components/Tinymce'
export default{
    components:{Tinymce},
    data(){
        return{
            saveBtnDisabled:false,
            courseInfo:{
                title: '',
                subjectId: '',
                subjectParentId:'',
                teacherId: '',
                lessonNum: 0,
                description: '',
                cover: '/static/2.jpg',
                price: 0
            },
            courseId:'',
            BASE_API:process.env.BASE_API,
            teacherList:[],
            subjectOneList:[],
            subjectTwoList:[]
        }
    },
    created(){
        if(this.$route.params && this.$route.params.id){
            this.courseId=this.$route.params.id
            this.getInfo()
        }else{
        this.getListTeacher()
        this.getOneSubject()
    }
        
    },
    methods:{
        getInfo() {
            course.getCourseInfoId(this.courseId)
                .then(response => {
                    //在courseInfo课程基本信息，包含 一级分类id 和 二级分类id
                    this.courseInfo = response.data.courseInfoVo
                    //1 查询所有的分类，包含一级和二级
                    subject.getSubjectList()
                        .then(response => {
                            //2 获取所有一级分类
                            this.subjectOneList = response.data.list
                            //3 把所有的一级分类数组进行遍历，
                            for(var i=0;i<this.subjectOneList.length;i++) {
                                //获取每个一级分类
                                var oneSubject = this.subjectOneList[i]
                                //比较当前courseInfo里面一级分类id和所有的一级分类id
                                if(this.courseInfo.subjectParentId == oneSubject.id) {
                                    //获取一级分类所有的二级分类
                                    this.subjectTwoList = oneSubject.children
                                }
                            }
                        })
                        //初始化所有讲师
                        this.getListTeacher()
                })
        },

        handleAvatarSuccess(res,file){
            this.courseInfo.cover=res.data.url
        },
        beforeAvatarUpload(file){
            const isJPG = file.type === 'image/jpeg'
            const isLt2M = file.size / 1024 / 1024 < 2
            if (!isJPG) {
                this.$message.error('上传头像图片只能是 JPG 格式!')
            }
            if (!isLt2M) {
                this.$message.error('上传头像图片大小不能超过 2MB!')
            }
            return isJPG && isLt2M
        },
        subjectLevelOneChanged(value){
            for (var i = 0; i < this.subjectOneList.length; i++) {
                var oneSubject=this.subjectOneList[i]
                if(value==oneSubject.id){
                    this.subjectTwoList=oneSubject.children
                    this.courseInfo.subjectId=''
                }
            }
        },

        getOneSubject(){
            subject.getSubjectList()
            .then(response=>{
                this.subjectOneList=response.data.list
            })
        },
        getListTeacher(){
            course.getListTeacher()
                .then(response=>{
                    this.teacherList=response.data.items
                })
        },
        addCourse(){
            course.addCourseInfo(this.courseInfo)
            .then(response=>{
                this.$message({
                    type:'success',
                    message:'Successly Save!'
                })
                this.$router.push({path:'/course/chapter/'+response.data.courseId})
            })
        },
        updateCourse(){
            course.updateCourseInfo(this.courseInfo)
            .then(response=>{
                this.$message({
                    type:'success',
                    message:'Successly Update!'
                })
                this.$router.push({path:'/course/chapter/'+this.courseId})
            })
            
        },
        saveOrUpdate(){
            if(!this.courseInfo.id){
                this.addCourse()
            }else{
                this.updateCourse()
            }
        }
    }
}

</script>


<style scoped>
.tinymce-container {
  line-height: 29px;
}
</style>