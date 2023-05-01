<template>
  <section class="vh-100" style="font-size: .2rem">
    <div class="container-fluid h-custom">
      <div class="row d-flex justify-content-center align-items-center h-100" style="position: relative">
        
        <graphic-loading style="position: absolute;top: 100px;left: 525px"/>
        <div class="col-md-9 col-lg-6 col-xl-5">
          <img src="./images/学习.svg" class="img-fluid"
               alt="Sample image">
        </div>
        <div class="col-md-8 col-lg-6 col-xl-4 offset-xl-1">
          
          <form style="zoom: 90%">
            <div class="flex-row align-items-center justify-content-center justify-content-lg-start">
              <br>
              <br>
              <br>
            </div>
            <img src="./images/logo3.png" alt="logo" width="480"
                 height="265">
            <div class="divider d-flex align-items-center my-4">
              <p class="text-center fw-bold mx-3 mb-0">&copy; 2023 应科博客, Nice</p>
            </div>
            
            
            <div class="input-group">
              <span class="input-group-text" id="basic-addon1">
                <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-mailbox"
                     viewBox="0 0 16 16">
                  <path
                    d="M4 4a3 3 0 0 0-3 3v6h6V7a3 3 0 0 0-3-3zm0-1h8a4 4 0 0 1 4 4v6a1 1 0 0 1-1 1H1a1 1 0 0 1-1-1V7a4 4 0 0 1 4-4zm2.646 1A3.99 3.99 0 0 1 8 7v6h7V7a3 3 0 0 0-3-3H6.646z"></path>
                  <path
                    d="M11.793 8.5H9v-1h5a.5.5 0 0 1 .5.5v1a.5.5 0 0 1-.5.5h-1a.5.5 0 0 1-.354-.146l-.853-.854zM5 7c0 .552-.448 0-1 0s-1 .552-1 0a1 1 0 0 1 2 0z"></path>
              </svg>
              </span>
              
              <input type="email"
                     id="form3Example3"
                     class="form-control form-control-lg"
                     placeholder="请输入您的邮箱账号"
                     v-model.trim="userLoginInfo.email"
                     @keyup.enter="login">
            </div>
            <label class="form-label  mb-4" for="form3Example3" style="font-size: 19px">输入邮箱地址</label>
            
            <div class="input-group">
              <span class="input-group-text" id="basic-addon1">
          <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-shield-lock"
               viewBox="0 0 16 16">
            <path
              d="M5.338 1.59a61.44 61.44 0 0 0-2.837.856.481.481 0 0 0-.328.39c-.554 4.157.726 7.19 2.253 9.188a10.725 10.725 0 0 0 2.287 2.233c.346.244.652.42.893.533.12.057.218.095.293.118a.55.55 0 0 0 .101.025.615.615 0 0 0 .1-.025c.076-.023.174-.061.294-.118.24-.113.547-.29.893-.533a10.726 10.726 0 0 0 2.287-2.233c1.527-1.997 2.807-5.031 2.253-9.188a.48.48 0 0 0-.328-.39c-.651-.213-1.75-.56-2.837-.855C9.552 1.29 8.531 1.067 8 1.067c-.53 0-1.552.223-2.662.524zM5.072.56C6.157.265 7.31 0 8 0s1.843.265 2.928.56c1.11.3 2.229.655 2.887.87a1.54 1.54 0 0 1 1.044 1.262c.596 4.477-.787 7.795-2.465 9.99a11.775 11.775 0 0 1-2.517 2.453 7.159 7.159 0 0 1-1.048.625c-.28.132-.581.24-.829.24s-.548-.108-.829-.24a7.158 7.158 0 0 1-1.048-.625 11.777 11.777 0 0 1-2.517-2.453C1.928 10.487.545 7.169 1.141 2.692A1.54 1.54 0 0 1 2.185 1.43 62.456 62.456 0 0 1 5.072.56z"/>
            <path
              d="M9.5 6.5a1.5 1.5 0 0 1-1 1.415l.385 1.99a.5.5 0 0 1-.491.595h-.788a.5.5 0 0 1-.49-.595l.384-1.99a1.5 1.5 0 1 1 2-1.415z"/>
          </svg>
              </span>
              <input
                type="password"
                id="form3Example4"
                class="form-control form-control-lg"
                placeholder="请输入密码"
                v-model.trim="userLoginInfo.password"
                @keyup.enter="login"
              >
            </div>
            
            <label class="form-label" for="form3Example4" style="font-size: 19px">输入密码</label>
            
            
            <div class="text-center text-lg-start mt-1 pt-2">
              <button type="button"
                      class="btn btn-primary btn-lg"
                      style="padding-left: 2rem; padding-right: 2.5rem;"
                      @click="login">登 录
              </button>
            </div>
          </form>
          
        </div>
      </div>
    </div>
    <div style="font-size: 15px;float: right;margin-right: 480px;">
      <p class="fw-bold pt-1 mb-0">
        还 没 有 注 册?
        <a class="link-danger cp" @click="goToRegister"> &nbsp;&nbsp;注 册 </a></p>
      <p class="fw-bold ">
        忘 记 密 码?
        <a class="link-danger cp" @click="goToRegister">&nbsp;&nbsp;&nbsp;Here</a></p>
    </div>
  </section>
