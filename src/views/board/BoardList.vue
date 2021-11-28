<template>
  <div class="boardList">
    <h2>자유게시판</h2>
    <div>
      <b-table striped hover :fields="fields" :items="items" @row-clicked="boardDetail">
        <template #cell(index)="data">
          {{data.index + 1}}
        </template>
        
        <template #cell(title)="data">
          {{data.item.title}}
          <router-link :to="{name: 'Board', params:{bno: data.item.bno}}"></router-link>
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
        ></b-form-input>
      </b-form>

      <b-button @click="searchList" variant="primary">검색</b-button>
      <b-button @click="insertBoard" >글 작성</b-button>

    </div>
  </div>
</template>

<script>
export default {
  data(){
    return {
        fields:[
          {key : 'index', label : '순번'},
          {key : 'title', label : '제목'},
          {key : 'cuser', label : '작성자'},
          {key : 'cdate', label : '작성일'},
          {key : 'cuser', label : '수정일'},
        ],
        items: [],
        conditions:[
          {text: '전체', value: 'null'},
          {text: '작성자', value: 'cuser'},
          {text: '제목', value: 'title'}
        ],
        condition:{key : '', value : ''}
    };
  },
  mounted(){
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
  }
  ,
  computed:{
    hasResult:function(){
      return this.items.length > 0
    }
  },
  methods:{
    searchList(){
        this.$axios.post('/board/searchList',
          this.condition.key == 'cuser' ? {cuser : this.condition.value} : {title : this.condition.value}
        )
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
        this.$router.push({name: 'Board',params:{bno:item.bno}});
      },
    insertBoard(item){
        this.$router.push({name: 'Board',params:{bno:'',cuser:item.cuser}});
      }
    }
  }
</script>