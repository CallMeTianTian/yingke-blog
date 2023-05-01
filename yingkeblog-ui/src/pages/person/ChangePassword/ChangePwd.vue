<template>
  <div>
    <div style="margin: 20px auto">
      <el-steps :active="index"
                finish-status="success"
                simple style="margin-top: 20px"
                direction="vertical">
        <el-step title="通过邮箱验证" icon="el-icon-s-comment"></el-step>
        <el-step title="修改密码" icon="el-icon-refresh-right"></el-step>
      </el-steps>
      
      
      <div style="margin: 50px auto; width: 500px;" v-show="index === 1">
        <h4>请输入您注册时使用的邮箱</h4>
        <el-input placeholder="请输入您的邮箱"
                  v-model="email"
                  @keyup.enter.native="getVerify">
          <template slot="append">
            <el-button
              :disabled="disabled"
              style="float: right"
              @click="getVerify">
              {{ !showTime ? "发送" : count }}
            </el-button>
          </template>
        </el-input>
        <br>
        <br>
        
        <el-input placeholder="请输入您收到的6位验证码"
                  v-model="verifyCode"
                  @keyup.enter.native="VerifyVerificationCode">
          <template slot="append">
            <el-button
              :disabled="!disabled"
              style="float: right"
              @click="VerifyVerificationCode">
              立即验证
            </el-button>
          </template>
        </el-input>
        
        
        <el-col v-show="showSuccess">
          <el-result icon="success" title="验证成功" subTitle="请点击下一步进行操作">
          </el-result>
        </el-col>
        
        <el-col v-show="showError">
          <el-result icon="error" title="验证失败" subTitle="验证码错误">
          </el-result>
        </el-col>
      
      </div>
      
      
      <el-form
        v-show="index === 2"
        style="margin: 50px auto; width: 500px;"
        :model="ruleForm"
        status-icon
        :rules="rules"
        ref="ruleForm"
        label-width="100px"
        class="demo-ruleForm">
        
        <el-form-item label="密码" prop="pass">
          <el-input type="password" v-model="ruleForm.pass" autocomplete="off"></el-input>
        </el-form-item>
        
        <el-form-item label="确认密码" prop="checkPass">
          <el-input type="password" v-model="ruleForm.checkPass" autocomplete="off"></el-input>
        </el-form-item>
        
        
        <el-form-item>
          <el-button type="primary" @click="submitForm">提交</el-button>
          <el-button @click="resetForm()">重置</el-button>
        </el-form-item>
      </el-form>
      
      <button v-show="showNext"
              @click="nextStep"
              class="btn btn-primary"
              style="float: right;margin-top: 50px">下一步
      </button>
    </div>
  </div>
</template>

<script>
import {userChangePWDByEmail, isCode, changePassword} from '@/api/user/user'

export default {
  name: "ChangePwd",
  data() {
    this.validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'));
      } else {
        if (value.length < 8) {
          callback(new Error('密码必须大于8位'));
        }
        if (this.ruleForm.checkPass !== '') {
          this.$refs.ruleForm.validateField('checkPass');
        }
        callback();
      }
    };
    this.validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'));
      } else if (value !== this.ruleForm.pass) {
        callback(new Error('两次输入密码不一致!'));
      } else {
        callback();
      }
    };
    return {
      index: 1,
      validatePass: '',
      validatePass2: '',
      showNext: false,
      
      showTime: false,
      count: 0,  // 初始化次数
      disabled: false, // 按钮是否禁用
      timer: null,
      showSuccess: false,
      showError: false,
      
      email: '',
      uid: this.$store.state.user.userInfo.uid,
      verifyCode: '', // 验证码的内容
      
      ruleForm: {
        pass: '',
        checkPass: '',
      },
      rules: {
        pass: [
          {validator: this.validatePass, trigger: 'blur'}
        ],
        checkPass: [
          {validator: this.validatePass2, trigger: 'blur'}
        ]
      }
    };
  },
  
  methods: {
    nextStep() {
      this.index++;
      if (this.index === 2) {
        this.showNext = false;
      }
    },
    
    resetForm() {
      this.ruleForm.pass = ''
      this.ruleForm.checkPass = ''
    },
    
    submitForm() {
      if (this.ruleForm.pass !== this.ruleForm.checkPass) {
        this.$message({
          showClose: true,
          message: '两次密码输入不一致',
          type: 'error'
        });
        this.resetForm()
      } else {
        changePassword(this.ruleForm.checkPass).then((res) => {
          if (res.data.code === 200) {
            this.$message({
              showClose: true,
              message: '修改成功',
              type: 'success'
            });
          } else {
            this.$message({
              showClose: true,
              message: '修改失败, 未知原因',
              type: 'error'
            });
          }
        })

        this.$router.push('/user-space/space-info')
      }
    },
    
    getVerify() {
      // 验证邮箱是否合法
      if (!/^([a-zA-Z\d][\w-]{2,})@(\w{2,})\.([a-z]{2,})(\.[a-z]{2,})?$/.test(this.email)) {
        this.$message.error("请填写正确的邮箱号");
      } else {
        this.showTime = true;
        this.disabled = true;
        // 更改倒计时时间
        this.showTime = true
        const TIME_COUNT = 60
        if (!this.timer) {
          this.count = TIME_COUNT
          this.show = false
          this.timer = setInterval(() => {
            if (this.count > 0 && this.count <= TIME_COUNT) {
              this.count--
            } else {
              this.show = true
              clearInterval(this.timer) // 清除定时器
              this.showTime = false
              this.timer = null
            }
          }, 1000);
        }
        userChangePWDByEmail(this.email).then((res) => {
          if (res.data.code === 200) {
            this.$notify({
              title: 'SUCCESS',
              message: '发送成功,请注意接收' +
                '\r\n5分钟内有效,请勿泄露或转发他人',
              type: 'success'
            });
          } else {
            this.$message({
              showClose: true,
              message: '发送失败',
              type: 'error'
            });
          }
        })
      }
    },
    
    VerifyVerificationCode() {
      if (this.verifyCode === '') {
        this.$message({
          showClose: true,
          message: '验证码不能为空',
          type: 'error'
        });
      }
      
      if (this.verifyCode.length > 6) {
        this.$message({
          showClose: true,
          message: '验证码不符合规范',
          type: 'error'
        });
      }
      
      isCode(this.verifyCode).then((res) => {
        if (res.data.code === 200) {
          this.showSuccess = true;
          this.showNext = true;
        } else {
          this.$message({
            showClose: true,
            message: '验证码错误',
            type: 'error'
          });
        }
      })
    }
  },
}
</script>