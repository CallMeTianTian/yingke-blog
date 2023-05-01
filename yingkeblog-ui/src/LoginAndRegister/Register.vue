<template>
  <section style="background-color: #eeeeee;zoom: 90%; font-size: 19px">
    <div class="container pt-4">
      <div class="row d-flex justify-content-center align-items-center">
        <div class="col-lg-12 col-xl-11">
          <div class="card text-black" style="border-radius: 25px;">
            <div class="card-body p-md-5">
              <div class="row justify-content-center">
                <div class="col-md-10 col-lg-6 col-xl-5 order-2 order-lg-1">
                  
                  <p class="text-center h1 fw-bold mb-5 mx-1 mx-md-4 mt-4">注 册</p>
                  
                  <form class="mx-1 mx-md-4">
                    
                    <div class="d-flex flex-row align-items-center mb-4">
                      <i class="fas fa-user fa-lg me-3 fa-fw"></i>
                      <div class="form-outline flex-fill mb-0">
                        <input type="text"
                               id="name"
                               class="form-control"
                               @keyup.enter="register"
                               placeholder="请输入您的用户名"
                               v-model.trim="userRegisterInfo.username"/>
                        <label class="form-label" for="name">Your Name</label>
                      </div>
                    </div>
                    
                    <div class="d-flex flex-row align-items-center mb-4">
                      <i class="fas fa-envelope fa-lg me-3 fa-fw"></i>
                      <div class="form-outline flex-fill mb-0">
                        <input type="email"
                               id="email"
                               class="form-control"
                               @keyup.enter="register"
                               placeholder="请输入您的邮箱账号"
                               v-model.trim="userRegisterInfo.email"/>
                        <label class="form-label" for="email">Your Email</label>
                      </div>
                    </div>
                    
                    <div class="d-flex flex-row align-items-center mb-4">
                      <i class="fas fa-lock fa-lg me-3 fa-fw"></i>
                      <div class="form-outline flex-fill mb-0">
                        <input type="password"
                               id="password"
                               class="form-control"
                               @keyup.enter="register"
                               placeholder="请输入您的密码"
                               v-model.trim="userRegisterInfo.password"/>
                        <label class="form-label" for="password">Password</label>
                      </div>
                    </div>
                    
                    <div class="d-flex flex-row align-items-center mb-4">
                      <i class="fas fa-key fa-lg me-3 fa-fw"></i>
                      <div class="form-outline flex-fill mb-0">
                        <input type="password"
                               id="confirm"
                               class="form-control"
                               @keyup.enter="register"
                               placeholder="请输入确认您的密码"
                               v-model.trim="userRegisterInfo.repeatPassword"/>
                        <label class="form-label" for="confirm">Repeat your password</label>
                      </div>
                    </div>
                    
                    <div class="form-check d-flex justify-content-center mb-5">
                      
                      <input class="form-check-input me-2"
                             id="form-check-label"
                             type="checkbox" v-model="agreeToTheTerms" @click="agree"
                      />
                      <label class="form-check-label" for="form-check-label" id="form-check-label"
                             @click="agree;console.log()">
                        阅读并接受
                        <a href="https://privacy.baidu.com/policy/children-privacy-policy/index.html?_1678523966343"
                           target="_blank">
                          个人信息保护声明
                        </a>
                      </label>
                    
                    </div>
                    
                    <div class="d-flex justify-content-center mx-4 mb-3 mb-lg-4">
                      <button type="button"
                              id="buttonForRegister"
                              class="btn btn-primary btn-lg"
                              :disabled="disabled"
                              @click="register"
                              @keyup.enter="register">&nbsp;注 册&nbsp;
                      </button>
                    </div>
                  
                  </form>
                
                </div>
                <div class="col-md-10 col-lg-6 col-xl-7 d-flex align-items-center order-1 order-lg-2">
                  <img src="./images/暂无记录.svg"
                       class="img-fluid" alt="Sample image">
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <br>
    <br>
    <br>
  </section>
</template>

<script>

import axios from "axios";
import {isRegisterCode, register, registerSendCode} from "@/api/user/user";

export default {
  name: "Register",
  data() {
    return {
      userRegisterInfo: {
        username: '',
        email: '',
        password: '',
        repeatPassword: '',
      },
      code:'',
      agreeToTheTerms: false,
      disabled: true
    }
  },
  methods: {
    agree() {
      this.agreeToTheTerms = !this.agreeToTheTerms
      this.disabled = !this.disabled
    },
    register() {
      if (this.userRegisterInfo.username === '') {
        this.$notify({
          title: '警告',
          message: '用户昵称不能为空,请输入用户昵称',
          type: 'warning'
        });
        return
      }
      if (this.userRegisterInfo.email === '') {
        this.$notify({
          title: '警告',
          message: '邮箱号不能为空,请输入邮箱号',
          type: 'warning'
        });
        return
      }
      if (!/^([a-zA-Z\d][\w-]{2,})@(\w{2,})\.([a-z]{2,})(\.[a-z]{2,})?$/.test(this.userRegisterInfo.email)) {
        this.$notify.error({
          title: '错误',
          message: '你输入的邮箱号不合法,请重写输入'
        });
        return
      }
      if (this.userRegisterInfo.password === '') {
        this.$notify({
          title: '警告',
          message: '密码不能为空,请输入密码',
          type: 'warning'
        });
        return
      }
      if (this.userRegisterInfo.password !== this.userRegisterInfo.repeatPassword) {
        this.$notify.error({
          title: '错误',
          message: '两次密码输入不一致',
        });
      } else {
        // 发送验证码
        registerSendCode(this.userRegisterInfo.email).then(res => {
          if (res.data.code === 200) {
            this.$message.success("验证码发送成功");
          } else {
            this.$message.success("验证码发送失败")
          }
        })
        
        // 验证码校验
        this.$prompt(`已往${this.userRegisterInfo.email}发送6位验证码`, '请输入验证码', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          inputPattern: /^\d{6}$/,
          inputErrorMessage: '验证码格式不正确',
          // 取消所以关闭按钮
          closeOnClickModal:false,
          showCancelButton:false,
          showClose:false
        }).then(({ value }) => {
          console.log('用户输入的验证码为' + value )
          isRegisterCode(this.userRegisterInfo.email, value).then((res) => {
            if (res.data.code === 200) {
              register(this.userRegisterInfo).then((res) => {
                if (res.data.code === 200) {
                  console.log('注册成功', res.data.code, res.data.message)
                  this.$message.success(res.data.message)
                  this.$router.replace('/login');
                } else {
                  this.$message.error(res.data.message)
                }
              });
            } else {
              this.$message.error("验证码错误")
            }
          })
        })
      }
    },
    
  }
}
</script>

<style scoped lang="css">

::-webkit-scrollbar {
  width: 5px;
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

</style>