<template>
  <div class="board">
    <h2>게시글</h2>
    <b-form>  
      <b-input-group>
        <template #prepend>
          <b-input-group-text class="">제목</b-input-group-text>
        </template>

        <b-form-input 
          id="board-title"
          class="mb-2 mr-sm-2 mb-sm-0" 
          v-model="items.title"
          :disabled="!canWrite"
        ></b-form-input>
      </b-input-group>

      <b-input-group prepend="작성자">
        <b-form-input 
          id="board-cuser" 
          class="mb-2 mr-sm-2 mb-sm-0"
          v-model="items.usernm"
          readonly
        ></b-form-input>
      </b-input-group>

      <b-input-group prepend="작성일">
        <b-form-input 
          id="board-cdate" 
          class="mb-2 mr-sm-2 mb-sm-0"
          v-model="items.cdate"
          readonly
        ></b-form-input>
      </b-input-group>

      <b-input-group prepend="내용">
        <b-form-textarea 
          id="board-content" 
          class="mb-2 mr-sm-2 mb-sm-0"
          v-model="items.content"
          :disabled="!canWrite"
        ></b-form-textarea>
      </b-input-group> 

      <b-input-group v-show="!canInsert" prepend="조회수">
        <b-form-input 
          id="board-views" 
          class="mb-2 mr-sm-2 mb-sm-0"
          v-model="items.views"
          :disabled="!canWrite"
        ></b-form-input>
      </b-input-group>      

    </b-form>
    <b-button type="submit" @click="update" v-show="canUpdate">수정</b-button>
    <b-button type="submit" @click="insert" v-show="canInsert">작성</b-button>
    <b-button type="submit" @click="del" v-show="canUpdate">삭제</b-button>
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
               , usernm  : ''
               , cuser   : ''
               , cdate   : ''
               , udate   : ''
               , views   : ''
            }
    };
  },
  mounted(){
    this.$nextTick(function(){ // 모든 화면이 렌더링된 후에 실행
      let bno = this.$route.params.bno;
      console.log("bno :: " + bno);

      if(bno != ''){
        this.$axios.get('http://localhost:8080/board/select/' + bno)
        .then(response =>{
          //this.items = response.data
          this.items.bno = response.data.bno;
          this.items.title = response.data.title;
          this.items.content = response.data.content;
          this.items.cuser = response.data.cuser;
          this.items.usernm = response.data.usernm;
          this.items.cdate = response.data.cdate;
          this.items.udate = response.data.udate;
          this.items.views = response.data.views;
        })
        .catch(err => {
          console.log(err);
        });
      }
      else{
        let today = new Date();

        this.items.usernm = this.$store.getters.getUserNm;
        this.items.cuser = this.$store.getters.getID;
        this.items.cdate = today.getFullYear() + "-" + (today.getMonth() + 1) + "-" + today.getDate();
      }
    }) 
  }
  ,
  computed:{
    canInsert:function(){
      return (this.items.bno == '' ? true : false);
    },
    canWrite:function(){
      return (this.items.cuser == this.$store.getters.getID ? true : false);
    },
    canUpdate:function(){
      return (this.items.bno != '' && this.items.cuser == this.$store.getters.getID ? true : false);
    }
    
  },
  methods:{
    update(){
        if(!window.confirm("수정하시겠습니까??"))
          return;
        this.$axios.put('/board/update/' + this.$route.params.bno,
          {bno : this.items.bno, cuser : this.items.cuser, title : this.items.title, content : this.items.content})
        .then(response =>{
          window.alert("수정하였습니다.");
          console.log(response.data);
          this.$router.push({name: 'BoardList'}); // name 기준은 router에서 설정한 name이다.
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
      },
      insert(){
        if(!window.confirm("작성하시겠습니까?"))
          return;

        this.$axios.post('/board/insert'
                        ,{title : this.items.title, content : this.items.content, cuser : this.items.cuser}
        ).then(response =>{
          //window.alert("작성하였습니다.");
          console.log(response.data);
          this.$router.push({name: 'BoardList'});
        })
        .catch(err => {
          window.alert("오류생김 ㅅㄱ.");
          console.log(err);
        });  
      } 
    }
  }
</script>