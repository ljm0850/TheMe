<template>
<div class="card">
  <div class="card-body">
    <img src="@/assets/image/recommandTheme.png" alt="" style="max-width:170px" @click="clickTheme()">
    <!-- <h5 class="card-title">Card title</h5>
    <h6 class="card-subtitle mb-2 text-muted">Card subtitle</h6>
    <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
    <a href="#" class="card-link">Card link</a>
    <a href="#" class="card-link">Another link</a> -->
  </div>
</div>
</template>
  
<script lang="ts">
import { useRouter } from 'vue-router'
// import { useStore } from "vuex";
export default {
  components: {
  },
setup() {
  const router = useRouter();
  const clickTheme = () => {
    router.push({ name: "PublicTheme", params: { themeName:"ThemeTextCard에서 바꾸자" } })
  }
  return {clickTheme}

  }
}
</script>
  
<style lang="scss`">
  
  
  </style>