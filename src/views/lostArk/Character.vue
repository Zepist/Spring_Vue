<template>
  <div class="Character">
    <h2>캐릭터 정보검색</h2>
    <div>
      <b-form-input
        id="nick-name"
        v-model="nickname"
        :state="hasResult"
      ></b-form-input>
      <b-button @click="searchCharacter" variant="primary" style="float:right;" :disabled="isDisabled">검색</b-button>
    </div>
    <!--
    <div>
      <b-table 
        striped hover 
        :fields="fields" 
        :items="items" 
      >
      </b-table>
    </div>
    -->
  </div>
</template>

<script>
export default {
  data(){
    return {
        fields:[
          {key : 'nickname', label : '캐릭터명'},
          {key : 'level'   , label : '레벨'}
        ],
        items: [],
        conditions:[
          //{text: '전체', value: 'null'},
          //{text: '작성자', value: 'usernm'},
          //{text: '제목', value: 'title'}
        ],
        //condition:{key : '', value : ''},
        nickname: ''
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
      return this.nickname.length > 0
    },
    isDisabled:function(){
      return this.hasResult == true ? false : true
    }
  },
  methods:{
    searchCharacter(){
        this.$axios.get('/lostark/character',{
          params: {
            nickname: this.nickname
          }
        })
        .then(response =>{
          if(response.data.length<=0)
            window.alert("해당 닉네임을 가진 캐릭터는 존재하지 않습니다.");
          else
          {
            window.alert(this.nickname + " 정보를 디스코드로 전송하였습니다.");
            this.items = response.data
          }
          console.log(this.items);
        })
        .catch(err => {
          window.alert("오류생김 ㅅㄱ.");
          console.log("nickname :: " + this.nickname);
          console.log(err);
        });   
      }
    }
  }
</script>

<style scoped>
  h2 {text-align: center;}
</style>