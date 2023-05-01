<template>
  <div>
    <el-row>
      <el-col :span="18">
        <el-input
          style="width: 960px"
          type="text"
          placeholder="请输入文章标题"
          v-model="article.title"
          clearable
          maxlength="35"
          show-word-limit>
          <template slot="prepend">✒️标题</template>
        </el-input>
      </el-col>
      <el-col :span="6">
        <el-select
          style="padding-left: 15px;width: 290px"
          v-model="article.label"
          clearable
          default-first-option
          placeholder="请选择文章标签">
          <template slot="prefix">
                <span style="position: absolute;top: 5px;font-size: 18px">
                  📚
                </span>
          </template>
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-col>
    </el-row>
    <br>
    <hr>
    
    <mavon-editor
      v-model="article.context"
      ref="md"
      @imgAdd="handleEditorImgAdd"
      @imgDel="handleEditorImgDel"
      @change="change"
      class="my-editor"
    />
    <button class="btn btn-primary container mt-3 mb-5" @click="submit">提 交</button>
    <br>
  </div>
</template>

<script>
import {mavonEditor} from 'mavon-editor';
import 'mavon-editor/dist/css/index.css';       //引入组件的样式
import {addBlog} from "@/api/blog/blogApi";
import {Message} from "element-ui";
import {deleteImg, uploadImg} from "@/api/user/file";

export default {
  name: 'writeMarkdown',
  // 注册组件
  components: {
    mavonEditor,
  },
  
  data() {
    return {
      article: {
        title: '',
        label: '',
        context: '# :one:请开始你的创造:smiley:',
        html: ''     // 实时转化的html内容
      },
      options: [{value: 'Java', label: 'Java'}, {value: 'algorithm', label: 'algorithm'}, {
        value: 'essay', label: 'essay'
      }, {value: 'vue', label: 'vue'}, {value: 'css', label: 'css'}, {
        value: 'Python',
        label: 'Python'
      }, {value: 'Javascript', label: 'Javascript'}, {value: '解题', label: '解题'}],
    }
  },
  
  created() {
    let initData = this.$route.query;
    if (initData !== {} || initData !== undefined) {
      this.article.title = initData.title;
      this.article.label = initData.label
      this.article.context = initData.context
    }
  },
  
  methods: {
    // 所有操作都会被解析重新渲染
    change(value, render) {        //value为编辑器中的实际内容，即markdown的内容，render为被解析成的html的内容
      this.article.html = render;
    },
    
    // 提交
    submit() {                     //点击提交后既可以获取html内容，又可以获得markdown的内容，之后存入到服务端就可以了
      if (this.article.title === '') {
        this.$notify({
          title: '警告',
          message: '文章标题不能为空',
          type: 'warning'
        });
        return
      }
      if (this.article.label === '') {
        this.$notify({
          title: '警告',
          message: '文章标签不能为空',
          type: 'warning'
        });
        return;
      }
      addBlog(this.article).then((res) => {
        if (res.data.code === 200) {
          this.$notify({
            title: '成功',
            message: '文章提交成功 ! 请等待审核',
            type: 'success'
          });
          // 刷新当前页面
          setTimeout(() => {
            location.reload();
          }, 500)
        } else {
          this.$notify({
            title: '失败',
            message: '文章提交成功',
            type: 'error'
          });
        }
      })
    },
    handleEditorImgAdd(pos, $file) {
      let formData = new FormData()
      formData.append('file', $file)
      // 发送请求
      uploadImg(formData).then(res => {
        console.log(res.data)
        if (res.data.code === 200) {
          Message.success("上传成功")
          // 上传成功 后端传递图片地址回来
          let url = res.data.message
          let name = $file.name
          if (name.includes('-')) {
            name = name.replace(/-/g, '')
          }
          let content = this.article.context
          // 第二步.将返回的url替换到文本原位置![...](0) -> ![...](url)  这里是必须要有的
          if (content.includes(name)) {
            let oStr = `(${pos})`
            let nStr = `(${url})`
            let index = content.indexOf(oStr)
            let str = content.replace(oStr, '')
            let insertStr = (source, start, newStr) => {
              return source.slice(0, start) + newStr + source.slice(start)
            }
            this.article.context = insertStr(str, index, nStr)
          }
        } else {
          Message.error("上传失败")
        }
      })
    },
    handleEditorImgDel(pos) {
      let fileName = pos[1]['name']
      console.log("删除图片的名称为: ", fileName)
      deleteImg(fileName).then((res) => {
        if (res.data.code === 200) {
          Message.success("删除图片成功");
          // 删除图片对应markdown内容(替换为空)
          let removeFileName = fileName.replace("-","");
          this.article.context.replace(`![${removeFileName}](http://localhost/common/download/${fileName})`,"")
        } else {
          Message.error("删除图片失败")
        }
      })
    },
    
  },
}
</script>
<style scoped lang="css">
.my-editor {
  margin: 5px auto;
  min-height: 530px;
  max-height: 800px;
  width: 100%;
}
</style>

