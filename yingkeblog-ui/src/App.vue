<template>
  <div id="container">
    <MyHeader v-if="this.$route.path !== '/login' && this.$route.path !== '/register' && this.$route.path.indexOf('/answer')!== 0 && this.$route.path.indexOf('/examMsg') !== 0"/>
    <router-view></router-view>
    <!--<my-footer/>-->
  </div>
</template>

<script>
import MyHeader from "@/components/home/Header/Header.vue";
// import MyFooter from "@/components/home/Footer.vue";
export default {
  name: 'App',
  data() {
    return {
      animate: '',
      path: ''
    }
  },
  components: {
    MyHeader,
    // MyFooter
  },
  
}
</script>

<style lang="css">
html, body {
  height: 100vh;
}
#container {
  min-height: 100%;
  position: relative
}

@font-face {
  font-family: HarmonyOS_Sans_SC_Medium;
  font-style: normal;
  font-display: swap;
  src: url('./assets/woff/HarmonyOS_Sans_SC_Medium.woff2') format('woff2'),
  url('./assets/woff/HarmonyOS_Sans_SC_Medium.woff') format('woff');
}

* {
  font-family: HarmonyOS_Sans_SC_Medium;
}

.Router {
  position: absolute;
  left: 0;
  right: 0;
}

.slide-right-enter-active,
.slide-right-leave-active,
.slide-left-enter-active,
.slide-left-leave-active {
  will-change: transform;
  transition: all 300ms;
  position: absolute;
}

.slide-right-enter {
  opacity: 0;
  transform: translate3d(-100%, 0, 0);
}

.slide-right-leave-active {
  opacity: 0;
  transform: translate3d(100%, 0, 0);
}

.slide-left-enter {
  opacity: 0;
  transform: translate3d(100%, 0, 0);
}

.slide-left-leave-active {
  opacity: 0;
  transform: translate3d(-100%, 0, 0);
}


/*滚动条*/

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
