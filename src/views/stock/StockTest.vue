<template>
  <div class="StockTest">
    <h2>주식테스트</h2>
    <div>
      <b-form-input
        id="stock-name"
        v-model="stockName"
        :state="hasResult"
      ></b-form-input>
      <b-button @click="searchStocks" variant="primary" style="float:right;" :disabled="isDisabled">검색</b-button>
    </div>
    
    <div>
      <b-table 
        striped hover 
        :fields="fields" 
        :items="items" 
        @row-dblclicked="selectStock"
      >
      </b-table>
    </div>
  </div>
</template>

<script>
export default {
  data(){
    return {
        fields:[
          {key : 'iscdFullName', label : '주식명'},
        ],
        items: [],
        conditions:[
          //{text: '전체', value: 'null'},
          //{text: '작성자', value: 'usernm'},
          //{text: '제목', value: 'title'}
        ],
        //condition:{key : '', value : ''},
        stockName: ''
    };
  },
  mounted(){
    /*
    this.$nextTick(function(){ // 모든 화면이 렌더링된 후에 실행
      this.$axios.get('/board/selectList')
        .then(response =>{
          this.items = response.data
          console.log("items :: " + this.items);
        })
        .catch(err => {
          console.log(err);
        });
    })
    */ 
  }
  ,
  computed:{
    hasResult:function(){
      return this.stockName.length > 0
    },
    isDisabled:function(){
      return this.hasResult == true ? false : true
    }
  },
  methods:{
    searchStocks(){
        this.$axios.get('/stock/searchStocks',{
          params: {
            stockName: this.stockName
          }
        })
        .then(response =>{
          if(response.data.length<=0)
            window.alert("조회된 결과가 없습니다.");
          else
            this.items = response.data
          console.log(this.items);
        })
        .catch(err => {
          window.alert("오류생김 ㅅㄱ.");
          console.log("stockName :: " + this.stockName);
          console.log(err);
        });   
      },
    selectStock(item){
        this.$axios.get('/stock/sendStockInfo',{
          params: {
            stockName: item.iscdName
          }
        })
        .then(response =>{
          window.alert(item.iscdName + " 정보를 디스코드로 전송하였습니다.");
          console.log(response.data);
        })
        .catch(err => {
          window.alert("오류생김 ㅅㄱ.");
          console.log(err);
        });   
      }
    }
  }
</script>

<style scoped>
  h2 {text-align: center;}
</style>