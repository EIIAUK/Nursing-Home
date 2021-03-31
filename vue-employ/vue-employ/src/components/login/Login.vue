<template>
    <div class="all"  >
        <div class="margin1">
            <el-row type="flex"  justify="center">
                <el-col :span="24"></el-col>
            </el-row>
        </div>
        <div class="wrap">
            <el-row type="flex"  justify="center">
                <el-col :span="6"><div></div></el-col>
                <el-col :span="6">
                    <h1  class="Head" style="color: #FFFFFF;font-style: oblique">  Nursing Home</h1>
                    <div  class="border">
                        <el-form   ref="form" :model="form" class="login" label-width="80px">
                            <el-form-item
                                    label="UserName:" class="text1" style="text-align: left;margin-left: 20% ;font-color: #FFFFFF">
                                <el-input
                                        placeholder="Please input UserName"
                                        v-model="form.username"
                                        clearable style="width: 60%">
                                </el-input>
                            </el-form-item>
                            <el-form-item label="Password:" style="text-align: left;margin-left: 20%;color: #FFFFFF">
                                <el-input
                                        style="width: 60%;"
                                        placeholder="please input password"
                                        v-model="form.password"
                                        show-password>
                                </el-input>
                            </el-form-item>

                            <el-form-item label="Status:" style="text-align: left;margin-left: 20%;color: #FFFFFF">
                                <el-select v-model="form.label" style="width: 60%">
                                    <el-option label="-Select-" value=""></el-option>
                                    <el-option label="医生" value="医生"></el-option>
                                    <el-option label="护士" value="护士"></el-option>
                                    <el-option label="护工" value="护工"></el-option>
                                    <el-option label="管理员" value="管理员"></el-option>
                                </el-select>
                            </el-form-item>
                            <div style="text-align:center">
                                <el-button class="but1" @click="onSubmit" style="margin-right: 0px">LogIn</el-button>
                                <el-popover style="margin-left: 5px"
                                        placement="bottom"
                                        title="演示账号"
                                        width="200"
                                        trigger="click"
                                        content="测试账号：admin      测试密码：admin；测试身份：管理员">
                                    <el-button slot="reference" style="margin-left: 30px">Point</el-button>
                                </el-popover>
                            </div>
                        </el-form>
                    </div>
                </el-col>
                <el-col :span="6"><div ></div></el-col>
            </el-row>
        </div>
        <div class="margin">
            <el-row type="flex"  justify="center">
                <el-col :span="24"></el-col>
            </el-row>
        </div>
    </div>
</template>

<script>
    import axios from 'axios'
    import {HOST} from '../../common/config'
    export default {
        data(){

            return{
                input: '',

                backgroundDiv: {

                    backgroundImage:'url(https://www.lifeofpix.com/wp-content/uploads/2014/05/Life-of-pix-window-Sarah-babineau-1600x1030.jpg)',

                    backgroundRepeat:'no-repeat',

                    backgroundSize:'100% 130%',

                },
                form: {
                    username: '',
                    password: '',
                    label:''
                }
            }
        },
        created(){
            //取需要显示的数据
            this.getData()
        },
        methods:{
            handleClick() {
                alert('button click');
            },

            onSubmit(){
                let url = `${HOST}/user/login`
                axios.post(url,this.form).then(res=>{
                    let response = res.data
                    console.log(res.data)
                    switch(response.msg){
                        case '0': //登录成功
                            this.$router.push('/main')
                            break;
                        case '1': //密码错误
                            this.$message.error('密码错误!')
                            break;
                        case '2'://用户名错误
                            this.$message.error("用户名错误")
                            break;
                        case '3'://身份错误
                            this.$message.error("用户身份错误")
                            break;
                    }
                })
            }
        }
    }
</script>

<style scoped>

    .border{
        size:50px;
        background-color: #313132;
        border-radius: 15px;
       /* box-shadow: 10px 10px 30px rgba(0, 0, 0, 100), 0 0 6px rgba(255, 255, 255, .100);*/
        box-shadow: 10px 10px 60px #C944FF,-30px -30px 60px #F93979;
    }
    .text1{
        padding-top: 30px;


    }
    .margin{
        margin-top: 339px;
    }
    .margin1{
        margin-bottom: 0px;
    }
    .wrap {
        margin-top: 250px;
    }

    .wrap h1 {
        display: flex;
        justify-content: center;
        align-items: center;
        border-radius: 25px;
        color: #292421;
        background-color: rgba(255,255,255,0);
        /*background-image: linear-gradient(120deg, #BDFCC9 0%, #8fd3f4 100%);*/
    }
    .but1{
        margin-bottom: 20px;

    }
    .wrap .login {
        margin-top: 30px;

    }
    .Head{
       text-shadow: #2fff3d -2px -2px 5px;
        font-size:35px;

    }
    .all{
        background-color: #444444;
        color: #FFFFFF;
    }
</style>