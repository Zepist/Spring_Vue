<template>
  <div class="common">
    <h2 style="text-align : center;">공통코드 관리</h2>

    <div class="commonGroupCode pb-5">
      <h5>공통 그룹코드</h5>
      <div>
        <b-table 
          sticky-header 
          striped hover 
          :fields="group_fields" 
          :items="group_items" 
          @row-clicked="codeDetail"
        >
          <template #cell(index)="data">
            {{data.index + 1}}
          </template>
          <template #cell(USE_YN)="data">
            <b-select v-model="data.value" :options="useYn_conditions"></b-select>
          </template>
        </b-table>

        <b-form inline>
          <b-form-select
            id="inline-form"
            v-model="group_condition.key"
            :options="group_conditions"
          ></b-form-select>

          <b-form-input
            id="inline-form-input-condition"
            v-model="group_condition.value"
            class="mb-2 mr-sm-2 mb-sm-0"
          ></b-form-input>

          <b-button @click="searchList" variant="primary">검색</b-button>
        </b-form>
      </div>
    </div>

    <div class="commonCode py-5">
      <h5>공통코드</h5>
      <div>
        <b-table striped hover :fields="code_fields" :items="code_items">
          <template #cell(index)="data">
            {{data.index + 1}}
          </template>
        </b-table>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data(){
    return {
        group_fields:[
          {key : 'index', label : '순번'},
          {key : 'GROUP_CODE', label : '그룹코드'},
          {key : 'GROUP_NAME', label : '그룹명'},
          {key : 'REMARK'    , label : '비고'},
          {key : 'ATTR01'    , label : '속성1'},
          {key : 'ATTR02'    , label : '속성2'},
          {key : 'ATTR03'    , label : '속성3'},
          {key : 'ATTR04'    , label : '속성4'},
          {key : 'ATTR05'    , label : '속성5'},
          {key : 'USE_YN'    , label : '사용여부'},
          {key : 'CUSER_NM'  , label : '생성자'},
          {key : 'CDATE'     , label : '생성일'},
          {key : 'UUSER_NM'  , label : '수정자'},
          {key : 'UDATE'     , label : '수정일'}
        ],
        group_items: [],
        group_conditions:[
          {text: '그룹코드', value: 'code'},
          {text: '그룹명', value: 'name'}
        ],
        group_condition:{key : '', value : ''},
        code_fields:[
          {key : 'index', label : '순번'},
          {key : 'CODE', label : '공통코드'},
          {key : 'CODENAME', label : '코드명'},
          {key : 'REMARK'    , label : '비고'},
          {key : 'ATTR01'    , label : '속성1'},
          {key : 'ATTR02'    , label : '속성2'},
          {key : 'ATTR03'    , label : '속성3'},
          {key : 'ATTR04'    , label : '속성4'},
          {key : 'ATTR05'    , label : '속성5'},
          {key : 'USE_YN'    , label : '사용여부'},
          {key : 'CUSER_NM'  , label : '생성자'},
          {key : 'CDATE'     , label : '생성일'},
          {key : 'UUSER_NM'  , label : '수정자'},
          {key : 'UDATE'     , label : '수정일'}
        ],
        code_items: [],
        useYn_conditions:[
          {text : '사용'  , value : 'Y'},
          {text : '미사용', value : 'N'}
        ]
    };
  },
  mounted(){
    this.$nextTick(function(){ // 모든 화면이 렌더링된 후에 실행
      this.$axios.post('commonCode/selectGroupCodeList'
                       ,{mapParam : this.group_items})
        .then(response =>{
          this.group_items = response.data
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
        this.$axios.post('commonCode/selectGroupCodeList'
        ,this.group_condition.key == 'code' ? {GROUP_CODE : this.group_condition.value} : {GROUP_NAME : this.group_condition.value})
        .then(response =>{
          window.alert("검색하였습니다.");
          this.group_items = response.data
        })
        .catch(err => {
          window.alert("오류생김 ㅅㄱ.");
          console.log(err);
        });   
      },
    codeDetail(item){
        this.$axios.post('commonCode/selectCodeList'
        ,{GROUP_CODE : item.GROUP_CODE})
        .then(response =>{
          this.code_items = response.data
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

</style>