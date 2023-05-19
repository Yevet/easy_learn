<template>
    <div class="app-container">
            <!--查询表单-->
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item>
        <el-input v-model="teacherQuery.name" placeholder="Name"/>
      </el-form-item>
      <el-form-item>
        <el-select v-model="teacherQuery.level" clearable placeholder="Title">
          <el-option :value="1" label="Senior"/>
          <el-option :value="2" label="Best"/>
        </el-select>
      </el-form-item>
      <el-form-item label="Add time">
        <el-date-picker
          v-model="searchObj.begin"
          type="datetime"
          placeholder="select begin time"
          value-format="yyyy-MM-dd HH:mm:ss"
          default-time="00:00:00"
        />
      </el-form-item>
      <el-form-item>
        <el-date-picker
          v-model="searchObj.end"
          type="datetime"
          placeholder="选择截止时间"
          value-format="yyyy-MM-dd HH:mm:ss"
          default-time="00:00:00"
        />
      </el-form-item>
      <el-button type="primary" icon="el-icon-search" @click="getList()">查询</el-button>
      <el-button type="default" @click="resetData()">清空</el-button>

    </el-form>
    <!-- 表格 -->
    <el-table
      :data="list"
      element-loading-text="data is loading"
      border
      fit
      highlight-current-row>
      <el-table-column
        label="ID"
        width="70"
        align="center">
        <template slot-scope="scope">
          {{ (page - 1) * limit + scope.$index + 1 }}
        </template>
      </el-table-column>
      <el-table-column prop="name" label="Name" width="80" />
      <el-table-column label="Title" width="80">
        <template slot-scope="scope">
          {{ scope.row.level===1?'Senior':'Best' }}
        </template>
      </el-table-column>
      <el-table-column prop="intro" label="Career" />
      <el-table-column prop="gmtCreate" label="Add Time" width="160"/>
      <el-table-column prop="sort" label="sort" width="60" />
      <el-table-column label="Command" width="200" align="center">
        <template slot-scope="scope">
          <router-link :to="'/edu/teacher/edit/'+scope.row.id">
            <el-button type="primary" size="mini" icon="el-icon-edit">modify</el-button>
          </router-link>
          <el-button type="danger" size="mini" icon="el-icon-delete" @click="removeDataById(scope.row.id)">delete</el-button>
        </template>
      </el-table-column>
    </el-table>
    1
   <!-- 分页 -->
    <el-pagination
      :current-page="page"
      :page-size="limit"
      :total="total"
      style="padding: 30px 0; text-align: center;"
      layout="total, prev, pager, next, jumper"
      @current-change="getList"
    />
    </div>
</template>

<script>
import teacher from "@/api/edu/teacher"

export default{
    data(){
        return{
            list:null,
            page:1,
            limit:10,
            total:0,
            teacherQuery:{}
        }
    },
    created(){
        this.getList()
    },
    methods:{
        getList(page=1){
            this.page=page
            teacher.getTeacherListPage(this.page,this.limit,this.teacherQuery)
            .then(response=>{
                this.list=response.data.rows
                this.total=response.data.total
            })
            .catch(error=>{
                console.log(error)
            })
        },
        resetData(){
            this.teacherQuery={}
            this.getList()
        },
        removeDataById(id){
        this.$confirm('The teacher will be deleted permanently', '提示', {
          confirmButtonText: 'Yes',
          cancelButtonText: 'Cancel',
          type: 'warning'
        }).then(() => {
            teacher.deleteTeacherId(id)
            .then(response=>{
            this.$message({
                type: 'success',
                message: 'Success Deletion!'
            });
            this.getList()
        })

            
        })
    }
}

}
</script>