<template>
  <div>
    <NavMenu/>
    <div class="container">
      
      <transition mode="out-in" enter-active-class="animate__animated animate__bounceInLeft"
                  leave-active-class="animate__animated animate__backOutRight">
        <router-view/>
      
      </transition>
    </div>
  </div>
</template>

<script>
import 'animate.css'
import NavMenu from "@/pages/blog/handers/NavMenu.vue";
import MusicPlayback from "@/pages/blog/MusicPlayback/MusicPlayback.vue";

export default {
  name: "discuss",
  components: {
    NavMenu,
    MusicPlayback
  },
  data() {
    return {
      content: ''
    }
  },
  mounted() {
    // 绑定自定义事件
    // this.$bus.$on('UserInput', (content)=>{
    //   this.content = content
    //   console.log('从Markdown组件中接收到数据', content);
    // })
  }
}
</script>

<style scoped>
.when-active {
  border-bottom: 2px black solid
}
</style>