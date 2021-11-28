<template>
  <div class="board">
    <h2>게시글</h2>
    <b-form>  
      <b-input-group prepend="제목">
        <b-form-input 
          id="board-title"
          class="mb-2 mr-sm-2 mb-sm-0" 
          v-model="items.title"
          :disabled="items.readonly"
        ></b-form-input>
      </b-input-group>

      <b-input-group prepend="작성자">
        <b-form-input 
          id="board-cuser" 
          class="mb-2 mr-sm-2 mb-sm-0"
          v-model="items.cuser"
          :disabled="items.readonly"
        ></b-form-input>
      </b-input-group>

      <b-input-group prepend="작성일">
        <b-form-input 
          id="board-cdate" 
          class="mb-2 mr-sm-2 mb-sm-0"
          v-model="items.cdate"
          :disabled="items.readonly"
        ></b-form-input>
      </b-input-group>

      <b-input-group prepend="내용">
        <b-form-textarea 
          id="board-content" 
          class="mb-2 mr-sm-2 mb-sm-0"
          v-model="items.content"
          :disabled="items.readonly"
        ></b-form-textarea>
      </b-input-group>      
    </b-form>
    <b-button type="submit" @click="update" v-show="items.canUpdate">수정</b-button>
    <b-button type="submit" @click="del" v-show="items.canUpdate">삭제</b-button>
  </div>
</template>

<script>
export default {
  data(){
    return {
        items:{  
                 bno     : ''
               , title   : ''
               , content : ''
               , cuser   : ''
               , cdate   : ''
               , udate   : ''
               , readonly : true
               , canUpdate : false
            }
    };
  },
  mounted(){
    this.$nextTick(function(){ // 모든 화면이 렌더링된 후에 실행
      let bno = this.$route.params.bno;

      if(bno != ''){
        this.$axios.get('http://localhost:8080/board/select/' + bno)
        .then(response =>{
          //this.items = response.data
          this.items.bno = response.data.bno;
          this.items.title = response.data.title;
          this.items.content = response.data.content;
          this.items.cuser = response.data.cuser;
          this.items.cdate = response.data.cdate;
          this.items.udate = response.data.udate;
        })
        .catch(err => {
          console.log(err);
        });
      }
      else{
        let today = new Date();

        this.items.cuser = this.$route.params.cuser;
        this.items.cdate = today.getFullYear() + "-" + (today.getMonth() + 1) + "-" + today.getDate();
      }
    }) 
  }
  ,
  computed:{
    hasResult:function(){
      return null;
    }
  },
  methods:{
    update(){
        if(!window.confirm("수정하시겠습니까??"))
          return;
        this.$axios.put('http://localhost:8080/board/update')
        .then(response =>{
          window.alert("수정하였습니다.");
          console.log(response.data);
        })
        .catch(err => {
          console.log(err);
        });      
      },
     del(){
        if(!window.confirm("삭제하시겠습니까?"))
          return;

        this.$axios.delete('http://localhost:8080/board/delete/' + this.items.bno)
        .then(response =>{
          window.alert("삭제하였습니다.");
          console.log(response.data);
          this.$router.push({name: 'BoardList'}); // name 기준은 router에서 설정한 name이다.
        })
        .catch(err => {
          console.log(err);
        });      
      } 
    }
  }
</script>