<template>
  <div class="container" style="background-color: #f2f2f2;border-radius: 18px">
    <h3 class="q-parse">编程题解析</h3>
    <h5 style="float: right">{{getTime2(new Date)}}</h5>
    <br>
    <div style="clear: both"></div>
    <!--编程题-->
    <div class="card" style="width: 468px;height: 400px;float: left;overflow-y: scroll">
      <div class="card-body">
        <h6 class="card-title">{{ aQuestion.title }}</h6>
        <span style="font-size: 10px">难度: </span>
        <el-tag>{{ aQuestion.level }}</el-tag>
        <span style="font-size: 10px;margin-left: 10px;">类型: </span>
        <el-tag>{{ aQuestion.language }}</el-tag>
        <!--题目内容区域-->
        <p class="card-text" style="font-size: 16px;margin-top: 10px;">{{ aQuestion.question }}</p>
        <h6 style="color: #6c757d">示例1</h6>
        <!--示例区域 bgc: #f2f3f4-->
        <div class="top-example">
          {{ aQuestion.exampleOne }}
        </div>
        <h6 style="color: #6c757d">示例2</h6>
        <div class="top-example">
          {{ aQuestion.exampleTwo }}
        </div>
      </div>
    </div>
    
    <IDE code-mode="text/x-java"
         :init-code="aQuestion.rightAnswer"
         style="width: 788px; float: right;"
         @getCodeAnswer="getCodeAnswer"/>
    <div style="clear: both"></div>
    <br>
    <br>
    
    <!--选择题-->
    <div>
      <h3 class="q-parse">选择题解析</h3>
      <div class="card" style="width: 25rem;height: 450px;float: left;overflow-y: scroll">
        <div class="card-body">
          <span style="font-size: 10px">难度: </span>
          <el-tag>{{ bQuestion.level }}</el-tag>
          <span style="font-size: 10px;margin-left: 10px;">类型: </span>
          <el-tag>{{ bQuestion.type }}</el-tag>
          <div class="ueditor">
            <mavon-editor
              class="markdown-body"
              v-highlight ref="md"
              codeStyle="github"
              :ishljs="true"
              v-html="codeDataHtml">
            </mavon-editor>
          </div>
        </div>
      </div>
      
      <div style="border: 1px solid #6c757d; border-radius: 10px;height: 450px;overflow-y: scroll">
        <p class="card-text" style="display: inline-block;margin-left: 20px;">{{ bQuestion.question }}</p>
        <br>
        <el-radio v-model="twoAnswer" class="el-radio" label="A" border>{{ bQuestion.aanswer }}</el-radio>
        <br>
        <el-radio v-model="twoAnswer" class="el-radio" label="B" border>{{ bQuestion.banswer }}</el-radio>
        <br>
        <el-radio v-model="twoAnswer" class="el-radio" label="C" border>{{ bQuestion.canswer }}
        </el-radio>
        <br>
        <el-radio v-model="twoAnswer" class="el-radio" label="D" border>{{ bQuestion.danswer }}
        </el-radio>
        <br>
        <div style="padding: 10px;">
          <el-tag type="success">解析:</el-tag>
          &nbsp;&nbsp;
          {{ bQuestion.rightAnswer }}
        </div>
      </div>
    </div>
    <div style="clear: both"></div>
    <br>
    <br>
    
    <!--解答题解析-->
    <h3 class="q-parse">解答题解析: {{bQuestion.question}}</h3>
    <mavon-editor
      class="markdown-body"
      v-highlight ref="md"
      code-style="github"
      :ishljs="true"
      v-html="CcodeDataHtml">
    </mavon-editor>
    <div style="clear: both"></div>
    <br>
    <br>
  </div>
</template>

<script>
import {getAllTopic} from "@/api/topic/topic";
import {marked} from "marked";
import IDE from "@/pages/heartfelt-content/training/IDE.vue";
import {getTime2} from "@/utils/DateFormatting";

export default {
  name: "QuestionParse",
  components: {
    IDE
  },
  data() {
    return {
      codeDataHtml: '',
      CcodeDataHtml:'',
      oneAnswer: '',
      twoAnswer: 'A',
      codeDataMarkDown: '',
      aQuestion: {},
      bQuestion: {},
      cQuestion: {}
    }
  },
  created() {
    getAllTopic().then((res) => {
      this.aQuestion = res.data.data.a
      this.bQuestion = res.data.data.b
      this.cQuestion = res.data.data.c
    })
    // 延时加载
    setTimeout(() => {
      this.markdownChangeHtml()
    }, 500)
  },
  methods: {
    getTime2,
    markdownChangeHtml() {
      // 将markdown转化为html
      this.codeDataHtml = marked(this.bQuestion.exampleCode)
      this.CcodeDataHtml = marked(this.cQuestion.rightAnswer)
    },
    // 组件间通信的方法
    getCodeAnswer(code) {
      this.oneAnswer = code
    },
  }
}
</script>

<style scoped>
.el-radio {
  margin: 10px;
}
.top-example {
  padding: 10px;
  background-color: #f2f3f4;
  font-size: 13px;
  border-radius: 10px;
  margin-top: 10px;
}

.q-parse {
  padding: 10px;
  margin-top: 20px;
  text-align: center;
  border-radius: 10px;
  background-color: #f3baba;
}

</style>