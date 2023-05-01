<template>
  <div class="container">
    <div class="row align-items-start">
      <button class="btn btn-primary col" @click="newInfoDialogFormVisible = true">New</button>
      
      <div class="col-auto w-100 bgc scroll bg-light border rounded-3" style="margin-top: 10px">
        <table class="table table-striped mb-5 mt-2">
          <thead>
          <tr>
            <th scope="col"></th>
            <th scope="col">课程名称</th>
            <th scope="col">机构</th>
            <th scope="col">入口</th>
            <th scope="col">进度</th>
            <th scope="col">操作</th>
          </tr>
          </thead>
          <tbody>
          <tr v-for="(item,index) in classList" :key="item.id">
            <th scope="row">{{ index + 1 }}</th>
            <td>{{ item.className }}</td>
            <td>{{ item.institution }}</td>
            <td>
              <button class="btn btn-primary btn-sm"><a :href="item.url" class="text-white"
                                                        target="_blank"
                                                        style="text-decoration: none">继续学习</a>
              </button>
            </td>
            <td class="text-sm-center">{{ item.progress }}</td>
            <td>
              <button class="btn btn-outline-dark btn-sm me-2" @click="handleUpdate(item.id)">编辑</button>
              <button class="btn btn-outline-danger btn-sm" @click="remove(item.id)">删除</button>
            </td>
          </tr>
          </tbody>
        
        
        </table>
        
        <!--修改-->
        <el-dialog title="课程信息" :visible.sync="dialogFormVisible">
          <el-form :model="showBackendForm">
            <el-form-item label="课程名称" :label-width="formLabelWidth">
              <el-input v-model="showBackendForm.className" autocomplete="off" placeholder="请输入课程名称"></el-input>
            </el-form-item>
            <el-form-item label="机构/讲师" :label-width="formLabelWidth">
              <el-input v-model="showBackendForm.institution" autocomplete="off"
                        placeholder="请输入课程的机构/讲师"></el-input>
            </el-form-item>
            
            <el-form-item label="学习进度" :label-width="formLabelWidth">
              <el-row>
                <el-col :span="8">
                  <el-input v-model="updateFormMolecule" autocomplete="off"
                            placeholder="分子"></el-input>
                </el-col>
                
                <el-col :span="3">
                  <el-button @click="updateFormMolecule++"><i class="el-icon-plus"></i></el-button>
                </el-col>
                
                <el-col :span="8">
                  <el-input v-model="updateFormDenominator" autocomplete="off"
                            placeholder="分母">
                  </el-input>
                </el-col>
                
                <el-col :span="5">
                  <button type="button" class="btn btn-primary"
                          style="--bs-btn-padding-y: .25rem;
                         --bs-btn-padding-x: .5rem;
                          --bs-btn-font-size: .75rem;
                          margin-left: 15px">
                    {{ computedProgress }}
                  </button>
                </el-col>
              </el-row>
            </el-form-item>
            
            <el-form-item label="学习网址" :label-width="formLabelWidth">
              <el-input v-model="showBackendForm.url" autocomplete="off" placeholder="请输入该课程学习网址"></el-input>
            </el-form-item>
          </el-form>
          
          <div slot="footer" class="dialog-footer">
            <el-button @click="cancel">取 消</el-button>
            <el-button type="primary" @click="update">确 定</el-button>
          </div>
        
        </el-dialog>
        
        
        <!--新增-->
        <el-dialog title="新增学习数据" :visible.sync="newInfoDialogFormVisible">
          <el-form :model="newFormData">
            
            <el-form-item label="课程名称" :label-width="formLabelWidth">
              <el-input v-model="newFormData.className" autocomplete="off" placeholder="请输入课程名称"></el-input>
            </el-form-item>
            <el-form-item label="机构/讲师" :label-width="formLabelWidth">
              <el-input v-model="newFormData.institution" autocomplete="off"
                        placeholder="请输入课程的机构/讲师"></el-input>
            </el-form-item>
            
            <el-form-item label="学习进度" :label-width="formLabelWidth">
              <el-row>
                <el-col :span="8">
                  <el-input v-model="newFormMolecule" autocomplete="off"
                            placeholder="分子"></el-input>
                </el-col>
                
                <el-col :span="3">
                  <el-button @click="newFormMolecule++"><i class="el-icon-plus"></i></el-button>
                </el-col>
                
                <el-col :span="8">
                  <el-input v-model="newFormDenominator" autocomplete="off"
                            placeholder="分母">
                  </el-input>
                </el-col>
                
                <el-col :span="5">
                  <button type="button" class="btn btn-primary"
                          style="--bs-btn-padding-y: .25rem;
                         --bs-btn-padding-x: .5rem;
                          --bs-btn-font-size: .75rem;
                          margin-left: 15px">
                    {{ computedNewProgress }}
                  </button>
                </el-col>
              </el-row>
            </el-form-item>
            
            <el-form-item label="学习网址" :label-width="formLabelWidth">
              <el-input v-model="newFormData.url" autocomplete="off" placeholder="请输入该课程学习网址"></el-input>
            </el-form-item>
          </el-form>
          
          <div slot="footer" class="dialog-footer">
            <el-button @click="newInfoDialogFormVisible = false"><i class="el-icon-error">取 消</i></el-button>
            <el-button type="primary" @click="add()"><i class="el-icon-zoom-in"> 确 定</i>
            </el-button>
          </div>
        </el-dialog>
      </div>
    </div>
  </div>

