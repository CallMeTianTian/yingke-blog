<template>
  <div>
    <div class="alert alert-secondary" role="alert"
         style="height: 135px;"
         v-for="mf in myFollow" :key="mf.uid">
      <img :src="mf.headurl" @click="goto(mf.uid)" style="float: left;cursor:pointer;" width="100" height="100"
           class="img-thumbnail"
           alt="MyFollowImg">
      <div style="float:left;margin-left: 30px;margin-top: 3px">
        <el-descriptions class="margin-top" :column="2" :contentStyle="CS" :label-style="LS" border>
          <template slot="extra">
          </template>
          
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-user"></i>
              用户名
            </template>
            {{ mf.username }}
          </el-descriptions-item>
          
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-mobile-phone"></i>
              邮箱号码
            </template>
            {{ mf.email }}
          </el-descriptions-item>
          
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-tickets"></i>
              擅长的语言
            </template>
            <el-tag size="small" v-for="(item,index) in mf.label" :key="index">{{ item }}</el-tag>
          </el-descriptions-item>
        
        
        </el-descriptions>
      </div>
    </div>
  </div>
</template>

<script>
import {getMyLove} from "@/api/user/follow";

export default {
  name: "MyFollow",
  data() {
    return {
      CS: {
        'min-width': '360px',   //最小宽度
        'word-break': 'break-all'  //过长时自动换行
      },
      LS: {
        'font-weight': '600',
        'height': '40px',
        'min-width': '110px',
        'word-break': 'keep-all'
      },
      /*[
        {
          "uid": 1,
          "username": "TianTianAdmin",
          "email": "1979214069@qq.com",
          "headurl": "https://my-web-oss.oss-cn-beijing.aliyuncs.com/daf57086-8471-4867-b5b0-bfb9698eba58.png",
          "label": [
            "Java",
            "Javascript",
            "Python"
          ]
       ...
      ]*/
      myFollow: []
    }
  },
  created() {
    getMyLove().then((res) => {
      this.myFollow = res.data.data
    })
  },
  methods:{
    goto(uid){
      console.log(uid)
      let routeData = this.$router.resolve({
        path: "/user",
        query: {
          uid
        }
      });
      window.open(routeData.href, '_blank');
    }
  }
}
</script>

<style scoped>

</style>