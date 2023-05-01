<template>
  <div>
    <el-descriptions class="margin-top" :title="userinfo.username + '的用户信息'" :column="2" border>
      <template slot="extra">
        <el-tooltip class="item" effect="light" content="一旦修改无法返回" placement="top-start">
          <el-button type="primary" size="small" @click="modifyInfo">操作</el-button>
        </el-tooltip>
      
      </template>
      
      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-user"></i>
          用户名
        </template>
        {{ userinfo.username }}
      </el-descriptions-item>
      
      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-message"></i>
          邮箱号
        </template>
        {{ email }}
      </el-descriptions-item>
      
      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-help"></i>
          身份
        </template>
        {{ userinfo.identity }}
      </el-descriptions-item>
      
      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-sunrise-1"></i>
          生日
        </template>
        <div class="block">
          <span class="demonstration"></span>
          <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
               class="bi bi-calendar-check-fill mb-1" viewBox="0 0 16 16">
            <path
              d="M4 .5a.5.5 0 0 0-1 0V1H2a2 2 0 0 0-2 2v1h16V3a2 2 0 0 0-2-2h-1V.5a.5.5 0 0 0-1 0V1H4V.5zM16 14V5H0v9a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2zm-5.146-5.146-3 3a.5.5 0 0 1-.708 0l-1.5-1.5a.5.5 0 0 1 .708-.708L7.5 10.793l2.646-2.647a.5.5 0 0 1 .708.708z"/>
          </svg>
          &nbsp;{{ userinfo.birthday }}
        </div>
      </el-descriptions-item>
      
      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-tickets"></i>
          擅长的语言
        </template>
        <div>
          <el-tag size="small" v-for="(item,index) in userinfo.label" :key="index">{{ item }}</el-tag>
        </div>
      </el-descriptions-item>
      
      <el-descriptions-item>
        <template slot="label">
          <i class="el-icon-office-building"></i>
          公司/学校
        </template>
        {{ userinfo.schoolOrCorporation }}
      </el-descriptions-item>
    
    
    </el-descriptions>
    
    
    <div class="alert alert-success mt-3" role="alert">
      <h4 class="alert-heading">个人描述</h4>
      <hr>
      <p class="mb-0" v-html="userinfo.description" v-model="userinfo.description"></p>
    </div>
    
    <!--个人文章和学习情况-->
    <div class="alert alert-primary mt-3" role="alert">
      <h4 class="alert-heading">热门文章</h4>
      <hr>
      <!--遍历popularArticles 依次取得blogId uid title watch star collection-->
      <div class="alert alert-light" role="alert" style="zoom: 88%">
        <div class="alert alert-light" v-show="popularArticles.length === 0">
          <el-empty description="暂时莫得"></el-empty>
        </div>
        <ul>
          <li v-for="popular in popularArticles" :key="popular.blogId" @click="goto(popular.blogId)"
              style="cursor:pointer">
            <a href="#" class="alert-link">{{ popular.title }}</a>
            <span style="float: right">
              📅{{ popular.releaseTime }}&nbsp;&nbsp;
              👁️‍🗨️️‍{{ popular.watch }}&nbsp;&nbsp;&nbsp;&nbsp;
              👍{{ popular.star }}&nbsp;&nbsp;&nbsp;
              💎{{ popular.collection }}
            </span>
          </li>
        </ul>
      
      </div>
    </div>
    
    <div class="alert alert-dark" role="alert">
      <h4 class="alert-heading">学习情况</h4>
      <hr>
      <table class="table table-striped table-hover">
        <thead>
        <tr>
          <th scope="col" width="50"></th>
          <th scope="col" width="250">课程名称</th>
          <th scope="col" width="160">学习进度</th>
          <th scope="col" width="250">最近一次学习的时间</th>
          <th scope="col" width="60">查看</th>
        </tr>
        </thead>
        
        <tbody>
        <tr v-for="(cp,index) in classAndProgressList">
          <th scope="row">{{ index + 1 }}</th>
          <td>{{ cp.className }}</td>
          <td>{{ cp.progress }}</td>
          <td>{{ cp.modifyTime }}</td>
          <td><a :href="cp.url" target="_blank">GO</a></td>
        </tr>
        
        </tbody>
      </table>
      <div class="alert alert-light" v-show="classAndProgressList.length === 0">
        <el-empty description="暂时莫得"></el-empty>
      </div>
    </div>
    
    
    <!-- Form -->
    <el-dialog title="TianTianAdmin的用户信息" :visible.sync="dialogFormVisible">
      
      <el-form :model="userinfo">
        
        <el-form-item label="用户名称" :label-width="formLabelWidth">
          <el-input v-model="userinfo.username"
                    maxlength="30"
                    show-word-limit
                    autocomplete="off"></el-input>
        </el-form-item>
        
        <el-form-item label="用户邮箱" :label-width="formLabelWidth">
          <el-input v-model="email" :disabled="true" autocomplete="off"></el-input>
        </el-form-item>
        
        <el-row>
          <el-col :span="10">
            <el-form-item label="身份" :label-width="formLabelWidth">
              <el-select v-model="userinfo.identity" placeholder="请选择身份">
                <el-option label="学生" value="学生"></el-option>
                <el-option label="打工人" value="打工人"></el-option>
                <el-option label="黑社会大哥" value="黑社会大哥"></el-option>
                <el-option label="五星级杀手" value="五星级杀手"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          
          <el-col :span="14">
            <el-form-item label="生日" :label-width="formLabelWidth">
              <el-date-picker
                v-model="userinfo.birthday"
                type="date"
                format="yyyy-MM-dd"
                placeholder="选择日期">
              </el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>
        
        <el-form-item label="学生/公司" :label-width="formLabelWidth">
          <el-input v-model="userinfo.schoolOrCorporation"
                    maxlength="25"
                    show-word-limit
                    autocomplete="off"></el-input>
        </el-form-item>
        
        
        <!--擅长的语言选择多系列-->
        <el-form-item label="擅长的语言" :label-width="formLabelWidth">
          <el-tag
            size="small"
            :key="tag"
            v-for="tag in userinfo.label"
            closable
            :disable-transitions="false"
            @close="handleClose(tag)">
            {{ tag }}
          </el-tag>
          
          <el-input
            :disabled="userinfo.label.length === 6"
            class="input-new-tag"
            v-if="inputVisible"
            v-model="inputValue"
            ref="saveTagInput"
            size="small"
            @keyup.enter.native="handleInputConfirm"
            @blur="handleInputConfirm"
          >
          </el-input>
          <el-button v-else class="button-new-tag" size="small" @click="showInput">+ 新增标签</el-button>
        </el-form-item>
        
        <!--  个人描述-->
        <!--        <div class="mb-3" style="margin-left: 45px">
                  <label for="exampleFormControlTextarea1" class="form-label">个人描述</label>
                  <textarea class="form-control" id="exampleFormControlTextarea1" rows="3"
                            v-html="userinfo.description" ></textarea>
                </div>-->
        <hr>
        <div style="margin-left: 18px;margin-bottom: 20px;">个人描述
          <div style="float: right">
            字体大小&nbsp;&nbsp;&nbsp;
            <el-button size="mini" @click="descriptionSize++">+</el-button>
            <el-button size="mini" @click="descriptionSize--">-</el-button>
  
  
  
            <!--<el-input-number v-model="descriptionSize" :min="1" :max="50" label="描述文字"></el-input-number>-->

          </div>
        </div>
        <el-input
          type="textarea"
          :style="{fontSize: descriptionSize + 'px'}"
          :autosize="{ minRows: 8, maxRows: 20}"
          placeholder="请输入个人描述"
          maxlength="1500"
          show-word-limit
          v-model="userinfo.description">
        </el-input>
      
      
      </el-form>
      <div slot="footer" class="dialog-footer">
        <button type="button" class="btn btn-secondary mx-1" @click="cancel">取 消</button>
        <button type="button" class="btn btn-outline-info mx-2" @click="view">预 览</button>
        <button type="button" class="btn btn-primary me-1" @click="sure">确 定</button>
      </div>
    </el-dialog>
  
  </div>
