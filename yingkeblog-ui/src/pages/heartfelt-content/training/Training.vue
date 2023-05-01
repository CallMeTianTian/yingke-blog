<template>
  <div class="container">
    <!--编程题-->
    
    <h4 class="mt-3" style="float: left;">每日刷题</h4>
    
    <el-tabs v-model="activeName" type="card" style="float: right;" class="mt-3" @tab-click="handleClick">
      <el-tab-pane :disabled="isAnswer" :label="answerForm.programmingAnswer === '' ?'编程题':'编程题✔️'"
                   name="first"></el-tab-pane>
      <el-tab-pane :disabled="isAnswer" :label="answerForm.chooseAnswer === '' ?'选择题':'选择题✔️'" name="second"></el-tab-pane>
      <el-tab-pane :disabled="isAnswer" :label="answerForm.jQuestionAnswer === ''?'解答题':'解答题✔️'" name="third"></el-tab-pane>
    </el-tabs>
    <div style="clear: both"></div>
    
    <!--编程题-->
    <div v-show="index === 0" class="card" style="width: 468px;height: 400px;float: left;overflow-y: scroll">
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
    <IDE v-show="index === 0" code-mode="text/x-java"
         style="width: 788px; float: right;"
         @getCodeAnswer="getCodeAnswer"/>
    
    <!--选择题-->
    <div v-show="index === 1">
      <div class="card" style="width: 25rem;height: 22rem;float: left;overflow-y: scroll">
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
      
      <div style="border: 1px solid #6c757d; border-radius: 10px;height: 400px;">
        <p class="card-text" style="display: inline-block;margin-left: 20px;">{{ bQuestion.question }}</p>
        <br>
        <el-radio v-model="answerForm.chooseAnswer" class="el-radio" label="A" border>{{ bQuestion.aanswer }}</el-radio>
        <br>
        <el-radio v-model="answerForm.chooseAnswer" class="el-radio" label="B" border>{{ bQuestion.banswer }}</el-radio>
        <br>
        <el-radio v-model="answerForm.chooseAnswer" class="el-radio" label="C" border>{{ bQuestion.canswer }}
        </el-radio>
        <br>
        <el-radio v-model="answerForm.chooseAnswer" class="el-radio" label="D" border>{{ bQuestion.danswer }}
        </el-radio>
      </div>
    </div>
    
    <!--解答题-->
    <div v-show="index === 2" style="height: 400px">
      {{ cQuestion.question }}
      <el-input
        style="font-size: 15px"
        type="textarea"
        :autosize="{ minRows: 15}"
        placeholder="请输入你的答案"
        maxlength="1000"
        show-word-limit
        v-model="answerForm.jQuestionAnswer">
      </el-input>
      <el-button style="float: right;margin-top: 10px;" @click="submit">提交所有</el-button>
    </div>
    
    <!--成功提示-->
    <div v-show="index === 3">
      <el-result icon="success" title="今日刷题已完成" subTitle="请等待分数公布 并自行看解析">
        <template slot="extra">
          <el-button type="primary" size="medium" @click="gotoQuestionParse">查看解析</el-button>
        </template>
        <template slot="extra">
          <el-button type="primary" size="medium" @click="gotoWriteBlog()">发布解题</el-button>
        </template>
      </el-result>
    </div>
    
    <!--查看解析-->
    <div v-show="index === 4">
      <topic-analysis/>
    </div>
    <div style="clear: both;"></div>
    <br>
    <br>
    <br>
    <br>
  </div>
</template>

<script>
import IDE from "@/pages/heartfelt-content/training/IDE.vue";
import {marked} from "marked";
import 'mavon-editor/dist/markdown/github-markdown.min.css'
import {mavonEditor} from "mavon-editor";
import {addAnswerUser, getAllTopic, isAnswer} from "@/api/topic/topic";
import {getTime, getTime2} from "@/utils/DateFormatting";

export default {
  name: "Training",
  components: {
    IDE,
    mavonEditor,
  },
  created() {
    getAllTopic().then((res) => {
      this.aQuestion = res.data.data.a
      this.bQuestion = res.data.data.b
      this.cQuestion = res.data.data.c
    })
    setTimeout(() => {
      this.markdownChangeHtml()
    }, 200)
    // 查看用户是否已经做完每日一题
    isAnswer().then((res) => {
      if (res.data.code === 200) {
        this.index = 3
        this.programmingAnswer = '1'
        this.chooseAnswer = '1'
        this.jQuestionAnswer = '1'
        this.isAnswer = true
      }
    })
  }
  ,
  data() {
    return {
      index: 0, // 题目索引
      codeDataHtml: '',
      isAnswer: false,
      codeDataMarkDown: '',
      activeName: 'first',
      aQuestion: {},
      bQuestion: {},
      cQuestion: {},
      answerForm: {
        programmingAnswer: '',
        chooseAnswer: '',
        jQuestionAnswer: '',
      }
    }
  },
  computed: {},
  methods: {
    // 将markdown转化为html展示给用户
    markdownChangeHtml() {
      this.codeDataHtml = marked(this.bQuestion.exampleCode)
    },
    handleClick(event) {
      this.index = event.index * 1
    },
    getCodeAnswer(code) {
      this.answerForm.programmingAnswer = code
    },
    submit() {
      // 非空校验
      if (this.answerForm.programmingAnswer === '') {
        this.$message.error("编程题答案不能为空");
        return
      }
      if (this.answerForm.chooseAnswer === '') {
        this.$message.error("选择题题答案不能为空");
        return
      }
      if (this.answerForm.jQuestionAnswer.length < 50) {
        this.$message.error("解答题题答案不能字数不能少于50字");
        return
      }
      addAnswerUser(this.answerForm).then((res) => {
        if (res.data.code === 200) {
          this.$message.success("提交成功");
          this.index = 3
        } else {
          this.$message.error("提交失败");
        }
      })
    },
    // 跳转到写博客页面并带上参数
    gotoWriteBlog() {
      this.$router.replace({
        path: '/blog/write-article',
        query: {
          title: '今日解题' + getTime2(new Date),
          label: '解题',
          context: '# 我的解题思路如下\n' +
            '\n' +
            '1. 编程题\n' +
            '\n' +
            '   \n' +
            '\n' +
            '2. 选择题\n' +
            '\n' +
            '\n' +
            '\n' +
            '3. 解答题',
        }
      })
    },
    gotoQuestionParse() {
      // 跳转到题目解析页面
      this.$router.replace('/question-parse')
    }
  },
}
</script>

<style scoped lang="css">
.top-example {
  padding: 10px;
  background-color: #f2f3f4;
  font-size: 13px;
  border-radius: 10px;
  margin-top: 10px;
}

.ueditor {
  margin-top: 20px;
}

.el-radio {
  margin: 20px;
}
</style>