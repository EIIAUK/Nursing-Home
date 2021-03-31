<template>
    <div class="banner">
     <!--   <div class="item">
            <img :src="dataList[currentIndex]">
        </div>
        <div class="page" v-if="this.dataList.length > 1">
            <ul>
                <li @click="gotoPage(prevIndex)">&lt;</li>
                <li v-for=   " (item,index) in dataList" @click="gotoPage(index)" :key='item' :class="{'current':currentIndex == index}">  {{index+1}}</li>
                <li @click="gotoPage(nextIndex)">&gt;</li>
            </ul>
        </div>-->
        <div class="text1">
        <p style="text-align: center;font-size: 40px "> 油炸食品本身不含有纤维素，对于维生素以及矿物质含量过少</p>
        <p style="text-align: center;font-size: 20px;color: blue ">多油多盐</p>
      <p style="text-align: center;font-size: 100px ">Fried Food</p>
    </div>
        <div style="margin-left: 500px">
            <img src="https://dpic.tiankong.com/gl/bp/QJ8876841567.jpg?x-oss-process=style/794ws" style="size: 50px " >
        </div>
        
        <div style="background-color: black">
           </div>
    </div>
</template>

<script>
    export default {
        name: "index",
        methods: {
            gotoPage(index) {
                this.currentIndex = index;
            }
        },

        data: function() {
            return {
                dataList: ["https://www.lifeofpix.com/wp-content/uploads/2017/11/LifeofPix-11-2-1600x1068.jpg","https://www.lifeofpix.com/wp-content/uploads/2017/11/LifeofPix-18-1600x1068.jpg", "https://www.lifeofpix.com/wp-content/uploads/2017/11/LifeofPix-12-1600x1068.jpg",],
                currentIndex: 0,   //默认显示图片
                timer: null    //定时器
            }
        },
        runInv() {
            this.timer = setInterval(() => {
                this.gotoPage(this.nextIndex)
            }, 1000)
        },
        computed: {
            //上一张
            prevIndex() {
                if(this.currentIndex == 0) {
                    return this.dataList.length - 1;
                }else{
                    return this.currentIndex - 1;
                }
            },
            //下一张
            nextIndex() {
                if(this.currentIndex == this.dataList.length - 1) {
                    return 0;
                }else {
                    return this.currentIndex + 1;
                }
            }
        }
    }

</script>

<style scoped>
.banner{
    width: 100%;

}
    .text1{
        margin-top: 80px;
    }

</style>