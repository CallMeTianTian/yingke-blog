<template>
  <div>
    <codemirror ref="myCm" :value="code" v-model="code" :options="cmOptions" @change="codeChange" @ready="onReady">
    </codemirror>
    
    <el-select v-model="cmOptions.theme"
               placeholder="切换主题"
               @change="setTheme"
               style="float: right;margin-top: 5px">
          <span slot="prefix">
             <el-tooltip content="更换主题">
      </el-tooltip>
      </span>
      <el-option v-for="(item,index) in supportThemes" :key="index" :label="item" :value="item">
      </el-option>
    </el-select>
    
    <button class="btn btn-secondary" style="float: right;margin-right: 20px;margin-top: 5px">
      开启代码提示功能
      <el-switch
        style="margin-bottom: 3px;"
        v-model="value"
        active-color="#409eff"
        inactive-color="#dcdfe6">
      </el-switch>
    </button>
  </div>
</template>

<script>
import {codemirror} from "vue-codemirror";
import 'codemirror/lib/codemirror.css'
import 'codemirror/theme/base16-dark.css'
import 'codemirror/mode/sql/sql'
import 'codemirror/mode/clike/clike.js'
import "codemirror/theme/eclipse.css";
import "codemirror/theme/blackboard.css";
import "codemirror/theme/cobalt.css";
import "codemirror/theme/ayu-mirage.css";
import "codemirror/theme/juejin.css";
import "codemirror/theme/duotone-light.css";
import "codemirror/theme/mdn-like.css";
import "codemirror/theme/paraiso-light.css";
import "codemirror/theme/idea.css";
import "codemirror/theme/material-darker.css";
import 'codemirror/addon/selection/active-line' //光标行背景高亮，配置里面也需要styleActiveLine设置为true

// 引入自动补全代码的功能
import "codemirror/addon/lint/lint.css";
import "codemirror/addon/lint/lint.js";
import "codemirror/addon/display/autorefresh";
import 'codemirror/addon/edit/closebrackets.js'
import 'codemirror/addon/hint/show-hint.js';
import 'codemirror/addon/hint/show-hint.css';
import 'codemirror/addon/hint/javascript-hint';
import 'codemirror/addon/hint/html-hint';
import 'codemirror/addon/hint/sql-hint';
import "codemirror/addon/edit/closebrackets.js";
import "codemirror/addon/edit/closetag.js";
import "codemirror/addon/edit/matchtags.js";
import "codemirror/addon/edit/matchbrackets.js";


// 代码折叠功能
import 'codemirror/addon/fold/foldgutter.css'
import 'codemirror/addon/fold/foldcode'
import 'codemirror/addon/fold/foldgutter'
import 'codemirror/addon/fold/brace-fold'
import 'codemirror/addon/fold/comment-fold'
import 'codemirror/addon/fold/markdown-fold'
import 'codemirror/addon/fold/xml-fold'
import 'codemirror/addon/fold/indent-fold'


import 'codemirror/addon/search/match-highlighter'

export default {
  name: "IDE",
  props: ['codeMode', 'initCode'],
  data() {
    return {
      // 默认有个main方法
      code: `public static void main(String args[]){
  
}`,
      value: true,
      cmOptions: {
        mode: this.codeMode || 'text/x-java', // 代码模式 默认为Java
        theme: "mdn-like",  // 设置主题 默认为 mdn-like
        autofocus: true,    // 自动获取焦点
        autocorrect: true,  // 自动更正
        spellcheck: true,  // 拼写检查
        lint: true,  // 检查格式
        tabSize: 4, // 制表符
        indentUnit: 2, // 缩进位数
        lineNumbers: true, // 显示行号
        smartIndent: true,
        autoCloseBrackets: true,
        matchBrackets: true, // 自动突出显示匹配的括号
        styleActiveLine: true,//line选择是是否加亮
        // 代码折叠
        gutters: [
          "CodeMirror-lint-markers",
          "CodeMirror-linenumbers",
          "CodeMirror-foldgutter"
        ],
        foldGutter: true, // 启用行槽中的代码折叠
        highlightSelectionMatches: {
          minChars: 2,
          trim: true,
          style: "matchhighlight",
          showToken: false
        },
      },
      supportThemes: ['eclipse', 'idea', 'material-darker', 'blackboard', 'mdn-like', 'paraiso-light', 'base16-dark', 'cobalt', 'ayu-mirage', 'juejin', 'duotone-light'],
    }
  },
  created() {
    let theme = localStorage.getItem("TTtheme");
    if (theme !== null) {
      this.cmOptions.theme = theme
    }
    
    
    setTimeout(() => {
      console.log("this.initCode", this.initCode)
      if (this.initCode !== undefined) {
        this.code = this.initCode
      }
    }, 500)
    
    
  },
  methods: {
    onReady(cm) {
      if (this.value) {
        cm.on('keypress', () => {
          cm.showHint({completeSingle: false})
        })
      }
    },
    codeChange() {
      this.$emit("getCodeAnswer", this.code);
    },
    setTheme(val) {
      localStorage.setItem("TTtheme", val)
    }
  },
  components: {
    codemirror,
  }
  
}
</script>