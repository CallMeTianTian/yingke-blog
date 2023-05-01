<template>
  <div>
    <button class="btn btn-outline-primary" style="float: left" @click="bulkOperations"><i class="el-icon-delete"></i>
      批量删除
    </button>
    <h3 style="float: right; margin-right: 450px">博客管理<i class="el-icon-menu"></i></h3>
    
    <template>
      <el-table :data="tableData"
                @selection-change="handleSelectionChange"
                highlight-current-row
                ref="multipleTable"
                tooltip-effect="dark"
                border
                style="width: 100%">
        
        <el-table-column
          type="selection"
          width="55">
        </el-table-column>
        
        <el-table-column fixed prop="releaseTime" sortable label="发布日期" width="200">
        </el-table-column>
        
        <el-table-column prop="title" label="标题" width="260">
        </el-table-column>
        
        <el-table-column prop="label" label="标签" width="120">
          <template slot-scope="scope">
            <el-tag
              type="primary"
              disable-transitions>{{ scope.row.label }}
            </el-tag>
          </template>
        
        </el-table-column>
        
        <el-table-column prop="watch" label="观看人数" sortable width="120">
        </el-table-column>
        
        <el-table-column prop="star" label="点赞数量" sortable width="120">
        </el-table-column>
        
        <el-table-column prop="collection" label="收藏数量" sortable width="120">
        </el-table-column>
        
        <el-table-column fixed="right" label="操作" width="150">
          
          <template slot-scope="scope">
            <el-button @click="handleClick(scope.row);dialogFormVisible = true" size="mini">编辑
            </el-button>
            
            <el-button size="mini" @click="goto(scope.row)">查看</el-button>
            
            <template>
              <el-popconfirm
                confirm-button-text='取消'
                cancel-button-text='确定'
                @confirm="Cacneldel()"
                @cancel="sureDel(scope.row.blogId)"
                icon="el-icon-info"
                icon-color="red"
                title="是否删除该文章?一旦删除无法找回"
              >
                <el-button slot="reference" size="mini" style="margin-top: 5px; margin-left: 30px">删除</el-button>
              </el-popconfirm>
            </template>
          
          </template>
        
        </el-table-column>
      </el-table>
      <div class="block" style="margin-left: 300px;margin-top: 15px;">
        <el-pagination
          :page-sizes="[100, 200, 300, 400]"
          :page-size="100"
          layout="total, sizes, prev, pager, next, jumper"
          :total="400">
        </el-pagination>
      </div>
    </template>
    
    <!--对话框内容展示 ==> 文章信息展示-->
    <el-dialog title="修改文章信息" :visible.sync="dialogFormVisible" :fullscreen=true>
      <el-form :model="form">
        
        <el-form-item label="文章标题" :label-width="formLabelWidth">
          <el-input v-model="form.title" autocomplete="off"></el-input>
        </el-form-item>
        
        <el-form-item label="文章类型" :label-width="formLabelWidth">
          <el-select v-model="form.label" placeholder="请选择活动区域">
            <el-option label="Java" value="Java"></el-option>
            <el-option label="随笔" value="suibi"></el-option>
            <el-option label="算法" value="suanfa"></el-option>
            <el-option label="Python" value="Python"></el-option>
            <el-option label="Javascript" value="Javascript"></el-option>
          </el-select>
        </el-form-item>
        
        <el-form-item label="" :label-width="'0px'">
          <h3 style="text-align: center">文章修改</h3>
          
          <mavon-editor
            class="markdown-body"
            v-model="form.context"
            @change="change"
            ref="md"
          />
        
        </el-form-item>
      
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancel" icon="el-icon-error">取 消</el-button>
        <el-button type="primary" @click="sure" icon="el-icon-success">确 定</el-button>
      </div>
    </el-dialog>
    
    <!-- 批量删除操作 -->
    <el-dialog title="批量删除" :visible.sync="dialogTableVisible">
      <el-table :data="BulkDeletionData">
        <el-table-column property="releaseTime" label="发布日期" width="200"></el-table-column>
        <el-table-column property="title" label="文章标题" width="350"></el-table-column>
        <el-table-column property="label" label="文章类型" width="120"></el-table-column>
      </el-table>
      
      <div style="float: right;margin-top: 10px;">
        <el-button @click="Cacneldel">取消</el-button>
        <el-button type="primary" @click="deletes">删除</el-button>
      </div>
      <div style="clear: both;"></div>
    
    </el-dialog>
  
  </div>
