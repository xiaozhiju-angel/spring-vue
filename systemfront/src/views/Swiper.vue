<template>
  <div id="slider">
    <div class="window" @mouseover="stop" @mouseleave="play">
      <ul class="container" :style="containerStyle">
        <li>
          <img :style="{width:imgWidth+'px'}" :src="sliders[sliders.length - 1].img" alt>
        </li>
        <li v-for="(item, index) in sliders" :key="index">
          <!--@click="showuser(index)"-->
          <img :style="{width:imgWidth+'px'}" :src="item.img" @click="showuser(index)" alt>
        </li>
        <li>
          <img :style="{width:imgWidth+'px'}" :src="sliders[0].img" alt>
        </li>
      </ul>
      <ul class="direction">
        <li class="left" @click="move(850, 1, speed)">
          <svg
            class="icon"
            width="30px"
            height="30.00px"
            viewBox="0 0 1024 1024"
            version="1.1"
            xmlns="http://www.w3.org/2000/svg"
          >
            <path
              fill="#ffffff"
              d="M481.233 904c8.189 0 16.379-3.124 22.628-9.372 12.496-12.497 12.496-32.759 0-45.256L166.488 512l337.373-337.373c12.496-12.497 12.496-32.758 0-45.255-12.498-12.497-32.758-12.497-45.256 0l-360 360c-12.496 12.497-12.496 32.758 0 45.255l360 360c6.249 6.249 14.439 9.373 22.628 9.373z"
            ></path>
          </svg>
        </li>
        <li class="right" @click="move(850, -1, speed)">
          <svg
            class="icon"
            width="30px"
            height="30.00px"
            viewBox="0 0 1024 1024"
            version="1.1"
            xmlns="http://www.w3.org/2000/svg"
          >
            <path
              fill="#ffffff"
              d="M557.179 904c-8.189 0-16.379-3.124-22.628-9.372-12.496-12.497-12.496-32.759 0-45.256L871.924 512 534.551 174.627c-12.496-12.497-12.496-32.758 0-45.255 12.498-12.497 32.758-12.497 45.256 0l360 360c12.496 12.497 12.496 32.758 0 45.255l-360 360c-6.249 6.249-14.439 9.373-22.628 9.373z"
            ></path>
          </svg>
        </li>
      </ul>
      <ul class="dots">
        <li
          v-for="(dot, i) in sliders"
          :key="i"
          :class="{dotted: i === (currentIndex-1)}"
          @click="jump(i+1)"
        ></li>
       <!-- <div style="color: #0bbbff" >幸福你我他，宠物健康靠大家</div>-->
      </ul>

    </div>

  </div>
</template>
<script>
  export default {
    name: 'slider',
    props: {
      initialSpeed: {
        type: Number,
        default: 30
      },
      initialInterval: {
        type: Number,
        default: 3
      }
    },
    data () {
      return {
        sliders:[
          {
            img:'../../static/petimg/047.jpg',
          },
          {
            img:'../../static/petimg/044.jpg'
          },
          {
            img:'../../static/petimg/043.jpg'
          },
          {
            img:'../../static/petimg/040.jpg'
          },
          {
            img:'../../static/petimg/039.jpg'
          },
        ],
        imgWidth:850,
        currentIndex:1,
        distance:-850,
        transitionEnd: true,
        speed: this.initialSpeed
      }
    },
    computed:{
      containerStyle() {
        return {
          transform:`translate3d(${this.distance}px, 0, 0)`
        }
      },
      interval() {
        return this.initialInterval * 100
      }
    },
    mounted() {
      this.init()
    },
    methods:{
      showuser(id){
        switch (id) {
          case 0:
            this.$router.push({
              path:'/login'
            });break;
          case 1:
            this.$router.push({
              path:'/login'
            });break;
          case 2:
            this.$router.push({
              path:'/login'
            });break;
          case 3:
            this.$router.push({
              path:'/login'
            });break;
          case 4:
            this.$router.push({
              path:'/login'
            });;break;
          default:alert(''); break;
        }
      },
      init() {
        this.play()
        window.onblur = function() { this.stop() }.bind(this)
        window.onfocus = function() { this.play() }.bind(this)
      },
      move(offset, direction, speed) {
        console.log(speed)
        if (!this.transitionEnd) return
        this.transitionEnd = false
        direction === -1 ? this.currentIndex += offset/850 : this.currentIndex -= offset/850
        if (this.currentIndex > 5) this.currentIndex = 1
        if (this.currentIndex < 1) this.currentIndex = 5

        const destination = this.distance + offset * direction
        this.animate(destination, direction, speed)
      },
      animate(des, direc, speed) {
        if (this.temp) {
          window.clearInterval(this.temp);
          this.temp = null ;
        }
        this.temp = window.setInterval(() => {
          if ((direc === -1 && des < this.distance) || (direc === 1 && des > this.distance)) {
            this.distance += speed * direc
          } else {
            this.transitionEnd = true
            window.clearInterval(this.temp)
            this.distance = des
            if (des < -4250) this.distance = -850
            if (des > -850) this.distance = -4250
          }
        }, 20)
      },
      jump(index) {
        const direction = index - this.currentIndex >= 0 ? -1 : 1;
        const offset = Math.abs(index - this.currentIndex) * 850;
        const jumpSpeed = Math.abs(index - this.currentIndex) === 0 ? this.speed : Math.abs(index - this.currentIndex) * this.speed ;
        this.move(offset, direction, jumpSpeed)
      },
      play() {
        if (this.timer) {
          window.clearInterval(this.timer)
          this.timer = null
        }
        this.timer = window.setInterval(() => {
          this.move(850, -1, this.speed)
        }, this.interval)
      },
      stop() {
        window.clearInterval(this.timer)
        this.timer = null
      }
    }
  }
</script>
<style scoped>
  * {
    box-sizing: border-box;
    margin: 0;
    padding: 0;
  }
  ol,
  ul {
    list-style: none;
  }
  #slider {
    text-align: center;
  }
  .window {
    position: relative;
    width: 850px;
    height: 400px;
    margin: 0 auto;
    overflow: hidden;
  }
  .container {
    display: flex;
    position: absolute;
  }
  .left,
  .right {
    position: absolute;
    top: 50%;
    transform: translateY(-50%);
    width: 50px;
    height: 50px;
    background-color: rgba(0, 0, 0, 0.3);
    border-radius: 50%;
    cursor: pointer;
  }
  .left {
    left: 3%;
    padding-left: 12px;
    padding-top: 10px;
  }
  .right {
    right: 3%;
    padding-right: 12px;
    padding-top: 10px;
  }
  img {
    user-select: none;
  }
  .dots {
    position: absolute;
    bottom: 10px;
    left: 50%;
    transform: translateX(-50%);
  }
  .dots li {
    display: inline-block;
    width: 15px;
    height: 15px;
    margin: 0 3px;
    border: 1px solid white;
    border-radius: 50%;
    background-color: #333;
    cursor: pointer;
  }
  .dots .dotted {
    background-color: orange;
  }
</style>
