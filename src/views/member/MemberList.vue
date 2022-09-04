<template>
  <div class="memberList">
    <h2>회원목록</h2>
    <div>
      <b-table striped hover :fields="fields" :items="items" @row-clicked="boardDetail">
        <template #cell(index)="data">
          {{data.index + 1}}
        </template>

        <template #cell(name)="data">
          {{data.item.name}}
          <router-link :to="{name: 'Member', params:{bno: data.item.userid}}"></router-link>
        </template>
      </b-table>

      <b-form inline>
        <b-form-select
          id="inline-form"
          v-model="condition.key"
          :options="conditions"
        ></b-form-select>

        <b-form-input
          id="inline-form-input-condition"
          v-model="condition.value"
          class="mb-2 mr-sm-2 mb-sm-0"
        ></b-form-input>

        <b-button @click="searchList" variant="primary">검색</b-button>
      </b-form>
    </div>
  </div>
</template>

<script>
export default {
  data(){
    return {
        fields:[
          {key : 'index', label : '순번'},
          {key : 'name', label : '이름'},
          {key : 'id', label : 'ID'},
          {key : 'cdate', label : '생성일'},
          {key : 'cuser', label : '생성자'},
        ],
        items: [],
        conditions:[
          {text: '이름', value: 'name'}
        ],
        condition:{key : '', value : ''}
    };
  },
  mounted(){
    this.$nextTick(function(){ // 모든 화면이 렌더링된 후에 실행
      this.$axios.get('http://localhost:8080/member/selectList')
        .then(response =>{
          this.items = response.data
          console.log("items :: " + this.items);
        })
        .catch(err => {
          console.log(err);
        });
    }) 
  }
  ,
  computed:{
    hasResult:function(){
      return this.items.length > 0
    }
  },
  methods:{
    searchList(){
        this.$axios.post('http://localhost:8080/member/searchList'
        ,this.condition.key == 'name' ? {name : this.condition.value} : {})
        .then(response =>{
          window.alert("검색하였습니다.");
          this.items = response.data
          console.log(this.items);
        })
        .catch(err => {
          window.alert("오류생김 ㅅㄱ.");
          console.log(err);
        });   
      },
      boardDetail(item){
        this.$router.push({name: 'Member',params:{userid:item.userid}});
      }
    }
  }
</script>