</template>

<script>
// import axios from "axios";
import {marked} from "marked";
import 'mavon-editor/dist/markdown/github-markdown.min.css'
import {mavonEditor} from 'mavon-editor'     //引入mavon-editor组件
import 'mavon-editor/dist/css/index.css'
import {getMyBlogByLoginUser} from "@/api/user/user";
import {updateBlogByLoginUser,deleteByBlogInLoginUser} from "@/api/blog/blogApi";       //引入组件的样式

export default {
  name: "BlogList",
  data() {
    return {
      type: 'primary',
      tableData: []
      ,
      BulkDeletionData: [],
      multipleSelection: [],
      dialogFormVisible: false,
      dialogTableVisible: false,
      visible: false,
      formLabelWidth: '90px',
      codeDataHtml: '',
      form: {
        blogId: '',
        title: '',
        context: '', // markdown语法的文本
        label: '',
      },
    }
  },
  components: {
    mavonEditor
  },
  created() {
    getMyBlogByLoginUser().then((res) => {
      this.tableData = res.data.data
    })
  },
  methods: {
    handleSelectionChange(val) {
      this.multipleSelection = val;
    },
    
    deletes() {
      if (this.BulkDeletionData.length === 0) {
        this.dialogTableVisible = false
        this.$message.error('选择为空,删除失败');
        return
      }
      this.BulkDeletionData = [];
      this.multipleSelection = []
      this.dialogTableVisible = false
      this.sureDel()
      setTimeout(() => {
        window.location.reload()
      }, 1500)
    },
    
    bulkOperations() {
      this.dialogTableVisible = true
      let val = this.multipleSelection
      for (let i = 0; i < val.length; i++) {
        let data = {}
        data.releaseTime = val[i].releaseTime
        data.title = val[i].title
        data.label = val[i].label
        this.BulkDeletionData.push(data)
      }
    },
    
    handleClick(rowDate) {
      this.form = rowDate
      this.markdownChangeHtml()
    },
    goto(rowBody) {
      let blogId = rowBody.blogId
      let routeData = this.$router.resolve({
        path: "/showcase-the-article",
        query: {
          blogId
        }
      });
      window.open(routeData.href, '_blank');
    },
    markdownChangeHtml() {
      // 将markdown转化成html的内容
      this.codeDataHtml = marked(this.form.context)
    },
    change(value, render) {
      //value为编辑器中的实际内容，即markdown的内容，render为被解析成的html的内容
      this.codeDataHtml = render;
    },
    sure() {
      // TODO 修改
      console.log(this.form)
      updateBlogByLoginUser(this.form).then((res) => {
        if (res.data.code === 200) {
          this.dialogFormVisible = false
          this.$message({
            message: '修改成功!',
            type: 'success'
          });
        }
      })
    },
    cancel() {
      this.dialogFormVisible = false
      this.$message({
        message: '取消修改',
        type: 'warning'
      });
    },
    Cacneldel() {
      this.dialogTableVisible = false
      this.$message({
        message: '取消删除',
        type: 'warning'
      });
    },
    sureDel(blogId) {
      deleteByBlogInLoginUser(blogId).then((res) => {
        console.log(res.data)
        if (res.data.code === 200) {
          this.$message({
            message: '删除成功',
            type: 'success'
          });
          setTimeout(() => {
            location.reload()
          }, 1000)
        } else {
          this.$message({
            message: '删除失败',
            type: 'error'
          });
        }
      })
    }
  }
}
</script>

<style lang="scss">
.markdown-body {
  padding: 30px;
}

.el-popconfirm__main {
  margin: 23px 24px 17px 24px;
  font-size: 14px;
  font-family: MicrosoftYaHei;
  color: #333333;
}

.el-popconfirm__action {
  margin-bottom: 24px;
  padding-bottom: 24px;
  
  .el-button:nth-child(1) {
    float: right;
    margin-right: 23%;
    width: 72px;
    height: 32px;
    background: #055fe7;
    border-radius: 4px;
    font-size: 14px;
    font-family: MicrosoftYaHei;
    color: #ffffff;
  }
  
  .el-button:nth-child(2) {
    float: left;
    margin-left: 23%;
    width: 72px;
    height: 32px;
    background: #ffffff;
    border-radius: 4px;
    border: 1px solid #aeb5c2;
    font-size: 14px;
    font-family: MicrosoftYaHei;
    color: #2e3846;
  }
}
</style>