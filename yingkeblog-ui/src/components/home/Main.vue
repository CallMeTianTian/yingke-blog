<template>
  <div class="container">
    <div class="main">
      <content-left/>
    </div>
    <div style="clear: both"></div>
    <Training/>

  </div>
</template>

<script>
import Todo from "@/pages/heartfelt-content/ShowTodo.vue";
import ContentLeft from "@/pages/heartfelt-content/HomePageText.vue";
import Training from "@/pages/heartfelt-content/training/Training.vue";

export default {
  name: "Main",
  components: {
    Todo,
    ContentLeft,
    Training
  }
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

.main {
  display: flex;
  justify-content: center;
  flex: auto;
}
</style>
