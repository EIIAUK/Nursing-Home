<template>
    <div>
        <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{ path: '/main' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item :to="{ path: '/foodList' }">膳食信息</el-breadcrumb-item>
            <el-breadcrumb-item>新增</el-breadcrumb-item>
        </el-breadcrumb>
        <el-form ref="form" :rules="rules" :model="form" label-width="100px" class="content">
            <el-form-item label="创建时间">
                <span class="demonstration"></span>
                <el-date-picker
                        v-model="form.createDate"
                        align="right"
                        type="date"
                        placeholder="选择日期"
                        :picker-options="pickerOptions">
                </el-date-picker>
            </el-form-item>
            <el-form-item label="客户姓名" prop="customName">
                <el-input v-model="form.customName"></el-input>
            </el-form-item>
            <el-form-item label="膳食名称" prop="foodName">
                <el-input v-model="form.foodName"></el-input>
            </el-form-item>
            <el-form-item label="膳食日期">
                <el-input v-model="form.foodDate"></el-input>
            </el-form-item>
            <el-form-item label="膳食星期">
                <el-select v-model="form.foodWeek" placeholder="请选择星期">
                    <el-option label="星期一" value="1"></el-option>
                    <el-option label="星期二" value="2"></el-option>
                    <el-option label="星期三" value="3"></el-option>
                    <el-option label="星期四" value="4"></el-option>
                    <el-option label="星期五" value="5"></el-option>
                    <el-option label="星期六" value="6"></el-option>
                    <el-option label="星期天" value="7"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="onSubmit('form')">立即创建</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
    import axios from 'axios'
    import {HOST} from "../../common/config";

    export default {
        data(){
            return{
                form: {
                    createDate:'',
                    customName: '',
                    foodName: '',
                    foodDate: '',
                    foodWeek:''
                },
                rules: { //校验规则
                    customName: [
                        { required: true, message: '请输入客户姓名', trigger: 'blur' },
                    ],
                    foodName: [
                        { required: true, message: '请输入膳食名称', trigger: 'blur' },
                    ],
                },
                pickerOptions: {
                    disabledDate(time) {
                        return time.getTime() > Date.now();
                    },
                    shortcuts: [{
                        text: '今天',
                        onClick(picker) {
                            picker.$emit('pick', new Date());
                        }
                    }, {
                        text: '昨天',
                        onClick(picker) {
                            const date = new Date();
                            date.setTime(date.getTime() - 3600 * 1000 * 24);
                            picker.$emit('pick', date);
                        }
                    }, {
                        text: '一周前',
                        onClick(picker) {
                            const date = new Date();
                            date.setTime(date.getTime() - 3600 * 1000 * 24 * 7);
                            picker.$emit('pick', date);
                        }
                    }]
                }
            }
        },
        methods:{
            onSubmit(formName){
                this.$refs[formName].validate((valid) => {
                    if (valid) { //校验通过
                        let url = `${HOST}/foodmanage/add`
                        axios.post(url,this.form).then(res=>{
                            if(res.data.state === 200){//成功
                                this.$message.success("添加成功")
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