<template>
  <div>
    <img :src="Error404Img" alt="404 NOT FOUND" width="950" height="534" style="margin-left: 280px;margin-top: 30px;">
  </div>
</template>

<script>
export default {
  name: "Error_404.vue",
  data() {
    return {
      Error404Img: require('@/assets/img/404场景.svg')
    }
  }
}
</script>

<style scoped>

</style>