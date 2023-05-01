<template>
  <div class="container">

    <div class="list-group scroll mt-0 pt-1"
         style="height: 17.2rem;width: 13.5rem;">
    
      <div v-for="(course,index) in courses" :key="index">
        <label class="list-group-item d-flex gap-3">
          <input class="form-check-input flex-shrink-0" type="checkbox" v-model="course.state" checked
                 style="font-size: 1.375em;">
          <span class="pt-1 form-checked-content">
      <strong>{{ course.className }}</strong>
      <small class="d-block text-muted">
        <svg class="bi me-1" width="1em" height="1em"><use xlink:href="#calendar-event"/></svg>
        {{ course.classTime }}
      </small>
    </span>
        </label>
      </div>
    </div>

  </div>
</template>

<script>
export default {
  name: "todo",
  data() {
    return {
      // 模拟数据
      courses: [
        {
          id: '0',
          className: '高等数学',
          classTime: '1:00-2:00pm',
          state: true
        },
        {
          id: '0',
          className: '大学英语',
          classTime: ' 2:00–2:30pm',
          state: true
        },
        {
          id: '0',
          className: '线性代数',
          classTime: '1:00-2:00pm',
          state: false
        },
        {
          id: '0',
          className: '数据结构与算法',
          classTime: '1:00-2:00pm',
          state: false
        },
        {
          id: '0',
          className: '大学语文',
          classTime: '1:00-2:00pm',
          state: false
        }
      ],
      courseInfo: {
        id: '',
        className: '',
        classTime: '',
        state: ''
      }
    }
  },
  methods: {
    add() {
      this.courses.push(this.courseInfo)
    }
  }
}
</script>

<style scoped>
/*滚动条*/
.scroll {
  overflow-y: scroll;
}
</style>