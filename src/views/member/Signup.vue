<template>
  <div class="signup">
    <h2>회원정보</h2>
    <b-form>  
      <b-input-group prepend="닉네임" class="mb-2 w-50">
        <b-form-input 
          id="member-name"
          class="mb-2 mr-sm-2 mb-sm-0" 
          v-model="items.name"
        ></b-form-input>
      </b-input-group>

      <b-input-group prepend="ID" class="mb-2 w-50">
        <b-form-input 
          id="member-ID" 
          class="mb-2 mr-sm-2 mb-sm-0"
          v-model="items.id"
          :state = "idState"
          aria-describedby="input-id-feedback"
          placeholder="Enter your ID"          
        ></b-form-input>

      <b-form-invalid-feedback id="input-id-feedback">
          8자리 이상으로 입력해주세요.
      </b-form-invalid-feedback>   

      </b-input-group>   

      <b-input-group prepend="비밀번호" class="mb-2 w-50">
        <b-form-input 
          id="member-PW" 
          class="mb-2 mr-sm-2 mb-sm-0"
          v-model="items.pw"
          type="password"
          :state = "pwState"
          aria-describedby="input-pw-feedback"
          placeholder="Enter your PW"
        ></b-form-input>

        <b-form-invalid-feedback id="input-pw-feedback">
            8자리 이상으로 입력해주세요.
        </b-form-invalid-feedback> 

      </b-input-group>

      <b-input-group prepend="비밀번호 확인" class="mb-2 w-50">
        <b-form-input 
          id="member-PW" 
          class="mb-2 mr-sm-2 mb-sm-0"
          v-model="items.pwChk"
          type="password"
          :state = "pwState"
          aria-describedby="input-pw-feedback"
          placeholder="Enter your PW"
        ></b-form-input>

        <b-form-invalid-feedback id="input-pw-feedback">
            8자리 이상으로 입력해주세요.
        </b-form-invalid-feedback> 

      </b-input-group>            

      <b-input-group prepend="핸드폰 번호" class="mb-2 w-50">
        <b-form-input 
          id="member-hp1" 
          class=""
          v-model="items.hp1"
          :state = "hp1State"
          aria-describedby="input-hp-feedback"
        ></b-form-input>
        <b-form-input 
          id="member-hp2" 
          class=""
          v-model="items.hp2"
          :state = "hp2State"
          aria-describedby="input-hp-feedback"
        ></b-form-input>
        <b-form-input 
          id="member-hp" 
          class=""
          v-model="items.hp3"
          :state = "hp3State"
          aria-describedby="input-hp-feedback"
        ></b-form-input>
        <b-form-invalid-feedback id="input-hp-feedback">
            핸드폰 번호는 필수입니다.
        </b-form-invalid-feedback>         
      </b-input-group>

      <b-input-group prepend="주소" class="mb-2">
        <b-form-textarea 
          id="member-address" 
          class="mb-2 mr-sm-2 mb-sm-0"
          v-model="items.address"
        ></b-form-textarea>
      </b-input-group>      
    </b-form>
    <b-button type="submit" @click="signup">회원가입</b-button>
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
        return null;
    }) 
  }
  ,
  computed:{
    pwValidChk:function(){
      if(this.items.pw != this.items.pwChk)
      {
        window.alert("비밀번호가 일치하지 않습니다.");
        return false;
      }
      else 
        return true;
    },
    idState(){
      return this.items.id.length > 7 ? true : false
    },
    pwState(){
      return this.items.pw.length > 7 ? true : false
    },
    hp1State(){
      return this.items.hp1.length > 0 && this.items.hp1.length < 5 ? true : false
    },
    hp2State(){
      return this.items.hp2.length > 0 && this.items.hp2.length < 5 ? true : false
    },
    hp3State(){
      return this.items.hp3.length > 0 && this.items.hp3.length < 5 ? true : false
    }
  },
  methods:{
     signup(){
       if(this.idState == true && this.pwState == true){
        if(!this.pwValidChk)
            return;
        if(!window.confirm("회원가입을 하시겠습니까?"))
            return;
        this.$axios.post('/member/signup' 
                    ,{pw : this.items.pw, address : this.items.address, hp1 : this.items.hp1, hp2 : this.items.hp2
                    , hp3 : this.items.hp3, name : this.items.name, id : this.items.id})
        .then(response =>{
        window.alert("회원가입되었습니다. 이제 로그인이 가능합니다.");
        console.log(response.data);
        this.$router.push({name: 'Home'}); // name 기준은 router에서 설정한 name이다.
        })
        .catch(err => {
        console.log(err);
        });   
       } 
     } 
    }
  }
</script>