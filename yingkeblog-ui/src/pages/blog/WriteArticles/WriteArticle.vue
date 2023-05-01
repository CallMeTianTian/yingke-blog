<template>
  <div style="float: left;width: 1300px;height: 600px;" class="container-fluid">
    <wirter-markdown/>
  </div>
</template>

<script>
import WirterMarkdown from "@/pages/blog/mavon-editor/WirterMarkdown.vue";

export default {
  name: "WriteArticle",
  components:{
    WirterMarkdown
  }
}
</script>

<style scoped>

</style>