<template>
  <div class="app-container">
     <el-form label-width="120px">
      <el-form-item label="Name">
        <el-input v-model="teacher.name"/>
      </el-form-item>
      <el-form-item label="Sort">
        <el-input-number v-model="teacher.sort" controls-position="right" min="0"/>
      </el-form-item>
      <el-form-item label="Title">
        <el-select v-model="teacher.level" clearable placeholder="请选择">
          <el-option :value="1" label="Senior"/>
          <el-option :value="2" label="Junior"/>
        </el-select>
      </el-form-item>
      <el-form-item label="Experiences">
        <el-input v-model="teacher.career"/>
      </el-form-item>
      <el-form-item label="Introduction">
        <el-input v-model="teacher.intro" :rows="10" type="textarea"/>
      </el-form-item>

      <!-- 讲师头像：TODO -->

      <el-form-item>
        <el-button :disabled="saveBtnDisabled" type="primary" @click="saveOrUpdate">Save</el-button>
      </el-form-item>
    </el-form>

  </div>
</template>
<script>
import teacherApi from '@/api/edu/teacher'
export default {
  data() {
    return {
      teacher:{
        name: '',
        sort: 0,
        level: 1,
        career: '',
        intro: '',
        avatar: ''
      },
      saveBtnDisabled:false  // 保存按钮是否禁用,
    }
  },
  created() { //页面渲染之前执行
    this.init()
  },
  watch: {  //监听
    $route(to, from) { //路由变化方式，路由发生变化，方法就会执行
      this.init()
    }
  },
  methods:{
    init() {
      //判断路径有id值,做修改
      if(this.$route.params && this.$route.params.id) {
          //从路径获取id值
          const id = this.$route.params.id
          //调用根据id查询的方法
          this.getInfo(id)
      } else { //路径没有id值，做添加
        //清空表单
        this.teacher = {}
      }
    },
    //根据讲师id查询的方法
    getInfo(id) {
      teacherApi.getTeacherInfo(id)
        .then(response => {
          this.teacher = response.data.teacher
        })
    },
    saveOrUpdate() {
      //判断修改还是添加
      //根据teacher是否有id
      if(!this.teacher.id) {
        //添加
        this.saveTeacher()
      } else {
        //修改
        this.updateTeacher()
      }
    },
    //修改讲师的方法
    updateTeacher() {
      teacherApi.updateTeacherInfo(this.teacher)
        .then(response => {
          //提示信息
          this.$message({
              type: 'success',
              message: 'Success Edit!'
          });
          //回到列表页面 路由跳转
          this.$router.push({path:'/teacher/table'})
        })
    },
    //添加讲师的方法
    saveTeacher() {
      teacherApi.addTeacher(this.teacher)
        .then(response => {//添加成功
          //提示信息
          this.$message({
              type: 'success',
              message: 'Success Add!'
          });
          //回到列表页面 路由跳转
          this.$router.push({path:'/teacher/table'})
        })
    }

  }
}
</script>
