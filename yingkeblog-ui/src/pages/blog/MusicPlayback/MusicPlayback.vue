<template>
  <div>
    <aplayer :music="audio[0]" :list="audio" :lrcType="3" :showlrc="false"   :listFolded="true" :float="false" class="aplayer"/>
  </div>
</template>

<script>
import aplayer from "vue-aplayer";

export default {
  name: "MusicPlayback",
  components: {aplayer},
  data() {
    return {
      // 音频列表
      audio: [
        {
          title: '玻璃之情',
          artist: '张国荣',
          url: 'https://www.xzmp3.com/down/fc7fd161bfc3.mp3',
          pic: 'https://p1.music.126.net/bqq6DITA5nj_yd_i6dsiTA==/109951166225429773.jpg?param=130y130',
        },
        {
          title: '蒲空英的约定',
          artist: '黄叶',
          url: 'https://edu-guil-1010.oss-cn-beijing.aliyuncs.com/%E8%92%B2%E5%85%AC%E8%8B%B1%E7%9A%84%E7%BA%A6%E5%AE%9A.m4a',
          pic: "https://edu-guil-1010.oss-cn-beijing.aliyuncs.com/QQ%E5%9B%BE%E7%89%8720200713203148.jpg",
          lrc: '[00:00.00] (,,•́ . •̀,,) 刚刚开始学钢琴弹的hhhh',
        },
        {
          title: '无畏',
          artist: '马頔',
          url: 'https://edu-guil-1010.oss-cn-beijing.aliyuncs.com/%E9%A9%AC%E9%A0%94%20-%20%E6%97%A0%E7%95%8F.flac',
          pic: 'https://edu-guil-1010.oss-cn-beijing.aliyuncs.com/%E4%BB%A5%E5%AE%B6%E4%B9%8B%E5%90%8D.jpg', // prettier-ignore
          lrc: '[00:00.00] (,,•́ . •̀,,) 抱歉，当前歌曲暂无歌词',
        },
        {
          title: '蓝乐',
          artist: '白纸',
          url: 'https://edu-guil-1010.oss-cn-beijing.aliyuncs.com/%E8%93%9D%E4%B9%90%20-%20%E7%99%BD%E7%BA%B8.flac',
          pic: "https://edu-guil-1010.oss-cn-beijing.aliyuncs.com/-1992181439.jpg",
          lrc: '[00:00.00] (,,•́ . •̀,,) 抱歉，当前歌曲暂无歌词',
        },
      ],
    }
  }
  
}
</script>

<style scoped>
.aplayer {
  width: 300px;
}
</style>