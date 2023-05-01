<template>
  <div id="footer">
    <footer class="d-flex flex-wrap justify-content-between align-items-center py-3 border-top">
      <p class="col-md-4 mb-0 text-muted">&copy; 2023 天天学习, Nice</p>
      <a href="#"
         class="col-md-4 d-flex align-items-center justify-content-center mb-3 mb-md-0 me-md-auto link-dark text-decoration-none">
        <span class="iconfont icon-lianshu me-2"></span>
        <span class="iconfont icon-tuitetwitter43 me-2"></span>
        <span class="iconfont icon-weixin me-2"></span>
      </a>

      <ul class="nav col-md-4 justify-content-end">
        <li class="nav-item"><a href="#" class="nav-link px-2 text-muted">Home</a></li>
        <li class="nav-item"><a href="#" class="nav-link px-2 text-muted">Features</a></li>
        <li class="nav-item"><a href="#" class="nav-link px-2 text-muted">Pricing</a></li>
        <li class="nav-item"><a href="#" class="nav-link px-2 text-muted">FAQs</a></li>
        <li class="nav-item"><a href="#" class="nav-link px-2 text-muted">About</a></li>
      </ul>
    </footer>
  </div>
</template>

<script>
export default {
  name: "MyFooter"
}
</script>

<style scoped>
footer {
  position: absolute;
  left: 120px;
  bottom: -10px;
  width: 80%;
}
</style>
