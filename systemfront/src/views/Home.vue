<template>
    <div class="total">
      <div class="top">
        <el-header >
          <div>
            <div class="left">
              <div class="left_top_left">
                <img src="../../static/petimg/pet3.png"/>
                <div class="left_top_right">
                  <p class="pet_manager">宠物管理</p>
                  <p class="pet_home">pet home</p>
                </div>
              </div>
              <div class="left_bottom">品质养宠&品质管宠</div>
            </div>
              <el-input placeholder="健康宠物" v-model="inputpetinfo" class="search" size="small">
                <el-button class="appendsearch" slot="append" >搜索</el-button>
              </el-input>

              <div class="right_end">
                <el-button type="primary" plain size="small" @click="login()">登录</el-button>
                <el-button type="primary" plain size="small" @click="register()">注册</el-button>
              </div>
          </div>
        </el-header>
      </div>
      <div class="nav">
           <ul>
             <li class="navinfo">
               <div><a href="#newinfo"><img src="../../static/homeimg/资讯1.png"/></a></div>
                <p><a href="#newinfo">最新消息</a></p>
             </li>
             <li class="navinfo">
               <div><a href="#health"><img src="../../static/homeimg/健康百科-03.png"/></a></div>
               <p><a href="#health">健康百科</a></p>
             </li>
             <li class="navinfo">
               <div><a href="#picture"><img src="../../static/homeimg/宠物1.png"/></a></div>
               <p><a href="#picture" style="text-decoration: none">宠物图片</a></p>
             </li>
             <li class="navinfo">
               <div><a href="#food"><img src="../../static/homeimg/生活用品.png"/></a></div>
               <p><a  href="#food" style="text-decoration: none">宠物食用</a></p>
             </li>
             <li class="navinfo">
               <div><a href="#serve"><img src="../../static/petimg/功能2.png" style="width: 40px;height: 50px"/></a></div>
               <p><a href="#serve" style="text-decoration: none">服务功能</a></p>
             </li>
           </ul>
        </div>
      <div class="main">
          <v-swiper></v-swiper>
      </div>
      <div class="divide"></div>
      <div class="zixun">
        <div class="title">
          <img src="../../static/homeimg/资讯.png">
          <b ><a id="newinfo">最新消息</a></b>
          <span class="more"> <a  href="http://localhost:8080/#/login" style="text-decoration: none;point:cursor">更多  ></a></span>
        </div>
        <div class="infomation">
          <div class="first">
                    <img src="../../static/petimg/066.jpg"/>
                    <p >
                      狸花猫的原产地是中国，属于自然猫，是在千百年中经过许多品种的自然淘汰而保留下来的品种。人们最熟悉的就要算是“狸猫换太子”（宋朝）的故事了，这也是能够找到的最早有关于狸花猫的记录了，
                      所以，狸花猫就有了一个这样的英文名字DragonLi，由CFA猫会授予。
                      它非常受百姓们喜欢，因为它有漂亮、厚实的皮毛，健康的身体。容易喂养，并且对捕捉老鼠十分在行
                    </p>
            <a  href="http://localhost:8080/#/login" style="text-decoration: none;point:cursor"><el-button class="btn_detail" type="primary" size="mini">详情</el-button></a>
               </div>
          <div class="first">
            <img src="../../static/petimg/078.jpg"/>
            <p >
              贵宾/泰迪，贵宾犬也称“贵妇犬”，属于非常聪明且喜欢狩猎的犬种，据猜测贵妇犬原本起源于德国，
              在那儿它以水中捕猎犬而著称。然而许多年以来，它一直被认为是法国的国犬，贵宾犬分为标准犬、迷你犬、玩具犬三种。
              它们之间的区别只是在于体型的大小不同。我们所说的“泰迪”，其实就是贵宾。
            </p>
            <a  href="http://localhost:8080/#/login" style="text-decoration: none;point:cursor"><el-button class="btn_detail" type="primary" size="mini">详情</el-button></a>
          </div>
          <div class="first">
            <img src="../../static/petimg/097.jpg"/>
            <p >
              银狐兔是最早的和最著名的肉兔品种，也是最大的家兔品种。体型结构匀称，
              骨骼粗重，产肉力市,肉质良好。育成历史不详，眼球呈圆形，单眼视角180°，所以兔用单眼看东西。兔的品种不同、毛色不同，其眼的颜色也不同。
              兔子的眼睛有：红色，蓝色，黑色，灰色等各种颜色，也有的兔子左右两只眼睛的颜色不一样但源于比利时北部弗朗德一带。
            </p>
            <a  href="http://localhost:8080/#/login"><el-button class="btn_detail" type="primary" size="mini">详情</el-button></a>
          </div>
         </div>
      </div>
      <div class="divide"></div>
      <div class="petpicture">
        <div class="title">
          <img src="../../static/petimg/宠物狗.png"/>
          <a id="picture" style="padding-top: 23px"><b>宠物图片</b></a>
          <span class="more" ><a  href="http://localhost:8080/#/login" style="text-decoration: none;point:cursor">更多  ></a></span>
        </div>
        <div class="top">
             <ul>
               <li >
                 <img src="../../static/petimg/061.jpg"/>
                 <p>缅因猫</p>
               </li>
               <li >
                 <img src="../../static/petimg/092.png"/>
                 <p>大麦町</p>
               </li>
               <li>
                 <img src="../../static/petimg/065.jpg"/>
                 <p>暹罗猫</p>
               </li>
               <li>
                 <img src="../../static/petimg/110.jpg"/>
                 <p>狮子狗</p>
               </li>
               <li>
                 <img src="../../static/petimg/105.jpg"/>
                 <p>迷你兔</p>
               </li>
             </ul>
        </div>
        <div class="bottom">
          <ul>
            <li >
              <img src="../../static/petimg/106.jpg"/>
              <p>银狐兔</p>
            </li>
            <li>
              <img src="../../static/petimg/124.jpg"/>
              <p>百灵鸟</p>
            </li>
            <li>
              <img src="../../static/petimg/095.png"/>
              <p>伊拉兔</p>
            </li>
            <li>
              <img src="../../static/petimg/129.jpg"/>
              <p>相思鸟</p>
            </li>
            <li>
              <img src="../../static/petimg/064.jpg"/>
              <p>哈巴狗</p>
            </li>
          </ul>
        </div>
      </div>
      <div class="divide"></div>
      <div class="serve">
           <div class="title">
             <a  href="http://localhost:8080/#/login" style="text-decoration: none;point:cursor"><img src="../../static/petimg/环保.png"/></a>
             <a id="serve" style="padding-top: 23px"><b>服务项目</b></a>
             <span class="more"><a  href="http://localhost:8080/#/login" style="text-decoration: none;point:cursor">更多  ></a></span>
           </div>
           <div class="picture">
             <el-row class="row" :span="24">
                 <el-col :span="6">
                   <a  href="http://localhost:8080/#/login" style="text-decoration: none;point:cursor"><img src="../../static/petimg/登记.png"/></a>
                   <p><a  href="http://localhost:8080/#/login" style="text-decoration: none;point:cursor">宠物登记</a></p>
                 </el-col>
               <el-col :span="6">
                 <a  href="http://localhost:8080/#/login" style="text-decoration: none;point:cursor"><img src="../../static/petimg/查询.png"/></a>
                 <p><a  href="http://localhost:8080/#/login" style="text-decoration: none;point:cursor">宠物查询</a></p>
               </el-col>
               <el-col :span="6">
                 <a  href="http://localhost:8080/#/login" style="text-decoration: none;point:cursor"><img src="../../static/petimg/宠物寄养.png"/></a>
                 <p><a  href="http://localhost:8080/#/login" style="text-decoration: none;point:cursor">宠物寄养</a></p>
               </el-col>
               <el-col :span="6">
                 <a  href="http://localhost:8080/#/login" style="text-decoration: none;point:cursor"><img src="../../static/petimg/领养.png"/></a>
                 <p><a  href="http://localhost:8080/#/login" style="text-decoration: none;point:cursor">宠物领养</a></p>
               </el-col>
             </el-row>
             <el-row class="row" :span="24">
               <el-col :span="6">
                 <a  href="http://localhost:8080/#/login" style="text-decoration: none;point:cursor"><img src="../../static/petimg/宠物疫苗.png"/></a>
                 <p><a  href="http://localhost:8080/#/login" style="text-decoration: none;point:cursor">宠物疫苗</a></p>
               </el-col>
               <el-col :span="6">
                 <a  href="http://localhost:8080/#/login" style="text-decoration: none;point:cursor"><img src="../../static/petimg/找.png"/></a>
                 <p><a  href="http://localhost:8080/#/login" style="text-decoration: none;point:cursor">宠物找寻</a></p>
               </el-col>
               <el-col :span="6">
                 <a  href="http://localhost:8080/#/login" style="text-decoration: none;point:cursor"><img src="../../static/petimg/发布消息.png"/></a>
                 <p><a  href="http://localhost:8080/#/login" style="text-decoration: none;point:cursor">发布信息</a></p>
               </el-col>
               <el-col :span="6">
                 <a  href="http://localhost:8080/#/login" style="text-decoration: none;point:cursor"><img src="../../static/petimg/2.png"/></a>
                 <p><a  href="http://localhost:8080/#/login" style="text-decoration: none;point:cursor">个人管理</a></p>
               </el-col>
             </el-row>
           </div>
      </div>
      <div class="divide"></div>
      <div class="end">
           <el-row :span="24" class="left">
             <el-col :span="12">
               <el-row :span="12" >
                 <div class="title">
                   <img src="../../static/homeimg/健康百科1.png"/>
                   <a id="health" style="padding-top: 23px" ><b >宠物管理知识百科</b></a>
                   <span class="endmore"><a  href="http://localhost:8080/#/login" style="text-decoration: none;point:cursor">更多  ></a></span>
                 </div>
               </el-row>
               <div class="infomation">
                 <el-row :span="23" >
                   <el-col class="pet_type" :span="3">
                     <b>犬类：</b>
                   </el-col>
                   <el-col  :span="5">
                     <el-button  class="info" type="info" size="mini" >田园犬</el-button>
                   </el-col>
                   <el-col  :span="5">
                     <el-button class="info" type="info" size="mini">哈士奇</el-button>
                   </el-col>
                   <el-col  :span="5">
                     <el-button  class="info" type="info" size="mini">泰迪</el-button>
                   </el-col>
                   <el-col  :span="5">
                     <el-button class="info" type="info" size="mini">藏獒</el-button>
                   </el-col>
                 </el-row>
                 <el-row :span="23" >
                   <el-col class="pet_type" :span="3">
                       <b>猫类：</b>
                   </el-col>
                   <el-col  :span="5">
                     <el-button  class="info" type="info" size="mini" >布偶猫</el-button>
                   </el-col>
                   <el-col  :span="5">
                     <el-button class="info" type="info" size="mini">波斯猫</el-button>
                   </el-col>
                   <el-col  :span="5">
                     <el-button  class="info" type="info" size="mini">索马里猫</el-button>
                   </el-col>
                   <el-col  :span="5">
                     <el-button class="info" type="info" size="mini">藏獒</el-button>
                   </el-col>
                 </el-row>
                 <el-row :span="23" >
                   <el-col class="pet_type" :span="3">
                     <b>兔类：</b>
                   </el-col>
                   <el-col  :span="5">
                     <el-button  class="info" type="info" size="mini" >田园犬</el-button>
                   </el-col>
                   <el-col  :span="5">
                     <el-button class="info" type="info" size="mini">哈士奇</el-button>
                   </el-col>
                   <el-col  :span="5">
                     <el-button  class="info" type="info" size="mini">泰迪</el-button>
                   </el-col>
                   <el-col  :span="5">
                     <el-button class="info" type="info" size="mini">藏獒</el-button>
                   </el-col>
                 </el-row>
                 <el-row :span="23" >
                   <el-col class="pet_type" :span="3">
                     <b>鸟类：</b>
                   </el-col>
                   <el-col  :span="5">
                     <el-button  class="info" type="info" size="mini" >田园犬</el-button>
                   </el-col>
                   <el-col  :span="5">
                     <el-button class="info" type="info" size="mini">哈士奇</el-button>
                   </el-col>
                   <el-col  :span="5">
                     <el-button  class="info" type="info" size="mini">泰迪</el-button>
                   </el-col>
                   <el-col  :span="5">
                     <el-button class="info" type="info" size="mini">藏獒</el-button>
                   </el-col>
                 </el-row>
               </div>
             </el-col>
             <el-col :span="12">
               <el-row :span="12">
                 <div class="title">
                   <img src="../../static/homeimg/用品.png"/>
                   <a id="food" style="padding-top: 23px"><b>宠物食用品</b></a>
                   <span class="endmore"><a  href="http://localhost:8080/#/login" style="text-decoration: none;point:cursor">更多  ></a></span>
                 </div>
               </el-row>
               <div class="food">
                 <el-row :span="12">
                   <el-col class="info" :span="4">
                     <img src="../../static/petimg/pet016.png"/>
                     <p>猫粮</p>
                   </el-col>
                   <el-col class="info" :span="4">
                     <img src="../../static/petimg/pet019.png"/>
                     <p>狗粮</p>
                   </el-col>
                   <el-col class="info" :span="4">
                     <img src="../../static/petimg/pet020.png"/>
                     <p>猫粮</p>
                   </el-col>
                 </el-row>
                 <el-row :span="12">
                   <el-col class="info" :span="4">
                     <img src="../../static/petimg/pet021.png"/>
                     <p>猫粮</p>
                   </el-col>
                   <el-col class="info" :span="4">
                     <img src="../../static/petimg/pet018.png"/>
                     <p>狗粮</p>
                   </el-col>
                   <el-col class="info" :span="4">
                     <img src="../../static/petimg/pet015.png"/>
                     <p>猫粮</p>
                   </el-col>
                 </el-row>
               </div>
             </el-col>
           </el-row>
      </div>
      <div class="divide"></div>
      <div class="endfooter">
           <ul>
             <li>网站介绍</li>
             <li>隐私政策</li>
             <li>广告服务</li>
             <li>合作媒体</li>
             <li>投稿指南</li>
             <li>使用条款</li>
             <li>网站地图</li>
             <li>营业执照</li>
             <li><a style="text-decoration: none;color: black" href="http://localhost:8080/#/connect">联系我们</a></li>
           </ul>
        </div>
      <div class="authority">Copyright © 2019 </div>

      <div class="ban">【免责声明：淘狗网上所有宠物狗交易信息内容系用户自行发布，其真实性、合法性由发布人负责，淘狗网不提供任何保证，亦不承担任何法律责任！】</div>
      </div>
    </div>
</template>

<script>
    import Swiper from './Swiper.vue'
    export default {
        data(){
          return{
            inputpetinfo:'',

          }
        },
        components:{
          'v-swiper':Swiper,
        },
      methods:{
        login(){
          this.$router.push({
            path:'/login'
          });
        },
        register(){
          this.$router.push({
            path:'/urg'
          });
        }
      }

    }
</script>

<style scoped>
  @import "../../static/css/home.css";
</style>
