<template>
    <div>
      <button @click="likePost">⭐</button>
    </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      likeCount: 0,
      postId: null,
      memberId: null
    };
  },

  methods: {
    async fetchLikeCount() {
      try {
        const response = await axios.get(`http://localhost:8081/like/Info/${this.infoId}/${this.memberId}`);
      } catch (error) {
        console.error('좋아요 수를 불러오는 중 에러 발생:', error);
      }
    },
    async likePost() {
      try {
        await axios.get(`http://localhost:8081/like/Info/${this.infoId}/${this.memberId}`);
        this.fetchLikeCount(); 
      } catch (error) {
        console.error('좋아요를 클릭하는 중 에러 발생:', error);
      }
    }
  },

  mounted() {
    this.infoId = this.$route.params.infoId;
    
    const memberIdCookie = document.cookie.split('; ').find(cookie => cookie.startsWith('memberId='));
    if (memberIdCookie) {
      this.memberId = memberIdCookie.split('=')[1];
    }

  }
};
</script>

<style scoped>
  @import url('@/assets/css/PostAndReply/Like.css');
</style>