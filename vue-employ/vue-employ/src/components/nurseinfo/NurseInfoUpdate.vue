<template>
    <div>
        <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{ path: '/main' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item :to="{ path: '/nurseinfoList' }">护理管理</el-breadcrumb-item>
            <el-breadcrumb-item>修改</el-breadcrumb-item>
        </el-breadcrumb>
        <el-form ref="form" :rules="rules" :model="form" label-width="100px" class="content">
            <el-form-item label="护理项目" prop="name">
                <el-select v-model="form.name" placeholder="请选择护理项目">
                    <el-option :key="index" :label="item.name" :value="item.name" v-for="(item,index) in selNurseInfo"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="护理等级" prop="level">
                <el-select v-model="form.level">
                    <el-option label="一级" value="一级"></el-option>
                    <el-option label="二级" value="二级"></el-option>
                    <el-option label="三级" value="三级"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="护理价格">
                <el-input v-model="form.price"></el-input>
            </el-form-item>
            <el-form-item label="护理描述">
                <el-input v-model="form.descibe"></el-input>
            </el-form-item>
            <el-form-item label="是否增值">
                <el-select v-model="form.flag">
                    <el-option label="是" value="是"></el-option>
                    <el-option label="否" value="否"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="护理状态">
                <el-select v-model="form.status">
                    <el-option label="开启" value="开启"></el-option>
                    <el-option label="关闭" value="关闭"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="onSubmit('form')">确定</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
    import axios  from 'axios'
    import {HOST} from "../../common/config";
    export default {
        data(){
            return{
                selNurseInfo:[],
                selInfo(){
                    this.host = HOST
                    let url = `${HOST}/nurseinfo/selNurseInfo`
                    axios.get(url).then(res=>{
                        this.selNurseInfo = res.data
                        console.log(this.selNurseInfo)
                    })
                },
                form: {
                    id:'',
                    name: '',
                    level: '',
                    price: '',
                    descibe: '',
                    status: '',
                    flag: '',

                },
                rules: { //校验规则
                    name: [
                        { required: true, message: '请输入护理名称', trigger: 'blur' },
                    ],
                    level: [
                        { required: true, message: '请选择等级', trigger: 'change' }
                    ],
                }
            }
        },
        created(){
            this.form.id = this.$route.params.id
            this.getData();
            this.selInfo();
        },
        methods:{
            //加载数据
            getData(){
                let url = `${HOST}/nurseinfo/selById/${this.form.id}`
                axios.get(url).then(res=>{
                    this.form = res.data
                })
            },

            onSubmit(formName){
                this.$refs[formName].validate((valid) => {
                    if (valid) { //校验通过
                        let url = `${HOST}/nurseinfo/update`
                        axios.post(url,this.form).then(res=>{
                            if(res.data.state === 200){//成功
                                this.$message.success("修改成功")
                            }
                        })
                    }
                })
            }
        }
    }
</script>

<style scoped>
    .content {
        margin-top: 10px;
    }
</style>