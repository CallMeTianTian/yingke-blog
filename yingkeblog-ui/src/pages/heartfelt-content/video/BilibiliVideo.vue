<template>
  <div class="bg-light border rounded-3">
    <!--视频播放区域-->
    <div class="container">
      <div>
        <h5 class="mt-1">"从现在开始 我将追寻你的名字"</h5>
        <vue-core-video-player class="video-container" style="float: left" :src="videoSource" :cover="cover"
                               :title="title" loop="false"/>
      </div>
      
      <div style="clear: both"></div>
      
      <div class="dropdown-menu d-block mx-1 rounded-3 shadow"
           style="position: absolute;width: 500px; height: 481px;overflow-y: scroll; top: 150px; right: 70px">
        <form class="p-2 mb-2 bg-light border-bottom">
          <input type="search" class="form-control" autocomplete="false" placeholder="搜索视频">
        </form>
        
        <ul class="list-unstyled">
          <li>
            <a class="dropdown-item d-flex align-items-center gap-2 py-2" href="#">
              <span class="d-inline-block bg-success rounded-circle p-1"/>
              从现在开始，我将追寻你的名字
            </a>
          </li>
          
          <li>
            <a class="dropdown-item d-flex align-items-center gap-2 py-2" href="#">
              <span class="d-inline-block bg-primary rounded-circle p-1"/>
              将过去和羁绊全部丢弃，不要吝啬那为了梦想流下的泪水
            </a>
          </li>
          
          <li>
            <a class="dropdown-item d-flex align-items-center gap-2 py-2" @click="gotoXjcy()" href="#">
              <span class="d-inline-block bg-success rounded-circle p-1"/>
              只有爱可以穿越时空
            </a>
          </li>
          
          <li>
            <a class="dropdown-item d-flex align-items-center gap-2 py-2" href="#">
              <span class="d-inline-block bg-primary rounded-circle p-1"/>
              不要轻易地口出狂言，那样只会透露出你的软弱
            </a>
          </li>
          
          <li>
            <a class="dropdown-item d-flex align-items-center gap-2 py-2" href="#">
              <span class="d-inline-block bg-success rounded-circle p-1"/>
              不管怎样，明天是新的一天!
            </a>
          </li>
          
          <li>
            <a class="dropdown-item d-flex align-items-center gap-2 py-2" href="#">
              <span class="d-inline-block bg-primary rounded-circle p-1"/>
              那些伟大的成就不能只属于过去 因为我们的命运就在太空
            </a>
          </li>
          
          <li>
            <a class="dropdown-item d-flex align-items-center gap-2 py-2" href="#">
              <span class="d-inline-block bg-success rounded-circle p-1"/>
              那不是山 那是海浪
            </a>
          </li>
          
          <li>
            <a class="dropdown-item d-flex align-items-center gap-2 py-2" href="#">
              <span class="d-inline-block bg-primary rounded-circle p-1"/>
              我爬得越高，眼前的风景便愈发迷人
            </a>
          </li>
          
          
          <li>
            <a class="dropdown-item d-flex align-items-center gap-2 py-2" href="#">
              <span class="d-inline-block bg-success rounded-circle p-1"/>
              生活就像一盒巧克力 你永远不知道下一块会是什么味道
            </a>
          </li>
          
          
          <li>
            <a class="dropdown-item d-flex align-items-center gap-2 py-2" href="#">
              <span class="d-inline-block bg-primary rounded-circle p-1"/>
              人并非生来就伟大，而是越活越伟大。
            </a>
          </li>
        </ul>
      
      </div>
    </div>
  </div>
</template>

<script>

export default {
  name: "BilibiliVideo",
  computed: {},
  data() {
    return {
      videoSource: [{
        src: 'https://media.vued.vanthink.cn/y2mate.com%20-%20sparkle_your_name_amv_K_7To_y9IAM_1080p.mp4',
        resolution: 1080
      }],
      cover: "https://img1.wxzxzj.com/vpc-example-cover-your-name-c.png",
      title: "你的名字",
      videoList: [
        "从现在开始 我将追寻你的名字"
      ]
    }
  },
  created() {
  
  },
  methods: {
    gotoXjcy() {
      this.videoSource = [{
        src: 'https://my-web-oss.oss-cn-beijing.aliyuncs.com/xjcy.mp4',
        resolution: 1080}]
      this.title = "只有爱可以穿越时空"
    }
  }
}
</script>

<style scoped lang="css">

.video-container {
  width: 800px;
  height: 500px;
}

/*.video-container {
  width: 800px;
  height: 500px;
}*/

.videoAbout {
  display: flex;
  justify-content: start;
  color: #9499A0
}

.videoAbout span {
  margin-right: 20px;
}

.video-container section h5 {
  margin: 0;
  padding: 0;
}

</style>