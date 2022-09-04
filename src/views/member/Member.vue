<template>
  <div class="member">
    <h2>회원정보</h2>
    <b-form>  
      <b-input-group prepend="이름" class="mb-2 w-25">
        <b-form-input 
          id="member-name"
          class="mb-2 mr-sm-2 mb-sm-0" 
          v-model="items.name"
        ></b-form-input>
      </b-input-group>

      <b-input-group prepend="ID" class="mb-2 w-25">
        <b-form-input 
          id="member-ID" 
          class="mb-2 mr-sm-2 mb-sm-0"
          v-model="items.id"
          readonly      
        ></b-form-input>
      </b-input-group>    

      <b-input-group prepend="비밀번호" class="mb-2 w-25">
        <b-form-input 
          id="member-PW" 
          class="mb-2 mr-sm-2 mb-sm-0"
          v-model="items.pw"
          type="password"
        ></b-form-input>
      </b-input-group>

      <b-input-group prepend="비밀번호 확인" class="mb-2 w-25">
        <b-form-input 
          id="member-PwChk" 
          class="mb-2 mr-sm-2 mb-sm-0"
          v-model="items.pwChk"
          type="password"
        ></b-form-input>
      </b-input-group>               

      <b-input-group prepend="핸드폰 번호" class="mb-2 w-50">
        <b-form-input 
          id="member-hp1" 
          class=""
          v-model="items.hp1"
        ></b-form-input>
        <b-form-input 
          id="member-hp2" 
          class=""
          v-model="items.hp2"
        ></b-form-input>
        <b-form-input 
          id="member-hp" 
          class=""
          v-model="items.hp3"
        ></b-form-input>
      </b-input-group>

      <b-input-group prepend="주소" class="mb-2">
        <b-form-textarea 
          id="member-address" 
          class="mb-2 mr-sm-2 mb-sm-0"
          v-model="items.address"
        ></b-form-textarea>
      </b-input-group>      
    </b-form>
    <b-button type="submit" @click="update">수정</b-button>
    <b-button type="submit" @click="del">삭제</b-button>
  </div>
</template>

<script>
export default {
  data(){
    return {
        items:{  
                 userid    : ''
               , name      : ''
               , nickname  : ''
               , id        : ''
               , pw        : ''
               , pwChk     : ''
               , hp1       : ''
               , hp2       : ''
               , hp3       : ''
               , address   : ''
               , cuser     : ''
               , cdate     : ''
               , uuser     : ''
               , udate     : ''
            }
    };
  },
  mounted(){
    this.$nextTick(function(){ // 모든 화면이 렌더링된 후에 실행
      //let userid = this.$route.params.userid;
      let userid = this.$route.params.gubun == 'ADMIN' ? this.$route.params.userid : this.$store.getters.getUserID;
      if(userid != ''){
        this.$axios.get('http://localhost:8080/member/select/' + userid)
          .then(response =>{
            //this.items = response.data
            this.items.userid = response.data.userid;
            this.items.name = response.data.name;
            this.items.id = response.data.id;
            this.items.pw = response.data.pw;
            this.items.hp1 = response.data.hp1;
            this.items.hp2 = response.data.hp2;
            this.items.hp3 = response.data.hp3;
            this.items.address = response.data.address;
            this.items.cuser = response.data.cuser;
            this.items.cdate = response.data.cdate;
            this.items.uuser = response.data.uuser;
            this.items.udate = response.data.udate;
          })
          .catch(err => {
            console.log(err);
          });
        }  
    }) 
  }
  ,
  computed:{
    pwValidChk:function(){
      if(this.items.pwChk == "")
        return;
      if(this.items.pw != this.items.pwChk)
      {
        window.alert("비밀번호가 일치하지 않습니다.");
        return false;
      }
      else 
        return true;
    }
  },
  methods:{
    update(){
        if(!window.confirm("수정하시겠습니까??"))
          return;
        if(!this.pwValidChk)
          return;
        this.$axios.put('/member/update'
                      ,{pw : this.items.pw, address : this.items.address, hp1 : this.items.hp1, hp2 : this.items.hp2
                      , hp3 : this.items.hp3,name : this.items.name, userid : this.items.userid, nickname : this.items.nickname})
        .then(response =>{
          window.alert("수정하였습니다.");
          console.log(response.data);
          if(this.$store.getters.getUserID == this.items.userid)
            this.$store.commit('loginInfo',this.items);
          this.$router.push({name: 'Home'}); // name 기준은 router에서 설정한 name이다.
        })
        .catch(err => {
          console.log(err);
        });      
      },
     del(){
        if(!window.confirm("탙퇴하시겠습니까?"))
          return;
        if(this.$store.getters.getAuthority == 'ADMIN')
        {
          window.alert("관리자 아이디는 삭제할수없습니다. 당연한거 아니냐?");
          return;
        }

        this.$axios.delete('http://localhost:8080/member/delete/' + this.items.userid)
        .then(response =>{
          window.alert("탈퇴하였습니다.");
          console.log(response.data);
          this.$router.push({name: 'Home'}); // name 기준은 router에서 설정한 name이다.
        })
        .catch(err => {
          console.log(err);
        });      
      }
    }
  }
</script>