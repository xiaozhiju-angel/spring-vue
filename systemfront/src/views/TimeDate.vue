<template>
  <div>
    <div class="block">
      <span class="demonstration">使用 value-format</span>
      <div class="demonstration">值：{{ value1 }}</div>
      <el-date-picker
        v-model="value1"
        @change="getSTime"
        type="date"
        placeholder="选择日期"
        format="yyyy-MM-dd"
        value-format="yyyy-MM-dd">
      </el-date-picker>
    </div>
    <div class="block">
      <span class="demonstration">使用 value-format</span>
      <div class="demonstration">值：{{ value2 }}</div>
      <el-date-picker
        v-model="value2"
        type="date"
        placeholder="选择日期"
        format="yyyy-MM-dd"
        value-format="yyyy-MM-dd">
      </el-date-picker>
    </div>


  </div>

</template>

<script>
  export default {
    data() {
      var that=this;
      return {
        value1: '',
        value2: '',
      };
    },

    methods: {
      getSTime(){
        let str=this.value1;
        let newstr = str.split('-');
        var year=parseInt(newstr[0]);
        var month= (parseInt(newstr[1])+1) + 11;
        if (month>12) {
          year++;
          month -= 12;
        }
        if(month<10) {
          month = "0" + month;
        }
        var date2=new Date(year,month,0);//新的年月
        var day1=parseInt(newstr[2]);
        var day2=date2.getDate();
        if(day1>day2){//11月份其实不需要，只有30天
          day1=day2;
        }
       var newMessage = year + '-'
          + month + '-'
          + day1;
        this.value2=newMessage;
        console.log(newMessage);
      },
    },
    mounted(){
      this.getSTime(this.value1);
    }
  }
</script>