</template>

<script>
import axios from "axios";
import {getBaseUserInfoByUid, UpdateUserInfoByUid} from '@/api/user/user'
import {getCourseByUid} from "@/api/course/course";
import {getTime2} from "../../../utils/DateFormatting";

export default {
  name: "SpaceInfo",
  data() {
    return {
      uid: '',
      email: '',
      userinfo: {
        /*      username: 'TianTianAdmin',
                gender: '男',
                birthday: '2023-1-10',
                email: '1979214069@qq.com',
                identity: '五星级杀手',
                schoolOrCorporation: '哈哈佛大学',
                description: 'Hello呀<br>' +
                  '我是天天，一天的天，两天的天<br>' +
                  '本人有很多优点，细说起来三天三夜也讲不完<br>' +
                  '缺点嘛，你也看到了',
                // 语言标签的选择 => 最大添加六个
                label: ['Java', 'Javascript', 'Python']*/
      },
      popularArticles: [],
      classAndProgressList: [],
      dialogFormVisible: false,
      formLabelWidth: '100px',
      descriptionSize: 18,
      
      inputVisible: false,
      inputValue: ''
    }
  },
  created() {
    let userInfo = JSON.parse(localStorage.getItem("userInfo"));
    this.uid = userInfo.uid
    this.email = userInfo.email
    
    // 获取基础用户信息
    this.getUserInfo()
    
    // 根据用户id来(从store中) =>  获取 标题 观看人数 点赞人数 时间
    axios.get(`http://localhost/blog/by-user/${this.uid}`).then((res) => {
      console.log(res.data)
      this.popularArticles = res.data.data
    }, (error) => {
      console.log(error.message)
    })
    
    // 根据用户id来获取课程的信息
    getCourseByUid(this.uid).then(res => {
      this.classAndProgressList = res.data.data
    }, err => {
      console.log(err.message)
    })
  },
  
  methods: {
    getTime2,
    modifyInfo() {
      this.dialogFormVisible = true
    },
    getUserInfo() {
      // 获取基础用户信息
      getBaseUserInfoByUid(this.uid).then((res) => {
        this.userinfo = res.data.data
      })
    },
    
    sure() {
      this.dialogFormVisible = false
      UpdateUserInfoByUid(this.userinfo).then((res) => {
        if (res.data.code === 200) {
          this.$message({
            message: '修改成功',
            type: 'success'
          });
        } else {
          this.$message({
            message: '修改失败',
            type: 'error'
          });
        }
      })
      
      this.dataReload()
      
    },
    view() {
      this.dialogFormVisible = false
      this.$notify({
        title: '提示',
        message: '预览中,点击确定即可提交',
        duration: 0
      });
    },
    
    cancel() {
      this.dialogFormVisible = false
      this.$message({
        message: '取消修改',
        type: 'warning'
      });
      this.dataReload()
    },
    
    goto(blogId) {
      let routeData = this.$router.resolve({
        path: "/showcase-the-article",
        query: {
          blogId
        }
      });
      window.open(routeData.href, '_blank');
    },
    
    // 标签的添加
    handleClose(tag) {
      this.userinfo.label.splice(this.userinfo.label.indexOf(tag), 1);
    },
    
    showInput() {
      this.inputVisible = true;
      this.$nextTick(_ => {
        this.$refs.saveTagInput.$refs.input.focus();
      });
    },
    
    dataReload() {
      // 重新获取数据
      setTimeout(() => {
        window.location.reload()
      }, 200)
    },
    
    handleInputConfirm() {
      let inputValue = this.inputValue;
      if (inputValue) {
        this.userinfo.label.push(inputValue);
      }
      
      if (inputValue.length >= 10) {
        this.$notify({
          title: '警告',
          message: '标签长度不超过10',
          type: 'warning'
        });
        this.userinfo.label.pop()
        return
      }
      
      if (this.userinfo.label.length >= 6) {
        this.$notify({
          title: '警告',
          message: '最多可以添加6个标签',
          type: 'warning'
        });
      }
      
      this.inputVisible = false;
      this.inputValue = '';
    },
  },
  
}

</script>

<style lang="css">

::-webkit-scrollbar {
  width: 6px;
}

/*定义滚动条轨道 内阴影+圆角*/
::-webkit-scrollbar-track {
  border-radius: 10px;
  background-color: rgba(0, 0, 0, 0.1);
}

/*定义滑块 内阴影+圆角*/
::-webkit-scrollbar-thumb {
  border-radius: 10px;
  -webkit-box-shadow: inset 0 0 6px rgba(0, 0, 0, .3);
  background-color: rgba(0, 0, 0, 0.1);
}

ul {
  padding: 0;
  margin: 0;
}

li {
  margin: 0;
  padding: 0;
  list-style: none;
}

.el-tag + .el-tag {
  margin-left: 10px;
}

.button-new-tag {
  margin-left: 10px;
  height: 32px;
  line-height: 30px;
  padding-top: 0;
  padding-bottom: 0;
}

.input-new-tag {
  width: 90px;
  margin-left: 10px;
  vertical-align: bottom;
}
</style>

