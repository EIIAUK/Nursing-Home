<template>
    <div>
        <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{ path: '/main' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item :to="{ path: '/nurserecordList' }">护理记录</el-breadcrumb-item>
            <el-breadcrumb-item>修改</el-breadcrumb-item>
        </el-breadcrumb>
        <el-form ref="form" :rules="rules" :model="form" label-width="100px" class="content">
            <el-form-item label="护理对象" prop="customername">
                <el-select v-model="form.customername" placeholder="请选择护理对象">
                    <el-option :key="index" :label="item.customerName" :value="item.customerName" v-for="(item,index) in selPsychosomatics"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="护理项目">
                <el-select v-model="form.nursingname" placeholder="请选择护理项目">
                    <el-option :key="index" :label="item.name" :value="item.name" v-for="(item,index) in selNurseInfo"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="护理数量">
                <el-input v-model="form.count"></el-input>
            </el-form-item>
            <el-form-item label="护理时间">
                <span class="demonstration"></span>
                <el-date-picker
                        v-model="form.time"
                        align="right"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="选择日期"
                        :picker-options="pickerOptions">
                </el-date-picker>
            </el-form-item>
            <el-form-item label="操作人" prop="staffname">
                <el-select v-model="form.staffname" placeholder="请选择操作人">
                    <el-option :key="index" :label="item.staffname" :value="item.staffname" v-for="(item,index) in selUser"></el-option>
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
                selUser: [],
                selPsychosomatics:[],
                selNurseInfo:[],
                selRecord(){
                    this.host = HOST
                    let url = `${HOST}/userinfo/selUser`
                    axios.get(url).then(res=>{
                        this.selUser = res.data
                        console.log(this.selUser)
                    })},
                selCustomer(){
                    this.host = HOST
                    let url = `${HOST}/hosteling/selPsychosomatics`
                    axios.get(url).then(res=>{
                        this.selPsychosomatics = res.data
                        console.log(this.selPsychosomatics)
                    })
                },
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
                    customername: '',
                    nursingname: '',
                    count: '',
                    time: '',
                    staffname: '',
                },
                rules: { //校验规则
                    customername: [
                        { required: true, message: '请输入护理名称', trigger: 'blur' },
                    ],
                    staffname: [
                        { required: true, message: '请选择操作人', trigger: 'blur' }
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
        created(){
            this.form.id = this.$route.params.id
            this.getData();
            this.selRecord();
            this.selCustomer();
            this. selInfo();
        },
        methods:{
            //加载数据
            getData(){
                let url = `${HOST}/nurserecord/selById/${this.form.id}`
                axios.get(url).then(res=>{
                    this.form = res.data
                })
            },

            onSubmit(formName){
                this.$refs[formName].validate((valid) => {
                    if (valid) { //校验通过
                        let url = `${HOST}/nurserecord/update`
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