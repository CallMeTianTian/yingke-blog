<template>
  <div>
    <div style="background-color: white;height: 1000px;width: 1430px;margin: 0 auto">
      
      <el-container style="height: 750px; border: 2px solid #eee;border-radius: 10px">
        <el-aside width="210px" style="margin-top: 1%">
          <div style="width: 190px;background-color: white;padding: 5px;margin: auto">
            
            <el-upload class="avatar-uploader"
                       :action="uploadUrl"
                       :headers="headers"
                       :show-file-list="false"
                       :on-success="handleAvatarSuccess"
                       :before-upload="beforeUpload"
                       ref="upload">
              
              <!--当用户头像不为空时 显示从localStorage中获取的这张图-->
              <img v-if="userInfo.headurl !== '' && userInfo.headurl !== null" :src="userInfo.headurl" width="150px"
                   height="150px"
                   style="border-radius: 300px;margin-left: 8%" alt="userImage">
              <!--当用户头像为扣时显示这张图片defaultUserUrl-->
              <img v-else :src="defaultUserUrl" width="150px" height="150px"
                   style="border-radius: 300px;margin-left: 8%" alt="userImage">
              
              <i>
                <el-button size="mini" style="margin-top: 20px">更换头像</el-button>
              </i>
            </el-upload>
            
            
            <br>
            <p style="margin:0 auto;text-align: center" v-model="userInfo.username">{{ userInfo.username }}</p>
          </div>
          
          
          <el-menu :default-openeds="['1','2','3']" :unique-opened="false">
            
            <el-submenu index="1">
              <template slot="title"><i class="el-icon-message"></i>用户中心</template>
              <el-menu-item-group>
                
                <router-link class="alink" to="/user-space/space-info">
                  <el-menu-item>
                    <i class="el-icon-user"></i>
                    用户信息
                  </el-menu-item>
                </router-link>
                
                <router-link class="alink" to="/user-space/changepwd">
                  <el-menu-item>
                    <i class="el-icon-lock"></i>
                    修改密码
                  </el-menu-item>
                </router-link>
                
                <!-- 我的关注 -->
                <router-link class="alink" to="/user-space/my-follow">
                  <el-menu-item>
                    <svg xmlns="http://www.w3.org/2000/svg" style="margin-right: 12px" width="17" height="17"
                         fill="currentColor" class="bi bi-postcard-heart" viewBox="0 0 16 16">
                      <path
                        d="M8 4.5a.5.5 0 0 0-1 0v7a.5.5 0 0 0 1 0v-7Zm3.5.878c1.482-1.42 4.795 1.392 0 4.622-4.795-3.23-1.482-6.043 0-4.622ZM2.5 5a.5.5 0 0 0 0 1h3a.5.5 0 0 0 0-1h-3Zm0 2a.5.5 0 0 0 0 1h3a.5.5 0 0 0 0-1h-3Zm0 2a.5.5 0 0 0 0 1h3a.5.5 0 0 0 0-1h-3Z"/>
                      <path fill-rule="evenodd"
                            d="M0 4a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v8a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V4Zm2-1a1 1 0 0 0-1 1v8a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1V4a1 1 0 0 0-1-1H2Z"/>
                    </svg>
                    我的关注
                  </el-menu-item>
                </router-link>
                <!-- 我的收藏 my-collect -->
                
                <router-link class="alink" to="/user-space/my-collect">
                  <el-menu-item>
                    <svg xmlns="http://www.w3.org/2000/svg" style="margin-right: 12px" width="17" height="17"
                         fill="currentColor" class="bi bi-bookmark-heart" viewBox="0 0 16 16">
                      <path fill-rule="evenodd"
                            d="M8 4.41c1.387-1.425 4.854 1.07 0 4.277C3.146 5.48 6.613 2.986 8 4.412z"/>
                      <path
                        d="M2 2a2 2 0 0 1 2-2h8a2 2 0 0 1 2 2v13.5a.5.5 0 0 1-.777.416L8 13.101l-5.223 2.815A.5.5 0 0 1 2 15.5V2zm2-1a1 1 0 0 0-1 1v12.566l4.723-2.482a.5.5 0 0 1 .554 0L13 14.566V2a1 1 0 0 0-1-1H4z"/>
                    </svg>
                    我的收藏
                  </el-menu-item>
                </router-link>
              
              </el-menu-item-group>
            </el-submenu>
            
            <el-submenu index="2">
              <template slot="title"><i class="el-icon-menu"></i>博客管理</template>
              <el-menu-item-group>
                
                <router-link class="alink" to="/user-space/blog-list">
                  <el-menu-item index="2-1">
                    <i class="el-icon-cloudy"></i>
                    博客列表
                  </el-menu-item>
                </router-link>
                
                <router-link class="alink" to="/blog/write-article">
                  <el-menu-item index="2-2">
                    <i class="el-icon-edit"></i>
                    书写文章
                  </el-menu-item>
                </router-link>
              
              </el-menu-item-group>
            
            </el-submenu>
          
          </el-menu>
        </el-aside>
        
        
        <el-container style="background-color: white">
          <el-main>
            <router-view/>
          </el-main>
        </el-container>
      
      </el-container>
    
    
    </div>
  </div>
</template>

<script>
import SpaceInfo from "@/pages/person/UserInfo/SpaceInfo.vue";
import {getUserDTO} from '@/api/user/user'
import {mapState} from 'vuex'

export default {
  name: "UserSpace",
  components: {
    SpaceInfo
  },
  data() {
    return {
      userInfo: {
        uid: '',
        headurl: '',
        username: '',
        email: ''
      },
      defaultUserUrl: require('@/assets/img/defaultUserUrl.png'),
      headers: {
        "TTtoken": localStorage.getItem("TTtoken")
      },
      uploadUrl: 'http://localhost/common/oss/upload'
    }
  },
  /*  computed:{
      ...mapState({ userInfoFormVuex: state => state.user.userInfo})
    },*/
  created() {
    // 初始化时 从localStorage中获取用户头像
    this.userInfo = JSON.parse(localStorage.getItem("userInfo"))
  },
  methods: {
    handleAvatarSuccess(response) {
      console.log(response)
      this.userInfo.headurl = response.message
      getUserDTO().then((res) => {
        this.userInfo = res.data.data
        // 重新写入userInfo
        localStorage.setItem("userInfo", JSON.stringify(res.data.data))
      })
      this.$message({
        showClose: true,
        message: '修改成功',
        type: "success"
      });
      
      setTimeout(() => {
        location.reload()
      }, 100);
    },
    
    beforeUpload(file) {
      if (file) {
        const suffix = file.name.substring(file.name.lastIndexOf(".") + 1);
        const size = file.size / 1024 / 1024 < 2
        if (['png', 'jpeg', 'jpg'].indexOf(suffix) < 0) {
          this.$message.error('上传图片只支持 png、jpeg、jpg 格式！')
          this.$refs.upload.clearFiles()
          return false
        }
        if (!size) {
          this.$message.error('上传文件大小不能超过 2MB!')
          return false
        }
        return file
      }
    }
  }
}
</script>

<style scoped>
.alink {
  text-decoration: none;
}

.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}

.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}

.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}

.avatar {
  width: 178px;
  height: 178px;
  display: block;
}

.better-close {
  padding: 0;
  margin-right: 1px;
}
</style>