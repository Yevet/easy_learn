<template>
      <div class="app-container">
        <h2 style="text-align: center;">Submit New Course</h2>
        <el-steps :active="2" process-status="wait" align-center style="margin-bottom: 40px;">
          <el-step title="Course Information"/>
          <el-step title="Course Menu"/>
          <el-step title="Final Submit"/>
        </el-steps>

        <el-button type="text" @click="dialogChapterFormVisible=true">Add Chapter</el-button>

        <!-- 章节 -->
<ul class="chanpterList">
    <li
        v-for="chapter in chapterVideoList"
        :key="chapter.id">
        <p>
            {{ chapter.title }}
            <span class="acts">
              <el-button style="" type="text" @click="openVideo(chapter.id)">Add Section</el-button>
                
                <el-button style="" type="text" @click="openEditChapter(chapter.id)">Edit</el-button>
                <el-button type="text" @click="removeChapter(chapter.id)">Delete</el-button>
            </span>

        </p>
        <!-- 视频 -->
        <ul class="chanpterList videoList">
            <li
                v-for="video in chapter.children"
                :key="video.id">
                <p>{{ video.title }}
                
                <span class="acts">
                <el-button style="" type="text" @click="openEditChapter(chapter.id)">Edit</el-button>
                <el-button type="text" @click="removeVideo(chapter.id)">Delete</el-button>
           
              </span>
            </p>
            </li>
        </ul>
    </li>
</ul>
<div>
    <el-button @click="previous">Back</el-button>
    <el-button :disabled="saveBtnDisabled" type="primary" @click="next">Next Step</el-button>
</div>
<!-- 添加和修改章节表单 -->
<el-dialog :visible.sync="dialogChapterFormVisible" title="添加章节">
    <el-form :model="chapter" label-width="120px">
        <el-form-item label="Title">
            <el-input v-model="chapter.title"/>
        </el-form-item>
        <el-form-item label="Sort">
            <el-input-number v-model="chapter.sort" :min="0" controls-position="right"/>
        </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
        <el-button @click="openChapterDialog()">取 消</el-button>
        <el-button type="primary" @click="saveOrUpdate">确 定</el-button>
    </div>
</el-dialog>
<!-- 添加和修改课时表单 -->
<el-dialog :visible.sync="dialogVideoFormVisible" title="Add Section">
  <el-form :model="video" label-width="120px">
    <el-form-item label="Title">
      <el-input v-model="video.title"/>
    </el-form-item>
    <el-form-item label="Sort">
      <el-input-number v-model="video.sort" :min="0" controls-position="right"/>
    </el-form-item>
    <el-form-item label="Fee">
      <el-radio-group v-model="video.free">
        <el-radio :label="true">Free</el-radio>
        <el-radio :label="false">default</el-radio>
      </el-radio-group>
    </el-form-item>
    
<el-form-item label="Upload Video">
    <el-upload
           :on-success="handleVodUploadSuccess"
           :on-remove="handleVodRemove"
           :before-remove="beforeVodRemove"
           :on-exceed="handleUploadExceed"
           :file-list="fileList"
           :action="BASE_API+'/eduvod/video/uploadAlyiVideo'"
           :limit="1"
           class="upload-demo">
    <el-button size="small" type="primary">Upload Video</el-button>
    <el-tooltip placement="right-end">
        <div slot="content">最大支持1G,<br>
            支持3GP、ASF、AVI、DAT、DV、FLV、F4V、<br>
            GIF、M2T、M4V、MJ2、MJPEG、MKV、MOV、MP4、<br>
            MPE、MPG、MPEG、MTS、OGG、QT、RM、RMVB、<br>
            SWF、TS、VOB、WMV、WEBM 等视频格式上传</div>
        <i class="el-icon-question"/>
    </el-tooltip>
    </el-upload>
</el-form-item>
  </el-form>
  <div slot="footer" class="dialog-footer">
    <el-button @click="dialogVideoFormVisible = false">Cancel</el-button>
    <el-button :disabled="saveVideoBtnDisabled" type="primary" @click="saveOrUpdateVideo">Yes</el-button>
  </div>
