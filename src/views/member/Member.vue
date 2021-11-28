<template>
  <div class="member">
    <h2>회원정보</h2>
    <b-form>  
      <b-input-group prepend="이름" class="mb-2 w-25">
        <b-form-input 
          id="member-name"
          class="mb-2 mr-sm-2 mb-sm-0" 
          v-model="items.name"
          :disabled="items.readonly"
        ></b-form-input>
      </b-input-group>

      <b-input-group prepend="ID" class="mb-2 w-25">
        <b-form-input 
          id="member-ID" 
          class="mb-2 mr-sm-2 mb-sm-0"
          v-model="items.id"
          :disabled="items.readonly"
        ></b-form-input>
      </b-input-group>

      <b-input-group prepend="PW" class="mb-2 w-25">
        <b-form-input 
          id="member-PW" 
          class="mb-2 mr-sm-2 mb-sm-0"
          v-model="items.pw"
          :disabled="items.readonly"
          type="password"
        ></b-form-input>
      </b-input-group>      

      <b-input-group prepend="핸드폰 번호" class="mb-2 w-50">
        <b-form-input 
          id="member-handphone" 
          class="mb-2 mr-sm-2 mb-sm-0"
          v-model="items.handphone"
          :disabled="items.readonly"
        ></b-form-input>
      </b-input-group>

      <b-input-group prepend="주소" class="mb-2">
        <b-form-textarea 
          id="member-address" 
          class="mb-2 mr-sm-2 mb-sm-0"
          v-model="items.address"
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
                 userid    : ''
               , name      : ''
               , id        : ''
               , pw        : ''
               , handphone : ''
               , address   : ''
               , cuser     : ''
               , cdate     : ''
               , uuser     : ''
               , udate     : ''
               , readonly  : true
               , canUpdate : false
            }
    };
  },
  mounted(){
    this.$nextTick(function(){ // 모든 화면이 렌더링된 후에 실행
      let userid = this.$route.params.userid;
      this.$axios.get('http://localhost:8080/member/select/' + userid)
        .then(response =>{
          //this.items = response.data
          this.items.userid = response.data.userid;
          this.items.name = response.data.name;
          this.items.id = response.data.id;
          this.items.pw = response.data.pw;
          this.items.handphone = response.data.handphone;
          this.items.address = response.data.address;
          this.items.cuser = response.data.cuser;
          this.items.cdate = response.data.cdate;
          this.items.uuser = response.data.uuser;
          this.items.udate = response.data.udate;
        })
        .catch(err => {
          console.log(err);
        });
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
        this.$axios.put('http://localhost:8080/member/update')
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

        this.$axios.delete('http://localhost:8080/member/delete/' + this.items.userid)
        .then(response =>{
          window.alert("삭제하였습니다.");
          console.log(response.data);
          this.$router.push({name: 'MemberList'}); // name 기준은 router에서 설정한 name이다.
        })
        .catch(err => {
          console.log(err);
        });      
      } 
    }
  }
</script>