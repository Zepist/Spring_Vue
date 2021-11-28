<template>
  <div id="app">
 <!--   <div id="nav">
      <router-link to="/">Home</router-link> |
      <router-link to="/board">Board</router-link>
      <router-link to="/member">Member</router-link>
    </div> -->
    <Header />
    <div id="content" class="content">
        <router-view/>
    </div>
  </div>
</template>

<script>
import Header from './components/layout/Header.vue'

export default {
  name : 'App',
  components: {
    Header,
  }
}
</script>

<style>

</style>