</el-dialog>
      </div>
    </template>
    <script>
    import chapter from '@/api/edu/chapter'
    import video from '@/api/edu/video'
    export default{
      
        data(){
            return{
                saveBtnDisabled:false,
                courseId:'',
                chapterVideoList:[],
                chapter:{
                  title:'',
                  sort:0
                },
                video:{
                  title:'',
                  sort:0,
                  free:0,
                  videoSourceId:'',
                  videoOriginName:''
                },
                dialogChapterFormVisible:false,
                dialogVideoFormVisible:false,
                fileList: [],//上传文件列表
                BASE_API: process.env.BASE_API // 接口API地址
            }
        },
        created(){
          if(this.$route.params && this.$route.params.id){
            this.courseId=this.$route.params.id
            this.getChapterVideo()
          }
          
        },
        methods:{
          handleVodRemove(){
            video.deleteAliyunvod(this.video.videoSourceId)
            .then(response=>{
              this.$message({
                        type: 'success',
                        message: 'Successfully Delete!'
                    });
                    this.fileList=[]
                    this.video.videoSourceId = ''
                    this.video.videoOriginName=''
            })
          },
          beforeVodRemove(file,fileList){
            return this.$confirm(`Confirm to remove ${file.name}?`);
          },
          handleVodUploadSuccess(response,file,fileList){
            this.video.videoSourceId = response.data.videoId
            this.video.videoOriginName=file.name
          },
          handleUploadExceed(){
            this.$message.warning('Please delete the previous uploaded video before upload new video.')
          },
          removeVideo(id){
            this.$confirm('This section will be deleted permanently!', 'NOTICE', {
                confirmButtonText: 'Yes',
                cancelButtonText: 'Cancel',
                type: 'warning'
            }).then(() => {  //点击确定，执行then方法
                //调用删除的方法
                video.deleteVideo(id)
                    .then(response =>{//删除成功
                    //提示信息
                    this.$message({
                        type: 'success',
                        message: 'Successfully Delete!'
                    });
                    //回到列表页面
                    this.getChapterVideo()
                })
            }) //点击取消，执行catch方法
          },
          openVideo(chapterId){
            this.dialogVideoFormVisible=true
            this.video.chapterId=chapterId
          },
          addVideo(){
            this.video.courseId=this.courseId
            video.addVideo(this.video)
            .then(response=>{
              this.dialogVideoFormVisible=false
              this.$message({
                type:'success',
                message:'Successfully Add!'
              });
              this.getChapterVideo
            })
          },
          saveOrUpdateVideo(){
            this.addVideo()
          },
          removeChapter(chapterId){
            this.$confirm('This chapter will be deleted permanently!', 'NOTICE', {
                confirmButtonText: 'Yes',
                cancelButtonText: 'Cancel',
                type: 'warning'
            }).then(() => {  //点击确定，执行then方法
                //调用删除的方法
                chapter.deleteChapter(chapterId)
                    .then(response =>{//删除成功
                    //提示信息
                    this.$message({
                        type: 'success',
                        message: 'Successfully Delete!'
                    });
                    //回到列表页面
                    this.getChapterVideo()
                })
            }) //点击取消，执行catch方法
          },
          openEditChapter(chapterId){
            this.dialogChapterFormVisible=true
            chapter.getChapter(chapterId)
            .then(response=>{
              this.chapter=response.data.chapter
            })
          },
          openChapterDialog(){
            this.dialogChapterFormVisible=true
            this.chapter.title=''
            this.chapter.sort=0
          },
          addChapter(){
            this.chapter.courseId=this.courseId
            chapter.addChapter(this.chapter)
            .then(response=>{
              this.dialogChapterFormVisible=false
              this.$message({
                type:'success',
                message:"Successfully Add Chapter!"
              })
              this.getChapterVideo()
            })
          },
          updateChapter(){
            chapter.updateChapter(this.chapter)
            .then(response=>{
              this.dialogChapterFormVisible=false
              this.$message({
                type:'success',
                message:"Successfully Update Chapter!"
              })
              this.getChapterVideo()
            })
          },
          saveOrUpdate(){
            if(!this.chapter.id){
              this.addChapter()
            }else{
              this.updateChapter()
            }
            
          },
          getChapterVideo() {
            chapter.getAllChapterVideo(this.courseId)
                .then(response => {
                    this.chapterVideoList = response.data.allChapterVideo
                })
        },

            previous(){
                this.$router.push({path:'/course/info/'+this.courseId})
            },
            next(){
                this.$router.push({path:'/course/publish/'+this.courseId})
            }
        }
    }
    
    </script>

<style scoped>
.chanpterList{
    position: relative;
    list-style: none;
    margin: 0;
    padding: 0;
}
.chanpterList li{
  position: relative;
}
.chanpterList p{
  float: left;
  font-size: 20px;
  margin: 10px 0;
  padding: 10px;
  height: 70px;
  line-height: 50px;
  width: 100%;
  border: 1px solid #DDD;
}
.chanpterList .acts {
    float: right;
    font-size: 14px;
}
.videoList{
  padding-left: 50px;
}
.videoList p{
  float: left;
  font-size: 14px;
  margin: 10px 0;
  padding: 10px;
  height: 50px;
  line-height: 30px;
  width: 100%;
  border: 1px dotted #DDD;
}
</style>