</template>

<script>
import axios from "axios";
import graphicLoading from "@/LoginAndRegister/GraphicLoading.vue";
import {isLogin} from "@/api/user/user";

export default {
  name: "Login",
  data() {
    return {
      logoShow: false,
      userLoginInfo: {
        email: '',
        password: ''
      }
    }
  },
  components: {
    graphicLoading
  },
  created() {
    // 判断是否有token/是否已经登录 => 若有/登录 => 直接跳转到home
    isLogin().then((res) => {
      if (res.data.code === 200) {
        this.$router.replace('/main');
      }
    })
  },
  mounted() {
    // this.$store.commit('user/STORAGE_USERINFO',this.userLoginInfo)
  },
  methods: {
    
    login() {
      if (this.userLoginInfo.email === '') {
        this.$notify({
          title: '警告',
          message: '邮箱号不能为空,请输入邮箱号',
          type: 'warning'
        });
        return
      }
      if (this.userLoginInfo.password === '') {
        this.$notify({
          title: '警告',
          message: '密码不能为空,请输入密码',
          type: 'warning'
        });
        return
      }
      if (!/^([a-zA-Z\d][\w-]{2,})@(\w{2,})\.([a-z]{2,})(\.[a-z]{2,})?$/.test(this.userLoginInfo.email)) {
        this.$notify.error({
          title: '错误',
          message: '你输入的邮箱号不合法,请重写输入'
        });
        return;
      }
      // mook(get): cl http://localhost:3000/user
      // 后端接口(post): http://localhost/user/login
      axios.post('http://localhost/user/login', this.userLoginInfo).then((res) => {
        if (res.data.code === 200) {
          
          
          /**   通过计算属性来获取数据
           *     ...mapState({
           *       userInfo: state => state.user.userInfo
           *     })
           */
          
          // 将userDTO和token值都存在localStorage中
          localStorage.setItem(res.data.data.TTtoken.tokenName, res.data.data.TTtoken.tokenValue)
          localStorage.setItem("userInfo", JSON.stringify(res.data.data.userDTO))
          // 将用户信息存储到vuex中(跳过dispatch)
          this.$store.commit('user/STORAGE_USERINFO', res.data.data.userDTO)
          // 跳转到首页
          this.$router.replace('/main');
          
          const h = this.$createElement;
          this.$message({
            type: 'success',
            message: h('p', null, [
              h('span', null, '欢迎使用应科博客'),
            ])
          });
        } else {
          this.$notify.error({
            title: '错误',
            message: '邮箱名或者密码错误'
          });
        }
      }).catch(error => {
        console.log(error.message)
      })
    },
    goToRegister() {
      this.$router.push('/register')
    }
  }
}
</script>

<style scoped>

.divider:after,
.divider:before {
  content: "";
  flex: 1;
  height: 1px;
  background: #eee;
}

.h-custom {
  height: calc(100% - 73px);
}

@media (max-width: 450px) {
  .h-custom {
    height: 100%;
  }
}

.cp {
  cursor: pointer
}
</style>