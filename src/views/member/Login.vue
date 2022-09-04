<template>
  <div class="login">
    <h2>로그인</h2>
    <b-form>
      <b-form-group
        id="input-group-ID"
        label="아이디"
      >
        <b-form-input
          id="input-id"
          class="w-50"
          v-model="items.id"
          :state = "idState"
          aria-describedby="input-id-feedback"
          placeholder="Enter your ID"
          type="text"
        ></b-form-input>

        <b-form-invalid-feedback id="input-id-feedback">
          Enter at least 8 letters
        </b-form-invalid-feedback>

      </b-form-group>

      <b-form-group 
        id="input-group-PW" 
        label="비밀번호" 
      >
        <b-form-input
          id="input-PW"
          class="w-50"
          v-model="items.pw"
          :state = "pwState"
          aria-describedby="input-pw-feedback"
          placeholder="Enter your PW"
          type="password"
        ></b-form-input>

        <b-form-invalid-feedback id="input-pw-feedback">
          Enter at least 8 letters
        </b-form-invalid-feedback>

      </b-form-group>

      <b-button type="button" @click="login" variant="primary">로그인</b-button>
      <b-button type="button" @click="signUp" variant="outline-primary">회원가입</b-button>
    </b-form>

  </div>
</template>

<script>
export default {
  data(){
    return {
        items:{  
                 id : ''
               , pw : ''
            }
    };
  },
  mounted(){
    if(this.$store.getters.getID != '')
    {
      window.alert("이미 로그인하셨습니다. 홈 화면으로 이동합니다.");
      this.$router.push({name: 'Home'});
    }
  },
  computed:{
    idState(){
      return this.items.id.length > 7 ? true : false
    },
    pwState(){
      return this.items.pw.length > 7 ? true : false
    },
    validChk(){
      if(this.items.id.length == 0){
        window.alert("아이디를 입력해주세요.");
        return false;
      }
      else if(this.items.pw.length == 0){
        window.alert("비밀번호를 입력해주세요.");
        return false;
      } else
        return true;
    }
  },
  methods:{
    login(){
      if(!this.validChk){
        return false;
      }
      if(this.idState == true && this.pwState == true){
        this.$axios.post('/member/loginInfo',
          {id : this.items.id, pw : this.items.pw}
          )
          .then(response =>{
            window.alert(response.data.id + "님 환영합니다.");
            //console.log(this.$store.getID);
            this.$store.commit('loginInfo',response.data);
            //console.log(response.data);
            this.$router.push({name: 'BoardList'}); // name 기준은 router에서 설정한 name이다.
          })
          .catch(err => {
            window.alert("아이디 또는 비밀번호가 일치하지않습니다.");
            console.log(err);
          }); 
      }
    },
    signUp(){
      this.$router.push({name: 'Signup'});
    } 
  }
}
</script>