</template>

<script>
import axios from "axios";
import {getAllCourseByUid} from "@/api/course/course";

export default {
  name: "StudyList",
  data() {
    return {
      classList: [],
      showBackendForm: {},
      dialogFormVisible: false,
      newInfoDialogFormVisible: false,
      formLabelWidth: '100px',
      
      newFormMolecule: 0, // 分子
      newFormDenominator: 0, // 分母
      
      updateFormMolecule: 0, // 分子
      updateFormDenominator: 0, // 分母
      
      
      newFormData: {
        uid: '1',
        className: '',
        institution: '',
        url: '',
        progress: '',
      }
    }
  },
  computed: {
    computedProgress() {
      return this.updateFormMolecule.toString() + ' / ' + this.updateFormDenominator.toString()
    },
    computedNewProgress() {
      return this.newFormMolecule.toString() + ' / ' + this.newFormDenominator.toString()
    }
  },
  created() {
    this.getAll();
  },
  methods: {
    
    add() {
      this.newFormData.progress = this.newFormMolecule.toString() + '/' + this.newFormDenominator.toString()
      console.log(this.newFormData)
      axios.post('http://localhost/course/add', this.newFormData).then(res => {
        console.log(this.newFormData)
        if (res.data.code === 200) {
          this.$notify({
            type: 'success',
            message: '新增成功!'
          });
          this.getAll()
          this.newInfoDialogFormVisible = false;
        } else {
          this.$notify({
            type: 'warning',
            message: '新增失败,该课程已经存在'
          });
        }
      }, err => {
        console.log(err)
      })
    },
    
    // 获取所有数据
    getAll() {
      getAllCourseByUid().then(response => {
        this.classList = response.data.data;
      })
    },
    
    update() {
      this.showBackendForm.progress = this.updateFormMolecule + '/' + this.updateFormDenominator
      axios.put('http://localhost/course/update', this.showBackendForm).then(response => {
        if (response.data.code === 2023) {
          this.dialogFormVisible = false
          this.getAll()
          this.resetForm()
          this.$notify({
            type: 'success',
            message: '更新成功'
          })
          this.getAll()
        }
      }).catch(() => {
        this.$notify({
          type: 'warning',
          message: '已取消操作'
        })
      });
    },
    // 新增数据
    // 根据id删除数据
    deleteById(id) {
      axios.delete(`http://localhost/course/${id}`).then(response => {
        if (response.data.code === 2023) {
          this.$notify({
            type: 'success',
            message: '删除成功!'
          });
          this.getAll()
        } else {
          this.$notify({
            type: 'info',
            message: '删除失败'
          })
        }
      }).catch(() => {
        this.$notify({
          type: 'info',
          message: '删除失败'
        })
      });
    },
    
    // 确认是否删除
    remove(id) {
      this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.deleteById(id)
      }).catch(() => {
        this.$notify({
          type: 'warning',
          message: '取消删除'
        })
        this.dialogFormVisible = false
      });
    },
    // 取消后执行的操作
    cancel() {
      this.dialogFormVisible = false
      this.resetForm()
      this.$notify.info("操作当前取消")
    },
    // 重置表单
    resetForm() {
      this.showBackendForm = {}
    },
    //弹出修改窗口 => 回显数据
    handleUpdate(id) {
      this.dialogFormVisible = true
      axios.get("http://localhost/course/" + id).then((res) => {
        this.showBackendForm = res.data.data[0]
        console.log(this.showBackendForm)
        let splitArr = res.data.data[0].progress.split('/');
        console.log(splitArr)
        this.updateFormMolecule = splitArr[0]
        this.updateFormDenominator = splitArr[1]
      })
    },
  },
}
</script>

<style scoped>
.bgc {
  width: 500px;
  height: 550px;
  border-radius: 25px;
  background-color: rgba(180, 182, 180, 0.1);
}

/*滚动条*/
.scroll {
  overflow-y: scroll;
}
</style>