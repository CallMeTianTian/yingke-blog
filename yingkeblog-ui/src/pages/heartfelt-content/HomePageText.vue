<template>
  <div>
    
    <div class="bg-light border rounded-3 scroll box-shadow"
         style="height: 250px">
      <div class="text-center mt-2"><h5 class="animate__animated animate__bounceIn">每日一句</h5><span
        style="float: right;padding-right: 20px">{{ MyDate }}</span></div>
      <br>
      <p style="text-indent: 2em;padding: .5rem; line-height: 2.7rem">
        {{ sentence }}
      </p>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import {getTime} from '@/utils/DateFormatting'
import 'animate.css'

export default {
  name: "HomePageText",
  data() {
    return {
      sentence: '',
      MyDate:getTime(new Date())
    }
  },
  created() {
    axios.get('http://localhost/word').then((res) => {
      this.sentence = res.data.data.word;
    })
  },
  mounted() {
  },
  methods:{

  }
}
</script>

<style lang="css">
.box-shadow {
  box-shadow: 5px 5px 30px rgba(1, 1, 1, 0.1)
}


</style>
